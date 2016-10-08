/**
 * 
 */
package com.hybris.commercesearch.searchandizing.cockpit.session.impl;

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.session.BrowserModel;
import de.hybris.platform.cockpit.session.EditorAreaListener;
import de.hybris.platform.cockpit.session.impl.BaseUICockpitPerspective;
import de.hybris.platform.cockpit.session.impl.BrowserAreaListener;
import de.hybris.platform.cockpit.session.impl.DefaultBrowserAreaListener;
import de.hybris.platform.cockpit.session.impl.DefaultEditorAreaListener;


/**
 * @author rmcotton
 * 
 */
public class SolrAdminPerspective extends BaseUICockpitPerspective
{
	private BrowserAreaListener browserAreaListener;
	private EditorAreaListener editorAreaListener;
	private SolrItemActivationHandler itemActivationHandler;

	@Override
	protected EditorAreaListener getEditorAreaListener()
	{
		if (this.editorAreaListener == null)
		{
			this.editorAreaListener = new DefaultEditorAreaListener(this)
			{
				@Override
				public void currentObjectChanged(final TypedObject previous, final TypedObject current)
				{
					if (current != null)
					{
						handleItemChange(current);
					}
					super.currentObjectChanged(previous, current);

				}

				@Override
				public void currentObjectUpdated()
				{
					handleItemChange(getEditorArea().getCurrentObject());
				}
			};
		}

		return this.editorAreaListener;
	}

	@Override
	protected BrowserAreaListener getBrowserAreaListener()
	{
		if (this.browserAreaListener == null)
		{
			this.browserAreaListener = new DefaultBrowserAreaListener(this)
			{
				@Override
				public void browserFocused(final BrowserModel browserModel)
				{
					// do nothing
				}


			};
		}
		return this.browserAreaListener;
	}

	protected void handleItemChange(final TypedObject activeItem)
	{
		if (getItemActivationHandler() != null)
		{
			getItemActivationHandler().handleItemActivated(activeItem);
		}
	}

	/**
	 * @return the itemActivationHandler
	 */
	public SolrItemActivationHandler getItemActivationHandler()
	{
		return itemActivationHandler;
	}

	/**
	 * @param itemActivationHandler
	 *           the itemActivationHandler to set
	 */
	public void setItemActivationHandler(final SolrItemActivationHandler itemActivationHandler)
	{
		this.itemActivationHandler = itemActivationHandler;
	}


}
