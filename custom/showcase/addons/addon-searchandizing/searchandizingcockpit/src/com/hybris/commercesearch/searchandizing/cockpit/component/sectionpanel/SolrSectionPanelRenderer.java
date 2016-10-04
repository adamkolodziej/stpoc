/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2012 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.hybris.commercesearch.searchandizing.cockpit.component.sectionpanel;

import de.hybris.platform.catalog.jalo.CatalogManager;
import de.hybris.platform.catalog.jalo.CatalogVersion;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cockpit.components.listview.ActionColumnConfiguration;
import de.hybris.platform.cockpit.components.sectionpanel.SectionPanelLabelRenderer;
import de.hybris.platform.cockpit.constants.ImageUrls;
import de.hybris.platform.cockpit.model.gridview.impl.GridView;
import de.hybris.platform.cockpit.model.meta.ObjectTemplate;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.config.GridViewConfiguration;
import de.hybris.platform.cockpit.services.dragdrop.DragAndDropWrapper;
import de.hybris.platform.cockpit.services.dragdrop.DraggedItem;
import de.hybris.platform.cockpit.services.dragdrop.impl.DefaultDraggedItem;
import de.hybris.platform.cockpit.session.UIEditorArea;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.session.impl.BaseUICockpitPerspective;
import de.hybris.platform.cockpit.util.ListActionHelper;
import de.hybris.platform.cockpit.util.TypeTools;
import de.hybris.platform.cockpit.util.UITools;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.jalo.Item;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Popup;


public class SolrSectionPanelRenderer implements SectionPanelLabelRenderer
{
	private static final Logger LOG = Logger.getLogger(SolrSectionPanelRenderer.class);

	private static final String EDITOR_AREA_ACTIONS_DIV = "editorAreaSearchandizingActionsDiv";
	private static final String EDITOR_AREA_STATUS_DIV = "editorAreaStatusDiv";

	private final UIEditorArea editorArea;
	private TypedObject currentObject;

	public SolrSectionPanelRenderer(final UIEditorArea editorArea)
	{
		if (editorArea == null)
		{
			throw new IllegalArgumentException("Editor area can not be null.");
		}
		else
		{
			this.editorArea = editorArea;
		}
	}

	@Override
	public void render(final String label, final String imageUrl, final Component parent)
	{
		String name = "";
		name = label;

		final Div box = new Div();
		UITools.maximize(box);

		final Div labelDiv = new Div();
		labelDiv.setSclass("labelContainerAction");
		labelDiv.appendChild(new Label(name));
		box.appendChild(labelDiv);

		box.setDroppable(BaseUICockpitPerspective.DRAG_DROP_ID);
		box.setDraggable(BaseUICockpitPerspective.DRAG_DROP_ID);
		box.addEventListener(Events.ON_DROP, new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws Exception //NOPMD: ZK specific
			{
				try
				{
					final DraggedItem draggedItem = getEditorArea().getPerspective().getDragAndDropWrapperService().getWrapper()
							.getDraggedItem(((DropEvent) event).getDragged());
					getEditorArea().getPerspective().activateItemInEditor(draggedItem.getSingleTypedObject());
				}
				catch (final Exception e)
				{
					LOG.error("D&D error: " + e);
				}
			}
		});


		final Div descriptionContainer = new Div();
		descriptionContainer.setSclass("descriptionContainer");
		descriptionContainer.appendChild(labelDiv);


		String displayImgUrl = ImageUrls.STOP;
		if (imageUrl != null)
		{
			displayImgUrl = imageUrl;
		}
		final Div imgDiv = new Div();
		imgDiv.setSclass("section-label-image");
		final Image img = new Image(displayImgUrl);
		img.setSclass("section-label-image");
		imgDiv.appendChild(img);

