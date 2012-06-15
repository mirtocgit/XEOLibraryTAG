package org.xeoframework.components.attributes;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class AttributeOutputBean extends XEOBaseBean {
String output;

public String getOutput() {
	if(output==null)
		output="Some text to show!";
	return output;
}
public void setOutput(String output) {
	this.output = output;
}
public boolean validateOutput(){
	if (output.length()<1)
		return false;
	return true;

}

}
