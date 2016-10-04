<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fb:recommendations
	 site="${domains}"
	 width="${width}" 
	 height="${height}" 
	 header="${showHeader}" 
	 colorscheme="${colorScheme}"
	 border_color="${borderColor}"
	 font="${font}"
	 max_age="${maxAge}"
	 link_target="${linkTarget}"
	 ref="${ref}">
</fb:recommendations>