		final Popup imgPopup = new Popup();
		final Image tooltipImg = new Image(displayImgUrl);
		imgPopup.appendChild(tooltipImg);
		imgDiv.appendChild(imgPopup);
		img.setTooltip(imgPopup);
		box.appendChild(imgDiv);


		final Div firstRow = new Div();
		firstRow.setSclass("statusActionContainer");
		descriptionContainer.appendChild(firstRow);

		final Div stausDiv = new Div();
		stausDiv.setClass(EDITOR_AREA_STATUS_DIV);
		firstRow.appendChild(stausDiv);

		if (getCurrentObject() != null)
		{
			final Div actionsDiv = new Div();
			actionsDiv.setClass(EDITOR_AREA_ACTIONS_DIV);
			renderActions(actionsDiv, getCurrentObject());
			//firstRow.appendChild(actionsDiv);
			firstRow.appendChild(actionsDiv);
		}


		final DragAndDropWrapper wrapper = getEditorArea().getPerspective().getDragAndDropWrapperService().getWrapper();
		wrapper.attachDraggedItem(new DefaultDraggedItem(getEditorArea().getCurrentObject()), img);
		img.setDraggable(BaseUICockpitPerspective.DRAG_DROP_ID);
		box.appendChild(descriptionContainer);
		parent.appendChild(box);

		// add catalog version mnemonic
		if (getEditorArea().getCurrentObject() != null && getEditorArea().getCurrentObject().getObject() instanceof ItemModel) //NOPMD: wtf?!
		{
			final ItemModel itemModel = (ItemModel) getEditorArea().getCurrentObject().getObject();

			final Object source = UISessionUtils.getCurrentSession().getModelService().getSource(itemModel);
			if (source instanceof Item && CatalogManager.getInstance().isCatalogItem((Item) source))
			{
				final CatalogVersion catalogVersionJalo = CatalogManager.getInstance().getCatalogVersion((Item) source);
				//null is possible for example for CatalogUnawareMedia
				if (catalogVersionJalo != null)
				{
					// get catalog version model
					final CatalogVersionModel catVersion = TypeTools.getModelService().get(catalogVersionJalo.getPK());
					final String mnemonic = catVersion.getMnemonic();
					if (!StringUtils.isBlank(mnemonic))
					{
						final Label mnemLabel = new Label(" (" + mnemonic + ")");
						mnemLabel.setParent(labelDiv);
						mnemLabel.setSclass("catalog-mnemonic-label");
					}
				}
			}
		}


	}

	/**
	 * Render actions related icons
	 * 
	 * @param parent
	 * @param item
	 */

	protected void renderActions(final Component parent, final TypedObject item)

	{
		final ObjectTemplate template = UISessionUtils.getCurrentSession().getTypeService().getBestTemplate(item);
		final GridViewConfiguration config = UISessionUtils.getCurrentSession().getUiConfigurationService()
				.getComponentConfiguration(template, GridView.DEFAULT_GRIDVIEW_CONF, GridViewConfiguration.class);
		final ActionColumnConfiguration actionConfiguration = getActionConfiguration(config);
		if (actionConfiguration != null)
		{
			ListActionHelper.renderActions(parent, item, actionConfiguration, "editorAreaSearchandizingActionImg");
		}
	}


	public ActionColumnConfiguration getActionConfiguration(final GridViewConfiguration config)
	{
		if (config != null)
		{
			final String actionSpringBeanID = config.getActionSpringBeanID();
			if (actionSpringBeanID != null)
			{
				return (ActionColumnConfiguration) SpringUtil.getBean(actionSpringBeanID);

			}
		}
		return null;
	}

	protected UIEditorArea getEditorArea()
	{
		return this.editorArea;
	}

	/**
	 * @return the currentObject
	 */
	public TypedObject getCurrentObject()
	{
		return currentObject;
	}

	/**
	 * @param currentObject
	 *           the currentObject to set
	 */
	public void setCurrentObject(final TypedObject currentObject)
	{
		this.currentObject = currentObject;
	}


}
