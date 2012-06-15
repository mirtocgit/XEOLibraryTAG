<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.xeoframework.library.modelRender.ModelToStringRender"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>

<%

	String param = (String) request.getParameter("comp");

	java.net.URL url = config.getServletContext().getResource("/");
	String path = url.getPath() + param;
	FileInputStream f = new FileInputStream(config.getServletContext().getRealPath("/"+param));

	ModelToStringRender mr = ModelToStringRender.getModelRender();
	
	out.print(mr.renderXEOModel(f));

%>