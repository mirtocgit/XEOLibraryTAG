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
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
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

label {
	width: 4em;
	float: left;
	text-align: right;
	margin-right: 0.5em;
	display: block
}

.submit input {
	margin-left: 4.5em;
}

input {
	color: #669;
	background: #EFEFFB;
	border: 1px solid #CCCCCC;
}

.submit input {
	color: #339;
	background: #669;
	border: 2px outset #d7b9c9
}

fieldset {
	border: 1px solid #000000;
	width: 30em
}

legend {
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
//Login a user
boSession bsession = app.boLogin("SYSUSER", "ABC");
//Create a context
EboContext ctx = app.createContext(bsession);
	String boui = null;
	String savePressed = null;
	boObject obj = null;
	
	boui = request.getParameter("boui");
	savePressed = request.getParameter("save");
	try {

		boSession bosession = (boSession) request.getSession()
				.getAttribute("boSession");

			
		if (boui == null || boui.length() == 0
				|| boui.equalsIgnoreCase("")){
			obj = boObject.getBoManager().createObject(ctx,
					"LIB_Book");}
		else{
			obj = boObject.getBoManager().loadObject(ctx,
					new Long(boui).longValue());
		}
		if (savePressed != null) {
			String title = request.getParameter("title");
			String isbn = request.getParameter("isbn");
			String publisher = request.getParameter("publisher");
			String edition = request.getParameter("edition");
			String state = request.getParameter("state");

			obj.getAttribute("title").setValueString(title);
			obj.getAttribute("isbn").setValueString(isbn);
			obj.getAttribute("publisher").setValueString(publisher);
			obj.getAttribute("edition").setValueString(edition);
			obj.getAttribute("state").setValueString(state);
			obj.update();
			response.sendRedirect("ObjectListing.jsp?name=LIB_Book");
		}
	
%>
<h1>Create/Edit Book</h1>
<form name='edit' action='editBook.jsp?boui<%=%> %>' method='POST'>
<fieldset><legend>Book Edit Form</legend>
<table width='50%'>
	<tr>
		<td><label>Title</label></td>
		
		<td><input type=text name='title' size='50'
			value='<% if(obj.exists()){out.print(obj.getAttribute("title").getValueString());} %>'>
			</td>
	</tr>
	<tr>
		<td><label>ISBN</label></td>
		<td><input type=text name='isbn' size='50'
			value='<%=obj.getAttribute("isbn").getValueString()%>'></td>
	</tr>
	<tr>
		<td><label>Publisher</label></td>
		<td><input type=text name='publisher' size='50'
			value='<%=obj.getAttribute("publisher").getValueString()%>'></td>
	</tr>
	<tr>
		<td><label>Edition</label></td>
		<td><input type=text name='edition' size='50'
			value='<%=obj.getAttribute("edition").getValueString()%>'></td>
	</tr>
	<tr>
		<td><label>State</label></td>
		<td><input type=text name='state' size='50'
			value='<%=obj.getAttribute("state").getValueString()%>'></td>
	</tr>
	<tr>
		<td colspan='2'><input type=hidden name='save' value='save'>
		<input type=hidden name='boui' value='<%=boui%>'>
		<input type=submit value='Save'></td>
	</tr>
</table>
</fieldset>
</form>
<%
	out.print("<a href='ObjectListing.jsp?name=LIB_Book'>Back to List</a><br><br>");
%>
<%

ctx.close();
bsession.closeSession();
	} catch (Exception e) {
		ctx.rollbackContainerTransaction();
		ctx.close();
		out.print("Erro: " + e.getMessage());
	}
%>



</body>
</html>
