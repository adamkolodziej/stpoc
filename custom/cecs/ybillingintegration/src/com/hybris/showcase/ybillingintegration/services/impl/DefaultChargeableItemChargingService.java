/**
 *
 */
package com.hybris.showcase.ybillingintegration.services.impl;


import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.highdeal.ws.charging.ChargeableItemChargingServices;
import com.highdeal.ws.charging.schema.ChargeableItem;
import com.highdeal.ws.charging.schema.ChargeableItemChargeContext;
import com.highdeal.ws.charging.schema.ChargeableItemChargeRequest;
import com.highdeal.ws.charging.schema.ChargeableItemChargeResponse;
import com.highdeal.ws.charging.schema.ChargeableItemProperty;
import com.highdeal.ws.charging.schema.ChargeableItemUserProperties;
import com.highdeal.ws.charging.schema.ChargingOutputContext;
import com.highdeal.ws.charging.schema.ChargingResultContext;
import com.highdeal.ws.charging.schema.ObjectFactory;
import com.highdeal.ws.charging.schema.ResponseStatus;
import com.hybris.showcase.cecs.servicesshowcase.model.ChannelProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.TVEpisodeProductModel;
import com.hybris.showcase.ybillingintegration.services.ChargeableItemChargingService;


/**
 * @author I307113
 */
public class DefaultChargeableItemChargingService implements ChargeableItemChargingService
{
	private static final Logger LOG = Logger.getLogger(DefaultChargeableItemChargingService.class);

	private static final String SERVICE_NAME = "VOD";
	private static final String VIDEO_TYPE_TV_EPISODE = "STD_EP";
	private static final String VIDEO_TYPE_MOVIE = "STD_MOV";

	private ChargeableItemChargingServices chargeableItemChargingWebService;
	private ProductService productService;

	private final ObjectFactory objectFactory = new ObjectFactory();

	@Override
	public boolean registerFreeProductEntitlement(final String userTechnicalId, final String productCode,
			final Date freeProductEntitlementDate)
	{
		return registerItemCharge(userTechnicalId, productCode, freeProductEntitlementDate, 1);
	}

	@Override
	public boolean registerItemCharge(final String userTechnicalId, final String productCode, final Date consumptionDate)
	{
		return registerItemCharge(userTechnicalId, productCode, consumptionDate, 0);
	}


	private boolean registerItemCharge(final String userTechnicalId, final String productCode, final Date consumptionDate,
			final int userOrGet)
	{
		try
		{

			final ProductModel product = productService.getProductForCode(productCode);
			if (product instanceof ChannelProductModel)
			{
				return false;
			}

			final ChargeableItemChargeRequest chargeableItemChargeRequest = objectFactory.createChargeableItemChargeRequest();
			chargeableItemChargeRequest.setChargeableItem(createChargeableItem(userTechnicalId, consumptionDate, userOrGet, product));
			chargeableItemChargeRequest.setContext(createContext());

			final ChargeableItemChargeResponse response = chargeableItemChargingWebService
					.chargeableItemCharge(chargeableItemChargeRequest);

			final ResponseStatus status = response.getStatus();
			if (status != null && status == ResponseStatus.ERROR)
			{
				LOG.error("Error: " + response.getMessage());
				if (response.getError() != null)
				{
					LOG.error(response.getError().getMessage());
				}
				return false;
			}
		}
		catch (final RuntimeException ex)
		{
			LOG.error("Error occured during registering item charge", ex);

			return false;
		}
		catch (final DatatypeConfigurationException | ParseException e)
		{
			LOG.error("Error occured during date conversion", e);

			return false;
		}


		return true;

	}

	private ChargeableItemChargeContext createContext()
	{
		final ChargeableItemChargeContext context = objectFactory.createChargeableItemChargeContext();
		final ChargingOutputContext outputContext = objectFactory.createChargingOutputContext();
		outputContext.setChargeableItemExported(false);
		context.setChargingOutputContext(outputContext);

		final ChargingResultContext resultContext = objectFactory.createChargingResultContext();
		resultContext.setChargingProcessInfoReturned(true);
		resultContext.setChargingContractInfoReturned(true);
		resultContext.setChargeableItemInfoReturned(true);
		resultContext.setAccountOperationReturned(true);
		resultContext.setAccountInfoReturned(true);
		resultContext.setChargedItemReturned(false);
		resultContext.setNotificationReturned(false);
		context.setChargingResultContext(resultContext);
		return context;
	}

	private ChargeableItem createChargeableItem(final String userTechnicalId, final Date consumptionDate, final int userOrGet,
			final ProductModel product) throws DatatypeConfigurationException, ParseException
	{
		final ChargeableItem chargeableItem = objectFactory.createChargeableItem();

		chargeableItem.setName(SERVICE_NAME);
		chargeableItem.setServiceId(SERVICE_NAME);
		chargeableItem.setUserTechnicalId(userTechnicalId);
		chargeableItem.setConsumptionDate(toXMLGregorianCalendar(consumptionDate));

		final ChargeableItemUserProperties userProperties = createUserProperties(getVideoType(product), product.getName(),
				userOrGet);
		chargeableItem.setUserProperties(userProperties);
		return chargeableItem;
	}

	private static String getVideoType(final ProductModel product)
	{
		if (product instanceof TVEpisodeProductModel)
		{
			return VIDEO_TYPE_TV_EPISODE;
		}
		else
		{
			return VIDEO_TYPE_MOVIE;
		}

	}

	private XMLGregorianCalendar toXMLGregorianCalendar(final Date date) throws DatatypeConfigurationException, ParseException
	{
		final LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

		final XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
		cal.setYear(dateTime.getYear());
		cal.setMonth(dateTime.getMonthValue());
		cal.setDay(dateTime.getDayOfMonth());
		cal.setHour(dateTime.getHour());
		cal.setMinute(dateTime.getMinute());
		cal.setSecond(dateTime.getSecond());

		return cal;
	}

	private ChargeableItemUserProperties createUserProperties(final String type, final String productCode, final int useOrGet)
	{
		final ChargeableItemUserProperties userProperties = objectFactory.createChargeableItemUserProperties();
		final ChargeableItemProperty operation = new ChargeableItemProperty();
		operation.setName("Use or Get");
		operation.setNumberValue(new BigDecimal(useOrGet));
		userProperties.getProperty().add(operation);

		final ChargeableItemProperty videoId = new ChargeableItemProperty();
		videoId.setName("Video ID");
		videoId.setStringValue(productCode);
		userProperties.getProperty().add(videoId);

		final ChargeableItemProperty videoType = new ChargeableItemProperty();
		videoType.setName("Video Type");
		videoType.setStringValue(type);
		userProperties.getProperty().add(videoType);
		return userProperties;
	}


	public ChargeableItemChargingServices getChargeableItemChargingWebService()
	{
		return chargeableItemChargingWebService;
	}

	@Required
	public void setChargeableItemChargingWebService(final ChargeableItemChargingServices chargeableItemChargingWebService)
	{
		this.chargeableItemChargingWebService = chargeableItemChargingWebService;
	}

	/**
	 * @return the productService
	 */
	public ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

}
