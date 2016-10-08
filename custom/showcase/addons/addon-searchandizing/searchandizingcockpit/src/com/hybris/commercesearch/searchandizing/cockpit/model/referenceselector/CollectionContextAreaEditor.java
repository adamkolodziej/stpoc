/**
 * 
 */
package com.hybris.commercesearch.searchandizing.cockpit.model.referenceselector;

import de.hybris.platform.cockpit.model.editor.AdditionalReferenceEditorListener;
import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.referenceeditor.collection.CollectionEditor;
import de.hybris.platform.cockpit.model.referenceeditor.collection.controller.CollectionSelectorController;
import de.hybris.platform.cockpit.model.referenceeditor.collection.model.CollectionEditorModel;
import de.hybris.platform.cockpit.model.referenceeditor.simple.SimpleReferenceSelector;


/**
 * @author rmcotton
 * 
 */
public class CollectionContextAreaEditor extends CollectionEditor
{
	/**
	 * @param editorListener
	 * @param additionalListener
	 */
	public CollectionContextAreaEditor(final EditorListener editorListener,
			final AdditionalReferenceEditorListener additionalListener)
	{
		super(editorListener, additionalListener);
	}

	@Override
	protected SimpleReferenceSelector initializeReferenceSelector()
	{
		final CollectionEditorModel model = getModel();
		final SimpleReferenceSelector simpleReferenceSelector = new ReferenceSelectorWithContextEditButton(this.editorListener)
		{
			@Override
			public void updateItems()
			{
				//	NOP
			}

			@Override
			public void saveCurrentValue(final Object currentValue)
			{
				fireSaveActualItem(currentValue);
			}

			@Override
			public boolean isEntrySelected(final Object value)
			{
				return model.getCollectionItems().contains(value);
			}
		};
		simpleReferenceSelector.setAutocompletionAllowed(isAutoCompletionAllowed());
		simpleReferenceSelector.setAllowcreate(isAllowCreate());
		simpleReferenceSelector.setCreateContext(getCreateContext());
		simpleReferenceSelector.setWidth(_100PERCENT);
		simpleReferenceSelector.setShowEditButton(false);
		simpleReferenceSelector.setDisabled(isDisabled());
		simpleReferenceSelector.setModel(getModel().getSimpleReferenceSelectorModel());

		selectorController = new CollectionSelectorController(getModel().getSimpleReferenceSelectorModel(), getModel(),
				simpleReferenceSelector, editorListener, this.additionalListener);
		selectorController.initialize();
		return simpleReferenceSelector;
	}

	protected boolean isAutoCompletionAllowed()
	{
		return getParameters().containsKey("autocomplete") ? Boolean.TRUE.equals(getParameters().get("autocomplete")) : false;
	}


}
