$(document).ready(function ()
{
	//if (parent.notifyIframeAboutUrlChange)
	//{
		//parent.notifyIframeAboutUrlChange(window.location.href, ACC.previewCurrentPagePk, ACC.previewCurrentUserId, ACC.previewCurrentJaloSessionId, ACC.previewSearchStateUrl, ACC.previewSearchStateQuery);
		parent.postMessage({eventName:'notifyIframeAboutUrlChange', data: [window.location.href,  ACC.previewCurrentPagePk, ACC.previewCurrentUserId, ACC.previewCurrentJaloSessionId, ACC.previewSearchStateUrl, ACC.previewSearchStateQuery]},'*')
	//}
});