package org.xeoframework.components.attributes;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class AttributeTextBean extends XEOBaseBean {
String text;
String submit;


public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

public String getSubmit() {
	return submit;
}

public void setSubmit(String submit) {
	this.submit = submit;
}

public void getButtonClick(){
	submit=text;
}

public boolean validateText(){
	if(text.length()>0)
		return true;
	else
		return false;
}


}
