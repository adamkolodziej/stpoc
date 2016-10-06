<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<template:page pageTitle="${pageTitle}">
<div id="globalMessages">
    <common:globalMessages/>
</div>
<!-- Content -->
<div id="content">
    <div class="container">
        <!-- Sub header -->
        <div class="row margin-bottom-20">
            <div class="col-xs-12">
                <div class="sub-header padding-top-5 padding-bottom-5 padding-left-5 padding-right-5">
                    <div class="row">
                        <div class="col-md-7 text-right">
                            <ul class="style-reset">
                                <li>Quotation</li>
                                <li>Order</li>
                                <li>Provide</li>
                                <li>Assure</li>
                            </ul>
                        </div>
                        <div class="col-md-5 text-right">
                            <ul class="style-reset">
                                <li>Manage organisation</li>
                                <li>Billing &amp; Invoicing</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Breadcrumb -->
        <div class="row margin-bottom-30">
            <div class="col-xs-12">
                <div class="top-breadcrumb">
                    <ul class="style-reset">
                        <li>Home</li>
                        <li>
                            <a href="#!" title="Dashboard">Dashboard</a>
                        </li>
                        <li class="current">Orders</li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Text -->
        <div class="row">
            <div class="col-xs-12">
                <span class="color-blue font-32">Orders</span>

                <!-- Search icon for search modal -->
                <span class="glyphicon glyphicon-search color-blue font-24 margin-left-30 cursor-pointer"
                      aria-hidden="true" data-toggle="modal" data-target="#search-modal"></span>
            </div>
        </div>

        <!-- Tabs -->
        <div class="row">
            <div class="col-xs-12">
                <div>
                    <!-- Nav tabs -->
                    <div class="row margin-bottom-20">
                        <div class="col-xs-8">
                            <ul class="nav nav-tabs nav-custom" role="tablist">
                                <li role="presentation" class="active">
                                    <a href="#new-orders" aria-controls="new-quotations" role="tab" data-toggle="tab">
                                        New orders
                                    </a>
                                </li>
                                <li role="presentation">
                                    <a href="#sla-close-by" aria-controls="sla-close-by" role="tab" data-toggle="tab">
                                        SLA close by
                                    </a>
                                </li>
                                <li role="presentation">
                                    <a href="#closed" aria-controls="closed" role="tab" data-toggle="tab">Closed</a>
                                </li>
                                <li role="presentation">
                                    <a href="#show-all" aria-controls="show-all" role="tab" data-toggle="tab">Show all
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-xs-4 text-right">
                            <a href="packages/" class="btn btn-primary">Start new order</a>
                        </div>
                    </div>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <!-- New orders -->
                        <div role="tabpanel" class="tab-pane active" id="new-orders">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Reference number</th>
                                        <th>Product Type</th>
                                        <th>Package</th>
                                        <th>SLA</th>
                                        <th>Status</th>
                                        <th>Delivery date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${orders}" var="order">
                                        <tr>
                                            <th scope="row">${order.code}</th>
                                            <td>${order.sourceProductCode}</td>
                                            <td>${order.sourcePackageCode}</td>
                                            <td>N/D</td>
                                            <!-- Toggle for modal window -->
                                            <td>
                                                <span data-toggle="modal" data-target="#order-modal">${order.status}</span>
                                            </td>
                                            <td><fmt:formatDate type="date" value="${order.deliveryDate}" /></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <!-- SLA close by -->
                        <div role="tabpanel" class="tab-pane" id="sla-close-by">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Reference number</th>
                                        <th>Product Type</th>
                                        <th>Package</th>
                                        <th>SLA</th>
                                        <th>Status</th>
                                        <th>Delivery date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row">CC03-28</th>
                                        <td>Dark Fibre</td>
                                        <td>Platinium Package</td>
                                        <td>0</td>
                                        <td>Approved</td>
                                        <td>2016-10-12</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">CC03-19</th>
                                        <td>Data Centre Interconnect</td>
                                        <td>Greenfield Package</td>
                                        <td>0</td>
                                        <!-- Toggle for modal window -->
                                        <td>
                                            <span data-toggle="modal" data-target="#order-modal">Open</span>
                                        </td>
                                        <td>2016-10-09</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">CC03-29</th>
                                        <td>CPE</td>
                                        <td>Platinium Package</td>
                                        <td>3</td>
                                        <td>Pending</td>
                                        <td>2016-10-14</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">CC03-25</th>
                                        <td>ADV</td>
                                        <td>Contact Center Solutions</td>
                                        <td>0</td>
                                        <td>Open</td>
                                        <td>2016-10-12</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- Closed -->
                        <div role="tabpanel" class="tab-pane" id="closed">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Reference number</th>
                                        <th>Product Type</th>
                                        <th>Package</th>
                                        <th>SLA</th>
                                        <th>Status</th>
                                        <th>Delivery date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row">CC03-29</th>
                                        <td>CPE</td>
                                        <td>Platinium Package</td>
                                        <td>0</td>
                                        <td>Closed</td>
                                        <td>2016-10-14</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">CC03-19</th>
                                        <td>Data Centre Interconnect</td>
                                        <td>Greenfield Package</td>
                                        <td>0</td>
                                        <!-- Toggle for modal window -->
                                        <td>
                                            <span data-toggle="modal" data-target="#order-modal">Closed</span>
                                        </td>
                                        <td>2016-10-09</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">CC03-25</th>
                                        <td>ADV</td>
                                        <td>Contact Center Solutions</td>
                                        <td>0</td>
                                        <td>Closed</td>
                                        <td>2016-10-12</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">CC03-28</th>
                                        <td>Dark Fibre</td>
                                        <td>Platinium Package</td>
                                        <td>0</td>
                                        <td>Closed</td>
                                        <td>2016-10-12</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- Show all -->
                        <div role="tabpanel" class="tab-pane" id="show-all">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Reference number</th>
                                        <th>Product Type</th>
                                        <th>Package</th>
                                        <th>SLA</th>
                                        <th>Status</th>
                                        <th>Delivery date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row">CC03-19</th>
                                        <td>Data Centre Interconnect</td>
                                        <td>Greenfield Package</td>
                                        <td>8</td>
                                        <!-- Toggle for modal window -->
                                        <td>
                                            <span data-toggle="modal" data-target="#order-modal">Open</span>
                                        </td>
                                        <td>2016-10-09</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">CC03-20</th>
                                        <td>MetroEthernet</td>
                                        <td>Smart Living consumer pack 1</td>
                                        <td>7</td>
                                        <td>Pending</td>
                                        <td>2016-10-10</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">CC03-28</th>
                                        <td>Dark Fibre</td>
                                        <td>Platinium Package</td>
                                        <td>8</td>
                                        <td>Approved</td>
                                        <td>2016-10-12</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">CC03-25</th>
                                        <td>ADV</td>
                                        <td>Contact Center Solutions</td>
                                        <td>8</td>
                                        <td>Open</td>
                                        <td>2016-10-12</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">CC03-29</th>
                                        <td>CPE</td>
                                        <td>Platinium Package</td>
                                        <td>7</td>
                                        <td>Pending</td>
                                        <td>2016-10-14</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</template:page>
