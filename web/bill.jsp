<%-- 
    Document   : checkbox
    Created on : Sep 11, 2013, 8:33:21 PM
    Author     : jryder
--%>

<%@page import="model.MenuItem"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	
	
	<style>
	    img{
		width:50px;
		height:50px;
	    }
	   h1{text-decoration:underline;} 
	   h2{text-decoration:underline;}
	    
	</style>
        <LINK href="main.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Final Bill</title>
    </head>
    <body>

        <div>
	    <h1>Order Summary</h1>

	    <Form method="Post" action ="Controller">
		<h2>Items Ordered:</h2> <br>	
		<%
		    List<MenuItem> menu = (List) request.getAttribute("menu");

			for (MenuItem m : menu){
		%>
		
		
		
		<%= "<strong>" + m.getName() + "</strong>" + " - " + m.getPrice() %> <br>
		<img src ="images/<%= m.getName() %>.jpg"> <br><br>
			
		<%}%>
		
		
		<h2>Amount Due </h2>
		
		Food and Beverage: $<%= request.getAttribute("orderSubTotal")%>  <br>
		5.1% Sales Tax: $<%= request.getAttribute("tax")%>  <br>
		<strong> Total Bill: $<%= request.getAttribute("orderGrandTotal")%> </strong> <br>
		    
		<br>
		
	    </form>	
		
        </div>
    </body>
</html>
