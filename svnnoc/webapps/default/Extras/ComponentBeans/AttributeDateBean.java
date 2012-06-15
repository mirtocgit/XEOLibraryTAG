package org.xeoframework.components.attributes;

import java.util.Calendar;
import java.util.Date;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class AttributeDateBean extends XEOBaseBean {
Date date;
String submit;
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getSubmit() {
	return submit;
}
public void setSubmit(String submit) {
	this.submit = submit;
}
public boolean validateDate(){
	Date dateNow=Calendar.getInstance().getTime();
	if(date.before(dateNow))
	return true;
	return false;
}

public void getButtonClick(){
	submit=date.toString();
}
}
