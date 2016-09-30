<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<template:page pageTitle="${pageTitle}">

    <div class="container">
        <div class="col-xs-12">
            <div class="row">

                <div id="globalMessages">
                    <common:globalMessages/>
                </div>


                <cms:pageSlot position="TopContent" var="feature" element="div"
                              class="col-xs-12 wide-content-slot cms_disp-img_slot">
                    <cms:component component="${feature}"/>
                </cms:pageSlot>

                <!-- LS003 - My Account Product Lists -->
                <div class="col-xs-2">

                    <cms:pageSlot position="Section1" var="feature">
                        <cms:component component="${feature}" element="div" class="section1 cms_disp-img_slot"/>
                    </cms:pageSlot>
                    <!--/span-4 nav_column-->
                </div>
                <!--/span-4-->
                <div class="col-xs-10 last my_prodlists">
                    <!--
                    <div class="prodlist_header">
                    <h1>{PersonsName's} {ProdListName}</h1>
                    <ul class="nav">
                        <li><a href="#">Edit Details</a></li>
                        <li><a href="#">Share</a></li>
                        <li><a href="#">Delete</a></li>
                    </ul>
                    <a class="positive">Add new Product List</a>
                </div>-->
                    <!--/prodlist_header-->
                    <div>
                        <h1>My Product Lists</h1>
                    </div>

                    <form>
                        <div class="control-group">
                            <a class="positive btn btn-primary text-uppercase btn-xs-block btn-sm-inline-block visible"
                               href="create">Add new Product List</a>
                        </div>
                    <form>

                    <div>
                        <table class="table table-bordered table-condensed table-striped table-responsive">
                            <tbody>
                            <c:forEach var="productList" items="${productLists}">
                                <tr>
                                    <c:url value="/productlists/view/${productList.guid}"
                                           var="viewEncodedUrl"/>
                                        <%--<c:url value="/productlists/edit/${productList.guid}"--%>
                                        <%--var="editEncodedUrl" />--%>
                                    <td><a href="${viewEncodedUrl}">
                                        <c:out value="${productList.name} (${fn:length(productList.entries)})"/></a></td>
                                     <td><a href="${viewEncodedUrl}">View</a></td>
                                    <%--<td><a href="${editEncodedUrl}">Edit</a></td>--%>
                                    <%--<td><a href="#">Share</a></td>--%>
                                    <%--<td><a href="#">Delete</a></td>--%>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!--/item_container_holder-->
                </div>
                <!--/span-20-->
                <!-- /LS003 - My Account Product Lists -->

            </div>

        </div>
    </div>

</template:page>
