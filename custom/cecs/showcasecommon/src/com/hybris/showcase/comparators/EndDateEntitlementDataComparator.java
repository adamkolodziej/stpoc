/**
 * 
 */
package com.hybris.showcase.comparators;

import de.hybris.platform.entitlementfacades.data.EntitlementData;

import java.util.Comparator;

import org.apache.commons.lang.ObjectUtils;



/**
 * @author I307113
 * 
 */
public class EndDateEntitlementDataComparator implements Comparator<EntitlementData>
{
	@Override
	public int compare(final EntitlementData o1, final EntitlementData o2)
	{
		return ObjectUtils.compare(o1.getEndTime(), o2.getEndTime());
	}
}