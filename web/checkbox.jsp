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
        <LINK href="main.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Place Order</title>
    </head>
    <body>
        <div>
	    <h1>Menu</h1>
	    <Form method="Post" action ="Controller">
		<%
		    List<MenuItem> menu = (List) request.getAttribute("menu");
		    for (MenuItem m:menu){%>	

			<input type="checkbox" id ="<%=m.getName()%>" name="<%= m.getName()%>" value ="test">
			<%= m.getName() + " - " + m.getPrice() %>
			<br>		     
			<%}%>

		<input id ="submit" type ="Submit" value ="Place Order" name ="placeOrder">
	    </form>	
        </div>
    </body>
</html>
