<%@page import="netgest.bo.runtime.EboContext"%>
<%@page import="netgest.bo.runtime.boObjectList"%>
<%@page import="netgest.bo.runtime.boObject"%>
<%@page import="netgest.bo.system.boApplication"%>
<%@page import="netgest.bo.system.boSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

HttpSession session2 = request.getSession(true);

if (session2.getAttribute("boSession") == null)
{
	String user = "demo";
	String pass = "demo";
	String profile = "Demos";
	
	if (request.getParameter("user") != null && request.getParameter("pass") != null){
		user = request.getParameter("user");
		pass = request.getParameter("pass");
		if (request.getParameter("profile") != null)
			profile = request.getParameter("profile");
	}
	
	
	
	if (user != null && pass != null && profile != null 
			&& user.length() > 0 && pass.length() > 0 && profile.length() > 0 )
	{
		
		boSession oXeoSession = boApplication.getApplicationFromStaticContext("XEO").boLogin(user,pass);
	
		EboContext loginCtx = null;
		if( boApplication.currentContext().getEboContext() == null ) {
			loginCtx = oXeoSession.createRequestContext( null, null, null );
			boApplication.currentContext().addEboContext( loginCtx );
		}
		else {
			loginCtx = boApplication.currentContext().getEboContext();
		}
	
		boObject workPlace=null;
		boObjectList proflist=boObjectList.list(loginCtx, "select uiWorkPlace where name='"+profile+"'");
		if (proflist.next())
			workPlace=proflist.getObject();
		
		
		oXeoSession.setPerformerIProfileBoui( String.valueOf(workPlace.getBoui()) );
		session2.setAttribute( "boSession", oXeoSession );
		
		if (loginCtx != null)
			loginCtx.close();
		
	}
	else{
		response.sendRedirect("Login.xvw"); 
	}
}
response.sendRedirect("/xeo/Main_Components.xvw");
%> 
