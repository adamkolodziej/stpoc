/**
 * 
 */
package com.hybris.social.facebook.opengraphmine.btg.operand.types;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Wraps a {@link Set} of Facebook likes to distinguish which operands and operators of <code>BTG</code> expression are
 * available.
 * <p>
 * Likes are really just strings.
 */
public class FacebookLikesSet extends HashSet<String>
{
	public FacebookLikesSet()
	{
		super();
	}

	public FacebookLikesSet(final Collection<? extends String> col)
	{
		super(col);
	}

	public FacebookLikesSet(final int initialCapacity, final float loadFactor)
	{
		super(initialCapacity, loadFactor);
	}

	public FacebookLikesSet(final int initialCapacity)
	{
		super(initialCapacity);
	}
}
