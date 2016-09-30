/**
 *
 */
package com.hybris.showcase.showcasecockpits.cscockpit.widgets.renderers.impl;

import de.hybris.platform.cockpit.events.impl.ItemChangedEvent;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.config.ColumnConfiguration;
import de.hybris.platform.cockpit.session.BrowserModel;
import de.hybris.platform.cockpit.session.UICockpitPerspective;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.widgets.Widget;
import de.hybris.platform.cockpit.widgets.impl.DefaultWidget;
import de.hybris.platform.cockpit.widgets.models.impl.DefaultItemWidgetModel;
import de.hybris.platform.cscockpit.utils.CockpitUiConfigLoader;
import de.hybris.platform.cscockpit.utils.CssUtils;
import de.hybris.platform.cscockpit.utils.LabelUtils;
import de.hybris.platform.cscockpit.utils.ObjectGetValueUtils;
import de.hybris.platform.cscockpit.widgets.renderers.details.impl.ConfigurableItemWidgetDetailRenderer;
import de.hybris.platform.cscockpit.widgets.renderers.impl.AbstractCsWidgetRenderer;
import de.hybris.platform.cscockpit.widgets.renderers.utils.PopupWidgetHelper;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.zkoss.zk.ui.api.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;

import com.hybris.showcase.showcasecockpits.cscockpit.widgets.controllers.CsExpenseDisputeController;


/**
 * @author Adrian Sbarcea (SAP - i309441)
 *
 */
