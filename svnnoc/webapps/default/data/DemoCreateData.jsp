<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="netgest.bo.system.boApplication"%>
<%@page import="netgest.bo.system.boSession"%>
<%@page import="netgest.bo.runtime.EboContext"%>
<%@page import="org.xeoframework.demos.data.CreateDemos"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DemosCreation</title>
</head>
<body>
<% 
boApplication app = boApplication.getApplicationFromStaticContext("XEO");
boSession bsession = app.boLogin("SYSUSER", "ABC");
EboContext ctx = bsession.createRequestContext(request,response,pageContext);

CreateDemos demos=new CreateDemos(ctx,response); 
demos.makeDemos(); 

ctx.close(); 
bsession.closeSession();

%>
</body>
</html>