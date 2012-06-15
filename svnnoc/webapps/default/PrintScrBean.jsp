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
<%@page import="com.uwyn.jhighlight.renderer.XmlXhtmlRenderer"%>
<%@page import="com.uwyn.jhighlight.renderer.JavaXhtmlRenderer"%>
<html>
<head>
	<script type="text/JavaScript" src="javahi/xregexp.js"></script>
	<script type="text/JavaScript" src="javahi/shCore.js"></script> 
	<script type="text/JavaScript" src="javahi/shBrushJava.js"></script>
	<link rel="stylesheet" type="text/css" href="javahi/shCore.css" />
	<link rel="stylesheet" type="text/css" href="javahi/shThemeEclipse.css" />
</head>

<body>

<%
//Usage: readViewer.jsp?comp=viewers/Ebo_Perf/edit.xvw
		



String param = (String) request.getParameter("comp");

java.net.URL url =config.getServletContext().getResource("/"+param);

String path = url.getPath();

//FileInputStream f = new FileInputStream(new File(path));
FileInputStream f = new FileInputStream(config.getServletContext().getRealPath("/"+param));


byte[] b = new byte[4096];
int rb = 0;
StringBuilder sb = new StringBuilder();
while ((rb = f.read(b)) > 0 )
{
     sb.append(new String(b));

}

//JavaXhtmlRenderer render = new JavaXhtmlRenderer();
//String result = render.highlight(param,sb.toString(),"UTF-8", false);


out.print("<pre class='brush: java'>");
out.print(sb.toString());
out.print("</pre>");
%>
<script type="text/javascript">
     SyntaxHighlighter.all()
</script>
</body>
</html>