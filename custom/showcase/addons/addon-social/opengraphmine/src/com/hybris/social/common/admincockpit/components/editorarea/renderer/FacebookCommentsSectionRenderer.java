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
package com.hybris.social.common.admincockpit.components.editorarea.renderer;


import de.hybris.platform.cockpit.components.editorarea.renderer.CommentsSectionRenderer;
import de.hybris.platform.cockpit.components.listview.ActionColumnConfiguration;
import de.hybris.platform.cockpit.components.listview.ListViewAction;
import de.hybris.platform.cockpit.components.listview.impl.AbstractCommentAction;
import de.hybris.platform.cockpit.components.sectionpanel.Section;
import de.hybris.platform.cockpit.components.sectionpanel.SectionPanel;
import de.hybris.platform.cockpit.components.sectionpanel.TooltipRenderer;
import de.hybris.platform.cockpit.model.editor.EditorListener;
import de.hybris.platform.cockpit.model.listview.ValueHandler;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.session.BrowserModel;
import de.hybris.platform.cockpit.session.UIBrowserArea;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.session.impl.CommunicationBrowserModel;
import de.hybris.platform.cockpit.session.impl.CustomEditorSection;
import de.hybris.platform.cockpit.util.TypeTools;
import de.hybris.platform.cockpit.util.UITools;
import de.hybris.platform.comments.constants.CommentsConstants;
import de.hybris.platform.comments.model.AbstractCommentModel;
import de.hybris.platform.comments.model.CommentModel;
import de.hybris.platform.comments.model.CommentTypeModel;
import de.hybris.platform.comments.model.ComponentModel;
import de.hybris.platform.comments.model.DomainModel;
import de.hybris.platform.comments.model.ReplyModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.security.AccessManager;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.AbstractTreeModel;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Vbox;

import com.hybris.social.facebook.opengraphmine.model.FacebookPageModel;
import com.hybris.social.facebook.opengraphmine.model.FacebookUserModel;


/**
 * @author piotr.kalinowski
 * 
 */
public class FacebookCommentsSectionRenderer extends CommentsSectionRenderer
{
	private static final Logger LOG = Logger.getLogger(FacebookCommentsSectionRenderer.class);

	protected static final String OPENED_NODES_ATTRIBUTE = "openedNodes";
	protected static final String IMG_ATTACHMENT = "/cockpit/images/icon_attachement.png";
	protected static final String IMG_NEW_COMMENT = "/cockpit/images/green_add_plus.gif";

	private String domainCode;
	private String commentTypeCode;
	private String componentCode;
	private String maxReplyLevelValue;

	protected int maxReplyLevel = 0;
	protected DomainModel domain;
	protected ComponentModel component;
	protected CommentTypeModel commentType;
	protected Section section;
	protected TooltipRenderer tooltipRenderer;

