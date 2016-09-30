/**
 *
 */
package com.hybris.showcase.servicescore.services.impl;

import com.sun.istack.internal.ByteArrayDataSource;
import de.hybris.platform.acceleratorservices.email.impl.DefaultEmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailAttachmentModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import javax.activation.DataSource;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author I316754
 *
 */
public class DefaultEmbededImgEmailService extends DefaultEmailService
{

	private static final Logger LOG = Logger.getLogger(DefaultEmailService.class);

	@Override
	public boolean send(final EmailMessageModel message)
	{
		if (message == null)
		{
			throw new IllegalArgumentException("message must not be null");
		}

		final boolean sendEnabled = getConfigurationService().getConfiguration().getBoolean(EMAILSERVICE_SEND_ENABLED_CONFIG_KEY,
				true);
		if (sendEnabled)
		{
			try
			{
				final HtmlEmail email = getPerConfiguredEmail();
				email.setCharset("UTF-8");

				final List<EmailAddressModel> toAddresses = message.getToAddresses();
				if (CollectionUtils.isNotEmpty(toAddresses))
				{
					email.setTo(getAddresses(toAddresses));
				}
				else
				{
					throw new IllegalArgumentException("message has no To addresses");
				}

				final List<EmailAddressModel> ccAddresses = message.getCcAddresses();
				if (ccAddresses != null && !ccAddresses.isEmpty())
				{
					email.setCc(getAddresses(ccAddresses));
				}

				final List<EmailAddressModel> bccAddresses = message.getBccAddresses();
				if (bccAddresses != null && !bccAddresses.isEmpty())
				{
					email.setBcc(getAddresses(bccAddresses));
				}

				final EmailAddressModel fromAddress = message.getFromAddress();
				email.setFrom(fromAddress.getEmailAddress(), nullifyEmpty(fromAddress.getDisplayName()));

				// Add the reply to if specified
				final String replyToAddress = message.getReplyToAddress();
				if (replyToAddress != null && !replyToAddress.isEmpty())
				{
					email.setReplyTo(Collections.singletonList(createInternetAddress(replyToAddress, null)));
				}

				email.setSubject(message.getSubject());

				if( getConfigurationService().getConfiguration().getBoolean("email.attach.images", false)) {
					addAttachments(message, email);
				} else {
					email.setHtmlMsg(getBody(message));

					// To support plain text parts use email.setTextMsg()
					final List<EmailAttachmentModel> attachments = message.getAttachments();
					if (attachments != null && !attachments.isEmpty())
					{
						for (final EmailAttachmentModel attachment : attachments)
						{
							try
							{
							final DataSource dataSource = new ByteArrayDataSource(getMediaService().getDataFromMedia(attachment),
										attachment.getMime());
								email.attach(dataSource, attachment.getRealFileName(), attachment.getAltText());
							}
							catch (final EmailException ex)
							{
								LOG.error("Failed to load attachment data into data source [" + attachment + "]", ex);
								return false;
							}
						}
					}
				}

				// Important to log all emails sent out
				LOG.info("Sending Email [" + message.getPk() + "] To [" + convertToStrings(toAddresses) + "] From ["
						+ fromAddress.getEmailAddress() + "] Subject [" + email.getSubject() + "]");

				// Send the email and capture the message ID
				final String messageID = email.send();

				message.setSent(true);
				message.setSentMessageID(messageID);
				message.setSentDate(new Date());
				getModelService().save(message);

				return true;
			}
			catch (final EmailException e)
			{
				LOG.warn("Could not send e-mail pk [" + message.getPk() + "] subject [" + message.getSubject() + "] cause: "
						+ e.getMessage());
				if (LOG.isDebugEnabled())
				{
					LOG.debug(e.getMessage(), e);
				}
			}
		}
		else
		{
			LOG.warn("Could not send e-mail pk [" + message.getPk() + "] subject [" + message.getSubject() + "]");
			LOG.info("Email sending has been disabled. Check the config property 'emailservice.send.enabled'");
			return true;
		}

		return false;
	}

	protected void addAttachments(EmailMessageModel message, HtmlEmail email) throws EmailException {
		final List<EmailAttachmentModel> attachments = new ArrayList<EmailAttachmentModel>();

		final Set<String> imgFileFullUrls = new HashSet<String>();

		String content = getBody(message);

		final Pattern pattern = Pattern.compile("<img src=\"(.*?)\"");

		final Matcher m = pattern.matcher(content);
		while (m.find())
        {
            final String url = m.group(1);
            imgFileFullUrls.add(url);
        }

		int generatedFileNameCnt = 0;

		for (final String filePath : imgFileFullUrls)
        {
            final File f = new File(filePath);
            final String cid;

            if( f.getName().contains("?") ) {
				// Media URL
                cid = message.getProcess().getPk() + "_" + (++generatedFileNameCnt);
            } else {
				// static image URL
                cid = message.getProcess().getPk() + "_" + f.getName();
            }

			content = StringUtils.replace(content, filePath, "cid:" + cid);

            String mime = null;

			try {
				final URL imgUrl = new URL(filePath);

				final HttpURLConnection urlConnection = (HttpURLConnection) imgUrl.openConnection();
				mime = urlConnection.getHeaderField("Content-Type");

				try (final InputStream inputStream = urlConnection.getInputStream()) {
					final DataInputStream dataStream = new DataInputStream(inputStream);
					final EmailAttachmentModel attachmentModel = createEmailAttachment(dataStream, cid, mime);

					final DataSource dataSource = new ByteArrayDataSource(getMediaService().getDataFromMedia(attachmentModel), mime);

					attachments.add(attachmentModel);

					email.embed(dataSource, cid, cid);
				}
			}
            catch (final IOException e)
            {
                LOG.error("No such file: " + filePath + " " + e.getMessage(), e);
            }
        }

		email.setHtmlMsg(content);

		message.setAttachments(attachments);
	}

}
