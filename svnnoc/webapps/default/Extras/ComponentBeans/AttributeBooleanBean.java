package org.xeoframework.components.attributes;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class AttributeBooleanBean extends XEOBaseBean {

	boolean bol;
	String submit;
	public String isBol() {
		if(bol)
		return "1";
		return "0";
	}
	public String getBol(){
		if(bol)
			return "1";
			return "0";
	}
	public void setBol(boolean bol) {
		this.bol = bol;
	}
	public void setBol(String bool){
		if(bool.equals("0"))
			bol=false;
		else
			bol=true;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	public boolean validateBol(){
		if(bol)
			return true;
		return false;
	}
	public void getButtonClick(){
		if(bol)
			submit="true";
		else
			submit="false";
	}
	
	
}
