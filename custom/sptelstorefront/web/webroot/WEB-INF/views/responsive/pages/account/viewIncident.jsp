<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>




<style>
.main_body{
width:100%;
 padding:10px 10px 10px 10px;
}
tbody tr:nth-child(odd) {
   background-color: #ccc;
}
table{
width:50%;
  border-collapse: collapse; 
}
td{
font-size:14px;
}
th{
font-zie:16px;
font-style:Arial, Helvetica, sans-serif;
font-weight:bold;
|
.bg{
background-color: #ccc;
}

</style>
<c:url var="sortUrl" value='/viewIncident/sortIncidents'/>
<div class="main_body">
<h1>Incidents </h1>
<br>
<div style="width="50%;">
<form:form  method="POST" action="${sortUrl}"  >
<input type="submit"  id="closed" style="height: 40px;width: 100px;background: #ccc;color: black;border: none;" name="sortCriteria" value="Closed" />
<input type="submit"  id="new" style="height: 40px;width: 100px;background: #ccc;color: black;border: none;" name="sortCriteria" value="New" />
<input type="button"  id="all" style="height: 40px;width: 100px;background: #ccc;color: black;border: none;" name="all" value="All" onclick="location.href='https://localhost:9002/sptelstorefront/powertools/en/USD/viewIncident'" />
<input type="button" value="Raise incident" onclick="location.href='https://localhost:9002/sptelstorefront/powertools/en/USD/createIncident'"style="height: 40px;width: 100px;background: #ccc;color: black;border: none;float:right;margin-right:100px;" />

</form:form>


<div>
<br><br>


<div id="viewTable" style="margin-bottom:30px;" >
<table>
<tr style="height:30px;">
<th>Ticket ID</th>
<th>State</th>
<th>Category</th>
</tr>

<c:forEach items="${tickets}" var="ticket" varStatus="loop">

    <tr style="border-bottom: 1pt solid black;" >
        <td>${ticket.ticketID}</td>
		<td>${ticket.state}</td>
		<td>${ticket.category}</td>
	<tr>
	
    
</c:forEach>
	
	</table>
	</div>
	</div>