package xeo.code.java.lib1_0;

import java.sql.Date;
import java.util.Calendar;

import netgest.bo.runtime.AttributeHandler;
import netgest.bo.runtime.boObject;
import netgest.bo.runtime.boRuntimeException;

public class LIB_MessageHandler {

	public String date_DefaultValue(boObject obj,AttributeHandler attribute) throws boRuntimeException {
		Calendar dueDate = Calendar.getInstance(); 
		return new Date(dueDate.getTimeInMillis()).toString();
	}


}