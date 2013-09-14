<%-- 
    Document   : checkbox
    Created on : Sep 11, 2013, 8:33:21 PM
    Author     : jryder
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <LINK href="main.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Place Order</title>
    </head>
    <body>

        <div>
	    <h1>Menu</h1>

	    <Form method="Post" action ="Controller">
			
		
		<%
		    HashMap menu = (HashMap) request.getAttribute("menu");

		    Iterator it = menu.entrySet().iterator();
		    while (it.hasNext()) {
		Map.Entry pairs = (Map.Entry) it.next();%>


		<input type="checkbox" id ="<%=pairs.getKey()%>" name="<%= pairs.getKey()%>" checked ="checked" value ="test">
		<%= pairs.getKey() + " - " + pairs.getValue()%>
		<br>

		<%
			it.remove(); // avoids a ConcurrentModificationException
		    }
		%>

		<input id ="submit" type ="Submit" value ="Place Order" name ="placeOrder">
		
	    </form>	
		
        </div>
    </body>
</html>
