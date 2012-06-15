package org.example.viewer.beans;

import netgest.bo.xwc.components.classic.GridPanel;
import netgest.bo.xwc.framework.components.XUIViewRoot;
import netgest.bo.xwc.xeo.beans.XEOEditBean;

public class LibReturnEditBean extends XEOEditBean {

	public void openLookup(){
		
		XUIViewRoot viewRoot = getSessionContext().createChildView("viewers/LIB_Return/ReturnBooks.xvw");
		LibBookReturnLookup bean = (LibBookReturnLookup) viewRoot.getBean("viewBean");
		
		XEOEditBean parentBean = getParentBean();
		Long boui = parentBean.getXEOObject().getBoui();
		
		bean.setParentBoui(boui);
		bean.setMultiLookup(true);
		
		bean.setParentBeanId("viewBean");
		bean.setParentParentBeanId("viewBean");
		
		GridPanel panel = (GridPanel) getViewRoot().findComponent(GridPanel.class);
		bean.setParentComponentId(panel.getClientId());
		bean.executeBoql("select LIB_Book where BOUI in (select LIB_Movement.books where boui = " + boui + ")");
		
		getRequestContext().setViewRoot(viewRoot);
		getRequestContext().renderResponse();
		
		
	}
	
}