	@Override
	public void render(final SectionPanel panel, final Component parent, final Component captionComponent, final Section section)
	{
		if (BooleanUtils.toBoolean(UITools.getCockpitParameter("default.comments.enabled", Executions.getCurrent())))
		{
			TypedObject currentItem = null;

			final Map<String, Object> context = panel.getModel().getContext();
			if (context != null)
			{
				currentItem = (TypedObject) context.get("currentObject");
			}

			if (currentItem == null)
			{
				LOG.error("Can not render section, current item is null.");
				return;
			}

			// FacebookComment - we want to set currentItem to linked product (or first of them)
			final Object object = currentItem.getObject();
			if (object instanceof FacebookPageModel)
			{
				if (!((FacebookPageModel) object).getLinkedProducts().isEmpty())
				{
					currentItem = getTypeService().wrapItem(((FacebookPageModel) object).getLinkedProducts().iterator().next());
				}
			}

			parent.getChildren().clear();

			final String domainCode = getDomainCode();
			final String componentCode = getComponentCode();
			final String commentTypeCode = getCommentTypeCode();

			if (UITools.getCockpitParameter("default.commentsection.maxreplylevel", Executions.getCurrent()) != null)
			{
				maxReplyLevel = Integer.parseInt(UITools.getCockpitParameter("default.commentsection.maxreplylevel",
						Executions.getCurrent()));
			}

			if (domainCode != null && componentCode != null && commentTypeCode != null)
			{
				domain = getCommentService().getDomainForCode(domainCode);
				component = getCommentService().getComponentForCode(domain, componentCode);
				commentType = getCommentService().getCommentTypeForCode(component, commentTypeCode);
			}

			final Div div = new Div();
			div.setSclass("commentSection");

			final Vbox vbox = new Vbox();
			vbox.setWidth("100%");

			final Tree commentTree = new Tree();

			final boolean permission = UISessionUtils.getCurrentSession().getSystemService()
					.checkPermissionOn(getTypeService().getObjectType(CommentsConstants.TC.REPLY).getCode(), AccessManager.CREATE);
			if (permission)
			{
				final Div captionDiv = new Div();
				final Image addImage = new Image();
				addImage.setSrc(IMG_NEW_COMMENT);
				addImage.setTooltip(Labels.getLabel("comment.addComment"));
				addImage.setSclass("addCommentBtn");
				final List<TypedObject> relatedItemList = Collections.singletonList(currentItem);
				addImage.addEventListener(Events.ON_CLICK, getAddCommentButtonEventListener(captionDiv, relatedItemList));
				captionDiv.appendChild(addImage);
				captionDiv.setSclass("commentSectionCaption");
				captionComponent.appendChild(captionDiv);
			}

			final Treecols columns = new Treecols();
			final Treecol commentCol = new Treecol();
			columns.appendChild(commentCol);
			final Treecol attachmentCol = new Treecol();
			columns.appendChild(attachmentCol);
			final Treecol userCol = new Treecol();
			columns.appendChild(userCol);
			final Treecol dateCol = new Treecol();
			columns.appendChild(dateCol);
			final Treecol actionCol = new Treecol();
			columns.appendChild(actionCol);
			UITools.setComponentWidths(getWidths(), commentCol, attachmentCol, userCol, dateCol, actionCol);

			commentTree.appendChild(columns);


			commentTree.setMultiple(true);
			commentTree.setFixedLayout(true);
			commentTree.setSclass("z-commenttree");
			final CommentTreeModel commentTreeModel = new CommentTreeModel(new TreeNodeWrapper(), currentItem);
			commentTree.setModel(commentTreeModel);
			commentTree.setTreeitemRenderer(new CommentTreeRenderer(currentItem, commentTree));
			vbox.appendChild(commentTree);
			div.appendChild(vbox);

			parent.appendChild(div);

			this.section = section;
			if (section instanceof CustomEditorSection)
			{
				final List<List<Integer>> openNodes = (List<List<Integer>>) ((CustomEditorSection) section)
						.getAttribute(OPENED_NODES_ATTRIBUTE);
				if (openNodes == null)
				{
					((CustomEditorSection) section).setAttribute(OPENED_NODES_ATTRIBUTE, getOpenedNodes(commentTree, 10));
				}
				else
				{
					restoreOpenedState(commentTree, openNodes);
				}
			}
		}
		else
		{
			final Div div = new Div();
			div.setSclass("editor_save_info");
			div.appendChild(new Label(Labels.getLabel("comment.notEnabled")));
			parent.appendChild(div);
		}
	}

	protected class CommentTreeModel extends AbstractTreeModel
	{
		private final List<CommentModel> comments;

		public CommentTreeModel(final Object root, final TypedObject item)
		{
			super(root);
			final Object object = item.getObject();
			if (component != null && commentType != null && object instanceof ItemModel)
			{
				// FacebookComment - load all comments posted by the user
				if (object instanceof FacebookUserModel)
				{
					this.comments = getCockpitCommentService().getComments((FacebookUserModel) object,
							Collections.singleton(component), Collections.singleton(commentType), 0, -1);
				}
				else
				{
					this.comments = getCockpitCommentService().getItemComments((ItemModel) object, null,
							Collections.singleton(component), Collections.singleton(commentType), 0, -1);
				}
			}
			else
			{
				this.comments = Collections.EMPTY_LIST;
			}
		}

