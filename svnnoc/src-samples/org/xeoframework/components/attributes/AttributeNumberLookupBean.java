package org.xeoframework.components.attributes;

import java.math.BigDecimal;

import netgest.bo.xwc.xeo.beans.XEOEditBean;

public class AttributeNumberLookupBean extends XEOEditBean {
	BigDecimal number;
	String submit;

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}
		
	public boolean validateNumber(){
		if (number.intValue()>1)
			return true;
		else
			return false;
	}
	public void getButtonClick(){
		submit=number.toString();
	}
	public String getAttNumberDisp(){
		if (number != null)
			return number.toString();
		else
			return "";
	}

		
		
}
