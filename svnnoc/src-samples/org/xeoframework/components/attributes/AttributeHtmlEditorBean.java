package org.xeoframework.components.attributes;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class AttributeHtmlEditorBean extends XEOBaseBean {
String html;
String submit;

public String getHtml() {
if(html!=null)
	return html;
return "";
}
public void setHtml(String html) {
	this.html = html;
}
public String getSubmit() {
	return submit;
}
public void setSubmit(String submit) {
	this.submit = submit;
}

public boolean validateHtml(){
	
	if(html.length()<1)
	return false;
	return true;
}
public void getButtonClick(){
	submit=html;
}

}