		@Override
		public Object getChild(final Object parent, final int index)
		{
			Object ret = null;
			final TreeNodeWrapper parentNode = (TreeNodeWrapper) parent;
			final Object item = parentNode.getItem();

			if (parentNode.getDepth() == 0) //root
			{
				ret = ((this.comments != null && !this.comments.isEmpty() && this.comments.size() - 1 >= index) ? this.comments
						.get(index) : null);
			}
			else if (item instanceof CommentModel
					&& !getCockpitCommentService().getDirectReplies((CommentModel) item, 0, -1).isEmpty())
			{
				final List<ReplyModel> replies = getCockpitCommentService().getDirectReplies((CommentModel) item, 0, -1);
				ret = ((replies != null && !replies.isEmpty() && replies.size() - 1 >= index) ? replies.get(index) : null);
			}
			else if (item instanceof ReplyModel && !((ReplyModel) item).getReplies().isEmpty())
			{
				final List<ReplyModel> replies = ((ReplyModel) item).getReplies();
				ret = ((replies != null && !replies.isEmpty() && replies.size() - 1 >= index) ? replies.get(index) : null);
			}
			return new TreeNodeWrapper(ret, parentNode);
		}

		@Override
		public int getChildCount(final Object parent)
		{
			int ret = 0;
			final TreeNodeWrapper parentNode = (TreeNodeWrapper) parent;
			final Object item = parentNode.getItem();

			if (parentNode.getDepth() == 0)
			{
				ret = ((this.comments != null) ? this.comments.size() : 0);
			}
			else if (item instanceof CommentModel
					&& !getCockpitCommentService().getDirectReplies((CommentModel) item, 0, -1).isEmpty())
			{
				final List<ReplyModel> replies = getCockpitCommentService().getDirectReplies((CommentModel) item, 0, -1);
				ret = ((replies != null) ? replies.size() : 0);
			}
			else if (item instanceof ReplyModel && !((ReplyModel) item).getReplies().isEmpty())
			{
				ret = ((((ReplyModel) item).getReplies() != null) ? ((ReplyModel) item).getReplies().size() : 0);
			}
			return ret;
		}

		@Override
		public boolean isLeaf(final Object node)
		{
			if (!(node instanceof TreeNodeWrapper))
			{
				throw new IllegalArgumentException(String.format("Unexpected type of node: %s. %s expected", node.getClass(),
						TreeNodeWrapper.class));
			}

			final TreeNodeWrapper treenode = (TreeNodeWrapper) node;

			if (!treenode.hasLeafInformation())
			{
				boolean leaf = true;
				final Object data = treenode.getItem();
				if (data == null)
				{
					throw new NullPointerException("Node data unexpectedly null");
				}
				if (data instanceof CommentModel)
				{
					final List<ReplyModel> replies = getCockpitCommentService().getDirectReplies((CommentModel) data, 0, -1);
					leaf = ((replies != null && replies.isEmpty()) || (treenode.getDepth() >= maxReplyLevel));
				}
				else if (data instanceof ReplyModel)
				{
					final List<ReplyModel> replies = ((ReplyModel) data).getReplies();
					leaf = ((replies != null && replies.isEmpty()) || (treenode.getDepth() >= maxReplyLevel));
				}
				else
				{
					throw new IllegalStateException(String.format("Unexpected type of node data: %s. %s or %s expected",
							data.getClass(), CommentModel.class, ReplyModel.class));
				}
				treenode.setLeaf(leaf);
			}
			return treenode.isLeaf();
		}
	}

	protected class TreeNodeWrapper
	{
		private Object item;
		private final int depth;
		private Boolean leaf;

