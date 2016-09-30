package com.hybris.mb2bshowcase.c4cintegration.services;

/**
 * Created by Dominik Strzyzewski on 2014-12-03.
 */
public class C4CServiceException extends RuntimeException {

	private static final long serialVersionUID = 8882934708869933005L;

    public C4CServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}
}