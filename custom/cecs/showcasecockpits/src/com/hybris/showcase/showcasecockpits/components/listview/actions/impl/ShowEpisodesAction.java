/**
 * 
 */
package com.hybris.showcase.showcasecockpits.components.listview.actions.impl;

import de.hybris.platform.cockpit.model.meta.ObjectTemplate;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.productcockpit.components.listview.impl.AbstractProductAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Menupopup;

import com.hybris.showcase.cecs.servicesshowcase.model.TVEpisodeProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.TVSeasonProductModel;
import com.hybris.showcase.cecs.servicesshowcase.model.TVShowProductModel;


/**
 * @author I307113
 * 
 */
public class ShowEpisodesAction extends AbstractProductAction
{


	private static final Logger LOG = Logger.getLogger(ShowEpisodesAction.class);

	protected String ICON_FUNC_OPEN_EPISODES = "cockpit/images/icon_func_open_episodes.png";

	private TypeService cockpitTypeService;

	@Override
	public String getImageURI(final Context paramContext)
	{
		final TypedObject item = getItem(paramContext);
		if ((item != null)
				&& ((item.getObject() instanceof TVSeasonProductModel) || (item.getObject() instanceof TVShowProductModel)))
		{
			return ICON_FUNC_OPEN_EPISODES;
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
			if ((itemObject instanceof TVSeasonProductModel) || (itemObject instanceof TVShowProductModel))
			{
				ret = new EventListener()
				{
					@Override
					public void onEvent(final Event paramEvent) throws Exception
					{
						final ObjectTemplate paramObjectTemplate = getCockpitTypeService().getObjectTemplate(
								TVEpisodeProductModel._TYPECODE);
						final Collection<TypedObject> episodesParamColl = new ArrayList<>();

						if (itemObject instanceof TVSeasonProductModel)
						{
							final TVSeasonProductModel product = (TVSeasonProductModel) itemObject;

							final Collection<TVEpisodeProductModel> episodes = product.getEpisodes();
							if (episodes != null)
							{
								for (final TVEpisodeProductModel episode : episodes)
								{
									episodesParamColl.add(getCockpitTypeService().wrapItem(episode));
								}
							}
						}
						else if (itemObject instanceof TVShowProductModel)
						{
							final TVShowProductModel product = (TVShowProductModel) itemObject;

							// filter episodes for current season
							final Collection<TVSeasonProductModel> seasons = product.getSeasons();

							final Collection<TVEpisodeProductModel> episodes = new ArrayList<>();
							for (final TVSeasonProductModel season : seasons)
							{
								episodes.addAll(season.getEpisodes());
							}

							for (final TVEpisodeProductModel episode : episodes)
							{
								episodesParamColl.add(getCockpitTypeService().wrapItem(episode));
							}
						}

						UISessionUtils
								.getCurrentSession()
								.getCurrentPerspective()
								.openReferenceCollectionInBrowserContext(episodesParamColl, paramObjectTemplate, item,
										Collections.<String, Object> emptyMap());
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
		final String labelSrc = "gridview.item.open.episodes.info";
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