		public TreeNodeWrapper(final Object item, final TreeNodeWrapper parent)
		{
			super();
			this.item = item;
			depth = parent != null ? parent.depth + 1 : 0;
		}

		public TreeNodeWrapper()
		{
			depth = 0;
		}

		public Object getItem()
		{
			return item;
		}

		public int getDepth()
		{
			return depth;
		}

		public boolean hasLeafInformation()
		{
			return leaf != null;
		}

		public void setLeaf(final boolean leaf)
		{
			this.leaf = Boolean.valueOf(leaf);
		}

		public boolean isLeaf()
		{
			if (leaf == null)
			{
				return false;
			}
			else
			{
				return leaf.booleanValue();
			}
		}
	}

	protected class CommentTreeRenderer implements TreeitemRenderer
	{
		private final TypedObject currentItem;
		private final Tree commentTree;


		public CommentTreeRenderer(final TypedObject currentItem, final Tree commentTree)
		{
			this.currentItem = currentItem;
			this.commentTree = commentTree;
		}

		@Override
		public void render(final Treeitem item, final Object data) throws Exception
		{
			AbstractCommentModel commentModel = null;
			if (data instanceof TreeNodeWrapper && ((TreeNodeWrapper) data).getItem() instanceof AbstractCommentModel)
			{
				commentModel = (AbstractCommentModel) ((TreeNodeWrapper) data).getItem();
			}

			if (commentModel != null)
			{
				final TypedObject commentTO = getTypeService().wrapItem(commentModel);

				Treerow tr = item.getTreerow();
				if (tr == null)
				{
					(tr = new Treerow()).setParent(item);
				}

				UITools.modifySClass(tr, "commentsTreeRow", true);
				if (commentModel instanceof CommentModel)
				{
					UITools.modifySClass(tr, "comment_root", true);
				}

				tr.getChildren().clear();

				final TypedObject typedComment = UISessionUtils.getCurrentSession().getTypeService().wrapItem(commentModel);
				final EventListener openCommentListener = new EventListener()
				{
					@Override
					public void onEvent(final Event event) throws Exception
					{
						final UIBrowserArea browserArea = UISessionUtils.getCurrentSession().getCurrentPerspective().getBrowserArea();

						CommunicationBrowserModel communicationBrowser = null;

						final List<BrowserModel> browsers = browserArea.getBrowsers();
						for (final BrowserModel browser : browsers)
						{
							if (browser instanceof CommunicationBrowserModel)
							{
								communicationBrowser = (CommunicationBrowserModel) browser;
								break;
							}
						}

						if (communicationBrowser == null)
						{
							communicationBrowser = getCommunicationBrowserModel();
							browserArea.addVisibleBrowser(0, communicationBrowser);
						}

						communicationBrowser.setRootType(UISessionUtils.getCurrentSession().getTypeService()
								.getObjectTemplate("AbstractComment"));
						communicationBrowser.setViewMode("COMMENT");
						communicationBrowser.setOpen(Collections.singletonList(typedComment));
						communicationBrowser.setContextVisible(false);
						browserArea.show(communicationBrowser);

						// FacebookComment - load all comments posted by the user
						final Object object = currentItem.getObject();
						ItemModel item = null;
						UserModel user = null;
						if (object instanceof FacebookUserModel)
						{
							user = (FacebookUserModel) object;
						}
						else
						{
							item = (ItemModel) object;
						}
						communicationBrowser.updateItems(getCockpitCommentService().getItemCommentsQuery(item, user,
								Collections.singletonList(component), Collections.singletonList(commentType)));
					}
				};

				final Treecell dateCell = new Treecell();
				final Treecell userCell = new Treecell();

				final Label authorLabel = new Label(UISessionUtils.getCurrentSession().getLabelService()
						.getObjectTextLabel(UISessionUtils.getCurrentSession().getTypeService().wrapItem(commentModel.getAuthor())));
				authorLabel.addEventListener(Events.ON_CLICK, openCommentListener);
				userCell.appendChild(authorLabel);

				final Treecell attachmentCell = new Treecell();
				if (!commentModel.getAttachments().isEmpty())
				{
					final Image attachmentImage = new Image(IMG_ATTACHMENT);
					attachmentImage.setTooltip(Labels.getLabel("comment.attachment"));
					attachmentImage.setSclass("header");
					attachmentCell.appendChild(attachmentImage);
				}

				final Object modifiedDate = TypeTools.getPropertyValue(getValueService(), commentTO, getTypeService()
						.getPropertyDescriptor(ItemModel._TYPECODE + "." + ItemModel.MODIFIEDTIME));

				String modifiedDateString = null;
				if (ValueHandler.NOT_READABLE_VALUE.equals(modifiedDate))
				{
					modifiedDateString = modifiedDate.toString();
				}
				else if (modifiedDate instanceof Date)
				{
					modifiedDateString = formatModifiedDate((Date) modifiedDate);
				}
				dateCell.appendChild(new Label(modifiedDateString));
				dateCell.addEventListener(Events.ON_CLICK, openCommentListener);

				final String text = TypeTools.getPropertyValueAsString(getValueService(), commentTO, getTypeService()
						.getPropertyDescriptor(CommentsConstants.TC.ABSTRACTCOMMENT + "." + AbstractCommentModel.TEXT));
				final String commentText = UITools.removeHtml(text);
				final Treecell commentCell = new Treecell();
				commentCell.appendChild(new Label(commentText));
				commentCell.addEventListener(Events.ON_CLICK, openCommentListener);

				if (!getCockpitCommentService().isRead(commentTO))
				{
					UITools.modifySClass(commentCell, "commentNotRead", true);
				}

				final Div actionDiv = new Div();
				actionDiv.setSclass("commentsActionCnt");
				final Treecell actionCell = new Treecell();

				actionCell.appendChild(actionDiv);

				renderActions(commentModel, actionDiv, commentTree, currentItem);

				final Popup popup = tooltipRenderer.renderItemTooltip(commentModel);
				userCell.appendChild(popup);
				userCell.setTooltip(popup);
				dateCell.setTooltip(popup);
				attachmentCell.setTooltip(popup);
				commentCell.setTooltip(popup);

				tr.appendChild(commentCell);
				tr.appendChild(attachmentCell);
				tr.appendChild(userCell);
				tr.appendChild(dateCell);
				tr.appendChild(actionCell);

				item.addEventListener(Events.ON_OPEN, new EventListener()
				{
					@Override
					public void onEvent(final Event event) throws Exception
					{
						if (section instanceof CustomEditorSection)
						{
							((CustomEditorSection) section).setAttribute(OPENED_NODES_ATTRIBUTE, getOpenedNodes(commentTree, 10));
						}
					}
				});
			}
		}
		//		private Popup renderPopup(final AbstractCommentModel data)
		//		{
		//			final Popup popup = new Popup();
		//			popup.setSclass("commentPopup");
		//			final Div leftCol = new Div();
		//			leftCol.setSclass("leftCol");
		//
		//			// User Image
		//			final Object profilePicture = TypeTools.getPropertyValue(getValueService(), getTypeService().wrapItem(data.getAuthor()),
		//					getTypeService().getPropertyDescriptor(PrincipalModel._TYPECODE + "." + UserModel.PROFILEPICTURE));
		//			String profilePictureUrl = IMG_USER_DUMMY_SMALL;
		//			if (profilePicture != null && !ValueHandler.NOT_READABLE_VALUE.equals(profilePicture))
		//			{
		//				profilePictureUrl = UITools.getAdjustedUrl(((MediaModel) ((TypedObject) profilePicture).getObject()).getURL());
		//			}
		//
		//			final String author = UISessionUtils.getCurrentSession().getLabelService().getObjectTextLabel(
		//					UISessionUtils.getCurrentSession().getTypeService().wrapItem(data.getAuthor()));
		//			final Image userImage = new Image(profilePictureUrl);
		//			userImage.setHeight("50px");
		//			userImage.setTooltip(author);
		//			leftCol.appendChild(userImage);
		//
		//			final Div rightCol = new Div();
		//			rightCol.setSclass("rightCol");
		//
		//
		//			// add author text
		//			final Div authorDiv = new Div();
		//			authorDiv.setSclass("author");
		//			final Label authorLabel = new Label(author);
		//			authorDiv.appendChild(authorLabel);
		//			rightCol.appendChild(authorDiv);
		//
		//			// add creation time
		//			final Div creationDiv = new Div();
		//			creationDiv.setSclass("creationTime");
		//			final Label creationLabel = new Label();
		//
		//
		//			final Object creationDate = TypeTools.getPropertyValue(getValueService(), getTypeService().wrapItem(data),
		//					getTypeService().getPropertyDescriptor(ItemModel._TYPECODE + "." + ItemModel.MODIFIEDTIME));
		//
		//			if (ValueHandler.NOT_READABLE_VALUE.equals(creationDate))
		//			{
		//				creationLabel.setValue(creationDate.toString());
		//			}
		//			else
		//			{
		//				creationLabel.setValue(UITools.getLocalDateTime((Date) creationDate));
		//			}
		//			creationDiv.appendChild(creationLabel);
		//			rightCol.appendChild(creationDiv);
		//
		//			// add subject
		//			final Object subject = TypeTools.getPropertyValue(getValueService(), getTypeService().wrapItem(data), getTypeService()
		//					.getPropertyDescriptor(CommentsConstants.TC.ABSTRACTCOMMENT + "." + AbstractCommentModel.SUBJECT));
		//
		//			if (subject != null && !ValueHandler.NOT_READABLE_VALUE.equals(subject))
		//			{
		//				final Div subjectDiv = new Div();
		//				subjectDiv.setSclass("subject");
		//				final Label subjectLabel = new Label();
		//				subjectLabel.setValue((String) subject);
		//				subjectDiv.appendChild(subjectLabel);
		//				rightCol.appendChild(subjectDiv);
		//			}
		//
		//			// add comment text
		//			final String text = TypeTools.getPropertyValueAsString(getValueService(), getTypeService().wrapItem(data),
		//					getTypeService().getPropertyDescriptor(CommentsConstants.TC.ABSTRACTCOMMENT + "." + AbstractCommentModel.TEXT));
		//			final Div commentTextDiv = new Div();
		//			commentTextDiv.setSclass("commentText");
		//			final Html commentTextLabel = new Html();
		//			commentTextLabel.setContent(text);
		//
		//			commentTextDiv.appendChild(commentTextLabel);
		//			rightCol.appendChild(commentTextDiv);
		//
		//			final Hbox hbox = new Hbox();
		//			hbox.appendChild(leftCol);
		//			hbox.appendChild(rightCol);
		//			popup.appendChild(hbox);
		//			return popup;
		//		}
	}

