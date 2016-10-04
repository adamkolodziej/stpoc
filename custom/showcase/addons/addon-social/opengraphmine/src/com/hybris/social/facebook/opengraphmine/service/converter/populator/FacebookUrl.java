package com.hybris.social.facebook.opengraphmine.service.converter.populator;

import com.restfb.Facebook;

public class FacebookUrl{
    @Facebook("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}