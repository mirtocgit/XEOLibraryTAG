package org.xeoframework.components.toolBar;

import netgest.bo.xwc.components.classic.scripts.XVWScripts;
import netgest.bo.xwc.framework.XUIMessage;
import netgest.bo.xwc.framework.XUIRequestContext;
import netgest.bo.xwc.framework.components.XUIViewRoot;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class ToolBarBean extends XEOBaseBean {
	
	private String textToShow = "<br/>Click in one of the two Tool Bar buttons.";
	
	public void getViewText() {
		textToShow = "<br/>You clicked the button only described by text.<br/>As you can see the text was " +
				"shown on the same viewer. Try the next button to see what happens.";
	}
	
	public void showWindow() {
		textToShow = "You can change the target to a window or tab instead of self";
	}

	public void setTextToShow(String textToShow) {
		this.textToShow = textToShow;
	}

	public String getTextToShow() {
		return textToShow;
	}
	
	
	public void shortcutDemo(){
		getRequestContext().addMessage("shortcut", new XUIMessage(XUIMessage.TYPE_POPUP_MESSAGE, XUIMessage.SEVERITY_INFO, "Shortcut Example!", "You pressed Ctrl+e"));
	}
	
	public void canCloseTab(){
		XUIRequestContext oRequestContext;
		oRequestContext = XUIRequestContext.getCurrentContext();
		XVWScripts.closeView( oRequestContext.getViewRoot() );
    	XUIViewRoot viewRoot = oRequestContext.getSessionContext().createView("netgest/bo/xwc/components/viewers/Dummy.xvw");
    	oRequestContext.setViewRoot( viewRoot );
    	oRequestContext.renderResponse();
	}
	
}
