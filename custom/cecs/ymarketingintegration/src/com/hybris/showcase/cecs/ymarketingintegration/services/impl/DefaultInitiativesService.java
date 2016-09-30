/**
 *
 */
package com.hybris.showcase.cecs.ymarketingintegration.services.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Collection;

import javax.annotation.Nullable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.hybris.showcase.cecs.ymarketingintegration.ServicesSAPInitiative;
import com.hybris.showcase.cecs.ymarketingintegration.dao.CEIInitiativeDAO;
import com.hybris.showcase.cecs.ymarketingintegration.model.CEIInitiativeModel;
import com.hybris.showcase.cecs.ymarketingintegration.services.InitiativesService;
import com.sap.wec.adtreco.be.CustomODataClientService;
import com.sap.wec.adtreco.be.HMCConfigurationReader;
import com.sap.wec.adtreco.bo.ADTUserIdProvider;
import com.sap.wec.adtreco.bo.impl.SAPInitiative;
import com.sap.wec.adtreco.bo.intf.SAPInitiativeReader;


/**
 * Created by miroslaw.szot@sap.com on 2015-06-15.
 */
public class DefaultInitiativesService implements InitiativesService
{
	private static Logger LOG = Logger.getLogger(DefaultInitiativesService.class);

	private FlexibleSearchService flexibleSearchService;
	private ModelService modelService;
	private SAPInitiativeReader sapInitiativeReader;
	private ADTUserIdProvider userIdProvider;
	private CEIInitiativeDAO ceiInitiativeDAO;
	private Converter<SAPInitiative, CEIInitiativeModel> ceiInitiativeConverter;

	private HMCConfigurationReader configuration;
	private CustomODataClientService oDataClientService;

	@Override
	public void retrieveInitiativesForCustomer(final CustomerModel customer)
	{
		try
		{
			final String adtUserId = getUserIdProvider().getADTUserId(customer);
			final Collection<SAPInitiative> remoteInitiatives = getSapInitiativeReader().searchInitiativesForBP(adtUserId);

			// CECS-560 yMkt: category is not included in yMkt responses
			for (final SAPInitiative remoteInitiative : remoteInitiatives)
			{
				loadExtraAttributes((ServicesSAPInitiative) remoteInitiative);
			}

			final Collection<CEIInitiativeModel> localInitiatives = getCeiInitiativeDAO().findInitiativesForUser(customer);
			removeOldInitiatives(remoteInitiatives, localInitiatives);
			addNewInitiatives(remoteInitiatives, localInitiatives, customer);
		}
		catch (ODataException | URISyntaxException | IOException e)
		{
			LOG.error(String.format("Failed to load initiatives for customer: %s.", customer.getUid()), e);
		}
	}

	// CECS-560 yMkt: category is not included in yMkt responses
	private void loadExtraAttributes(final ServicesSAPInitiative initiative) throws IOException, ODataException, URISyntaxException
	{
		if (StringUtils.isBlank(initiative.getCategoryId()))
		{
			// This hack has been introduced because:
			// $select=.....,Category doesn't work
			// $select=.....,Category/CategoryCode doesn't work
			// $expand=Category doesn't work
			// /Initiatives('0000000011')/Category/CategoryCode/$value doesn't work (it does in browser)
			configuration.loadADTConfiguration();
			final String user = configuration.getHttpDestination().getUserid();
			final String password = configuration.getHttpDestination().getPassword();
			final String SERVICE_URL = "/sap/opu/odata/sap/CUAN_COMMON_SRV";
			final String url = String.format("%s%s/Initiatives('%010d')/Category/CategoryCode",
					configuration.getHttpDestination().getTargetURL(), SERVICE_URL, Integer.parseInt(initiative.getId()));

			LOG.info("URL: " + url);
			try (final InputStream stream = oDataClientService.execute(url, CustomODataClientService.APPLICATION_XML,
					CustomODataClientService.HTTP_METHOD_GET, user, password))
			{
				String response = IOUtils.readLines(stream).get(0);
				response = response.replaceAll(".*>(.+)</d:CategoryCode>", "$1");
				LOG.info("CategoryId: " + response);
				initiative.setCategoryId(response);
			}
		}
	}

