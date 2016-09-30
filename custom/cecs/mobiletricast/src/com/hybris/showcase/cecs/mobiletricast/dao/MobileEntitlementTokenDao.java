package com.hybris.showcase.cecs.mobiletricast.dao;

import com.hybris.showcase.cecs.mobiletricast.model.MobileEntitlementTokenModel;


public interface MobileEntitlementTokenDao
{

	MobileEntitlementTokenModel getToken(String authToken);

}
