<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
        rel="stylesheet"  type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>TopDownSimpleService Client Application</title>
</head>
<body>
<div class="row">
   <div class="col-sm-offset-3">
		<h1>TopDownSimpleService Client</h1>
		<div class = "row">
		   <div  class="col-sm-6">
			<div class="panel panel-warning">
			   <div class="panel-heading">
			    <h3 class="panel-title">List of Current Market Data</h3>
			  </div>
			  <div class="panel-body">
			     <b><c:out value="${importedMarketData}"></c:out></b>
			  </div>
			</div>
		  </div>
		</div>

		<div class = "row">
		   <div  class="col-sm-6">
				<form action="home" action="GET">	
				  		 
					<button type="submit" class="btn btn-info">Go to TopDownSimpleService</button>
				</form>
				<form action="home2" action="GET">
		
					<button type="submit" class="btn btn-warning"> Go to MarketDataUtilService</button>
		       </form>
				
		  </div>
		</div>
		<br>
	   <div class = "row">
		   <div  class="col-sm-8">
			<div class="panel panel-success">
			   <div class="panel-heading">
			    <h3 class="panel-title">Import Market Data</h3>
			  </div>
			  <div class="panel-body">
			     <form  action="importMarket" action="GET">
			        <input type="hidden" name="action" value="invokeImportMarket"/>
			        <div class="row">
				        <div class="form-group">
					      <label for="sec">Stock Code:   Eg.BHP</label>
					      <br>
					      <div class="col-sm-2">
					      	<input type="text" class="form-control" id="sec" name="sec"> 
					      </div>
					    </div>
				    </div>
				    <div class="row">
					    <div class="form-group">
					      <label for="startDate">Start Date:    Eg.jan 1,2010</label>
					      <br>
					     <div class="col-sm-2">
						       <select class="form-control" id="startMonth" name="startMonth">
							    <option value="01">jan</option>
							    <option value="02">feb</option>
							    <option value="03">mar</option>
							    <option value="04">apr</option>
							    <option value="05">may</option>
							    <option value="06">jun</option>
							    <option value="07">jul</option>
							    <option value="08">aug</option>
							    <option value="09">sep</option>
							    <option value="10">oct</option>
							    <option value="11">nov</option>
							    <option value="12">dec</option>

							  </select> 
						  </div>
						  <div class="col-sm-2">
						     <input type="text" class="form-control" id="startDay" name="startDay">
						  </div>
						   <div class="col-sm-2">
						     <input type="text" class="form-control" id="startYear" name="startYear">
						  </div>
				
					    </div>
					</div>	
					
					<div class="row">
					    <div class="form-group">
					      <label for="startDate">End Date:   Eg.jan 1,2010</label>
					      <br>
					     <div class="col-sm-2">
						       <select class="form-control" id="endMonth" name="endMonth">
							    <option value="01">jan</option>
							    <option value="02">feb</option>
							    <option value="03">mar</option>
							    <option value="04">apr</option>
							    <option value="05">may</option>
							    <option value="06">jun</option>
							    <option value="07">jul</option>
							    <option value="08">aug</option>
							    <option value="09">sep</option>
							    <option value="10">oct</option>
							    <option value="11">nov</option>
							    <option value="12">dec</option>

							  </select> 
						  </div>
						  <div class="col-sm-2">
						     <input type="text" class="form-control" id="endDay" name="endDay">
						  </div>
						   <div class="col-sm-2">
						     <input type="text" class="form-control" id="endYear" name="endYear">
						  </div>
				
					    </div>
					</div>	     
					<br>
					<button type="submit" class="btn btn-warning">Invoke Import Market Data</button>
			     </form>
			  </div>
			</div>
		  </div>
		</div>
		<!-- separator -->
		<div class = "row">
		   <div  class="col-sm-6">
			<div class="panel panel-success">
			   <div class="panel-heading">
			    <h3 class="panel-title">Import Market Data Response</h3>
			  </div>
			  <div class="panel-body">
			    The return value from the service is: <b><c:out value="${returnData}"></c:out></b>
			  </div>
			</div>
		  </div>
		</div>
		<!--separator-->
		
	   <div class = "row">
		   <div  class="col-sm-8">
			<div class="panel panel-warning">
			   <div class="panel-heading">
			    <h3 class="panel-title">Download Market Data</h3>
			  </div>
			  <div class="panel-body">
			     <form action="downloadFile" action="GET">
			     <input type="hidden" name="action" value="invokeDownloadFile"/>
			        <div class="row">
				        <div class="form-group">
					      <label for="eventSetID">Stock Code:</label>
					      <br>
					      <div class="col-sm-2">
					      	<input type="text" class="form-control" id="eventSetID" name="eventSetID">
					      </div>
					    </div>
				    </div>
	     
					<br>
					<button type="submit" class="btn btn-warning">Invoke Download Market Data</button>
			     </form>
			  </div>
			</div>
		  </div>
		</div>
		
		
		<!-- separator -->
		<div class = "row">
		   <div  class="col-sm-6">
			<div class="panel panel-warning">
			   <div class="panel-heading">
			    <h3 class="panel-title">Download Market Data Response</h3>
			  </div>
			  <div class="panel-body">
			    This is your download Link:<b><c:out value="${downloadLink}"></c:out></b>
			  </div>
			</div>
		  </div>
		</div>
		
   </div>
</div>



<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>
