$(document).ready(function ()
{
	if (parent.notifyIframeAboutUrlChanage)
	{
		parent.notifyIframeAboutUrlChanage(window.location.href, ACCMOB.previewCurrentPagePk, ACCMOB.previewCurrentUserId, ACCMOB.previewCurrentJaloSessionId, ACCMOB.previewSearchStateUrl, ACCMOB.previewSearchStateQuery);
	}
});