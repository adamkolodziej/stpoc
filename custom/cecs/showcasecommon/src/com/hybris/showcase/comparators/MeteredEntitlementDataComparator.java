/**
 * 
 */
package com.hybris.showcase.comparators;

import de.hybris.platform.entitlementfacades.data.EntitlementData;

import java.util.Comparator;


/**
 * @author I307113
 * 
 */
public class MeteredEntitlementDataComparator implements Comparator<EntitlementData>
{
	@Override
	public int compare(final EntitlementData o1, final EntitlementData o2)
	{
		return -1 * (o1.getRemainingQuantity() - o2.getRemainingQuantity());
	}
}