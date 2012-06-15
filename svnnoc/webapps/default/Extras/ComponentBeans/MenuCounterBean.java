package org.xeoframework.components.menuCounter;

import netgest.bo.xwc.framework.XUIRequestContext;
import netgest.bo.xwc.framework.XUIScriptContext;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class MenuCounterBean extends XEOBaseBean {

	/**
	 * Variable to hold the static counter value
	 */
	private String staticCounterValue = "2";
	
	/**
	 * 
	 * Retrieves the value for the static counter
	 * 
	 * @return
	 */
	public String getStaticCounterValue(){
		return staticCounterValue;
	}
	
	/**
	 * Updates the menu with the static value
	 */
	public void updateMenu(){
		staticCounterValue = "5";
		//MenuCounter.updateClientCounter("form:myMenuCounterStatic");
		
		XUIRequestContext.getCurrentContext()
		.getScriptContext()
			.add( XUIScriptContext.POSITION_FOOTER , Math.random() + "updCtnr", "XVW.MenuCounter.updateCounter('form:myMenuCounterStatic')" );
	}
	
}
