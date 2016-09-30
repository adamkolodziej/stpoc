package com.hybris.servicesshowcase;

import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import de.hybris.platform.commerceservices.converter.config.ModifyPopulatorListBeanPostProcessor;
import de.hybris.platform.converters.PopulatorList;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class ServicesModifyPopulatorListBeanPostProcessor extends ModifyPopulatorListBeanPostProcessor implements
		InitializingBean
{
	private static final Logger LOG = Logger.getLogger(ServicesModifyPopulatorListBeanPostProcessor.class);

	@Override
	public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException
	{
		return bean;
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		final Map<String, PopulatorList> beans = getApplicationContext().getBeansOfType(PopulatorList.class);
		final Stopwatch stopWatch = Stopwatch.createUnstarted(Ticker.systemTicker());
		stopWatch.start();
		try
		{
			processAll(beans);
		}
		finally
		{
			stopWatch.stop();
			LOG.info("[" + this.getApplicationContext().getDisplayName() + "] " + this + " Finished Processing [" + beans.size()
					+ "] directives in " + stopWatch.elapsed(TimeUnit.MILLISECONDS) + "ms");
		}

	}

	protected void processAll(final Map<String, PopulatorList> beans)
	{
		final Set<Map.Entry<String, PopulatorList>> entrySet = beans.entrySet();
		for (final Map.Entry<String, PopulatorList> entry : entrySet)
		{
			final String populatorListBeanName = entry.getKey();
			final PopulatorList populatorList = entry.getValue();
			addPopulators(populatorList, populatorListBeanName);
		}
	}

}
