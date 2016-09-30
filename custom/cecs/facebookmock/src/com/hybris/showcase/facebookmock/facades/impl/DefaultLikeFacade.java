package com.hybris.showcase.facebookmock.facades.impl;

import com.hybris.showcase.facebookmock.facades.LikeFacade;

/**
 * Created by mgolubovic on 24.4.2015..
 */
public class DefaultLikeFacade implements LikeFacade
{
    @Override
    public String like(String wasLiked)
    {
        return "OK";
    }
}
