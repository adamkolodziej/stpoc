package com.hybris.showcase.cecs.ymarketingintegration;

import com.sap.wec.adtreco.bo.impl.SAPInitiative;
import com.sap.wec.adtreco.bo.impl.SAPInitiativeReaderImpl;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;
import org.apache.olingo.odata2.api.exception.ODataException;

import com.sap.wec.adtreco.bo.impl.SAPInitiative;
import com.sap.wec.adtreco.bo.impl.SAPInitiativeReaderImpl;
import org.springframework.beans.factory.annotation.Required;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * CECS-295 fix sapadtreco - match customer by CustomerId
 * Created by miroslaw.szot@sap.com on 2015-04-27.
 */
public class ServicesSAPInitiativeReaderImpl extends SAPInitiativeReaderImpl {
	protected static final String INITIATIVES = "Initiatives";
	protected static final String DT_selectTerms = "Name,Description,InitiativeId,InitiativeIdExt,LifecycleStatus,TargetGroup/CustomerMemberCount";
	protected static final String RT_selectTerms = "Name,Description,InitiativeId,InitiativeIdExt";
	protected static final String ACTIVE = "1";
    protected static final String PLANNED = "2";
	protected static final String EQ_UTF8 = " eq '";
	protected static final String QUOT_UTF8 = "'";
	protected static final String AND_UTF8 = " and ";
    protected static final String OR_UTF8 = " or ";
	protected static final String B2B_CUSTOMER = "B2BCustomer";
	protected static final String B2C_CUSTOMER = "Customer";
	protected static final String TARGETGROUPS = "TargetGroup";

	private static final Logger LOG = Logger.getLogger(ServicesSAPInitiativeReaderImpl.class);

    private UserService userService;

    @Override
    public List<SAPInitiative> searchInitiativesForBP(String businessPartner) throws ODataException, URISyntaxException, IOException {
        if (StringUtils.isBlank(businessPartner)) {
            return Collections.emptyList();
        }

        getConfiguration().loadADTConfiguration();

        String customerType = B2C_CUSTOMER;
        String unitId = "";
        String filterBP = "";

        final B2BCustomerModel b2bCustomer = (B2BCustomerModel) b2bCustomerService.getCurrentB2BCustomer();
        if (b2bCustomer != null)
        {
            unitId = b2bUnitService.getParent(b2bCustomer).getUid();
            if (unitId != null)
            {
                customerType = B2B_CUSTOMER;
            }
        }

        if (businessPartner != null && businessPartner.length() > 0)
        {
            if (customerType.equals(B2C_CUSTOMER))
            {
                filterBP = "Filter/InteractionContactIdOrigin" + EQ_UTF8 + getConfiguration().getIdOrigin() + QUOT_UTF8
                        + " and Filter/InteractionContactId" + EQ_UTF8 + businessPartner + QUOT_UTF8;
            }
            else if (customerType.equals(B2B_CUSTOMER))
            {
                filterBP = "Filter/CustomerId" + EQ_UTF8 + unitId + QUOT_UTF8;
            }
        }

        final String filterCategory;
        if( StringUtils.isNotBlank( getConfiguration().getFilterCategory() )) {
            filterCategory = "Category/CategoryCode" + EQ_UTF8 + getConfiguration().getFilterCategory() + QUOT_UTF8 + AND_UTF8;
        } else {
            filterCategory = "";
        }

        final String filterStatus = "Search/TileFilterCategory" + EQ_UTF8 + ACTIVE + QUOT_UTF8;
        final String filterTerms = filterCategory + filterStatus + AND_UTF8 + filterBP;

        final ODataFeed feed = accessBE.getInitiatives(RT_selectTerms, filterTerms, INITIATIVES, null);
        if (feed == null)
        {
            LOG.warn("query for Customer=" + businessPartner + " found 0 initiatives");
            return Collections.emptyList();
        }
        else
        {
            final List<ODataEntry> foundEntries = feed.getEntries();

            LOG.info("query for Customer=" + businessPartner + " found " + foundEntries.size() + " initiatives");
            return extractInitiatives(foundEntries);
        }
    }

    public List<SAPInitiative> searchInitiatives(final String search) throws ODataException, URISyntaxException, IOException, RuntimeException
    {
        getConfiguration().loadADTConfiguration();
        final String filterCategory;
        if( StringUtils.isNotBlank( getConfiguration().getFilterCategory() )) {
            filterCategory = "Category/CategoryCode" + EQ_UTF8 + getConfiguration().getFilterCategory() + QUOT_UTF8 + AND_UTF8;
        } else {
            filterCategory = "";
        }

        final String filterDescription = "Search/SearchTerm" + EQ_UTF8 + search + QUOT_UTF8;

        // CECS-398 initiatives not loaded from yMarketing
        final String filterStatus = "(Search/TileFilterCategory" + EQ_UTF8 + ACTIVE + QUOT_UTF8 + OR_UTF8
                + "Search/TileFilterCategory" + EQ_UTF8 + PLANNED + QUOT_UTF8 + ")";

        final String filterTerms = filterDescription  + filterCategory + AND_UTF8 + filterStatus;
        final ODataFeed feed = accessBE.getInitiatives(DT_selectTerms, filterTerms, INITIATIVES, TARGETGROUPS);

        List<SAPInitiative> initiatives = new ArrayList<SAPInitiative>();
        if (feed != null)
        {
            final List<ODataEntry> foundEntries = feed.getEntries();
            initiatives = extractInitiatives(foundEntries);
        }

        return initiatives;
    }

	@Override
	protected SAPInitiative extractInitiative(final ODataEntry entity)
	{
		final SAPInitiative initiative = new ServicesSAPInitiative();
		final Map<String, Object> props = entity.getProperties();
		if (props != null)
		{
			initiative.setName(props.get("Name").toString());
			initiative.setDescription(props.get("Description").toString());
			initiative.setId(props.get("InitiativeIdExt").toString());
			//TODO Check is "Category" right attribute name
			final HashMap<String, String> category = (HashMap<String, String>) props.get("Category");
			if (category != null)
			{
				final String categoryId = category.get("CategoryCode");
				((ServicesSAPInitiative) initiative).setCategoryId(categoryId);
			}

			final HashMap<String, String> status = (HashMap<String, String>) props.get("LifecycleStatus");
			if (status != null)
			{
				final String statusName = status.get("StatusDescription");
				initiative.setStatus(statusName);
			}

			final ODataEntry tg = (ODataEntry) props.get("TargetGroup");
			if (tg != null)
			{
				final Map<String, Object> tgProps = tg.getProperties();
				initiative.setMemberCount(tgProps.get("CustomerMemberCount").toString());
			}
		}
		return initiative;
	}

	@Required
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}
}
