package com.hybris.showcase.cecs.ymarketingintegration.interaction;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.sap.core.bol.backend.jco.BackendBusinessObjectBaseJCo;
import de.hybris.platform.sap.core.jco.connection.JCoConnection;
import de.hybris.platform.sap.core.jco.exceptions.BackendException;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.servicelayer.user.UserService;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.sap.wec.adtreco.bo.ADTUserIdProvider;


public class InteractionManager extends BackendBusinessObjectBaseJCo
{
	private final static Logger LOG = Logger.getLogger(InteractionManager.class.getName());
	private static String JCO_STATELESS = "JCoStateless";

	private UserService userService;
	private ADTUserIdProvider adtUserIdProvider;
	private TimeService timeService;

	protected HMCConfigurationReader configuration;

	public void postInteraction(final Interaction interaction)
	{
		final JCoConnection jCoConnection = getJcoConnection();

		try
		{
			final String functionName = "CUAN_CE_INTERACTIONS_POST_FLAT";
			final JCoFunction function = jCoConnection.getFunction(functionName);
			final JCoTable interactions = function.getTableParameterList().getTable("IT_INTERACTIONS");
			interactions.appendRow();

			if (StringUtils.isBlank(interaction.getIdOrigin()))
			{
				final String idOrigin = configuration.getIdOrigin();
				interactions.setValue("ID_ORIGIN", idOrigin);

				final UserModel customer = userService.getUserForUID(interaction.getUid());
				final String id = adtUserIdProvider.getADTUserId(customer);
				interactions.setValue("ID", id);
			}
			else
			{
				interactions.setValue("ID_ORIGIN", interaction.getIdOrigin());
				interactions.setValue("ID", interaction.getUid());
			}

			SimpleDateFormat timeStampFormat = new SimpleDateFormat("YYYYMMddhhmmss");

			interactions.setValue("COMM_MEDIUM", interaction.getCommMedium());//"WEB" - CommunicationMedium
			interactions.setValue("IA_TYPE", interaction.getIAType());//"WEBSITE_VISIT" - InteractionType
			interactions.setValue("TIMESTAMP", timeStampFormat.format(timeService.getCurrentTime()));
			if (StringUtils.isNotBlank(interaction.getInitiativeId()))
			{
				interactions.setValue("INITIATIVE_ID", interaction.getInitiativeId());
			}
			// This field contains the title of the respective content, for example, email subject or
			// the social media post (abbreviated). If the content title is empty,
			// the field is filled with the content data (see field CONTENT_DATA).
			interactions.setValue("CONTENT_TITLE", interaction.getContentTitle());
			interactions.setValue("CONTENT_DATA", interaction.getContentData());

			// This field contains the navigation URL of the source object, for example, the URL of a Twitter post.
			interactions.setValue("SOURCE_DATA_URL", interaction.getSourceDataURL());

			// This field contains the object ID of the source object, for example,
			// the GUID of the SAP Cloud for Customer opportunity or the original post ID
			// of the respective social media network (such as TW or FB).
			interactions.setValue("SOURCE_OBJECT_ID", interaction.getSourceObjectId());

			// This field contains the sentiment or opinion of interaction contacts (positive or negative).
			// example 5
			interactions.setValue("VALUATION", interaction.getValuation());

			// Interaction interests of your contacts.
			interactions.setValue("INTEREST_ITEM", interaction.getItemOfInterest());

			jCoConnection.execute(function);

			final JCoTable failed_interactions = function.getTableParameterList().getTable("ET_FAILED_INTERACTIONS");
			final JCoTable messages = function.getTableParameterList().getTable("ET_MESSAGES");

			if (!messages.isEmpty())
			{
				logJCoMessages(messages, Level.INFO);
			}
			if (!failed_interactions.isEmpty())
			{
				logJCoMessages(failed_interactions, Level.ERROR);
			}
		}
		catch (final BackendException e)
		{
			LOG.error("", e);
		}

	}

	private JCoConnection getJcoConnection()
	{
		JCoConnection jCoConnection;
		if (configuration.getRfcDestinationId() == null)
		{
			jCoConnection = getDefaultJCoConnection();
		}
		else
		{
			jCoConnection = getJCoConnection(JCO_STATELESS, configuration.getRfcDestinationId());
		}

		try
		{
			if (jCoConnection.isBackendAvailable() == false)
			{
				LOG.error("RFC - " + configuration.getRfcDestinationId() + " backend is not available");
			}
		}
		catch (final BackendException e)
		{
			LOG.error("", e);
		}
		return jCoConnection;
	}

	public HMCConfigurationReader getConfiguration()
	{
		return configuration;
	}

	public void setConfiguration(final HMCConfigurationReader configuration)
	{
		this.configuration = configuration;
	}

	private void logJCoMessages(final JCoTable table, final Level level)
	{
		try
		{
			final int len = table.getNumRows();
			for (int i = 0; i < len; i++)
			{
				table.setRow(i);
				final String msgId = table.getString("ID");
				final String msg = table.getString("MESSAGE");
				if (level.equals(Level.ERROR))
				{
					LOG.error(msgId + " " + msg);
				}
				else if (level.equals(Level.INFO))
				{
					LOG.info(msgId + " " + msg);
				}
			}
		}
		catch (final Exception e)
		{
			LOG.error("Error while trying to log outcome.", e);
		}
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	public ADTUserIdProvider getAdtUserIdProvider()
	{
		return adtUserIdProvider;
	}

	@Required
	public void setAdtUserIdProvider(ADTUserIdProvider adtUserIdProvider)
	{
		this.adtUserIdProvider = adtUserIdProvider;
	}

	public TimeService getTimeService()
	{
		return timeService;
	}

	@Required
	public void setTimeService(TimeService timeService)
	{
		this.timeService = timeService;
	}
}
