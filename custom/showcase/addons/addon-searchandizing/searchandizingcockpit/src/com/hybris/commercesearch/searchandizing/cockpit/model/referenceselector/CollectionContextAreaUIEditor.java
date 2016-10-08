/**
 * 
 */
package com.hybris.commercesearch.searchandizing.cockpit.model.referenceselector;

import de.hybris.platform.cockpit.model.editor.AdditionalReferenceEditorListener;
import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.referenceeditor.collection.CollectionEditor;
import de.hybris.platform.cockpit.model.referenceeditor.collection.CollectionUIEditor;


/**
 * @author rmcotton
 * 
 */
public class CollectionContextAreaUIEditor extends CollectionUIEditor
{
	@Override
	public CollectionEditor createCollectionEditor(final EditorListener listener,
			final AdditionalReferenceEditorListener additionalListener)
	{

		return new CollectionContextAreaEditor(listener, additionalListener);

	}
}
