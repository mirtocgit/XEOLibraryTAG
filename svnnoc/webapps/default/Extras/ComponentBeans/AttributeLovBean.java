package org.xeoframework.components.attributes;

import java.util.HashMap;

import netgest.bo.xwc.xeo.beans.XEOEditBean;
@SuppressWarnings("unchecked")
public class AttributeLovBean extends XEOEditBean {
	String selected;
HashMap lov;
String submit;

public void setLovMap(){
	lov=new HashMap();
		lov.put(1, "Male");
		lov.put(2, "Female");
	
}
public String getSelected() {
	return selected;
}
public void setSelected(String selected) {
	this.selected = selected;
}
public HashMap getLov() {
	if(lov==null)
	setLovMap();
	return lov;
}
public void setLov(HashMap lov) {
	this.lov = lov;
}
public String getSubmit() {
	return submit;
}
public void setSubmit(String submit) {
	this.submit = submit;
}
public boolean validateLov(){
	return true;
}
public void getButtonClick(){
submit=selected;
String name=(String) lov.get(Long.parseLong(selected));
submit+="<br/>"+name;
}

}
