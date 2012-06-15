package org.xeoframework.components.ajaxText;

import java.util.Date;

import netgest.bo.xwc.components.classic.AjaxText;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

/**
 * 
 * A bean to support the {@link AjaxText} component
 * 
 * @author Pedro Rio
 *
 */
public class AjaxTextBean extends XEOBaseBean {

	
	private String[] colors = new String[]{"red","blue","green","yellow","black","pink","brown"};
	
	/**
	 * 
	 * Retrieves the Text to update
	 * 
	 * @return The current date
	 */
	public String getText(){
		return new Date(System.currentTimeMillis()).toString();
	}
	
	
	/**
	 * 
	 * Retrieves the update time for AjaxText component
	 * 
	 * @return 1 second update (1000 miliseconds)
	 */
	public String getUpdateTime(){
		return "1000";
	}
	
	
	/**
	 * 
	 * Retrieves the Html to update
	 * 
	 * @return The current date in a different color
	 */
	public String getHtml(){
		String b = "Random changing color text every 2 seconds";
		Double val = (Math.random()*10) % colors.length;
		String result = "<font color='"+colors[val.intValue()]+"'>" + b + "</font>"; 
		return result;
	}
	
	
	/**
	 * 
	 * Retrieves the update time for AjaxText Html example
	 * 
	 * @return 2 second update (2000 miliseconds)
	 */
	public String getUpdateTimeHtml(){
		return "2000";
	}
	
	
}
