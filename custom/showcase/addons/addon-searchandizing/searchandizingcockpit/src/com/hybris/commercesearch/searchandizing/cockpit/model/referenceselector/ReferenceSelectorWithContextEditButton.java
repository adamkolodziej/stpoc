/**
 * 
 */
package com.hybris.commercesearch.searchandizing.cockpit.model.referenceselector;

import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.referenceeditor.simple.SimpleReferenceSelector;

import java.util.LinkedList;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Toolbarbutton;


/**
 *
 */
public class ReferenceSelectorWithContextEditButton extends SimpleReferenceSelector
{

	private final static String OPEN_BROWSER_EDITOR_IMG = "cockpit/images/icon_func_open_list.png";

	private final EditorListener editorListerner;

	public ReferenceSelectorWithContextEditButton(final EditorListener editorListener)
	{
		this.editorListerner = editorListener;
	}


	@Override
	public boolean initialize()
	{

		final boolean result = super.initialize();

		final Toolbarbutton openContextEditorButton = new Toolbarbutton("", OPEN_BROWSER_EDITOR_IMG);

		openContextEditorButton.setTooltiptext(Labels.getLabel("editor.button.referenceeditor.open.tooltip"));
		openContextEditorButton.setStyle("padding-top:3px; padding-left:2px; float: right;");

		final List<Toolbarbutton> buttonsToPosition = new LinkedList<Toolbarbutton>();
		for (final HtmlBasedComponent component : (List<HtmlBasedComponent>) mainHbox.getChildren())
		{
			if (component instanceof Toolbarbutton)
			{
				buttonsToPosition.add((Toolbarbutton) component);
			}
		}

		if (!isAutocompletionAllowed())
		{
			mainHbox.removeChild(componentContainer);
		}

		for (final Toolbarbutton buttonToRemove : buttonsToPosition)
		{
			mainHbox.removeChild(buttonToRemove);
		}

		mainHbox.appendChild(openContextEditorButton);
		for (final Toolbarbutton buttonToReturn : buttonsToPosition)
		{
			mainHbox.appendChild(buttonToReturn);
		}

		openContextEditorButton.addEventListener(Events.ON_CLICK, new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws Exception //NOPMD: ZK Specific
			{

				editorListerner.actionPerformed(EditorListener.OPEN_EXTERNAL_CLICKED);
			}
		});


		return result;
	}



}
