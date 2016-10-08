/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 * 
 */
package com.hybris.searchandizing.wildwords.cockpit.wizards;

import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.model.referenceeditor.impl.AbstractReferenceUIEditor;
import de.hybris.platform.cockpit.model.referenceeditor.simple.impl.DefaultSimpleReferenceUIEditor;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.solrfacetsearch.enums.KeywordRedirectMatchType;
import de.hybris.platform.solrfacetsearch.model.redirect.SolrAbstractKeywordRedirectModel;

import java.util.Collections;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Footer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Span;
import org.zkoss.zul.Textbox;

import com.hybris.searchandizing.wildwords.cockpit.model.KeywordRedirectValueModel;
import com.hybris.searchandizing.wildwords.cockpit.wizards.events.WildWordsListener;


/**
 * @author piotr.kalinowski
 * 
 */
public class KeywordRedirectPanelComposer extends GenericForwardComposer
{
	protected final static String REMOVE_ICON = "/cockpit/images/cnt_elem_remove_action.png";
	protected final static ListModelList MATCH_TYPE_LIST = new ListModelList(KeywordRedirectMatchType.values());

	private TypeService cockpitTypeService;
	private EditWildWordsViewModel model;

	private Grid redirectGrid;
	private Textbox newKeyword;
	private Combobox newMatchType;
	private Checkbox newIgnoreCase;
	private Footer newRedirectFooter;
	private Button newKeywordButton;
	private AbstractReferenceUIEditor newRedirectEditor;


	@Override
	public void doAfterCompose(final Component comp) throws Exception
	{
		super.doAfterCompose(comp);

		final Map<String, Object> args = comp.getDesktop().getExecution().getArg();
		model = (EditWildWordsViewModel) args.get(EditWildWordsWizard.WIZARD_ARG);
		model.registerListener(getModelListener());
		newMatchType.setModel(MATCH_TYPE_LIST);
		newMatchType.setValue(KeywordRedirectMatchType.EXACT.getCode());

		newRedirectEditor = new DefaultSimpleReferenceUIEditor(getCockpitTypeService().getObjectType(
				SolrAbstractKeywordRedirectModel._TYPECODE));
		final Component redirectComponent = newRedirectEditor.createViewComponent(getCockpitTypeService().wrapItem(null),
				Collections.EMPTY_MAP, new EditorListener()
				{
					@Override
					public void valueChanged(final Object value)
					{
						//
					}

					@Override
					public void actionPerformed(final String actionCode)
					{
						//
					}
				});
		redirectComponent.setParent(newRedirectFooter);
	}

