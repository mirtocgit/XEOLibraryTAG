package org.xeoframework.components.tabs;

import netgest.bo.xwc.components.classic.Tabs;
import netgest.bo.xwc.components.classic.scripts.XVWScripts;
import netgest.bo.xwc.framework.XUIMessage;
import netgest.bo.xwc.framework.XUIRequestContext;
import netgest.bo.xwc.framework.components.XUIViewRoot;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

/**
 * 
 * Ban to support the example of a Multi-Step Wizard using forms
 * 
 * @author Pedro Rio
 *
 */
public class TabWizardForm extends XEOBaseBean {

	private Float progress = (float) 0;
	private String percentage = "0%";
	
	/**
	 * Advances the form to second step
	 */
	public void step1(){
		Tabs tabs = (Tabs) getViewRoot().findComponent("form:tabs");
		tabs.setActiveTab("t2");
		progress = (float) 0.33;
		percentage = "33%";
	}

	
	public void step2(){
		Tabs tabs = (Tabs) getViewRoot().findComponent("form:tabs");
		tabs.setActiveTab("t3");
		progress = (float) 0.66;
		percentage = "66%";
	}
	
	public void finish(){
		
		XUIMessage m = new XUIMessage(XUIMessage.TYPE_ALERT, XUIMessage.SEVERITY_INFO, "Congratulations", "Form filled");
		getRequestContext().addMessage("message", m);
		progress = (float) 1.0;
		percentage = "100%";
	}
	
	
	public void canCloseTab(){
		XUIRequestContext oRequestContext;
		oRequestContext = XUIRequestContext.getCurrentContext();
		XVWScripts.closeView( oRequestContext.getViewRoot() );
    	XUIViewRoot viewRoot = oRequestContext.getSessionContext().createView("netgest/bo/xwc/components/viewers/Dummy.xvw");
    	oRequestContext.setViewRoot( viewRoot );
		oRequestContext.renderResponse();
	}
	
	
	
	public Float getProgress() {
		return progress;
	}

		public String getPercentage() {
		return percentage;
	}
	
	
	
}
