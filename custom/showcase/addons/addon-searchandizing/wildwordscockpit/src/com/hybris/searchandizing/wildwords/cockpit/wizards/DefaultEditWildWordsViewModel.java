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
package com.hybris.searchandizing.wildwords.cockpit.wizards;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.classification.ClassificationSystemVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cmscockpit.components.liveedit.LiveEditViewModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.cronjob.impl.DefaultCronJobService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfigService;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.enums.IndexerOperationValues;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.indexer.cron.SolrIndexerCronJobModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.zkoss.spring.SpringUtil;

import com.hybris.searchandizing.wildwords.cockpit.model.KeywordRedirectListModel;
import com.hybris.searchandizing.wildwords.cockpit.model.KeywordRedirectValueModel;
import com.hybris.searchandizing.wildwords.cockpit.model.StopWordListModel;
import com.hybris.searchandizing.wildwords.cockpit.model.StopWordValueModel;
import com.hybris.searchandizing.wildwords.cockpit.model.SynonymListModel;
import com.hybris.searchandizing.wildwords.cockpit.model.SynonymValueModel;
import com.hybris.searchandizing.wildwords.cockpit.model.WildWordsListModel;
import com.hybris.searchandizing.wildwords.cockpit.model.WildWordsValueModel;
import com.hybris.searchandizing.wildwords.cockpit.wizards.events.WildWordsListener;
import com.hybris.searchandizing.wildwords.service.WildWordsAdminService;



/**
 * @author piotr.kalinowski
 * 
 */
public class DefaultEditWildWordsViewModel implements EditWildWordsViewModel
{
	private final String INDEXER_TYPE = "FULL";

	private LanguageModel language;
	private SolrFacetSearchConfigModel solrFacetSearchConfigModel;
	private LiveEditViewModel liveEditViewModel;

	private KeywordRedirectListModel keywordRedirectListModel;
	private SynonymListModel synonymListModel;
	private StopWordListModel stopWordListModel;

	private ModelService modelService;
	private DefaultCronJobService cronJobService;
	private CommonI18NService commonI18NService;
	private FlexibleSearchService flexibleSearchService;
	private FacetSearchConfigService facetSearchConfigService;
	private WildWordsAdminService wildWordsAdminSearvice;

	private final List<WildWordsListener> listeners = new LinkedList<WildWordsListener>();

	@Override
	public void initModel(final LiveEditViewModel liveEditViewModel, final LanguageModel language)
	{
		this.language = language;
		this.liveEditViewModel = liveEditViewModel;
		this.solrFacetSearchConfigModel = getWildWordsAdminService().getSolrFacetSearchConfigModel(getFacetSearchConfig());

		newKeywordRedirectListModel();
		newSynonymListModel();
		newStopWordsListModel();
	}

	protected void newKeywordRedirectListModel()
	{
		keywordRedirectListModel = new KeywordRedirectListModel(new ArrayList(getWildWordsAdminService()
				.getKeywordRedirectModelList(getFacetSearchConfig(), getCommonI18NService().getLocaleForLanguage(language))));
	}

	protected void newSynonymListModel()
	{
		synonymListModel = new SynonymListModel(getWildWordsAdminService().getSynonyms(getFacetSearchConfig(),
				getCommonI18NService().getLocaleForLanguage(language)));
	}

	protected void newStopWordsListModel()
	{
		stopWordListModel = new StopWordListModel(getWildWordsAdminService().getStopWords(getFacetSearchConfig(),
				getCommonI18NService().getLocaleForLanguage(language)));
	}

	@Override
	public void fireInitEvents()
	{
		for (final WildWordsListener listener : getListeners())
		{
			listener.onLoad();
		}
	}

	@Override
	public void fireChangeEvents()
	{
		for (final WildWordsListener listener : getListeners())
		{
			listener.onChange();
		}
	}

	@Override
	public void fireSaveEvents()
	{
		for (final WildWordsListener listener : getListeners())
		{
			listener.onSave();
		}
	}

	@Override
	public void fireErrorEvents(final String message)
	{
		for (final WildWordsListener listener : getListeners())
		{
			listener.onError(message);
		}
	}

	@Override
	public void reset()
	{
		newKeywordRedirectListModel();
		newSynonymListModel();
		newStopWordsListModel();

		fireInitEvents();
	}

	@Override
	public boolean save()
	{
		if (validate(keywordRedirectListModel) && validate(synonymListModel) && validate(stopWordListModel))
		{
			saveListModel(keywordRedirectListModel);
			saveListModel(synonymListModel);
			saveListModel(stopWordListModel);

			fireSaveEvents();
			return true;
		}
		else
		{
			fireErrorEvents("You have to fulfil all required values.");
			return false;
		}

	}

	public void saveListModel(final WildWordsListModel listModel)
	{
		for (final WildWordsValueModel value : listModel.getValues())
		{
			if (value.isChanged())
			{
				value.save(solrFacetSearchConfigModel, language);
				getModelService().save(value.getModel());
				value.setChanged(false);
			}
		}
		final Iterator<ItemModel> it = listModel.getModelsToRemove().iterator();
		while (it.hasNext())
		{
			getModelService().remove(it.next());
			it.remove();
		}
		getModelService().save(solrFacetSearchConfigModel);
	}

