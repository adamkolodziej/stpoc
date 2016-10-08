/**
 * 
 */
package com.hybris.commercesearch.searchandizing.cockpit.session.impl;


import de.hybris.platform.cockpit.components.sectionpanel.AbstractSectionPanelModel;
import de.hybris.platform.cockpit.components.sectionpanel.SectionPanelModel;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.label.LabelService;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.session.impl.DefaultEditorAreaController;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;

import java.util.Map;

import org.apache.log4j.Logger;

import com.hybris.commercesearch.searchandizing.cockpit.component.sectionpanel.SolrSectionPanelRenderer;


public class SolrEditorAreaController extends DefaultEditorAreaController
{
	private static final Logger LOG = Logger.getLogger(SolrEditorAreaController.class);


	private SolrSectionPanelRenderer sectionPanelLabelRenderer;
	private Map<String, String> imageMap;

	@Override
	protected SolrSectionPanelRenderer createSectionPanelLabelRenderer()
	{
		if (sectionPanelLabelRenderer == null)
		{
			sectionPanelLabelRenderer = new SolrSectionPanelRenderer(this.getModel());
		}
		return sectionPanelLabelRenderer;
	}

	@Override
	public void updateLabel(final SectionPanelModel sectionPanelModel)
	{
		if (sectionPanelModel instanceof AbstractSectionPanelModel)
		{
			final TypedObject currentObject = getModel().getCurrentObject();
			if (currentObject != null)
			{
				final LabelService labelService = UISessionUtils.getCurrentSession().getLabelService();
				((AbstractSectionPanelModel) sectionPanelModel).setLabel(labelService.getObjectTextLabel(currentObject));
				((AbstractSectionPanelModel) sectionPanelModel).setImageUrl(getImagePath(currentObject));
				sectionPanelLabelRenderer.setCurrentObject(UISessionUtils.getCurrentSession().getTypeService()
						.wrapItem(currentObject.getObject()));
			}

			((AbstractSectionPanelModel) sectionPanelModel).refreshInfoContainer();
		}
	}

	@Override
	public void updateEditorRequest(final TypedObject typedObject, final PropertyDescriptor descriptor)
	{
		super.updateEditorRequest(typedObject, descriptor);
		final TypedObject current = getModel().getCurrentObject();
		if (current != null && current.getObject() instanceof SolrFacetSearchConfigModel)
		{
			final SectionPanelModel mod = getSectionPanelModel();
			((AbstractSectionPanelModel) mod).refreshInfoContainer();
		}
	}

	public String getImagePath(final TypedObject imagePathObject)
	{
		final String imagePath = imageMap.get(imagePathObject.getType().getCode());
		return imagePath;
	}

	/**
	 * @return the imageMap
	 */
	public Map<String, String> getImageMap()
	{
		return imageMap;
	}

	/**
	 * @param imageMap
	 *           the imageMap to set
	 */
	public void setImageMap(final Map<String, String> imageMap)
	{
		this.imageMap = imageMap;
	}


}