public class TicketExpenseDisputeWidgetRenderer
		extends AbstractCsWidgetRenderer<DefaultWidget<DefaultItemWidgetModel<TypedObject>, CsExpenseDisputeController>>
{

	protected static final String CSS_TICKET_EXPENSE_DISPUTE_DETAILS_CONTAINER = "csTicketExpenseDisputeDetailsContainer";
	protected static final String CSS_TICKET_EXPENSE_DISPUTE_DETAILS = "csTicketExpenseDisputeDetails";
	protected static final String CSS_TICKET_EXPENSE_DISPUTE_DETAILS_BUTTONS = "csTicketExpenseDisputeDetailsButtons";
	protected static final String CSS_TICKET_EXPENSE_DISPUTE_DETAILS_BUTTONS_LEFT = "csTicketExpenseDisputeDetailsButtonsLeft";
	protected static final String CSS_BLUE_LINK = "blueLink";
	protected static final String CSS_BTNRED = "btnred";
	protected static final String CSS_TICKET_ACCEPT_DISPUTE_POPUP_BTN = "csTicketAcceptDisputePopupBtn";
	protected static final String CSS_TICKET_ACCEPT_DISPUTE_POPUP = "csTicketAcceptExpenseDisputePopup";

	private String configurationCode;
	private PopupWidgetHelper popupWidgetHelper;

	protected PopupWidgetHelper getPopupWidgetHelper()
	{
		return this.popupWidgetHelper;
	}

	@Required
	public void setPopupWidgetHelper(final PopupWidgetHelper popupWidgetHelper)
	{
		this.popupWidgetHelper = popupWidgetHelper;
	}

	protected String getConfigurationCode()
	{
		return this.configurationCode;
	}

	@Required
	public void setConfigurationCode(final String configurationCode)
	{
		this.configurationCode = configurationCode;
	}

	@Override
	protected HtmlBasedComponent createContentInternal(
			final DefaultWidget<DefaultItemWidgetModel<TypedObject>, CsExpenseDisputeController> widget,
			final HtmlBasedComponent rootContainer)
	{
		final Div container = new Div();
		container.setSclass("csTicketExpenseDisputeDetailsContainer");
		final TypedObject expenseDispute = (TypedObject) ((DefaultItemWidgetModel) widget.getWidgetModel()).getItem();
		if (expenseDispute != null)
		{
			//expense details section
			final ConfigurableItemWidgetDetailRenderer renderer = new TicketConfigurableItemWidgetDetailRenderer();
			renderer.setPropertyEditorHelper(this.getPropertyEditorHelper());
			renderer.setPropertyRendererHelper(this.getPropertyRendererHelper());
			renderer.setConfigurationCode(this.getConfigurationCode());
			final Div expenseDisputeDetailsDiv = new Div();
			expenseDisputeDetailsDiv.setSclass("csTicketExpenseDisputeDetails");
			final HtmlBasedComponent detailContent = renderer.createContent((Object) null, expenseDispute, widget);
			if (detailContent != null)
			{
				expenseDisputeDetailsDiv.appendChild(detailContent);
			}
			container.appendChild(expenseDisputeDetailsDiv);

			//buttons section
			final Div buttonsDiv = new Div();
			buttonsDiv.setSclass("csTicketExpenseDisputeDetailsButtons");
			final Div leftContainer = new Div();
			leftContainer.setSclass("csTicketExpenseDisputeDetailsButtonsLeft");
			buttonsDiv.appendChild(leftContainer);
			if (widget.getWidgetController().canAcceptDispute(expenseDispute))
			{
				final Div buttonContainerUpdate = new Div();
				this.createTicketUpdateButton(expenseDispute, widget, buttonContainerUpdate);
				leftContainer.appendChild(buttonContainerUpdate);
			}
			container.appendChild(buttonsDiv);

		}
		return container;
	}

	protected void createTicketUpdateButton(final TypedObject expenseDispObj,
			final Widget<DefaultItemWidgetModel<TypedObject>, CsExpenseDisputeController> widget, final Div container)
	{
		final Button acceptExpenseDisputeBtn = new Button(LabelUtils.getLabel(widget, "acceptDispute", new Object[0]));
		acceptExpenseDisputeBtn.setParent(container);
		acceptExpenseDisputeBtn.setSclass(CSS_TICKET_ACCEPT_DISPUTE_POPUP_BTN);
		container.setSclass("csTicketAcceptExpenseDisputePopup");
		acceptExpenseDisputeBtn.addEventListener("onClick", new EventListener()
		{
			@Override
			public void onEvent(final Event arg0) throws Exception
			{
				final CsExpenseDisputeController ctrlr = widget.getWidgetController();
				ctrlr.acceptExpenseDispute(expenseDispObj);

				final UICockpitPerspective currentPerspective = UISessionUtils.getCurrentSession().getCurrentPerspective();
				currentPerspective.getNavigationArea().update();
				for (final BrowserModel visBrowser : currentPerspective.getBrowserArea().getVisibleBrowsers())
				{
					visBrowser.updateItems();
				}

				UISessionUtils.getCurrentSession().sendGlobalEvent(new ItemChangedEvent(this, ctrlr.getCurrentTicket(),
						Collections.emptyList(), ItemChangedEvent.ChangeType.CHANGED));
				ctrlr.dispatchEvent(null, widget, null);

			}

		});
	}

	protected static class TicketConfigurableItemWidgetDetailRenderer extends ConfigurableItemWidgetDetailRenderer
	{

		@Override
		public HtmlBasedComponent createContent(final Object context, final TypedObject expenseDispObj, final Widget widget)
		{
			final Div detailContainer = new Div();
			if (expenseDispObj != null)
			{
				final Div expenseDisputeInfo = new Div();
				expenseDisputeInfo.setParent(detailContainer);
				final Div expenseInfo = new Div();
				expenseInfo.setParent(detailContainer);

				expenseDisputeInfo.setSclass("csObject" + CssUtils.sanitizeForCss(expenseDispObj.getType().getCode()) + "Container");
				expenseInfo.setSclass("csObject" + CssUtils.sanitizeForCss(expenseDispObj.getType().getCode()) + "Container");

				final List<ColumnConfiguration> columns = CockpitUiConfigLoader.getAllVisibleColumnConfigurations(
						UISessionUtils.getCurrentSession(), this.getConfigurationCode(), expenseDispObj.getType().getCode());

				for (final ColumnConfiguration col : columns)
				{
					if (!col.getName().contains("ExpenseDispute.expense."))
					{
						this.renderRow(this.getPropertyRendererHelper().getPropertyDescriptorName(col),
								ObjectGetValueUtils.getValue(col.getValueHandler(), expenseDispObj), expenseDisputeInfo);
					}
					else if (col.getName().contains("ExpenseDispute.expense."))
					{
						this.renderRow(this.getPropertyRendererHelper().getPropertyDescriptorName(col),
								ObjectGetValueUtils.getValue(col.getValueHandler(), expenseDispObj), expenseInfo);
					}
				}

			}
			else
			{
				detailContainer.setSclass("csObject" + CssUtils.sanitizeForCss(this.getConfigurationCode()) + "Container");
			}
			return detailContainer;

		}

	}

}