	@Override
	public void addLocalInitiative(final CustomerModel customer, final String categoryId)
	{
		final CEIInitiativeModel newLocalInitiative = new CEIInitiativeModel();
		newLocalInitiative.setId(categoryId);
		newLocalInitiative.setUser(customer);
		newLocalInitiative.setLocal(true);
		modelService.save(newLocalInitiative);
	}

	private void removeOldInitiatives(final Collection<SAPInitiative> remoteInitiatives,
			final Collection<CEIInitiativeModel> localInitiatives)
	{
		final Collection<String> remoteIds = getRemoteInitiativesIds(remoteInitiatives);
		final Collection<CEIInitiativeModel> removedInitiatives = Collections2.filter(localInitiatives,
				new Predicate<CEIInitiativeModel>()
				{
					@Override
					public boolean apply(final CEIInitiativeModel initiative)
					{
						return !remoteIds.contains(initiative.getId()) && !initiative.isLocal();
					}
				});

		for (final CEIInitiativeModel initiative : removedInitiatives)
		{
			modelService.remove(initiative);
		}
	}

	Collection<String> getRemoteInitiativesIds(final Collection<SAPInitiative> initiatives)
	{
		return Collections2.transform(initiatives, new Function<SAPInitiative, String>()
		{
			@Nullable
			@Override
			public String apply(final SAPInitiative initiative)
			{
				return initiative.getId();
			}
		});
	}

	private void addNewInitiatives(final Collection<SAPInitiative> remoteInitiatives,
			final Collection<CEIInitiativeModel> localInitiatives, final UserModel user)
	{
		// TODO so for now initiatives data is not updated
		final Collection<String> localIds = getLocalInitiativesIds(localInitiatives);
		final Collection<SAPInitiative> newInitiatives = Collections2.filter(remoteInitiatives, new Predicate<SAPInitiative>()
		{
			@Override
			public boolean apply(final SAPInitiative initiative)
			{
				return !localIds.contains(initiative.getId());
			}
		});

		for (final SAPInitiative initiative : newInitiatives)
		{
			final CEIInitiativeModel newLocalInitiative = getCeiInitiativeConverter().convert(initiative);
			newLocalInitiative.setUser(user);
			modelService.save(newLocalInitiative);
		}
	}

	private Collection<String> getLocalInitiativesIds(final Collection<CEIInitiativeModel> initiatives)
	{
		return Collections2.transform(initiatives, new Function<CEIInitiativeModel, String>()
		{
			@Nullable
			@Override
			public String apply(final CEIInitiativeModel initiative)
			{
				return initiative.getId();
			}
		});
	}

	protected SAPInitiativeReader getSapInitiativeReader()
	{
		return sapInitiativeReader;
	}

	@Required
	public void setSapInitiativeReader(final SAPInitiativeReader sapInitiativeReader)
	{
		this.sapInitiativeReader = sapInitiativeReader;
	}

	protected ADTUserIdProvider getUserIdProvider()
	{
		return userIdProvider;
	}

	@Required
	public void setUserIdProvider(final ADTUserIdProvider userIdProvider)
	{
		this.userIdProvider = userIdProvider;
	}

	protected CEIInitiativeDAO getCeiInitiativeDAO()
	{
		return ceiInitiativeDAO;
	}

	@Required
	public void setCeiInitiativeDAO(final CEIInitiativeDAO ceiInitiativeDAO)
	{
		this.ceiInitiativeDAO = ceiInitiativeDAO;
	}

	protected Converter<SAPInitiative, CEIInitiativeModel> getCeiInitiativeConverter()
	{
		return ceiInitiativeConverter;
	}

	@Required
	public void setCeiInitiativeConverter(final Converter<SAPInitiative, CEIInitiativeModel> ceiInitiativeConverter)
	{
		this.ceiInitiativeConverter = ceiInitiativeConverter;
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public HMCConfigurationReader getConfiguration()
	{
		return configuration;
	}

	@Required
	public void setConfiguration(final HMCConfigurationReader configuration)
	{
		this.configuration = configuration;
	}

	public CustomODataClientService getoDataClientService()
	{
		return oDataClientService;
	}

	@Required
	public void setoDataClientService(final CustomODataClientService oDataClientService)
	{
		this.oDataClientService = oDataClientService;
	}
}