	/**
	 * @param commentItem
	 * @param parent
	 * @param commentTree
	 * @param item
	 */
	protected void renderActions(final AbstractCommentModel commentItem, final Div parent, final Tree commentTree,
			final TypedObject item)
	{
		if (parent != null && commentItem != null)
		{
			final TypedObject typedObj = getTypeService().wrapItem(commentItem);
			parent.getChildren().clear();

			final ActionColumnConfiguration actionConfiguration = getCommentActionConfiguration();
			if (actionConfiguration == null)
			{
				return;
			}

			final List<ListViewAction> listViewActions = actionConfiguration.getActions();
			boolean firstIter = true;
			for (final ListViewAction listViewAction : listViewActions)
			{
				final ListViewAction.Context context = listViewAction.createContext(null, typedObj);
				final Map contextMap = context.getMap();
				contextMap.put(AbstractCommentAction.AREA, AbstractCommentAction.EDITOR_AREA);
				contextMap.put(AbstractCommentAction.PARENT, parent);
				contextMap.put(AbstractCommentAction.UPDATELISTENER, new EditorListener()
				{
					@Override
					public void valueChanged(final Object value)
					{
						update(item, commentTree);
					}

					@Override
					public void actionPerformed(final String actionCode)
					{
						// YTODO Auto-generated method stub
					}
				});

				// image (button)
				final String imgURI = listViewAction.getImageURI(context);
				if (imgURI != null && imgURI.length() > 0)
				{
					if (firstIter)
					{
						firstIter = false;
					}
					else
					{
						parent.appendChild(createSeparator());
					}

					final Image actionImg = new Image(imgURI);
					actionImg.setStyle("display: inline; cursor: pointer;");

					final EventListener listener = listViewAction.getEventListener(context);
					if (listener != null)
					{
						actionImg.addEventListener(Events.ON_CLICK, listener);
						actionImg.addEventListener("onLater", listener);
					}

					// Label
					if (listViewAction.getTooltip(context) != null && listViewAction.getTooltip(context).length() > 0)
					{
						actionImg.setTooltiptext(listViewAction.getTooltip(context));
					}

					// popup
					final Menupopup popup = listViewAction.getPopup(context);
					if (popup != null)
					{
						parent.appendChild(popup);
						actionImg.setPopup(popup);
					}
					parent.appendChild(actionImg);

					// context popup
					final Menupopup contextPopup = listViewAction.getContextPopup(context);
					if (contextPopup != null)
					{
						parent.appendChild(contextPopup);
						actionImg.setContext(contextPopup);
					}
				}
			}
		}
	}

