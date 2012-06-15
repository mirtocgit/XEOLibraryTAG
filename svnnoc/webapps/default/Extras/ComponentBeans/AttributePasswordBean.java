package org.xeoframework.components.attributes;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class AttributePasswordBean extends XEOBaseBean {
String password;
String submit;

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getSubmit() {
	return submit;
}
public void setSubmit(String submit) {
	this.submit = submit;
}

public void getButtonClick(){
	submit=password;
}
public boolean validatePassword(){
	if(password.length()<1)
	return false;
	return true;
}

}
