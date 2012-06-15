package org.xeoframework.components.title;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class TitleBean extends XEOBaseBean {
	
	private String titleText = "Simple Title";

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}
	
	public void buttonClick() {
		
	}

	public String getTitleText() {
		return titleText;
	}
	
}
