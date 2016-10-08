/**
 * 
 */
package com.hybris.productlists.comparators;

import java.util.Comparator;

import com.hybris.productlists.model.ProductListEntryModel;


/**
 * @author craig.wayman
 * 
 */
public class AscPriorityComparator implements Comparator<ProductListEntryModel>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final ProductListEntryModel o1, final ProductListEntryModel o2)
	{
		if (o1 == null || (o2 != null && o2.getPriority() == null))
		{
			return -1;
		}
		else if (o2 == null || o1.getPriority() == null)
		{
			return 1;
		}
		else
		{

			if (o1.getPriority().intValue() < o2.getPriority().intValue())
			{
				return -1;
			}
			else if (o1.getPriority().intValue() > o2.getPriority().intValue())
			{
				return 1;
			}
			else
			{
				return 0;
			}

		}

	}

}
