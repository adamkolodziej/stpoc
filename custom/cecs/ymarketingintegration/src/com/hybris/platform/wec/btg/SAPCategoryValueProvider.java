package com.hybris.platform.wec.btg;

import de.hybris.platform.btg.condition.operand.types.StringSet;
import de.hybris.platform.btg.condition.operand.valueproviders.CollectionOperandValueProvider;
import de.hybris.platform.btg.enums.BTGConditionEvaluationScope;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.session.SessionService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.olingo.odata2.api.exception.ODataException;

import com.hybris.platform.cockpit.model.editor.impl.DefaultSAPCategoryUIEditor;
import com.hybris.showcase.cecs.ymarketingintegration.ServicesSAPInitiative;
import com.hybris.showcase.cecs.ymarketingintegration.btg.model.BTGSAPCategoryOperandModel;
import com.sap.wec.adtreco.bo.ADTUserIdProvider;
import com.sap.wec.adtreco.bo.impl.SAPInitiative;
import com.sap.wec.adtreco.bo.intf.SAPInitiativeReader;


/**
 *
 * @author marius.bogdan.ionescu@sap.com
 *
 * @see com.sap.wec.adtreco.btg.SAPInitiativeValueProvider
 */
public class SAPCategoryValueProvider implements CollectionOperandValueProvider<BTGSAPCategoryOperandModel>
{
	private static final Logger LOG = Logger.getLogger(DefaultSAPCategoryUIEditor.class); // NOPMD
	private static final String CATEGORIES_PREFIX = "Categories_";
	protected SAPInitiativeReader sapInitiativeReader;
	protected ADTUserIdProvider userIdProvider;
	protected SessionService sessionService;

	@Override
	public Object getValue(final BTGSAPCategoryOperandModel operand, final UserModel user, final BTGConditionEvaluationScope scope)
	{
		List<String> result = new ArrayList<String>();
		if (user != null)
		{
			final String userId = userIdProvider.getADTUserId(user);

			if (userId != null)
			{
				if (sessionService.getAttribute(CATEGORIES_PREFIX + userId) != null)
				{
					result = sessionService.getAttribute(CATEGORIES_PREFIX + userId);
				}
				else
				{
					List<SAPInitiative> initiativesForBP = new ArrayList<SAPInitiative>();
					try
					{
						initiativesForBP = sapInitiativeReader.searchInitiativesForBP(userId);
						if (initiativesForBP.size() > 0)
						{
							sessionService.setAttribute(CATEGORIES_PREFIX + userId, result);
							for (final SAPInitiative initiative : initiativesForBP)
							{
								result.add(((ServicesSAPInitiative) initiative).getCategoryId());
							}
						}
						else
						{
							//If the user doesn't belong to any campaigns, a "null" value is placed in cache
							//to avoid making the check against the backend again within the session
							sessionService.setAttribute(CATEGORIES_PREFIX + userId, new ArrayList<String>());
						}
					}
					catch (final URISyntaxException ex)
					{
						LOG.error("Connection to CEI system failed due to wrong URI syntax", ex);
					}
					catch (final ODataException ex)
					{
						LOG.error("HTTP Destination is not configured correctly", ex);
					}
					catch (final IOException ex)
					{
						LOG.error("Connection to backend system failed", ex);
					}
					catch (final RuntimeException ex)
					{
						LOG.error("Runtime Error in the backend", ex);
					}
				}
			}
		}
		return new StringSet(result);
	}

	@Override
	public Class getValueType(final BTGSAPCategoryOperandModel operand)
	{
		return SAPCategorySet.class;
	}

	@Override
	public Class getAtomicValueType(final BTGSAPCategoryOperandModel operand)
	{
		return String.class;
	}

	/**
	 * @return the userIdProvider
	 */
	public ADTUserIdProvider getUserIdProvider()
	{
		return userIdProvider;
	}

	/**
	 * @param userIdProvider
	 *           the userIdProvider to set
	 */
	public void setUserIdProvider(final ADTUserIdProvider userIdProvider)
	{
		this.userIdProvider = userIdProvider;
	}

	public SessionService getsessionService()
	{
		return sessionService;
	}

	public void setsessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the sapInitiativeReader
	 */
	public SAPInitiativeReader getSapInitiativeReader()
	{
		return sapInitiativeReader;
	}

	/**
	 * @param sapInitiativeReader
	 *           the sapInitiativeReader to set
	 */
	public void setSapInitiativeReader(final SAPInitiativeReader sapInitiativeReader)
	{
		this.sapInitiativeReader = sapInitiativeReader;
	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService
	 *           the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}


}
