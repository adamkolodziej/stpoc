<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
width:200px;
background:black;
color:white;
border:none;
}
.line{
height:2px;background:black;
}


</style>

<div class="main_body">

<h1>You have raised new incident</h1>
<p>Reference number:${ticket.ticketID}</p>
<br>
<div class="display_block h3">Company details</div>
<hr class="line">

<div class="display_block mt20">
<div class="display_block h5">Company name</div>
<div class="h3 display_block" >${ticket.companyDetails.companyName}</div>
</div>

<div class="display_block mt20">
<div class="display_block h5">Contact name</div>
<div class="h3 display_block" >${ticket.companyDetails.contactName}</div>
</div>

<div class="display_block mt20">
<div class="display_block h5">Phone number</div>
<div class="h3 display_block" >${ticket.companyDetails.phoneNumber}</div>
</div>

<div class="display_block mt20">
<div class="display_block h5">Email address</div>
<div class="h3 display_block" >${ticket.companyDetails.emailID}</div>
</div>


<div class="display_block h3 mt100">Incident details</div>
<hr class="line">

<div class="display_block mt20">
<div class="display_block h5">Ticket type</div>
<div class="h3 display_block" >${ticket.incidentDetails.ticketType}</div>
</div>


<div class="display_block mt20">
<div class="display_block h5">Is this a repeat issue?</div>
<div class="h3 display_block" >${ticket.incidentDetails.repeatIssue}</div>
</div>


<div class="display_block mt20">
<div class="display_block h5">Customer reference</div>
<div class="h3 display_block" >${ticket.incidentDetails.customerRef}</div>
</div>


<div class="display_block mt20">
<div class="display_block h5">Prodcuts impacted</div>
<div class="h3 display_block" >${ticket.incidentDetails.productsImpacted}</div>
</div>  


<div class="display_block mt20">
<div class="display_block h5">Symptoms</div>
<div class="h3 display_block" >${ticket.incidentDetails.symptoms}</div>
</div>  


<div class="display_block mt20">
<div class="display_block h5">Can we intrusively test?</div>
<div class="h3 display_block" >${ticket.incidentDetails.intrusiveTestPossible}</div>
</div>

<div class="display_block mt20">
<div class="display_block h5">Any recent changes/planned work?</div>
<div class="h3 display_block" >${ticket.incidentDetails.recentChanges}</div>
</div>


<div class="display_block mt20">
<div class="display_block h5">Detailed description of the issue</div>
<div class="h3 display_block" >${ticket.incidentDetails.detailDescription}</div>
</div>        

<input type="submit" value="Raise another incident" onclick="location.href='https://localhost:9002/sptelstorefront/powertools/en/USD/createIncident'" class="btn alignRight mt100 "/>

</div>
