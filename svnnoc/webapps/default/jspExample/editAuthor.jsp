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
    <title>Edit Book</title>
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
String boui =null;
String savePressed=null;
if (request != null) {
boui=request.getParameter("boui");
savePressed=request.getParameter("save");}

  try {
  boSession bosession = (boSession)request.getSession().getAttribute("boSession");
  boObject obj=null;
  if (boui==null || boui.length() == 0 || boui.equalsIgnoreCase(""))
	  obj=boObject.getBoManager().createObject(ctx,"LIB_Author");
  else
	  obj=boObject.getBoManager().loadObject(ctx,new Long(boui).longValue());
    
  if (savePressed!=null)
  {
	String name=request.getParameter("name");
	
	obj.getAttribute("name").setValueString(name);
	
	obj.update();	
	response.sendRedirect("ObjectListing.jsp?name=LIB_Author");
  }
 %> 


<h1>Create/Edit Book</h1>
		<form name='edit' action='editAuthor.jsp' method='POST'>
<fieldset>
<legend>Book Edit Form</legend>
<table width='50%'  >
	<tr>
		<td><label>Name</label></td>
		<td><input type=text name='name' size='50' value='<%=obj.getAttribute("name").getValueString()%>'></td>
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
 
<% out.print("<a href='ObjectListing.jsp?name=LIB_Author'>Back to List</a><br><br>"); %> 
 <%
ctx.close();
bsession.closeSession();
}
catch (Exception e)
{
  ctx.rollbackContainerTransaction();
  ctx.close();
  out.print("Erro: "+e.getMessage());
}

%>


  
  </body>
</html>
