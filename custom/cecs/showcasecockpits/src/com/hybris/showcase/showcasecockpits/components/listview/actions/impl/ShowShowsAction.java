/**
 * 
 */
package com.hybris.showcase.showcasecockpits.components.listview.actions.impl;

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.productcockpit.components.listview.impl.AbstractProductAction;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Menupopup;

import com.hybris.showcase.cecs.servicesshowcase.model.TVSeasonProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.TVShowProductModel;


/**
 * @author I307113
 * 
 */
public class ShowShowsAction extends AbstractProductAction
{


	private static final Logger LOG = Logger.getLogger(ShowShowsAction.class);

	protected String ICON_FUNC_OPEN_SHOW = "cockpit/images/icon_func_open_show.png";

	private TypeService cockpitTypeService;

	@Override
	public String getImageURI(final Context paramContext)
	{
		final TypedObject item = getItem(paramContext);
		if ((item != null) && (item.getObject() instanceof TVSeasonProductModel))
		{
			return ICON_FUNC_OPEN_SHOW;
		}
		return null;
	}

	@Override
	public EventListener getEventListener(final Context paramContext)
	{
		EventListener ret = null;
		final TypedObject item = getItem(paramContext);

		if (item != null)
		{
			final Object itemObject = item.getObject();
			if (itemObject instanceof TVSeasonProductModel)
			{
				ret = new EventListener()
				{
					@Override
					public void onEvent(final Event paramEvent) throws Exception
					{
						TypedObject paramTypedObject = null;
						if (itemObject instanceof TVSeasonProductModel)
						{
							final TVSeasonProductModel product = (TVSeasonProductModel) itemObject;

							// filter show for current season
							final TVShowProductModel show = product.getTvShow();
							if (show != null)
							{
								paramTypedObject = getCockpitTypeService().wrapItem(show);
							}
						}

						if (paramTypedObject != null)
						{
							UISessionUtils.getCurrentSession().getCurrentPerspective().activateItemInEditor(paramTypedObject);
						}
					}
				};
			}
		}
		return ret;
	}

	@Override
	public Menupopup getPopup(final Context paramContext)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	@Override
	public Menupopup getContextPopup(final Context paramContext)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTooltip(final Context paramContext)
	{
		final String labelSrc = "gridview.item.open.show.info";
		return Labels.getLabel(labelSrc);
	}

	@Override
	protected void doCreateContext(final Context arg0)
	{
		// YTODO Auto-generated method stub

	}

	public TypeService getCockpitTypeService()
	{
		return cockpitTypeService;
	}

	@Resource(name = "cockpitTypeService")
	public void setCockpitTypeService(final TypeService cockpitTypeService)
	{
		this.cockpitTypeService = cockpitTypeService;
	}
}
