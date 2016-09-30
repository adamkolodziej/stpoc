package com.hybris.platform.cockpit.model.editor.impl;

import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.editor.impl.AbstractTextBasedUIEditor;
import de.hybris.platform.cockpit.model.editor.impl.AbstractUIEditor;
import de.hybris.platform.cockpit.model.meta.PropertyDescriptor;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.util.UITools;
import de.hybris.platform.util.localization.Localization;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;

import com.hybris.showcase.cecs.ymarketingintegration.enums.SAPCategory;


/**
 * Simple text editor.
 *
 * @author marius.bogdan.ionescu@sap.com
 * @see de.hybris.platform.cockpit.model.editor.impl.DefaultSAPInitiativeUIEditor
 */
public class DefaultSAPCategoryUIEditor extends AbstractTextBasedUIEditor
{
	private static final Logger LOG = Logger.getLogger(DefaultSAPCategoryUIEditor.class); // NOPMD
	@SuppressWarnings("unused")
	private static final String EDITOR_TOOLTIP_PARAM = "editorTooltip";
	private static final String ENUM_EDITOR_SCLASS = "enumEditor";
	private static final String i18nPREFIX = "type.SAPCategory.";

	private List<? extends Object> availableValues;
	private List<? extends Object> originalAvailableValues;
	private final Combobox editorView = new Combobox();
	private String searchString = "";
	private String lastSearch;

	//Add a single search result to the drop down list box.
	protected Comboitem addcategoryToCombo(final SAPCategory category, final Combobox box)
	{

		final String value = category.toString();
		String label = value + " " + " (" + getCategoryDesc(value) + ")";

		final Comboitem comboitem = new Comboitem();

		if (label == null || label.isEmpty())
		{
			label = "unknown";
		}
		comboitem.setLabel(label);
		comboitem.setValue(value);
		comboitem.setTooltiptext(label);
		box.appendChild(comboitem);
		return comboitem;
	}


	private String getCategoryDesc(final String cat)
	{
		return Localization.getLocalizedString(i18nPREFIX + cat);
	}

	protected int getPosition(final Object item)
	{
		int index = -1;
		if (availableValues != null)
		{
			index = getAvailableValues().indexOf(item);
		}
		return index;
	}

	protected void setEnumValue(final Combobox combo, final Object value)
	{
		final int index = getPosition(value);
		if (index >= 0)
		{
			combo.setSelectedIndex(index);
		}
	}

	@Override
	public HtmlBasedComponent createViewComponent(final Object initialValue, final Map<String, ? extends Object> parameters,
			final EditorListener listener)
	{
		parseInitialInputString(parameters);
		SAPCategory category = null;

		editorView.setConstraint("strict");
		editorView.setSclass("category-combo");
		editorView.setAutodrop(true);

		final String intialValueString = (String) initialValue;
		if (intialValueString != null && !intialValueString.isEmpty())
		{

			category = SAPCategory.valueOf(intialValueString);
		}

		if (isEditable())
		{
			if (category != null)
			{
				final Comboitem item = addcategoryToCombo(category, editorView);
				editorView.setSelectedItem(item);
			}

			final CancelButtonContainer ret = new CancelButtonContainer(listener, new CancelListener()
			{
				@Override
				public void cancelPressed()
				{
					setEnumValue(editorView, initialEditValue);
					setValue(initialEditValue);
					fireValueChanged(listener);
					listener.actionPerformed(EditorListener.ESCAPE_PRESSED);
				}
			});

			ret.setSclass(ENUM_EDITOR_SCLASS);
			ret.setContent(editorView);


			editorView.addEventListener(Events.ON_FOCUS, new EventListener()
			{

				@Override
				public void onEvent(final Event event) throws Exception
				{
					if (editorView.getSelectedItem() != null)
					{
						initialEditValue = editorView.getSelectedItem().getValue();
					}
					ret.showButton(Boolean.TRUE.booleanValue());
				}
			});

			editorView.addEventListener(Events.ON_CHANGE, new EventListener()
			{
				@Override
				public void onEvent(final Event arg0) throws Exception // NOPMD zk specific
				{
					validateAndFireEvent(listener);
				}
			});
			editorView.addEventListener(Events.ON_BLUR, new EventListener()
			{
				@Override
				public void onEvent(final Event arg0) throws Exception // NOPMD zk specific
				{
					ret.showButton(Boolean.FALSE.booleanValue());
				}
			});
			editorView.addEventListener(Events.ON_OK, new EventListener()
			{
				@Override
				public void onEvent(final Event arg0) throws Exception // NOPMD zk specific
				{
					validateAndFireEvent(listener);
					listener.actionPerformed(EditorListener.ENTER_PRESSED);
				}
			});
			editorView.addEventListener(Events.ON_CANCEL, new EventListener()
			{
				@Override
				public void onEvent(final Event arg0) throws Exception // NOPMD zk specific
				{
					ret.showButton(Boolean.FALSE.booleanValue());
					DefaultSAPCategoryUIEditor.this.setEnumValue(editorView, initialEditValue);
					setValue(initialEditValue);
					fireValueChanged(listener);
					listener.actionPerformed(EditorListener.ESCAPE_PRESSED);
				}
			});

			editorView.addEventListener(Events.ON_CHANGING, new EventListener()
			{
				@Override
				public void onEvent(final Event event) throws Exception // NOPMD zk specific
				{
					ret.showButton(true);
					handleChangingEvents(listener, event);
				}
			});


			if (UISessionUtils.getCurrentSession().isUsingTestIDs())
			{
				String id = "Enum_";
				String attributeQualifier = (String) parameters.get(AbstractUIEditor.ATTRIBUTE_QUALIFIER_PARAM);
				if (attributeQualifier != null)
				{
					attributeQualifier = attributeQualifier.replaceAll("\\W", "");
					id = id + attributeQualifier;
				}
				UITools.applyTestID(editorView, id);
			}

			return ret;
		}
		else
		{
			editorView.setDisabled(true);

			final Label ret;

			if (category != null)
			{
				final String value = category.toString();
				ret = new Label(value + " " + getCategoryDesc(value));
			}
			else
			{
				ret = new Label(" ");
			}
			return ret;
		}
	}