	protected void update(final TypedObject currentItem, final Tree commentTree)
	{
		final List<List<Integer>> openNodes = getOpenedNodes(commentTree, 10);
		final CommentTreeModel commentTreeModel = new CommentTreeModel(new TreeNodeWrapper(), currentItem);
		commentTree.setModel(commentTreeModel);
		restoreOpenedState(commentTree, openNodes);
	}

	/**
	 * @return the domainCode
	 */
	public String getDomainCode()
	{
		if (domainCode == null)
		{
			domainCode = UITools.getCockpitParameter("default.commentsection.domaincode", Executions.getCurrent());
		}
		return domainCode;
	}

	/**
	 * @param domainCode
	 *           the domainCode to set
	 */
	public void setDomainCode(final String domainCode)
	{
		this.domainCode = domainCode;
	}

	/**
	 * @return the commentTypeCode
	 */
	public String getCommentTypeCode()
	{
		if (commentTypeCode == null)
		{
			commentTypeCode = UITools.getCockpitParameter("default.commentsection.commenttypecode", Executions.getCurrent());
		}
		return commentTypeCode;
	}

	/**
	 * @param commentTypeCode
	 *           the commentTypeCode to set
	 */
	public void setCommentTypeCode(final String commentTypeCode)
	{
		this.commentTypeCode = commentTypeCode;
	}

	/**
	 * @return the componentCode
	 */
	public String getComponentCode()
	{
		if (componentCode == null)
		{
			componentCode = UITools.getCockpitParameter("default.commentsection.componentcode", Executions.getCurrent());
		}
		return componentCode;
	}

	/**
	 * @param componentCode
	 *           the componentCode to set
	 */
	public void setComponentCode(final String componentCode)
	{
		this.componentCode = componentCode;
	}

	/**
	 * @return the maxReplyLevelValue
	 */
	public String getMaxReplyLevelValue()
	{
		return maxReplyLevelValue;
	}

	/**
	 * @param maxReplyLevelValue
	 *           the maxReplyLevelValue to set
	 */
	public void setMaxReplyLevelValue(final String maxReplyLevelValue)
	{
		this.maxReplyLevelValue = maxReplyLevelValue;
	}

	@Override
	@Required
	public void setTooltipRenderer(final TooltipRenderer tooltipRenderer)
	{
		this.tooltipRenderer = tooltipRenderer;
	}

}
