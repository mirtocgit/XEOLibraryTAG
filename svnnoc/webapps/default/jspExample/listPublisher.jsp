<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.util.*"%>
<%@ page import="netgest.bo.*"%>
<%@ page import="netgest.bo.runtime.*"%>
<%@ page import="netgest.bo.def.*"%>
<%@ page import="netgest.utils.*,netgest.bo.system.*"%>
<%@ page import="javax.ejb.*"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Authors List</title>
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
	width: 480px;
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
EboContext boctx = null;
boSession bosession= (boSession)request.getSession().getAttribute("boSession");
  try {
  if (bosession==null) 
  {
	  bosession = boApplication.getApplicationFromConfig("xeo").
  		boLogin("SYSUSER",boLoginBean.getSystemKey());
	  request.getSession().setAttribute("boSession",bosession);

  }

  boctx = bosession.createRequestContext(request,response,pageContext);    
  
  boObjectList list=boObjectList.list(boctx,"Select LIB_Publisher where 1=1");
  
  list.beforeFirst();
  %>
  <h1 class='text'>List of Publishers</h1>
  <table width="50%" id="background-image">
	<tr>
		<th scope="col">
		<%=boDefHandler.getBoDefinition("LIB_Publisher").getAttributeRef("name").getLabel() %></th>
		<th scope="col">
		<%=boDefHandler.getBoDefinition("LIB_Publisher").getAttributeRef("address").getLabel() %></th>
		<th scope="col">&nbsp;</th>
	</tr>
  	<tbody>
  <%
  while (list.next())
  {
	out.println("<tr>");
    boObject obj= list.getObject();    
    out.println("<td>");
    	out.print(obj.getAttribute("name").getValueString());
    out.println("</td>");
    out.println("<td>");
		out.print(obj.getAttribute("address").getValueString());
	out.println("</td>");
	out.println("<td >");
    out.print("   <a href='editPublisher.jsp?boui="+obj.getBoui()+"'>edit</a>");
    out.println("</td>");
    out.println("</tr>");
  }
  boctx.close();

}
catch (Exception e)
{
  boctx.close();
  out.print("Erro: "+e.getMessage());
}

%>
</tbody>
</table>
<% out.print("<a href='editPublisher.jsp'>New</a><br><br>"); %>
  </body>
</html>