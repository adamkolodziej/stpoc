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
public class StatusEntitlementDataComparator implements Comparator<EntitlementData>
{
	@Override
	public int compare(final EntitlementData o1, final EntitlementData o2)
	{
		return o1.getStatus().compareTo(o2.getStatus());
	}
}