	@Override
	public boolean isInline()
	{
		return true;
	}


	@Override
	public String getEditorType()
	{
		return PropertyDescriptor.TEXT;
	}

	/**
	 * @return availableValues
	 */
	public List<? extends Object> getAvailableValues()
	{
		return this.availableValues;
	}

	/**
	 * @param availableValues
	 */
	public void setAvailableValues(final List<? extends Object> availableValues)
	{
		if (availableValues == null || availableValues.isEmpty())
		{
			editorView.setValue(Labels.getLabel("general.nothingtodisplay"));
			editorView.setDisabled(true);
			this.availableValues = null;
			this.originalAvailableValues = null;
		}
		else
		{
			this.availableValues = new ArrayList<Object>(availableValues);
			if (isOptional())
			{
				this.availableValues.add(0, null);
			}
			this.originalAvailableValues = new ArrayList<Object>(availableValues);
		}
	}

	@Override
	public void setFocus(final HtmlBasedComponent rootEditorComponent, final boolean selectAll)
	{
		final Combobox element = (Combobox) ((CancelButtonContainer) rootEditorComponent).getContent();
		element.setFocus(true);

		if (initialInputString != null)
		{
			element.setText(initialInputString);
		}
	}

	@Override
	public void setOptional(final boolean optional)
	{
		if (!optional)
		{
			availableValues = originalAvailableValues;
		}
		super.setOptional(optional);
	}

	protected void validateAndFireEvent(final EditorListener listener)
	{
		if (editorView.getSelectedItem() == null)
		{
			setEnumValue(editorView, initialEditValue);
		}
		else
		{
			DefaultSAPCategoryUIEditor.this.setValue(editorView.getSelectedItem().getValue());
			editorView.setTooltiptext(ObjectUtils.toString(editorView.getSelectedItem().getValue()));
			listener.valueChanged(getValue());
		}
	}

	protected void handleChangingEvents(final EditorListener listener, final Event event)
	{
		final String newSearchString = ((InputEvent) event).getValue();
		lastSearch = newSearchString;
		if (newSearchString.length() >= 3 && !searchString.equals(newSearchString))
		{
			final List<SAPCategory> catrgories = searchValues(newSearchString);
			synchronized (this)
			{
				if (newSearchString.equals(lastSearch))
				{
					searchString = lastSearch;
					clearComboBox();
					fillComboBox(catrgories);
					listener.valueChanged(getValue());
				}
			}
		}
	}

	/**
	 *
	 */
	protected List<SAPCategory> searchValues(final String newSearchString)
	{
		List<SAPCategory> categories = null;

		try
		{

			final Field f = SAPCategory.class.getDeclaredField("cache");
			f.setAccessible(true);
			final Collection<SAPCategory> coll = ((ConcurrentMap<String, SAPCategory>) f.get(null)).values();
			f.setAccessible(false);
			categories = new ArrayList<SAPCategory>(coll);
		}
		catch (final NoSuchFieldException nsfe)
		{
			showErrorPopup();
			LOG.error("Internal error occured. Please notify admin.");
			LOG.debug(nsfe);
		}
		catch (final IllegalAccessException iae)
		{
			showErrorPopup();
			LOG.error("Internal error occured. Please notify admin.");
			LOG.debug(iae);
		}

		return categories;
	}

	protected void showErrorPopup()
	{
		final String exceptionText = de.hybris.platform.util.localization.Localization
				.getLocalizedString("connectionError.description");
		final String exceptionTitle = de.hybris.platform.util.localization.Localization.getLocalizedString("connectionError.title");

		try
		{
			Messagebox.show(exceptionText, exceptionTitle, Messagebox.OK, Messagebox.ERROR);
		}
		catch (final Exception e)
		{
			LOG.error("", e);
		}
	}

	/**
	 * Fill the search results drop down list box with the list of catrgories retrieved from the SAP backend
	 */
	protected void fillComboBox(final List<SAPCategory> catrgories)
	{
		for (final SAPCategory category : catrgories)
		{
			addcategoryToCombo(category, editorView);
		}

	}

	/**
	 *
	 */
	protected void clearComboBox()
	{
		final int size = editorView.getChildren().size();
		for (int i = 0; i < size; i++)
		{
			editorView.removeItemAt(0);
		}
	}

	protected Combobox getEditorView()
	{
		return editorView;
	}

}
