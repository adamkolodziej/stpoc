package com.hybris.campaigns.wizard;

import de.hybris.platform.cockpit.components.navigationarea.workflow.visualization.WorkflowFacade;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.cockpit.services.sync.SynchronizationService;
import de.hybris.platform.cockpit.session.UISession;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.wizards.Wizard;
import de.hybris.platform.cockpit.wizards.WizardPage;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.workflow.WorkflowActionService;
import de.hybris.platform.workflow.WorkflowProcessingService;
import de.hybris.platform.workflow.WorkflowService;
import de.hybris.platform.workflow.WorkflowTemplateService;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowModel;
import de.hybris.platform.workflow.model.WorkflowTemplateModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hybris.campaigns.model.PublicationPeriodModel;
import com.hybris.campaigns.services.ItemSyncStrategy;


public class DefaultPostWizardStrategy implements PostWizardStrategy
{
	private WorkflowFacade workflowFacade;
	private WorkflowTemplateService workflowTemplateService;
	private WorkflowProcessingService workflowProcessingService;
	private WorkflowActionService workflowActionService;
	private WorkflowService workflowService;
	private String workflowCode;
	private TypeService typeService;
	private ItemSyncStrategy itemSyncStrategy;

	@Override
	public void afterWizardAction(final Wizard wizard, final WizardPage page)
	{
		final CampaignWizard campaignWizard = (CampaignWizard) wizard;
		final TypedObject publicationPeriodItem = campaignWizard.getPublicationPeriodItem();
		final PublicationPeriodModel pub = (PublicationPeriodModel) campaignWizard.getPublicationPeriodItem().getObject();

		final WorkflowTemplateModel workflowActionTemplateForCode = getWorkflowTemplateService().getWorkflowTemplateForCode(
				getWorkflowCode());

		if (!editExistingWorkflowIfPossible(pub, workflowActionTemplateForCode))
		{
			//create
			final WorkflowModel createWorkflow = getWorkflowFacade().createWorkflow(pub.getName(), workflowActionTemplateForCode,
					getTypeService().unwrapItems(Collections.singleton(publicationPeriodItem)), getWorkflowFacade().getCurrentUser());
			workflowTagging(pub, createWorkflow);
			getWorkflowFacade().startWorkflow(createWorkflow);
		}

	}

	private boolean editExistingWorkflowIfPossible(final PublicationPeriodModel pub,
			final WorkflowTemplateModel workflowActionTemplateForCode)
	{
		final List<WorkflowModel> workflowsByTemplate = getWorkflowService().getWorkflowsForTemplateAndUser(
				workflowActionTemplateForCode, getWorkflowFacade().getCurrentUser());
		for (final WorkflowModel wm : workflowsByTemplate)
		{
			final List<ItemModel> models = new ArrayList<ItemModel>();
			models.add(pub);
			if (null != getWorkflowFacade().containsItem(wm, models))
			{
				//edit
				workflowTagging(pub, wm);
				return true;
			}
		}
		return false;
	}

	private void workflowTagging(final PublicationPeriodModel pub, final WorkflowModel createWorkflow)
	{
		activateAction(createWorkflow, "AddCampaignPromotion");//mandatory attribute to save

		if (pub.getEnabled().booleanValue())
		{
			activateAction(createWorkflow, "AddCampaignVisibility");
		}
		if (pub.getStartDate() != null && pub.getEndDate() != null)
		{
			activateAction(createWorkflow, "AddCampaignTimeframe");
		}
		if (CollectionUtils.isNotEmpty(pub.getCmsitems()))
		{
			activateAction(createWorkflow, "AddCampaignContent");
		}
		if (CollectionUtils.isNotEmpty(pub.getMediaTags()))
		{
			activateAction(createWorkflow, "AddCampaignTags");
		}
		final int status = getItemSyncStrategy().getSyncStatus(getTypeService().wrapItem(pub));

		if (status == SynchronizationService.SYNCHRONIZATION_OK)
		{
			activateAction(createWorkflow, "AddCampaignSync");
		}
	}

	private void activateAction(final WorkflowModel createWorkflow, final String code)
	{
		final WorkflowActionModel actionForCode = getWorkflowActionService().getActionForCode(createWorkflow, code);
		getWorkflowProcessingService().activate(actionForCode);
	}

	public WorkflowFacade getWorkflowFacade()
	{
		return workflowFacade;
	}

	public void setWorkflowFacade(final WorkflowFacade workflowFacade)
	{
		this.workflowFacade = workflowFacade;
	}

	public String getWorkflowCode()
	{
		return workflowCode;
	}

	public void setWorkflowCode(final String workflowCode)
	{
		this.workflowCode = workflowCode;
	}

	public WorkflowTemplateService getWorkflowTemplateService()
	{
		return workflowTemplateService;
	}

	public void setWorkflowTemplateService(final WorkflowTemplateService workflowTemplateService)
	{
		this.workflowTemplateService = workflowTemplateService;
	}

	public TypeService getTypeService()
	{
		if (typeService == null)
		{
			final UISession session = UISessionUtils.getCurrentSession();
			typeService = session.getTypeService();
		}
		return typeService;
	}

	public WorkflowProcessingService getWorkflowProcessingService()
	{
		return workflowProcessingService;
	}

	public void setWorkflowProcessingService(final WorkflowProcessingService workflowProcessingService)
	{
		this.workflowProcessingService = workflowProcessingService;
	}

	public WorkflowActionService getWorkflowActionService()
	{
		return workflowActionService;
	}

	public void setWorkflowActionService(final WorkflowActionService workflowActionService)
	{
		this.workflowActionService = workflowActionService;
	}

	public WorkflowService getWorkflowService()
	{
		return workflowService;
	}

	public void setWorkflowService(final WorkflowService workflowService)
	{
		this.workflowService = workflowService;
	}

	public ItemSyncStrategy getItemSyncStrategy()
	{
		return itemSyncStrategy;
	}

	public void setItemSyncStrategy(final ItemSyncStrategy itemSyncStrategy)
	{
		this.itemSyncStrategy = itemSyncStrategy;
	}
}
