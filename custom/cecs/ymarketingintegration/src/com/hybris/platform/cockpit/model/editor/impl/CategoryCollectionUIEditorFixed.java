package com.hybris.platform.cockpit.model.editor.impl;

import de.hybris.platform.cockpit.constants.ImageUrls;
import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.editor.ListUIEditor;
import de.hybris.platform.cockpit.model.editor.UIEditor;
import de.hybris.platform.cockpit.model.editor.impl.GenericCollectionUIEditor;
import de.hybris.platform.cockpit.model.meta.PropertyEditorDescriptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.SimpleListModel;


/**
 * @author marius.bogdan.ionescu@sap.com
 *
 * @see de.hybris.platform.cockpit.model.editor.impl.InitiativeCollectionUIEditorFixed
 *
 */
public class CategoryCollectionUIEditorFixed extends GenericCollectionUIEditor
{

	private PropertyEditorDescriptor singleValueEditorDescriptor;
	private transient Listbox collectionItems;
	private transient List<Object> collectionValues;
	protected static final String WIDTH = "width";
	protected static final String HEIGHT = "height";
	protected static final String SINGLE_VALUE_EDITOR_CODE = "singleValueEditorCode";

	protected static final String _100PERCENT = "100%";
	private List<? extends Object> availableValues;

	@Override
	public void setSingleValueEditorDescriptor(final PropertyEditorDescriptor singleValueEditorDescriptor)
	{
		this.singleValueEditorDescriptor = singleValueEditorDescriptor;
	}

	@Override
	public PropertyEditorDescriptor getSingleValueEditorDescriptor()
	{
		return this.singleValueEditorDescriptor;
	}

	@Override
	public HtmlBasedComponent createViewComponent(final Object initialValue, final Map<String, ? extends Object> parameters,
			final EditorListener listener)
	{
		collectionValues = createNewCollectionValuesList(initialValue);

		final Div listContainer = new Div();
		collectionItems = new Listbox();
		collectionItems.setSclass("collectionUIEditorItems");
		collectionItems.setOddRowSclass("oddRowRowSclass");
		collectionItems.setDisabled(!isEditable());
		collectionItems.setModel(getCollectionSimpleListModel(collectionValues));
		collectionItems.setFixedLayout(false);
		collectionItems.setItemRenderer(createCollectionItemListRenderer(parameters, listener));
		listContainer.appendChild(collectionItems);

		return listContainer;
	}

	@Override
	public void updateCollectionItems()
	{
		collectionValues = new ArrayList<Object>(collectionValues);
		collectionItems.setModel(getCollectionSimpleListModel(collectionValues));
	}

	@Override
	public String getEditorType()
	{
		final PropertyEditorDescriptor desc = getSingleValueEditorDescriptor();
		return desc == null ? null : desc.getEditorType();
	}

	@Override
	public boolean isInline()
	{
		return false;
	}

	@Override
	protected UIEditor createSingleValueEditor(final Map<String, ? extends Object> parameters)
	{

		String editorCode = PropertyEditorDescriptor.SINGLE;
		if (parameters.containsKey(SINGLE_VALUE_EDITOR_CODE))
		{
			editorCode = ObjectUtils.toString(parameters.get(SINGLE_VALUE_EDITOR_CODE));
		}
		return getSingleValueEditorDescriptor().createUIEditor(editorCode);
	}

