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
		<h1>Market Utils</h1>
		<div class = "row">
		   <div  class="col-sm-6">
			<div class="panel panel-success">
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
				   <input type="hidden" name="action" value="goToTDSS"/>	
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
			    <h3 class="panel-title">Market Data Summary</h3>
			  </div>
			  <div class="panel-body">
			  
			     <form  action="marketDataSummary" action="GET"> <!-- this invokes the method with mapping marketDataSummary -->
			        
			        <div class="row">
				        <div class="form-group">
					      <label for="sec">Event Set Id:</label>
					      <br>
					      <div class="col-sm-2">
					      	<input type="text" class="form-control" id="eventSetIDMU" name="eventSetIDMU">
					      </div>
					    </div>
				    </div>
				  
	     
					<br>
					<button type="submit" class="btn btn-warning">Invoke Summarise Market Data</button>
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
			    <h3 class="panel-title">Summarise Market Data Response</h3>
			  </div>
			  <div class="panel-body">
			    The return value from the service is: <b><c:out value="${MarketDataResponse}"></c:out></b>
			  </div>
			</div>
		  </div>
		</div>
		<!--separator-->
		
	   <div class = "row">
		   <div  class="col-sm-8">
			<div class="panel panel-success">
			   <div class="panel-heading">
			    <h3 class="panel-title">Currency Convert Market Data</h3>
			  </div>
			  <div class="panel-body">
			     <form action="currencyConvert" action="GET"> <!--action invoked the method mapped at currecny Convert  -->
			    
			        <div class="row">
				        <div class="form-group">
					      <label for="eventSetID">eventSetID:</label>
					      <br>
					      <div class="col-sm-2">
					      	<input type="text" class="form-control" id="eventSetIDCC" name="eventSetIDCC">
					      </div>
					      
					    </div>
					    <br>
					     <div class="form-group">
					      <label for="eventSetID">Yyour Target Currency:</label>
					      <br>
					      <div class="col-sm-2">
					      	<input type="text" class="form-control" id="currency" name="currency">
					      </div>
					    </div>
					    <br>
					     <div class="form-group">
					      <label for="eventSetID">Your Target Date:</label>
					      <br>
					      <div class="col-sm-2">
					      	<input type="text" class="form-control" id="targetDate" name="targetDate">
					      </div>
					    </div>
				    </div>
	     
					<br>
					<button type="submit" class="btn btn-warning">Invoke Convert Market Data</button>
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
			    <h3 class="panel-title">Convert Market Data Response</h3>
			  </div>
			  <div class="panel-body">
			    <b><c:out value="${convertResponse}"></c:out></b>
			  </div>
			</div>
		  </div>
		</div>
		
   </div>
</div>



<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>
