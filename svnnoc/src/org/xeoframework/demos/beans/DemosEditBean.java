package org.xeoframework.demos.beans;

import netgest.bo.xwc.framework.XUIMessage;
import netgest.bo.xwc.framework.XUIRequestContext;
import netgest.bo.xwc.xeo.beans.XEOEditBean;

public class DemosEditBean extends XEOEditBean {
	
	@Override
	public void save(){
		XUIRequestContext.getCurrentContext().addMessage("SAVE", 
				new XUIMessage(XUIMessage.TYPE_ALERT, 
						XUIMessage.SEVERITY_INFO, 
						"Notice", "Saving has been disabled in this demo"));
	}

	@Override
	public void saveAndClose(){
		this.save();
	}
	
	@Override
	public void removeConfirm(){
		XUIRequestContext.getCurrentContext().addMessage("SAVE", 
				new XUIMessage(XUIMessage.TYPE_ALERT, 
						XUIMessage.SEVERITY_INFO, 
						"Notice", "Deleting has been disabled in this demo"));
	}
	
	
	
}