	protected RowRenderer getRowRenderer()
	{
		return new RowRenderer()
		{
			@Override
			public void render(final Row row, final Object data) throws Exception
			{
				final KeywordRedirectValueModel val = (KeywordRedirectValueModel) data;
				row.setValue(val);
				if (val.isChanged())
				{
					row.setSclass("changed");
				}
				if (!val.isValid())
				{
					row.setSclass(row.getSclass() + " invalid");
				}

				final Label changedLabel = new Label();
				changedLabel.setParent(row);
				changedLabel.setValue(val.isChanged() ? "*" : "");

				final Textbox keywordText = new Textbox();
				keywordText.setParent(row);
				keywordText.setValue(val.getKeyword());
				keywordText.addEventListener(Events.ON_CHANGE, new EventListener()
				{
					@Override
					public void onEvent(final Event event) throws Exception
					{
						final Textbox input = (Textbox) event.getTarget();
						val.setKeyword(input.getValue());
						changeValue();
					}
				});

				final Combobox matchTypeCombo = new Combobox();
				matchTypeCombo.setParent(row);
				matchTypeCombo.setModel(MATCH_TYPE_LIST);
				matchTypeCombo.setValue(val.getMatchType().getCode());
				matchTypeCombo.addEventListener(Events.ON_CHANGE, new EventListener()
				{
					@Override
					public void onEvent(final Event event) throws Exception
					{
						final Combobox input = (Combobox) event.getTarget();
						val.setMatchType(KeywordRedirectMatchType.valueOf(input.getValue()));
						changeValue();
					}
				});

				final Checkbox ignoreCaseCheckbox = new Checkbox();
				ignoreCaseCheckbox.setParent(row);
				ignoreCaseCheckbox.setChecked(val.isIgnoreCase().booleanValue());
				ignoreCaseCheckbox.addEventListener(Events.ON_CHECK, new EventListener()
				{
					@Override
					public void onEvent(final Event event) throws Exception
					{
						final Checkbox input = (Checkbox) event.getTarget();
						val.setIgnoreCase(Boolean.valueOf(input.isChecked()));
						changeValue();
					}
				});

				final DefaultSimpleReferenceUIEditor editor = new DefaultSimpleReferenceUIEditor(getCockpitTypeService()
						.getObjectType(SolrAbstractKeywordRedirectModel._TYPECODE));
				final Component redirectComponent = editor.createViewComponent(getCockpitTypeService().wrapItem(val.getRedirect()),
						Collections.EMPTY_MAP, new EditorListener()
						{
							@Override
							public void valueChanged(final Object value)
							{
								if (value != null)
								{
									if (value instanceof TypedObject)
									{
										val.setRedirect((SolrAbstractKeywordRedirectModel) ((TypedObject) value).getObject());
									}
									else
									{
										throw new UnsupportedOperationException("Can't cast value to TypedObject");
									}
								}
								else
								{
									val.setRedirect(null);
								}
								changeValue();
							}

							@Override
							public void actionPerformed(final String actionCode)
							{
								//
							}
						});
				redirectComponent.setParent(row);

				final Span actions = new Span();
				actions.setParent(row);
				final Image deleteBtn = new Image(REMOVE_ICON);
				deleteBtn.setParent(actions);
				deleteBtn.setStyle("cursor: pointer");
				deleteBtn.setTooltiptext(Labels.getLabel("general.remove"));
				deleteBtn.addEventListener(Events.ON_CLICK, new EventListener()
				{
					@Override
					public void onEvent(final Event event) throws Exception
					{
						Messagebox.show(Labels.getLabel("general.confirmation"), Labels.getLabel("general.confirm"), Messagebox.YES
								| Messagebox.CANCEL, Messagebox.QUESTION, new EventListener()
						{
							@Override
							public void onEvent(final Event event) throws Exception
							{
								if (event.getName().equals("onYes"))
								{
									model.getKeywordRedirectListModel().removeKeywordRedirect(val);
									changeValue();
								}
							}
						});
					}
				});
			}
		};
	}

	public WildWordsListener getModelListener()
	{
		return new WildWordsListener()
		{
			@Override
			public void onLoad()
			{
				initValues();
			}

			@Override
			public void onChange()
			{
				redirectGrid.setModel(new ListModelList(getModel().getKeywordRedirectListModel().getValues()));
			}

			@Override
			public void onSave()
			{
				redirectGrid.setModel(new ListModelList(getModel().getKeywordRedirectListModel().getValues()));
			}

			@Override
			public void onError(final String message)
			{
				// YTODO Auto-generated method stub

			}
		};
	}

	protected void initValues()
	{
		redirectGrid.setModel(new ListModelList(getModel().getKeywordRedirectListModel().getValues()));
		redirectGrid.setRowRenderer(getRowRenderer());
	}

	protected void changeValue()
	{
		model.fireChangeEvents();
	}

	protected EditWildWordsViewModel getModel()
	{
		return this.model;
	}

	protected void addNew()
	{
		final KeywordRedirectValueModel value = new KeywordRedirectValueModel(true);
		value.setKeyword(newKeyword.getValue());
		newKeyword.setValue(null);
		value.setMatchType(KeywordRedirectMatchType.valueOf(newMatchType.getValue()));
		newMatchType.setValue(KeywordRedirectMatchType.EXACT.getCode());
		value.setIgnoreCase(Boolean.valueOf(newIgnoreCase.isChecked()));
		newIgnoreCase.setChecked(true);

		final Object referenceEditorValue = newRedirectEditor.getValue();
		final Object valueObject = (referenceEditorValue instanceof TypedObject) ? ((TypedObject) referenceEditorValue).getObject()
				: referenceEditorValue;
		if (valueObject != null)
		{
			value.setRedirect((SolrAbstractKeywordRedirectModel) valueObject);
		}

		model.addNewValue(value);
		redirectGrid.getPaginal().setActivePage(redirectGrid.getPageCount() - 1);
		newRedirectEditor.setValue(null);
	}

	@SuppressWarnings("PMD")
	public void onClick$newKeywordButton(final ForwardEvent event)
	{
		addNew();
	}

	public TypeService getCockpitTypeService()
	{
		if (cockpitTypeService == null)
		{
			cockpitTypeService = UISessionUtils.getCurrentSession().getTypeService();
		}
		return cockpitTypeService;
	}
}