	@Override
	protected ListitemRenderer createCollectionItemListRenderer(final Map<String, ? extends Object> parameters,
			final EditorListener listener)
	{
		return new ListitemRenderer()
		{
			@Override
			public void render(final Listitem itemRow, final Object value) throws Exception //NOPMD: ZK specific
			{
				if ((value instanceof Collection && ((Collection) value).isEmpty()))
				{
					return;
				}
				else if (value == null)
				{
					// add new value editor
					final UIEditor editor = createSingleValueEditor(parameters);
					editor.setEditable(isEditable());

					if (editor instanceof ListUIEditor && !getAvailableValues().isEmpty())
					{
						((ListUIEditor) editor).setAvailableValues(getAvailableValues());
					}

					Image addImage = null;
					if (isEditable())
					{
						addImage = new Image(ImageUrls.GREEN_ADD_PLUS_IMAGE);
						addImage.setTooltiptext(Labels.getLabel("collectionEditor.button.add.tooltip"));
						addImage.addEventListener(Events.ON_CLICK, new EventListener()
						{
							@Override
							public void onEvent(final Event arg0) throws Exception //NOPMD: ZK specific
							{
								// fake button, see valueChanged() below
							}
						});
					}

					final Component editorView = editor.createViewComponent(value, parameters, new EditorListener()
					{
						@Override
						public void actionPerformed(final String actionCode)
						{
							if (EditorListener.ENTER_PRESSED.equals(actionCode) && editor.getValue() != null)
							{
								addCollectionElement(editor, listener);
							}
						}

						@Override
						public void valueChanged(final Object value)
						{
							if (editor.getValue() != null)
							{
								addCollectionElement(editor, listener);
							}
						}
					});

					itemRow.appendChild(createListCell(editorView, addImage));
				}
				else
				{
					final UIEditor editor = createSingleValueEditor(parameters);
					editor.setEditable(isEditable());

					if (editor instanceof ListUIEditor && !getAvailableValues().isEmpty())
					{
						((ListUIEditor) editor).setAvailableValues(getAvailableValues());
					}

					final Component editorView = editor.createViewComponent(value, parameters, new EditorListener()
					{
						@Override
						public void actionPerformed(final String actionCode)
						{
							// YTODO Auto-generated method stub
						}

						@Override
						public void valueChanged(final Object value)
						{
							if (collectionValues != null && !collectionValues.isEmpty() && itemRow.getIndex() >= 0
									&& collectionValues.size() > itemRow.getIndex())
							{
								if (!collectionValues.get(itemRow.getIndex()).equals(value))
								{
									collectionValues = createNewCollectionValuesList(collectionValues);
								}
								if (value == null)
								{
									collectionValues.remove(itemRow.getIndex());
									collectionValues = createNewCollectionValuesList(collectionValues);
									setValue(collectionValues);
									updateCollectionItems();
								}
								else
								{
									collectionValues.set(itemRow.getIndex(), value);
									setValue(collectionValues);
								}
							}
							listener.valueChanged(getValue());
						}
					});

					Image removeImage = null;
					if (isEditable())
					{
						removeImage = new Image(ImageUrls.REMOVE_BUTTON_IMAGE);
						removeImage.setTooltiptext(Labels.getLabel("collectionEditor.button.remove.tooltip"));
						removeImage.addEventListener(Events.ON_CLICK, new EventListener()
						{
							@Override
							public void onEvent(final Event arg0) throws Exception //NOPMD: ZK specific
							{
								collectionValues.remove(itemRow.getIndex());
								collectionValues = createNewCollectionValuesList(collectionValues);
								setValue(collectionValues);
								updateCollectionItems();
								listener.valueChanged(getValue());
							}
						});
					}
					itemRow.appendChild(createListCell(editorView, removeImage));
				}
			}

			private Listcell createListCell(final Component editorView, final Image image)
			{
				final Listcell cellItem = new Listcell();

				final Div cellContainerDiv = new Div();
				cellContainerDiv.setSclass("collectionUIEditorItem");

				final Div firstCellDiv = new Div();
				firstCellDiv.appendChild(editorView);
				cellContainerDiv.appendChild(firstCellDiv);

				if (image != null)
				{
					firstCellDiv.setSclass("editor");
					final Div secondCellDiv = new Div();
					secondCellDiv.appendChild(image);
					secondCellDiv.setSclass("image");
					cellContainerDiv.appendChild(secondCellDiv);
				}
				cellItem.appendChild(cellContainerDiv);
				return cellItem;
			}
		};
	}

	private SimpleListModel getCollectionSimpleListModel(final List<Object> collectionValues)
	{
		final List<Object> newCollectionValues = new ArrayList<Object>(
				((collectionValues == null) ? Collections.EMPTY_LIST : collectionValues));

		// add empty element to the list for the 'add element' editor
		if ((!newCollectionValues.isEmpty() && newCollectionValues.get(newCollectionValues.size() - 1) != null)
				|| newCollectionValues.isEmpty())
		{
			newCollectionValues.add(null);
		}

		return new SimpleListModel(newCollectionValues);
	}

	private void addCollectionElement(final UIEditor editor, final EditorListener listener)
	{
		collectionValues.add(editor.getValue());
		collectionValues = createNewCollectionValuesList(collectionValues);
		setValue(collectionValues);
		updateCollectionItems();
		listener.valueChanged(getValue());
	}

	@Override
	public List<? extends Object> getAvailableValues()
	{
		return (this.availableValues == null) ? Collections.EMPTY_LIST : this.availableValues;
	}


	@Override
	public void setAvailableValues(final List<? extends Object> availableValues)
	{
		this.availableValues = availableValues;
	}

	@Override
	protected List<Object> createNewCollectionValuesList(final Object values)
	{
		return new ArrayList<Object>(values == null ? Collections.EMPTY_LIST
				: ((values instanceof String) && StringUtils.isEmpty((String) values)) ? Collections.EMPTY_LIST
						: (values instanceof Collection ? (Collection) values : Collections.EMPTY_LIST));
	}
}
