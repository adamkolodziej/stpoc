package com.hybris.campaigns.cockpit.services.editor;

import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.editor.impl.DefaultSelectUIEditor;
import de.hybris.platform.cockpit.services.config.AvailableValuesProvider;
import de.hybris.platform.core.Registry;

import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

import com.hybris.campaigns.model.PublicationPeriodModel;


/**
 * author: dariusz.malachowski
 */
public class PublicationPeriodEditor extends DefaultSelectUIEditor
{

	public static final String SESSION_PUBLICATION_PERIODS = "activePublicationPeriods";
	private static final String EMPTY_LABEL = " ";

	@Override
	public HtmlBasedComponent createViewComponent(Object initialValue, final Map<String, ?> parameters,
			final EditorListener listener)
	{
		final String beanName = (String) parameters.get("allowedValuesList");
		final AvailableValuesProvider valuesProvider = (AvailableValuesProvider) Registry.getApplicationContext().getBean(beanName);
		final List<? extends Object> availableValues = valuesProvider.getAvailableValues(null);

		if (!availableValues.isEmpty())
		{
			setAvailableValues(availableValues);


			for (final Object object : getAvailableValues())
			{
				final PublicationPeriodModel publicationPeriod = (PublicationPeriodModel) object;
				if (initialValue instanceof de.hybris.platform.cockpit.model.meta.TypedObject)
				{
					if (((de.hybris.platform.cockpit.model.meta.TypedObject) initialValue).getObject() != null)
					{
						if (publicationPeriod.getCode().equals(
								((PublicationPeriodModel) ((de.hybris.platform.cockpit.model.meta.TypedObject) initialValue).getObject())
										.getCode()))
						{
							initialValue = object;
						}
					}
				}
				else if (initialValue instanceof PublicationPeriodModel)
				{
					if (publicationPeriod.getCode().equals(((PublicationPeriodModel) initialValue).getCode()))
					{
						initialValue = object;
					}
				}
			}

		}

		return super.createViewComponent(initialValue, parameters, listener);
	}

	@Override
	protected void addObjectToCombo(final Object value, final Combobox box)
	{
		if (box.getChildren().isEmpty())
		{
			final Comboitem comboitem = new Comboitem();

			comboitem.setLabel(EMPTY_LABEL);
			comboitem.setValue(null);
			comboitem.setImage(null);

			box.appendChild(comboitem);
		}

		if (value instanceof PublicationPeriodModel)
		{
			final Comboitem comboitem = new Comboitem();
			final PublicationPeriodModel publicationPeriodModel = (PublicationPeriodModel) value;

			comboitem.setLabel(publicationPeriodModel.getName() == null ? publicationPeriodModel.getCode() : publicationPeriodModel
					.getName());
			comboitem.setValue(value);
			comboitem.setImage(null);

			box.appendChild(comboitem);
		}
		else
		{
			super.addObjectToCombo(value, box);
		}
	}

	@Override
	protected int getPosition(final Object item)
	{
		return super.getPosition(item) + 1;
	}

}
