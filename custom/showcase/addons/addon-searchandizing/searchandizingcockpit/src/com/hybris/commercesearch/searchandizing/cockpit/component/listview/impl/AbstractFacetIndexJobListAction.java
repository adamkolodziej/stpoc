package com.hybris.commercesearch.searchandizing.cockpit.component.listview.impl;

import de.hybris.platform.cockpit.components.listview.AbstractListViewAction;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.cronjob.impl.DefaultCronJobService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.solrfacetsearch.enums.IndexerOperationValues;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.indexer.cron.SolrIndexerCronJobModel;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.zul.Menupopup;


/**
 * @author Shailesh
 */
public abstract class AbstractFacetIndexJobListAction extends AbstractListViewAction
{
	private DefaultCronJobService cronJobService;
	private FlexibleSearchService flexibleSearchService;
	private EnumerationService enumerationService;
	protected TypeService typeService = null;

	private boolean BATCH_STATUS = false;
	protected static final String CURRENT_STATUS_KEY = "currentStatus";

	private final int RUNNING_CRON_JOBS = 1;
	private final int ACTIVE_CRON_JOBS = 2;
	private final int UNKNOWN_CRON_JOBS = 0;

	private static final Logger LOG = Logger.getLogger(AbstractFacetIndexJobListAction.class);


	protected SolrIndexerCronJobModel getModel(final SolrFacetSearchConfigModel solrFacetSearchConfigModel,
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


	public int isRunning(final Context context, final String INDEXER_TYPE)
	{
		final TypedObject item = context.getItem();
		final SolrFacetSearchConfigModel solrFacetSearchConfigModel = (SolrFacetSearchConfigModel) item.getObject();

		final SolrIndexerCronJobModel cronJobModel = getModel(solrFacetSearchConfigModel, INDEXER_TYPE);


		if (cronJobModel != null)
		{
			BATCH_STATUS = getCronJobService().isRunning(cronJobModel);
			if (BATCH_STATUS)
			{
				return RUNNING_CRON_JOBS;
			}
			else
			{
				return ACTIVE_CRON_JOBS;
			}
		}
		else
		{
			return UNKNOWN_CRON_JOBS;
		}

	}

	protected int getCurrentStatus(final Context context)
	{
		final Object object = context.getMap().get(CURRENT_STATUS_KEY);
		if (object instanceof Integer)
		{
			return ((Integer) object).intValue();
		}
		return 0;
	}

	protected void startCronJob(final Context context, final String INDEXER_TYPE)
	{
		final TypedObject item = context.getItem();
		final SolrFacetSearchConfigModel solrFacetSearchConfigModel = (SolrFacetSearchConfigModel) item.getObject();

		final SolrIndexerCronJobModel cronJobModel = getModel(solrFacetSearchConfigModel, INDEXER_TYPE);
		if (cronJobModel != null)
		{
			getCronJobService().performCronJob(cronJobModel, true);
		}
	}

	protected boolean checkTypeRestriction(final Context context)
	{
		boolean ret = false;
		try
		{
			ret = getTypeRestriction() == null
					|| getTypeService().getBaseType(getTypeRestriction()).isAssignableFrom(context.getItem().getType());
		}
		catch (final Exception e)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.error(e.getMessage());
			}
		}
		return ret;
	}

	protected String getTypeRestriction()
	{
		return SolrFacetSearchConfigModel._TYPECODE;
	}

	protected TypeService getTypeService()
	{
		if (this.typeService == null)
		{
			this.typeService = UISessionUtils.getCurrentSession().getTypeService();
		}

		return this.typeService;
	}


	@Override
	public Menupopup getContextPopup(final Context context)
	{
		return null;
	}

	@Override
	public Menupopup getPopup(final Context context)
	{
		return null;
	}


	protected DefaultCronJobService getCronJobService()
	{
		return cronJobService;
	}

	@Required
	public void setCronJobService(final DefaultCronJobService cronJobService)
	{
		this.cronJobService = cronJobService;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	/**
	 * @return the enumerationService
	 */
	public EnumerationService getEnumerationService()
	{
		return enumerationService;
	}

	/**
	 * @param enumerationService
	 *           the enumerationService to set
	 */
	public void setEnumerationService(final EnumerationService enumerationService)
	{
		this.enumerationService = enumerationService;
	}

}
