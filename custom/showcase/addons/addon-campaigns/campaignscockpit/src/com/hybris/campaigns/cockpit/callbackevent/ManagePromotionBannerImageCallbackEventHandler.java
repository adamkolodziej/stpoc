/**
 * 
 */
package com.hybris.campaigns.cockpit.callbackevent;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cmscockpit.components.liveedit.LiveEditView;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.liveeditaddon.cockpit.callbackevent.AbstractLiveEditCallbackEventHandler;
import de.hybris.liveeditaddon.cockpit.service.MediaFormatService;
import de.hybris.liveeditaddon.cockpit.wizards.mediacontainermanagement.ManageMediaContainerWizard;
import de.hybris.liveeditaddon.cockpit.wizards.mediacontainermanagement.service.impl.AbstractManageMediaService;

import java.util.Map;

import javax.annotation.Resource;

import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Messagebox;

import com.hybris.campaigns.cockpit.wizards.mediamanagement.service.impl.DefaultManagePromotionBannerMediaService;
import com.hybris.campaigns.model.cms.PromotionBannerComponentModel;


/**
 * @author dilic
 * 
 */
public class ManagePromotionBannerImageCallbackEventHandler extends AbstractLiveEditCallbackEventHandler
{
	private static final String COMPONENT_ID="cmp_id";
	
	@Resource(name = "mediaFormatService")
	private MediaFormatService mediaFormatService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.addon.cockpits.components.liveedit.CallbackEventHandler#getEventId()
	 */
	@Override
	public String getEventId()
	{
		return "managePromotionBannerImage";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hybris.addon.cockpits.components.liveedit.CallbackEventHandler#onCallbackEvent(de.hybris.platform.cmscockpit
	 * .components.liveedit.LiveEditView, java.lang.String[])
	 */
	@Override
	public void onCallbackEvent(final LiveEditView view, Map passedAttributes) throws Exception
	{
		final String componentId = passedAttributes.get(COMPONENT_ID).toString();
		//FIX THIS MADAS ASAP
		final String serverPath = passedAttributes.get(COMPONENT_ID).toString();//passedAttributes[3];
		try
		{
			final AbstractCMSComponentModel component = getComponentForUid(componentId, view);
			if (component instanceof PromotionBannerComponentModel)
			{
				final PromotionBannerComponentModel abstractMediaContainerComponent = (PromotionBannerComponentModel) component;
				final CatalogVersionModel catalogVersion = view.getModel().getCurrentPreviewData().getActiveCatalogVersion();
				final String siteUid = view.getModel().getSite().getUid();

				final MediaContainerModel mediaContainerModel = abstractMediaContainerComponent.getPromotion().getPromotionBanners();

				final Map<String, String> mediaFormatsForCurrentSite = mediaFormatService.getMediaFormatsForCurrentSite(siteUid);
				final AbstractManageMediaService manageMediaService = new DefaultManagePromotionBannerMediaService(serverPath,
						siteUid, mediaContainerModel != null ? mediaContainerModel.getCatalogVersion() : catalogVersion,
						mediaContainerModel, abstractMediaContainerComponent, mediaFormatsForCurrentSite);
				final ManageMediaContainerWizard wizard = new ManageMediaContainerWizard(manageMediaService);
				wizard.show(view);
			}
			else
			{
				throw new IllegalArgumentException(
						"Current component is not type of AbstractMediaContainerComponentModel. Please provide correct component");
			}
		}
		catch (final IllegalArgumentException e)
		{
			Messagebox.show(Labels.getLabel("dialog.managemedia.nocomponentfound.error.message"),
					Labels.getLabel("dialog.managemedia.nocomponentfound.error.title"), Messagebox.CANCEL, Messagebox.ERROR);
		}
		finally
		{
			view.update();
		}

	}

}
