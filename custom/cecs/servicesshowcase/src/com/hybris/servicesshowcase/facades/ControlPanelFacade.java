package com.hybris.servicesshowcase.facades;

import com.hybris.showcase.servicesshowcase.data.ControlPanelData;
import com.hybris.showcase.servicesshowcase.data.ControlPanelMessage;

import java.util.Map;

/**
 * Created by m.golubovic on 25.6.2015..
 */
public interface ControlPanelFacade
{
    ControlPanelData getControlPanelData(final String serverName);

    ControlPanelData saveAll(final ControlPanelData controlPanelData);

    Map<String,String> getApplicableBtgSegmentsMap();

    ControlPanelMessage evaluateSegment(final String uid);
}
