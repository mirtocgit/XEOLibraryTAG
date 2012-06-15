<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="netgest.bo.system.boApplication"%>
<%@page import="netgest.bo.system.boSession"%>
<%@page import="netgest.bo.runtime.EboContext"%>
<%@page import="org.xeoframework.data.DeleteData"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete</title>
</head>
<body>
<%

boApplication app = boApplication.getDefaultApplication();
//Login a user
boSession bsession = app.boLogin("SYSUSER", "ABC");
//Create a context
EboContext ctx = app.createContext(bsession);

DeleteData data = new DeleteData(ctx,response);
data.deleteLibrary();

ctx.close();
bsession.closeSession();



%>
</body>
</html>