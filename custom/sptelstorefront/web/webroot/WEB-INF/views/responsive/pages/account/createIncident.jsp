<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<style>
.main_body{
width:100%;
 padding:10px 10px 10px 10px;
}
.display_block{
display:block;
}
.h3{
font-size:150%;
}
.h5{
font-size:120%;
}
.h4{
font-size:100%;
color:#ff99ce;
}
.mt10{
margin-top:10px;
}
.mt20{
margin-top:25px;
}
.mt100{
margin-top:100px;
}
.input{
border: 0;
    outline: 0;
    background: transparent;
    border-bottom: thin solid black;
    width: 300px;
}
.alignRight{
float:right;
}
.alignLeft{
float:left
}
.clearFix{
clear:both;
}
.btn{
height:40px;
width:100px;
background:black;
color:white;
border:none;
}
.line{
height:3px;background:black;
}



</style>



<c:url var="ctickUrl" value='/createIncident/createTicket'/>
<div class="main_body" >
<form:form action="${ctickUrl}" method='POST'  >
<h2>Raise new incident </h2>
<br>
<div class="display_block h3">Company details</div>
<hr class="line">

<div class="display_block mt20">
<div class="display_block h5">Company name</div>
<input type="text" name="companyName" class="input display_block" />
<div class="display_block h4">Start typing company name</div>
</div>


<div class="display_block mt20">
<div class="display_block h5">Contact name</div>
<input type="text" name="contactName" class="input display_block" />
<div class="display_block h4">Please provide name</div>
</div>


<div class="display_block mt20">
<div class="display_block h5">Direct phone number</div>
<input type="text" name="phoneNumber" class="input display_block" />
<div class="display_block h4">Please provide phone number(8-10 digits)</div>
</div>



<div class="display_block mt20">
<div class="display_block h5">Email address</div>
<input type="text" name="emailID" class="input display_block" />
<div class="display_block h4">Please provide email address(name@company.com)</div>
</div>


<div class="display_block h3 mt100">Incident details</div>
<hr class="line">

<div class="display_block mt20">
<div class="display_block h5">Chose ticket type</div>
<select class="mt10 input" name="ticketType" >
  <option value="FLT - Service affecting fault">FLT - Service affecting fault</option>
  <option value="OUT - Customer planned outage">OUT - Customer planned outage</option>
  <option value="INV - Test/assist investigation">INV - Test/assist investigation</option>
  <option value="ADV - Reason for outage">ADV - Reason for outage</option>
</select>

</div>

<div class="display_block mt20" >
<div class="display_block h5">Is there a repeat issue?</div>
<div style="width:300px;" class="mt10 display_block"> 
 <div class="alignLeft"><input type="radio" name="repeat" class="alignLeft" value="Has happen for first time" />Yes </div>
 <div class="alignRight"> <input type="radio" name="repeat" class="alignRight"  value="Repeated more than once" />No </div>
</div>
</div>
<div class="clearFix display_block" />
<div class="display_block mt20 ">
<div class="display_block h5">Customer reference</div>
<input type="text" class="input display_block" name="customerReference" />
<div class="display_block h4">Please provide your refernce number</div>
</div>


<div class="display_block mt20">
<div class="display_block h5">Products impacted</div>
<select class="mt10 input" name="productsImpacted" >
  <option value="Don't remember">Don't remember</option>
  <option value="Dark fiber">Dark fiber</option>
  <option value="Data centre">Data centre</option>
  <option value="Metro ethernet">Metro ethernet</option>
  <option value="IPVPN">IPVPN</option>
  <option value="CPE">CPE</option> 
</select>

<div class="display_block mt20">
<div class="display_block h5">Symptoms</div>
<select class="mt10 input" name="symptoms" >
  <option value="Simply Not working">Simply not working</option>
  <option value="Hardware">Hardware</option>
  <option value="Hard down">Hard down</option>
  <option value="Latency">Latency </option>
  <option value="Packet loss">Packet loss</option>
  <option value="Bouncing">Bouncing</option> 
</select>

</div>


<div class="display_block mt20" >
<div class="display_block h5">Can we intrusively test?</div>
<div style="width:300px;" class="mt10 display_block"> 
 <div class="alignLeft"><input type="radio" name="test" class="alignLeft" value="Yes" />Yes </div>
 <div class="alignRight"><input type="radio" name="test" class="alignRight"  value="No" />No </div>
</div>
</div>
<div class="clearFix display_block" />


<div class="display_block mt20" >
<div class="display_block h5">Any recent changes/planned work?</div>
<div style="width:300px;" class="mt10 display_block"> 
 <div class="alignLeft"><input type="radio" name="changes" class="alignLeft" value="Yes" />Yes </div>
 <div class="alignRight"><input type="radio" name="changes" class="alignRight"  value="No" />No </div>
</div>
</div>
<div class="clearFix display_block" />



<div class="display_block mt20" >
<div class="display_block h5">Details description</div>
<input type="text" name="desc" style="height:70px;width:300px;margin-top:10px;" />
</div>



    <input type="submit" value="Raise incident" class="btn alignRight mt20 "/>
</form:form>
</div>





