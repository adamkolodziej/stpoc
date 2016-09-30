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
public class NameEntitlementDataComparator implements Comparator<EntitlementData>
{
	@Override
	public int compare(final EntitlementData o1, final EntitlementData o2)
	{
		return o1.getName().compareTo(o2.getName());
	}
}