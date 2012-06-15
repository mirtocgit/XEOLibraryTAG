<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="netgest.bo.system.boApplication"%>
<%@page import="netgest.bo.system.boSession"%>
<%@page import="netgest.bo.runtime.EboContext"%>
<%@page import="org.xeoframework.data.ListObject"%>
<%@page import="netgest.bo.runtime.boObject"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Object Listing</title>
<style type="text/css">
    	 h1 {
    	 	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
			font-size: 30px;
    	 	color: #339;
    	 }
    	 
    	 
    	 a {
    	 	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
			font-size: 12px;
    	 	color: #339;
    	 	
    	 }
    	 
    	#background-image
{
	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
	font-size: 12px;
	margin: 15px;
	width: 80%;
	text-align: left;
	border-collapse: collapse;
	background: url('blurry.jpg') 330px 59px no-repeat;
}
#background-image th
{
	padding: 12px;
	font-weight: bold;
	font-size: 14px;
	color: #339;
}
#background-image td
{
	padding: 9px 12px;
	color: #669;
	border-top: 1px solid #fff;
}
#background-image tfoot td
{
	font-size: 11px;
}
#background-image tbody td
{
	background: url('back.png');
}
#background-image tbody tr:hover td
{
	color: #339;
	background: none;
}
    </style>


</head>
<body>

<% 
String req=request.getParameter("name").toString();
boApplication app = boApplication.getDefaultApplication();
//Login a user
boSession bsession = app.boLogin("SYSUSER", "ABC");
//Create a context
EboContext ctx = app.createContext(bsession);

ListObject data = new ListObject(ctx,response);

data.LoadXEOModelInstances(req);

ctx.close();
bsession.closeSession();
%>
</body>
</html>