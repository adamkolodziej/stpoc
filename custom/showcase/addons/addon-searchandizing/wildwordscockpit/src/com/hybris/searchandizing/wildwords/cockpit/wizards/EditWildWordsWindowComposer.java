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
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;

import com.hybris.searchandizing.wildwords.cockpit.wizards.events.WildWordsListener;


/**
 * @author piotr.kalinowski
 * 
 */
public class EditWildWordsWindowComposer extends GenericForwardComposer
{
	private static final String KEYWORDS_REDIRECT_NAME = Labels.getLabel("cockpit.config.label.keywordredirects");
	private static final String SYNONYMS_NAME = Labels.getLabel("cockpit.config.label.synonyms");
	private static final String STOP_WORD_NAME = Labels.getLabel("cockpit.config.label.stopwords");

	private Component window;
	private Button btnReset;
	private Button btnDone;
	private Button btnPublish;
	private Tab tabKeywordRedirect;
	private Tab tabSynonym;
	private Tab tabStopWord;

	private EditWildWordsViewModel model;

	@Override
	public void doAfterCompose(final Component comp) throws Exception
	{
		super.doAfterCompose(comp);

		window = comp;
		final Map<String, Object> args = comp.getDesktop().getExecution().getArg();
		model = (EditWildWordsViewModel) args.get(EditWildWordsWizard.WIZARD_ARG);
		model.registerListener(getModelListener());
	}

	public WildWordsListener getModelListener()
	{
		return new WildWordsListener()
		{
			@Override
			public void onLoad()
			{
				updateState();
			}

			@Override
			public void onChange()
			{
				updateState();
			}

			@Override
			public void onSave()
			{
				updateState();
			}

			@Override
			public void onError(final String message)
			{
				try
				{
					Messagebox.show(message, "Error", Messagebox.OK, Messagebox.ERROR);
				}
				catch (final InterruptedException e)
				{
					// YTODO Auto-generated catch block
					e.printStackTrace();
				}
				updateState();
			}
		};
	}

	protected void updateState()
	{
		final boolean keywordsChanged = model.getKeywordRedirectListModel().isChanged();
		final boolean synonymsChanged = model.getSynonymListModel().isChanged();
		final boolean stopWordsChanged = model.getStopWordListModel().isChanged();

		tabKeywordRedirect.setLabel(keywordsChanged ? "*" + KEYWORDS_REDIRECT_NAME : KEYWORDS_REDIRECT_NAME);
		tabSynonym.setLabel(synonymsChanged ? "*" + SYNONYMS_NAME : SYNONYMS_NAME);
		tabStopWord.setLabel(stopWordsChanged ? "*" + STOP_WORD_NAME : STOP_WORD_NAME);

		btnPublish.setVisible(synonymsChanged || stopWordsChanged ? true : false);
		btnReset.setDisabled(keywordsChanged || synonymsChanged || stopWordsChanged ? false : true);
	}

	@SuppressWarnings("PMD")
	public void onClick$btnReset(final ForwardEvent event)
	{
		model.reset();
	}

	@SuppressWarnings("PMD")
	public void onClick$btnDone(final ForwardEvent event)
	{
		if (model.isChanged())
		{
			if (model.save())
			{
				final Event closeEvent = new Event("onClose", window, null);
				Events.postEvent(closeEvent);
			}
		}
		else
		{
			final Event closeEvent = new Event("onClose", window, null);
			Events.postEvent(closeEvent);
		}
	}

	@SuppressWarnings("PMD")
	public void onClick$btnPublish(final ForwardEvent event)
	{
		if (model.save())
		{
			model.startFullIndexJob();
		}
	}
}
