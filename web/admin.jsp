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
	
 
  </script>
		
    </head>
    <body>

        <div>
	    <h1>Menu Editor</h1>
   
		<%
		    List<MenuItem> menu = (List) request.getAttribute("menu");
		    for (MenuItem m:menu){%>	
			<Form method="Post" action ="Controller">  
			<input type="hidden" id ="ItemID" name="<%= "ItemID" %>" value ="<%= m.getItemId() %>">    
			<input type ="text" value ="<%= m.getName()%>" name ="Name">
			<input type ="text" value ="<%= m.getPrice()%>" name = "Price">
			<input type="Submit" id ="<%=m.getName()%>" name="Delete" value ="Delete">
			<input type ="Submit" value ="Update" name ="Update">
			</form>
			
			<br>
		<%}%>

	   	<Form method="Post" action ="Controller">
		Name: <input id ="Name" type ="text" value ="" name ="Name">
		Price: <input id ="Price" type ="text" value ="" name ="Price">
		<input type ="Submit" name="Add" value ="Add">
		<Form method="Post" action ="Controller">

        </div>
    </body>
</html>
