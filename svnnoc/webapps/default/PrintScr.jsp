  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
     pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.nio.CharBuffer"%>
<%@page import="java.io.StringReader"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.uwyn.jhighlight.renderer.XmlXhtmlRenderer"%><html>


<%
//Usage: PrintScr.jsp?comp=viewers/Ebo_Perf/edit.xvw
		


String xeoModelPath = (String) request.getParameter("xeoModelPath");
String param = (String) request.getParameter("comp");

java.net.URL url = config.getServletContext().getResource("/");
String path = url.getPath() + param;
FileInputStream f = new FileInputStream(config.getServletContext().getRealPath("/"+param)); 


byte[] b = new byte[4096];
int rb = 0;
StringBuilder sb = new StringBuilder();
while ((rb = f.read(b)) > 0 )
{
     sb.append(new String(b));

}

String result = new String();

XmlXhtmlRenderer render = new XmlXhtmlRenderer();

//System.out.println(sb.toString());

if(xeoModelPath == null)
	result = render.highlight(param,sb.toString(),"UTF-8", false);
else
	result = render.highlight(xeoModelPath, sb.toString(), "UTF8", false);

out.print(result);

%>