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

import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Span;
import org.zkoss.zul.Textbox;

import com.hybris.searchandizing.wildwords.cockpit.model.StopWordValueModel;
import com.hybris.searchandizing.wildwords.cockpit.wizards.events.WildWordsListener;


/**
 * @author piotr.kalinowski
 * 
 */
public class StopWordPanelComposer extends GenericForwardComposer
{
	protected final static String REMOVE_ICON = "/cockpit/images/cnt_elem_remove_action.png";

	private EditWildWordsViewModel model;

	private Grid stopWordGrid;
	private Textbox newStopWord;
	private Button newStopWordButton;

	@Override
	public void doAfterCompose(final Component comp) throws Exception
	{
		super.doAfterCompose(comp);

		final Map<String, Object> args = comp.getDesktop().getExecution().getArg();
		model = (EditWildWordsViewModel) args.get(EditWildWordsWizard.WIZARD_ARG);
		model.registerListener(getModelListener());
	}

	protected RowRenderer getRowRenderer()
	{
		return new RowRenderer()
		{
			@Override
			public void render(final Row row, final Object data) throws Exception
			{
				final StopWordValueModel val = (StopWordValueModel) data;
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

				final Textbox stopWordText = new Textbox();
				stopWordText.setParent(row);
				stopWordText.setValue(val.getStopWord());
				stopWordText.addEventListener(Events.ON_CHANGE, new EventListener()
				{
					@Override
					public void onEvent(final Event event) throws Exception
					{
						final Textbox input = (Textbox) event.getTarget();
						val.setStopWord(input.getValue());
						changeValue();
					}
				});

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
									model.getStopWordListModel().removeStopWord(val);
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
				stopWordGrid.setModel(new ListModelList(getModel().getStopWordListModel().getValues()));
			}

			@Override
			public void onSave()
			{
				stopWordGrid.setModel(new ListModelList(getModel().getStopWordListModel().getValues()));
			}

			@Override
			public void onError(final String message)
			{
				// YTODO Auto-generated method stub

			}
		};
	}

	protected EditWildWordsViewModel getModel()
	{
		return this.model;
	}

	protected void initValues()
	{
		stopWordGrid.setModel(new ListModelList(getModel().getStopWordListModel().getValues()));
		stopWordGrid.setRowRenderer(getRowRenderer());
	}

	protected void changeValue()
	{
		model.fireChangeEvents();
	}

	protected void addNew()
	{
		final StopWordValueModel value = new StopWordValueModel(true);
		value.setStopWord(newStopWord.getValue());
		newStopWord.setValue(null);
		model.addNewValue(value);
		stopWordGrid.getPaginal().setActivePage(stopWordGrid.getPageCount() - 1);
	}

	@SuppressWarnings("PMD")
	public void onClick$newStopWordButton(final ForwardEvent event)
	{
		addNew();
	}
}