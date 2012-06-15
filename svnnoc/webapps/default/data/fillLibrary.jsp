<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.xeoframework.data.FillData"%>
<%@page import="netgest.bo.system.boApplication"%>
<%@page import="netgest.bo.system.boSession"%>
<%@page import="netgest.bo.runtime.EboContext"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fill Library</title>
</head>
<body>
<% 

boApplication app = boApplication.getDefaultApplication();
//Login a user
boSession bsession = app.boLogin("SYSUSER", "ABC");
//Create a context
EboContext ctx = app.createContext(bsession);

FillData data = new FillData(ctx,response);

data.fillLibrary();

//context closed in fillLibrary
bsession.closeSession();
%>
</body>
</html>