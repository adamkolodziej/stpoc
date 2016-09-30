<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<template:page pageTitle="${pageTitle}">
	<div id="globalMessages">
		<common:globalMessages/>
	</div>

    <div class="section tailored">
            <div class="container">
              <div class="row" style="padding-bottom:20px">
                <div class="col-xs-12">
                  <div class="padding-top-70">
                    <div class="text-center color-text-k6 font-weight-light">
                      <h1 class="font-weight-light color-text-k3">Tailored to your needs</h1><span>Delivering the network infrastructure solutions you need</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row" style="padding-bottom:20px">
                <div class="col-sm-4 col-sm-offset-2">
                  <div class="padding-top-70">
                    <div class="text-center-xs"><img src="/yacceleratorstorefront/_ui/desktop/common/images/sptel-landing-icon-1.png" alt=""></div>
                    <br><br>
                    <h4 class="text-center-xs font-weight-bold">OpticNet</h4>
                    <br>
                    <p class="text-center-xs min-height-120">Our OpticNet service offers one of the most diverse, extensive and secure dark fibre networks in Singapore. Because we build and own our fibre network, we are able to offer our customers and partners tailored solutions – giving them the flexibility and speed they need to be nimble and to stay ahead of the competition.</p>
                    <br>
                    <div class="text-center-xs"><a href="opticnet.html"><div class="btn-learn-more">LEARN MORE</div></a></div>
                  </div>
                </div>
                <div class="col-sm-4">
                  <div class="padding-top-70">
                    <div class="text-center-xs"><img src="/yacceleratorstorefront/_ui/desktop/common/images/sptel-landing-icon-2.png" alt=""></div>
                    <br><br>
                    <h4 class="text-center-xs font-weight-bold">SmartConnect</h4>
                    <br>
                    <p class="text-center-xs min-height-120">SP Telecom’s SmartConnect service combines the power of Ethernet with Dense Wavelength Division Multiplexing (DWDM) to optimise connectivity at more cost-efficient, secure and speedy connections – whether between people and businesses or between data centres and key interconnection hubs.</p>
                    <br>
                    <div class="text-center-xs"><a href="smartconnect.html"><div class="btn-learn-more">LEARN MORE</div></a></div>
                  </div>
                </div>
              </div>

            </div>
          </div>
	
</template:page>

<style>
 .btn-learn-more {
    background-color: white;
    display: inline-block;
    line-height: 2;
    color: #00a7d8;
    font-size: 0.9em;
    padding: 1em 2em;
    border-radius: 2em;
    border-color: #00a7d8;
    border-width: 2px;
    border-style: solid;
    cursor: pointer;
    text-align: center;
    line-height: 1em;
 }

</style>
