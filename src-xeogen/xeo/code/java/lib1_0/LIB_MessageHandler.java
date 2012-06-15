package xeo.code.java.lib1_0;

import java.util.Calendar;
import java.util.Date;

import netgest.bo.runtime.*;

public class LIB_MessageHandler {

	public boolean date_Required(boObject obj,AttributeHandler attribute) throws boRuntimeException {
		Calendar dueDate = Calendar.getInstance();
        return new Date(dueDate.getTimeInMillis()).toString() != null;
	}


}