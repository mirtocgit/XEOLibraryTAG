package org.xeoframework.components.toolBar;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class ToolBarBean extends XEOBaseBean {
	
	private String textToShow = "<br/>Click in one of the tow Tool Bar buttons.";
	
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
	
}
