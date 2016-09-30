package com.hybris.showcase.cecs.ymarketingintegration.converters.populators;

import de.hybris.platform.converters.Populator;

import com.hybris.showcase.cecs.ymarketingintegration.ServicesSAPInitiative;
import com.hybris.showcase.cecs.ymarketingintegration.model.CEIInitiativeModel;
import com.sap.wec.adtreco.bo.impl.SAPInitiative;


public class BasicCEIInitiativePopulator implements Populator<SAPInitiative, CEIInitiativeModel>
{
	@Override
	public void populate(final SAPInitiative source, final CEIInitiativeModel target)
	{
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setStatus(source.getStatus());
		target.setMemberCount(source.getMemberCount());
		target.setStartDate(source.getStartDate());
		target.setEndDate(source.getEndDate());
		target.setCategoryId(((ServicesSAPInitiative) source).getCategoryId());
	}
}
