package org.xeoframework.components.attributes;

import java.sql.Date;
import java.sql.Timestamp;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class AttributeTimeBean extends XEOBaseBean {
Timestamp time;
String submit;

public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
	this.time = time;
}
public void setTime(Date date){
	this.time=Timestamp.class.cast(date);
}
public void setTime(String time){
	this.time=Timestamp.valueOf(time);
	}
public void setTime(Long date){
	this.time=Timestamp.class.cast(date);
}
public void setTime(Double date){
	this.time=Timestamp.class.cast(date);
}
public void setTime(float date){
	this.time=Timestamp.class.cast(date);
}
public String getSubmit() {
	return submit;
}
public void setSubmit(String submit) {
	this.submit = submit;
}

public void getButtonClick(){
	submit=time.toString();
}
	
public boolean validateTime(){
	return true;
}
}
