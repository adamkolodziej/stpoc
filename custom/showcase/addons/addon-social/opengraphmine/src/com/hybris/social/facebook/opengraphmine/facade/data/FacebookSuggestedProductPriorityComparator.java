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
package com.hybris.social.facebook.opengraphmine.facade.data;

import java.util.Comparator;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;


/**
 * @author javier.moreno The products will appear in the facebook carousel in this way:
 * @author kbaranski
 * 
 */
public class FacebookSuggestedProductPriorityComparator implements Comparator<FacebookSuggestedProductData>
{
	public static final int POINTS_PRODUCT_PURCHASED = 3;
	public static final int POINTS_PRODUCT_LIKED = 1;
	public static final int POINTS_PRODUCT_COMMENTED = 2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final FacebookSuggestedProductData faceData1, final FacebookSuggestedProductData faceData2)
	{
		int compareResult = compareBestCandidatesLoggedUser(faceData1, faceData2);
		if (compareResult != 0)
		{
			return compareResult;
		}

		compareResult = compareGeneralScore(faceData1, faceData2);
		if (compareResult != 0)
		{
			return compareResult;
		}

		compareResult = compareBestCandidatesByName(faceData1, faceData2);
		if (compareResult != 0)
		{
			return compareResult;
		}

		return compareProductByName(faceData1, faceData2);
	}

	// the product whose best candidate is not the logged user has preference (i.e. appears earlier in the carousel)
	// if both users or none of them have as best candidate the logged user then the method returns 0
	private int compareBestCandidatesLoggedUser(final FacebookSuggestedProductData faceData1,
			final FacebookSuggestedProductData faceData2)
	{
		if (isBestCandidateLoggedUser(faceData1) && !isBestCandidateLoggedUser(faceData2))
		{
			return 1;
		}
		else if (isBestCandidateLoggedUser(faceData2) && !isBestCandidateLoggedUser(faceData1))
		{
			return -1;
		}
		return 0;
	}

	// compares the "appeal" score of both products
	private int compareGeneralScore(final FacebookSuggestedProductData faceData1, final FacebookSuggestedProductData faceData2)
	{
		final int valueFaceData1 = getValueAccordingToFriendsActions(faceData1);
		final int valueFaceData2 = getValueAccordingToFriendsActions(faceData2);

		return valueFaceData2 - valueFaceData1;

	}

	// compares using the firstname of the products' best candidates
	private int compareBestCandidatesByName(final FacebookSuggestedProductData faceData1,
			final FacebookSuggestedProductData faceData2)
	{

		return faceData1.getBestCandidate().getFirstname().compareTo(faceData2.getBestCandidate().getFirstname());

	}

	// compares using the products' name
	private int compareProductByName(final FacebookSuggestedProductData faceData1, final FacebookSuggestedProductData faceData2)
	{
		if (faceData1.getProduct().getName() != null && faceData2.getProduct().getName() != null)
		{
			return faceData1.getProduct().getName().compareTo(faceData2.getProduct().getName());
		}

		return 0;
	}

	private int size(final Set set)
	{
		if (CollectionUtils.isEmpty(set))
		{
			return 0;
		}
		return CollectionUtils.size(set);
	}

	// this method returns the product's appeal score
	private int getValueAccordingToFriendsActions(final FacebookSuggestedProductData faceData)
	{
		// if best candidate is the logged user then points for purchase are not counted
		if (faceData.getLoggedUser().equals(faceData.getBestCandidate()))
		{
			return size(faceData.getUsersThatLikeIt()) * POINTS_PRODUCT_LIKED + size(faceData.getUsersThatCommentedIt())
					* POINTS_PRODUCT_COMMENTED;
		}
		return size(faceData.getUsersThatLikeIt()) * POINTS_PRODUCT_LIKED + size(faceData.getUsersThatPurchasedIt())
				* POINTS_PRODUCT_PURCHASED + size(faceData.getUsersThatCommentedIt()) * POINTS_PRODUCT_COMMENTED;
	}

	private boolean isBestCandidateLoggedUser(final AbstractFacebookSuggestedData suggestedData)
	{
		return suggestedData.getLoggedUser().equals(suggestedData.getBestCandidate());
	}
}