	protected boolean validate(final WildWordsListModel listModel)
	{
		for (final WildWordsValueModel value : listModel.getValues())
		{
			if (!value.isValid())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void addNewValue(final KeywordRedirectValueModel value)
	{
		getKeywordRedirectListModel().getValues().add(value);
		fireChangeEvents();
	}

	@Override
	public void addNewValue(final SynonymValueModel value)
	{
		getSynonymListModel().getValues().add(value);
		fireChangeEvents();
	}

	@Override
	public void addNewValue(final StopWordValueModel value)
	{
		getStopWordListModel().getValues().add(value);
		fireChangeEvents();
	}

	@Override
	public FacetSearchConfig getFacetSearchConfig()
	{
		try
		{
			final LiveEditViewModel liveEditView = getLiveEditViewModel();
			for (final CatalogVersionModel catalogVersion : liveEditView.getCurrentPreviewData().getCatalogVersions())
			{
				if (!((catalogVersion.getCatalog() instanceof ContentCatalogModel) || (catalogVersion instanceof ClassificationSystemVersionModel)))
				{
					final FacetSearchConfig facetSearchConfig = getFacetSearchConfigService().getConfiguration(catalogVersion);
					if (facetSearchConfig != null)
					{
						return facetSearchConfig;
					}
				}
			}
		}
		catch (final FacetConfigServiceException fse)
		{
			throw new IllegalStateException(fse);
		}
		return null;
	}

	@Override
	public boolean isChanged()
	{
		return keywordRedirectListModel.isChanged() || synonymListModel.isChanged() || stopWordListModel.isChanged();
	}

	@Override
	public void registerListener(final WildWordsListener listener)
	{
		this.listeners.add(listener);
	}

	public List<WildWordsListener> getListeners()
	{
		return this.listeners;
	}

	protected LiveEditViewModel getLiveEditViewModel()
	{
		return this.liveEditViewModel;
	}

	@Override
	public void startFullIndexJob()
	{
		final SolrIndexerCronJobModel cronJobModel = getSolrIndexerCronJobModel(solrFacetSearchConfigModel, INDEXER_TYPE);
		if (cronJobModel != null)
		{
			getCronJobService().performCronJob(cronJobModel, true);
		}
	}

	protected SolrIndexerCronJobModel getSolrIndexerCronJobModel(final SolrFacetSearchConfigModel solrFacetSearchConfigModel,
			final String INDEXER_TYPE)
	{
		SearchResult<SolrIndexerCronJobModel> result = null;
		final String QUERY = "select {pk} from {SolrIndexerCronJob} where {active} = ?true  and {facetSearchConfig} = ?facetSearchConfig and {indexerOperation} = ?indexerOperation order by {modifiedtime} desc";
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("true", Boolean.TRUE);
		params.put("facetSearchConfig", solrFacetSearchConfigModel);

		if ("FULL".equals(INDEXER_TYPE))
		{
			params.put("indexerOperation", IndexerOperationValues.FULL);
		}
		else
		{
			params.put("indexerOperation", IndexerOperationValues.UPDATE);
		}


		result = getFlexibleSearchService().search(QUERY, params);

		if (result.getCount() > 0)
		{
			for (final SolrIndexerCronJobModel cronJobModel : result.getResult())
			{
				return cronJobModel;
			}
		}
		return null;
	}

	protected FlexibleSearchService getFlexibleSearchService()
	{
		if (flexibleSearchService == null)
		{
			flexibleSearchService = (FlexibleSearchService) SpringUtil.getBean("flexibleSearchService");
		}
		return flexibleSearchService;
	}

	protected FacetSearchConfigService getFacetSearchConfigService()
	{
		if (facetSearchConfigService == null)
		{
			facetSearchConfigService = (FacetSearchConfigService) SpringUtil.getBean("facetSearchConfigService");
		}
		return facetSearchConfigService;
	}

	protected ModelService getModelService()
	{
		if (modelService == null)
		{
			modelService = (ModelService) SpringUtil.getBean("modelService");
		}
		return modelService;
	}

	@Override
	public KeywordRedirectListModel getKeywordRedirectListModel()
	{
		return keywordRedirectListModel;
	}

	@Override
	public SynonymListModel getSynonymListModel()
	{
		return synonymListModel;
	}

	@Override
	public StopWordListModel getStopWordListModel()
	{
		return stopWordListModel;
	}

	@Override
	public WildWordsAdminService getWildWordsAdminService()
	{
		if (this.wildWordsAdminSearvice == null)
		{
			wildWordsAdminSearvice = (WildWordsAdminService) SpringUtil.getBean("wildWordsAdminService");
		}
		return wildWordsAdminSearvice;
	}

	protected DefaultCronJobService getCronJobService()
	{
		if (cronJobService == null)
		{
			cronJobService = (DefaultCronJobService) SpringUtil.getBean("cronJobService");
		}
		return cronJobService;
	}

	protected CommonI18NService getCommonI18NService()
	{
		if (commonI18NService == null)
		{
			commonI18NService = (CommonI18NService) SpringUtil.getBean("commonI18NService");
		}
		return commonI18NService;
	}
}
