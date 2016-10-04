package com.hybris.commercesearch.searchandizing.cockpit.component.listview.impl;

import de.hybris.platform.cockpit.model.listview.ColumnDescriptor;
import de.hybris.platform.cockpit.model.listview.TableModel;
import de.hybris.platform.cockpit.model.listview.ValueHandler;
import de.hybris.platform.cockpit.model.listview.impl.DefaultTextCellRenderer;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.label.LabelService;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.util.TypeTools;
import de.hybris.platform.cockpit.util.UITools;
import de.hybris.platform.cronjob.model.CronJobModel;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;


public class CronJobDetailsTextCellRenderer extends DefaultTextCellRenderer
{
	private static final Logger LOG = Logger.getLogger(CronJobDetailsTextCellRenderer.class);

	@Override
	public void render(final TableModel model, final int colIndex, final int rowIndex, final Component parent)
	{
		if (model == null || parent == null)
		{
			throw new IllegalArgumentException("Model and parent can not be null.");
		}

		String text = "";
		Object value = null;
		try
		{
			value = model.getValueAt(colIndex, rowIndex);
		}
		catch (final IllegalArgumentException iae)
		{
			LOG.warn("Could not render cell (Reason: '" + iae.getMessage() + "').", iae);
		}

		final Div div = new Div();
		div.setStyle("overflow: hidden;height: 100%;");

		if (ValueHandler.NOT_READABLE_VALUE.equals(value))
		{
			div.setSclass("listview_notreadable_cell");
			text = Labels.getLabel("listview.cell.readprotected");
		}
		else if (value != null)
		{
			final LabelService labelService = UISessionUtils.getCurrentSession().getLabelService();
			text = TypeTools.getValueAsString(labelService, value);
		}

		final Label label = new Label(text);

		applyTestID(model, colIndex, rowIndex, label);

		div.appendChild(label);
		parent.appendChild(div);
	}

	private void applyTestID(final TableModel model, final int colIndex, final int rowIndex, final Component component)
	{
		if (UISessionUtils.getCurrentSession().isUsingTestIDs())
		{
			final Object listItem = model.getListComponentModel().getValueAt(rowIndex);
			if (listItem instanceof TypedObject && ((TypedObject) listItem).getObject() instanceof CronJobModel)
			{
				final ColumnDescriptor column = model.getColumnComponentModel().getVisibleColumn(colIndex);
				String name = column.getName();
				if (StringUtils.isBlank(name))
				{
					name = "unnamed";
				}
				else
				{
					name = StringUtils.deleteWhitespace(name);
				}

				final CronJobModel cronJob = ((CronJobModel) ((TypedObject) listItem).getObject());
				final StringBuilder test_id = new StringBuilder(component.getClass().getSimpleName());
				test_id.append("_").append(StringUtils.deleteWhitespace(cronJob.getCode())).append("_");
				test_id.append(name).append("_row_").append(rowIndex);

				UITools.applyTestID(component, test_id.toString());
			}
		}
	}
}
