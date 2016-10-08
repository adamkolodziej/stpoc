<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="productSet" tagdir="/WEB-INF/tags/addons/productsets/desktop/productset" %>

<div class="prod_add_to_cart">
	<productSet:quantityField product="${product}" baseProductCode="${baseProductCode}"/>
</div><!--/prod_add_to_cart-->
<div>			
	<productSet:genericVariantSelector code="${product.code}" variants="${productSelectOptions}" baseProductCode="${baseProductCode}" baseStockLevel="${product.stock.stockLevelStatus.code}" />
</div>