package com.hybris.showcase.servicesshowcase.controllers;

import com.hybris.servicesshowcase.facades.ControlPanelFacade;
import com.hybris.showcase.servicesshowcase.data.ControlPanelData;
import com.hybris.showcase.servicesshowcase.data.ControlPanelMessage;
import com.hybris.showcase.servicesshowcase.data.Severity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by m.golubovic on 24.6.2015..
 */
@Controller
@RequestMapping("/settings")
public class ControlPanelController
{
    @Resource(name="controlPanelFacade")
    private ControlPanelFacade controlPanelFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(final Model model, final HttpServletRequest request)
    {
        model.addAttribute("contextPath", request.getContextPath());
        model.addAttribute("applicableBtgSegments", controlPanelFacade.getApplicableBtgSegmentsMap());
        return "controlpanel";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reload")
    @ResponseBody
    public ControlPanelData doReload(final HttpServletRequest request)
    {
        final String serverName = request.getScheme() + "://" + request.getHeader("Host");
        return controlPanelFacade.getControlPanelData(serverName);
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveAll")
    @ResponseBody
    public ControlPanelData saveAll(@RequestBody final ControlPanelData controlPanelData)
    {
        return controlPanelFacade.saveAll(controlPanelData);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/evaluateSegment")
    @ResponseBody
    public ControlPanelMessage evaluateSegment(@RequestParam(value = "uid", required = true) final String uid)
    {
        return controlPanelFacade.evaluateSegment(uid);
    }

    protected boolean isApplicableSegment(final String uid)
    {
        final Map<String,String> segmentsMap = controlPanelFacade.getApplicableBtgSegmentsMap();
        final String value = segmentsMap.get(uid);
        return (StringUtils.isNotEmpty(value));
    }
}
