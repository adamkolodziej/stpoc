/**
 * 
 */
package com.hybris.commercesearch.searchandizing.cockpit.component.listview.impl;

import de.hybris.platform.cockpit.components.listview.AbstractListViewAction;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.cronjob.impl.DefaultCronJobService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.solrfacetsearch.indexer.exceptions.IndexUpdateException;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.solrfacetsearch.model.cron.SolrUpdateStopWordsCronJobModel;
import de.hybris.platform.solrfacetsearch.stopwords.services.SolrStopWordsService;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.zul.Menupopup;


/**
 * @author shailesh.gajera
 * 
 */
public abstract class AbstractStopWordsExportJobAction extends AbstractListViewAction
{

	private DefaultCronJobService cronJobService;
	private FlexibleSearchService flexibleSearchService;
	private EnumerationService enumerationService;
	private SolrStopWordsService solrStopWordsService;
	protected TypeService typeService = null;

	private boolean BATCH_STATUS = false;
	protected static final String CURRENT_STATUS_KEY = "currentStatus";

	private final int RUNNING_CRON_JOBS = 1;
	private final int ACTIVE_CRON_JOBS = 2;

	private static final Logger LOG = Logger.getLogger(AbstractFacetIndexJobListAction.class);

	protected static final String ICON_BUSY_RUNNING_JOBS = "cockpit/images/job_busy.gif";
	protected static final String ICON_ACTIVE_JOBS = "cockpit/images/stopWordsExport_play.png";



	protected SolrUpdateStopWordsCronJobModel getModel(final SolrFacetSearchConfigModel solrFacetSearchConfigModel)
	{
		SearchResult<SolrUpdateStopWordsCronJobModel> result = null;
		final String QUERY = "select {pk} from {SolrUpdateStopWordsCronJob} where {active} = ?true AND {solrFacetSearchConfig} =?solrFacetSearchConfig order by {modifiedtime} desc";
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("true", Boolean.TRUE);
		params.put("solrFacetSearchConfig", solrFacetSearchConfigModel);
		result = getFlexibleSearchService().search(QUERY, params);

		if (result.getCount() > 0)
		{
			for (final SolrUpdateStopWordsCronJobModel solrStopWordsJob : result.getResult())
			{
				return solrStopWordsJob;
			}
		}
		return null;
	}


	public int isRunning(final Context context)
	{
		final TypedObject item = context.getItem();
		final SolrFacetSearchConfigModel solrFacetSearchConfigModel = (SolrFacetSearchConfigModel) item.getObject();

		final SolrUpdateStopWordsCronJobModel solrStopWordsJob = getModel(solrFacetSearchConfigModel);


		if (solrStopWordsJob != null)
		{
			BATCH_STATUS = getCronJobService().isRunning(solrStopWordsJob);
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
			//returning Active as it will fire one time batch;
			return ACTIVE_CRON_JOBS;
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

	protected void performStopWordsExportJob(final Context context)
	{
		final TypedObject item = context.getItem();
		final SolrFacetSearchConfigModel solrFacetSearchConfigModel = (SolrFacetSearchConfigModel) item.getObject();

		if (solrFacetSearchConfigModel != null)
		{
			try
			{
				getSolrStopWordsService().updateStopWords(solrFacetSearchConfigModel);
			}
			catch (final IndexUpdateException e)
			{
				// YTODO Auto-generated catch block
				e.printStackTrace();
			}
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

	/**
	 * @return the solrStopWordsService
	 */
	public SolrStopWordsService getSolrStopWordsService()
	{
		return solrStopWordsService;
	}


	/**
	 * @param solrStopWordsService
	 *           the solrStopWordsService to set
	 */
	public void setSolrStopWordsService(final SolrStopWordsService solrStopWordsService)
	{
		this.solrStopWordsService = solrStopWordsService;
	}


	public String getBusyImagePath()
	{
		return ICON_BUSY_RUNNING_JOBS;
	}

	public String getActiveJobsImagePath()
	{
		return ICON_ACTIVE_JOBS;
	}


}
