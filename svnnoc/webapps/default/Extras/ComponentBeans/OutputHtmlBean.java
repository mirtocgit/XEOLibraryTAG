package org.xeoframework.components.outputHtml;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class OutputHtmlBean extends XEOBaseBean {

	private String textHtml = "<br/><font size='5'>OutputHtml</font><br/><br/>" +
			"<font color='blue'>Just a simple example.</font><br/><font>Place your text on the Text Area above" +
			" and submit.</font>";
	private String attributeTextArea = textHtml;
	
	public void buttonClick() {
		this.textHtml = getAttributeTextArea();
	}

	public void setTextHtml(String textHtml) {
		this.textHtml = textHtml;
	}

	public String getTextHtml() {
		return textHtml;
	}

	public void setAttributeTextArea(String attributeTextArea) {
		this.attributeTextArea = attributeTextArea;
	}

	public String getAttributeTextArea() {
		return attributeTextArea;
	}
	
}
