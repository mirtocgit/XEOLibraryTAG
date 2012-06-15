<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.util.*"%>
<%@ page import="netgest.bo.*"%>
<%@ page import="netgest.bo.dochtml.*"%>
<%@ page import="netgest.bo.runtime.*"%>
<%@ page import="netgest.bo.def.*"%>
<%@ page import="netgest.utils.*,netgest.bo.system.*"%>
<%@ page import="javax.ejb.*"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Edit Publisher</title>
   	<style>
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
   	
   		label
{
width: 4em;
float: left;
text-align: right;
margin-right: 0.5em;
display: block
}

.submit input
{
margin-left: 4.5em;
} 
input
{
color: #669;
background: #EFEFFB;
border: 1px solid #CCCCCC;
}

.submit input
{
color: #339;
background: #669;
border: 2px outset #d7b9c9
} 
fieldset
{
border: 1px solid #000000;
width: 30em
}

legend
{
color: #fff;
background: #339;
border: 1px solid #781351;
padding: 2px 6px
}
   	</style>
  </head>  
  <body>

<%
boApplication app = boApplication.getDefaultApplication();
boSession bsession = app.boLogin("SYSUSER", "ABC");
EboContext ctx = app.createContext(bsession);
EboContext boctx = null;
String boui=request.getParameter("boui");
String savePressed=request.getParameter("save");
  try {
   
 
  boSession bosession = (boSession)request.getSession().getAttribute("boSession");

  boctx = ctx;

  boObject obj=null;
  if (boui==null || boui.length() == 0 || boui.equalsIgnoreCase(""))
	  obj=boObject.getBoManager().createObject(boctx,"LIB_Publisher");
  else
	  obj=boObject.getBoManager().loadObject(boctx,new Long(boui).longValue());
    
  
  if (savePressed!=null)
  {
	String name=request.getParameter("name");
	String address=request.getParameter("address");
	obj.getAttribute("name").setValueString(name);
	obj.getAttribute("address").setValueString(address);
	obj.update();	
	response.sendRedirect("ObjectListing.jsp?name=LIB_Publisher");
  }
 %> 


<h1>Create/Edit Book</h1>
<form name='edit' action='editPublisher.jsp' method='POST'>
<fieldset>
<legend>Book Edit Form</legend>
<table width='50%'  >
	<tr>
		<td><label>Name</label></td>
		<td><input type=text name='name' size='50' value='<%=obj.getAttribute("name").getValueString()%>'></td>
	</tr>
	<tr>
		<td><label>Address</label></td>
		<td><input type=text name='address' size='50' value='<%=obj.getAttribute("address").getValueString()%>'></td>
	</tr>
	<tr>
	<td colspan='2'>
		<input type=hidden name='save' value='save'>
		<input type=hidden name='boui' value='<%=boui%>'>
		 <input type=submit value='Save'>
	</td>	 
	</tr>
</table>
	 </fieldset>
  </form>
<% out.print("<a href='ObjectListing.jsp?name=LIB_Publisher'>Back to List</a><br><br>"); %> 
 <% 
boctx.close();
 ctx.close();
 bsession.closeSession();
}
catch (Exception e)
{
  boctx.rollbackContainerTransaction();
  boctx.close();
  out.print("Erro: "+e.getMessage());
}

%>


  
  </body>
</html>
