/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.hybris.productsets.cockpit.captionhandler;

import com.hybris.productsets.model.ProductSetModel;

import de.hybris.platform.acceleratorservices.urldecoder.FrontendUrlDecoder;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.cmscockpit.cms.strategies.CounterpartProductCatalogVersionsStrategy;
import de.hybris.platform.cmscockpit.components.liveedit.AbstractLiveEditCaptionButtonHandler;
import de.hybris.platform.cmscockpit.session.impl.LiveEditBrowserArea;
import de.hybris.platform.cmscockpit.session.impl.LiveEditBrowserModel;
import de.hybris.platform.cmscockpit.session.impl.LiveEditContentBrowser;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.yacceleratorcockpits.cmscockpit.session.impl.DefaultLiveEditBrowserArea;
import de.hybris.liveeditaddon.cockpit.session.impl.LiveeditaddonPerspective;

import org.springframework.beans.factory.annotation.Required;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;

import javax.annotation.Resource;



/**
 * Created by tamas.iklodi on 20/08/14.
 */
public class EditProductSetLiveEditCaptionButtonHandler extends AbstractLiveEditCaptionButtonHandler
{

    @Resource(name = "cockpitTypeService")
    private TypeService cockpitTypeService;

    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Resource(name = "catalogVersionService")
    private CatalogVersionService catalogVersionService;

    @Resource(name = "counterpartProductCatalogVersionsStrategy")
    private CounterpartProductCatalogVersionsStrategy counterpartProductCatalogVersionsStrategy;

    private FrontendUrlDecoder<ProductSetModel> productSetFrontendUrlDecoder;


    /*
     * (non-Javadoc)
     *
     * @see com.hybris.addon.cockpits.components.liveedit.LiveEditCaptionButtonHandler#createButton(de.hybris.platform.
     * yacceleratorcockpits.cmscockpit.session.impl.DefaultLiveEditBrowserArea,
     * com.hybris.addon.cockpits.session.liveedit.impl.AddOnLiveEditBrowserModel,
     * com.hybris.addon.cockpits.session.liveedit.impl.AddOnLiveEditContentBrowser, org.zkoss.zul.Hbox)
     */
    @Override
    public void createButton(final LiveEditBrowserArea area, final LiveEditBrowserModel browserModel,
                             final LiveEditContentBrowser contentBrowser, final Hbox buttonContainer)
    {
        final ProductSetModel productSet = getProductSetForPreviewCatalogVersions(browserModel.getCurrentUrl());

        if (productSet != null)
        {
            createRightCaptionButton(Labels.getLabel("browser.editProductSet"), "btnliveeditcontent_editproduct", buttonContainer,
                    new org.zkoss.zk.ui.event.EventListener()
                    {
                        @Override
                        public void onEvent(final Event event) throws Exception //NOPMD: ZK Specific
                        {
                            final TypedObject wrappedItem = getCockpitTypeService().wrapItem(productSet);

                            ((LiveeditaddonPerspective) UISessionUtils.getCurrentSession().getCurrentPerspective())
                                    .activateItemInLiveEditPopup(wrappedItem, createPopupWindow(contentBrowser.getParent()),
                                            new LiveeditaddonPerspective.OnEventCallback()
                                            {


                                                @Override
                                                public void onEvent(final Event event)
                                                {
                                                    if (event.getName().equals(Events.ON_CLOSE))
                                                    {
                                                        contentBrowser.update();
                                                    }

                                                }
                                            }, null);
                        }
                    });
        }

    }


    protected ProductSetModel getProductSetForPreviewCatalogVersions(final String url)
    {
        return ((ProductSetModel) getSessionService().executeInLocalView(new SessionExecutionBody()
        {
            @Override
            public Object execute()
            {
                getCatalogVersionService().setSessionCatalogVersions(
                        getCounterpartProductCatalogVersionsStrategy().getCounterpartProductCatalogVersions());
                return getProductSetFrontendUrlDecoder().decode(url);

            }
        }));
    }


    @Required
    public void setSessionService(final SessionService sessionService)
    {
        this.sessionService = sessionService;
    }

    public SessionService getSessionService()
    {
        return this.sessionService;
    }

    public CatalogVersionService getCatalogVersionService()
    {
        return catalogVersionService;
    }

    @Required
    public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
    {
        this.catalogVersionService = catalogVersionService;
    }

    public CounterpartProductCatalogVersionsStrategy getCounterpartProductCatalogVersionsStrategy()
    {
        return counterpartProductCatalogVersionsStrategy;
    }

    /**
     * @param counterpartProductCatalogVersionsStrategy
     *           the counterpartProductCatalogVersionsStrategy to set
     */
    @Required
    public void setCounterpartProductCatalogVersionsStrategy(
            final CounterpartProductCatalogVersionsStrategy counterpartProductCatalogVersionsStrategy)
    {
        this.counterpartProductCatalogVersionsStrategy = counterpartProductCatalogVersionsStrategy;
    }

    public FrontendUrlDecoder<ProductSetModel> getProductSetFrontendUrlDecoder()
    {
        return productSetFrontendUrlDecoder;
    }

    @Required
    public void setProductSetFrontendUrlDecoder(final FrontendUrlDecoder<ProductSetModel> productSetFrontendUrlDecoder)
    {
        this.productSetFrontendUrlDecoder = productSetFrontendUrlDecoder;
    }


    @Required
    public void setCockpitTypeService(final TypeService typeService)
    {
        this.cockpitTypeService = typeService;
    }

    public TypeService getCockpitTypeService()
    {
        return this.cockpitTypeService;
    }

}
