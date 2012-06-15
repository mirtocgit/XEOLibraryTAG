package xeo.code.java.demo1_0;

import netgest.bo.runtime.*;

public class DemoHandler {

	public boolean age_Valid(boObject obj,AttributeHandler attribute) throws boRuntimeException {
		if (attribute.getValueLong() > 18){
			return true;
		}
		else
		{
			obj.addErrorMessage("Age must be > 18");
			return false;
		}
	}

	public String timeToRetire_Formula(boObject obj,AttributeHandler attribute) throws boRuntimeException {
		return String.valueOf(65 - obj.getAttribute("age").getValueLong());
	}

	public void age_onAfterChange(boObject obj,AttributeHandler attribute) throws boRuntimeException {
		
	}


}