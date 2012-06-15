package org.xeoframework.components.attributes;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class AttributeTextAreaBean extends XEOBaseBean {
String textArea;
String submit;

public String getTextArea() {
	return textArea;
}
public void setTextArea(String textArea) {
	this.textArea = textArea;
}
public String getSubmit() {
	return submit;
}
public void setSubmit(String submit) {
	this.submit = submit;
}

public boolean validateTextArea(){
	if(textArea.length()<2)
	return false;
	return true;
}
public void getButtonClick(){
	submit=textArea;
}

}
