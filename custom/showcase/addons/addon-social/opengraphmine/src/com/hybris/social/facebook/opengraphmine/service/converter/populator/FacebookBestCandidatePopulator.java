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
package com.hybris.social.facebook.opengraphmine.service.converter.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.HashMap;
import java.util.Map;

import com.hybris.social.facebook.opengraphmine.facade.data.FacebookSuggestedProductData;
import com.hybris.social.facebook.opengraphmine.facade.data.FacebookUserData;


public class FacebookBestCandidatePopulator implements Populator<Object, FacebookSuggestedProductData>
{
	private static final int SCORE_PURCHASE = 3;
	private static final int SCORE_LIKE = 1;
	private static final int SCORE_COMMENT = 2;

	@Override
	public void populate(final Object source, final FacebookSuggestedProductData target) throws ConversionException
	{
		calculateBestCandidate(target);
		setBooleanFlags(target);
	}

	/*
	 * There are some situations in which a product has more than one user that likes, has purchased or commented it.
	 * Since no more than one user (his profile icon and name) can be shown related to that product, a best candidate
	 * needs to be selected. The preference is calculated as follows: 1) User that purchased, commented and liked the
	 * product 2) If none satisfies rule 1, then user that purchased and commented the product 3) If none was found, user
	 * that purchased the product. Then user that commented and liked the product...
	 * 
	 * Two things to take into account: 1) The preference is given always to not logged users. If a product is purchased,
	 * liked and commented by the logged user and there is another user that happens just to have purchased or liked or
	 * commented it, then that user takes preference. 2) As last resort, the candidates' firstname is used: let's say
	 * user Alex and user John had both the same score (both purchased, or both liked and commented), it would be
	 * selected as best candidate that with lower (alphabetical) firstname (in this case Alex)
	 */
	protected void calculateBestCandidate(final FacebookSuggestedProductData suggestion)
	{

		final Map<FacebookUserData, Integer> mapFacebookUserScore = new HashMap<FacebookUserData, Integer>();

		// users that purchased the product are the most important
		if (suggestion.getUsersThatPurchasedIt() != null)
		{
			for (final FacebookUserData facebookUserData : suggestion.getUsersThatPurchasedIt())
			{
				mapFacebookUserScore.put(facebookUserData, Integer.valueOf(SCORE_PURCHASE));
			}
		}

		// then users that Commented
		if (suggestion.getUsersThatCommentedIt() != null)
		{
			for (final FacebookUserData facebookUserData : suggestion.getUsersThatCommentedIt())
			{
				final Integer tmpPriorityFacebookUser = mapFacebookUserScore.get(facebookUserData);
				mapFacebookUserScore.put(
						facebookUserData,
						tmpPriorityFacebookUser == null ? Integer.valueOf(SCORE_COMMENT) : Integer.valueOf(tmpPriorityFacebookUser
								.intValue() + SCORE_COMMENT));
			}
		}

		// then the ones that like the product
		if (suggestion.getUsersThatLikeIt() != null)
		{
			for (final FacebookUserData facebookUserData : suggestion.getUsersThatLikeIt())
			{
				final Integer tmpPriorityFacebookUser = mapFacebookUserScore.get(facebookUserData);
				mapFacebookUserScore.put(
						facebookUserData,
						tmpPriorityFacebookUser == null ? Integer.valueOf(SCORE_LIKE) : Integer.valueOf(tmpPriorityFacebookUser
								.intValue() + SCORE_LIKE));
			}
		}

		// if there is any single friend that liked, purchased or commented the product
		// the logged user will not be taken into consideration to become a candidate
		if (mapFacebookUserScore.size() > 1)
		{
			// removes from the candidate map the logged ser
			mapFacebookUserScore.remove(suggestion.getLoggedUser());
		}

		int currentPriority = -1;
		FacebookUserData currentBestCandidate = null;

		for (final FacebookUserData tmpCandidate : mapFacebookUserScore.keySet())
		{
			final int tmpPriority = mapFacebookUserScore.get(tmpCandidate).intValue();
			// if more priority or same one but user with lower firtname (alphabetical)
			if (tmpPriority > currentPriority || (tmpPriority == currentPriority)
					&& tmpCandidate.getFirstname().compareTo(currentBestCandidate.getFirstname()) < 0)
			{
				currentPriority = tmpPriority;
				currentBestCandidate = tmpCandidate;
			}
		}
		suggestion.setBestCandidate(currentBestCandidate);
	}

	protected void setBooleanFlags(final FacebookSuggestedProductData suggestion)
	{
		suggestion.setLikedByBestCandidate(suggestion.getUsersThatLikeIt().contains(suggestion.getBestCandidate()));
		suggestion.setPurchasedByBestCandidate(suggestion.getUsersThatPurchasedIt().contains(suggestion.getBestCandidate()));
		suggestion.setCommentedByBestCandidate(suggestion.getUsersThatCommentedIt().contains(suggestion.getBestCandidate()));
	}
}
