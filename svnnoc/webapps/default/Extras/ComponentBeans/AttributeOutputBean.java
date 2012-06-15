package org.xeoframework.components.attributes;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class AttributeOutputBean extends XEOBaseBean {
String output;
String submit;

public String getOutput() {
	if(output==null)
		output="Some text to show!";
	return output;
}
public void setOutput(String output) {
	this.output = output;
}
public String getSubmit() {
	return submit;
}
public void setSubmit(String submit) {
	this.submit = submit;
}
public void getButtonClick(){
	submit=output;
}
public boolean validateOutput(){
	if (output.length()<1)
		return false;
	return true;

}

}
