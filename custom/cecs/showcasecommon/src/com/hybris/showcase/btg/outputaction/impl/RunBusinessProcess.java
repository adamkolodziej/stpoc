package com.hybris.showcase.btg.outputaction.impl;

import com.google.common.base.Preconditions;
import com.hybris.showcase.events.RunBusinessProcessEvent;
import com.hybris.showcase.model.BTGRunBusinessProcessDefinitionModel;
import de.hybris.platform.btg.outputaction.OutputActionContext;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.action.impl.ActionPerformable;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.action.AbstractActionModel;
import de.hybris.platform.store.services.BaseStoreService;
import org.springframework.beans.factory.annotation.Required;

public class RunBusinessProcess implements ActionPerformable<OutputActionContext<BTGRunBusinessProcessDefinitionModel>> {

    private EventService eventService;
    private BaseStoreService baseStoreService;
    private CommonI18NService commonI18NService;
    private CMSSiteService cmsSiteService;

    @Override
    public void performAction(final AbstractActionModel abstractActionModel, final OutputActionContext<BTGRunBusinessProcessDefinitionModel> outputActionContext) {
        Preconditions.checkArgument(outputActionContext.getUser() instanceof CustomerModel);

        final RunBusinessProcessEvent event = new RunBusinessProcessEvent();
        event.setBusinessProcessName(outputActionContext.getActionDefinition().getBusinessProcessName());
        event.setCustomer((CustomerModel) outputActionContext.getUser());
        event.setBaseStore(getBaseStoreService().getCurrentBaseStore());
        event.setCurrency(getCommonI18NService().getCurrentCurrency());
        event.setLanguage(getCommonI18NService().getCurrentLanguage());
        event.setSite(getCmsSiteService().getCurrentSite());

        getEventService().publishEvent(event);
    }

    protected EventService getEventService() {
        return eventService;
    }

    @Required
    public void setEventService(final EventService eventService) {
        this.eventService = eventService;
    }

    protected BaseStoreService getBaseStoreService() {
        return baseStoreService;
    }

    @Required
    public void setBaseStoreService(final BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }

    protected CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }

    @Required
    public void setCommonI18NService(final CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }

    protected CMSSiteService getCmsSiteService() {
        return cmsSiteService;
    }

    @Required
    public void setCmsSiteService(final CMSSiteService cmsSiteService) {
        this.cmsSiteService = cmsSiteService;
    }
}
