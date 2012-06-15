package xeo.code.java.lib1_0;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import netgest.bo.runtime.AttributeHandler;
import netgest.bo.runtime.boObject;
import netgest.bo.runtime.boRuntimeException;

public class LIB_LibrarianHandler {

	public boolean email_Valid(boObject obj,AttributeHandler attribute) throws boRuntimeException {
		
		//Keep the value to return
		boolean res = false;
		
		//Get the attribute value
		String email = attribute.getValueString();
		
		//Create a pattern to check for e-mails
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		
		//If the pattern matches, e-mail is ok
		if(m.matches() || email.length() == 0)
			res = true;
		else
			obj.addErrorMessage("Invalid e-mail " + email); //Else, add error 
		
		//Return the result
		return res;
		
	}

	public String username_Formula(boObject obj,AttributeHandler attribute) throws boRuntimeException {
		
		//Only use the rule for a new librarian
		if(!obj.exists()) {
			//Get the name value
			String firstName = obj.getAttribute("name").getValueString().toLowerCase().toString();
			//Get the last name value
			String lastName = obj.getAttribute("lastname").getValueString().toLowerCase().toString();
			
			//If both values are not null
			if(firstName.length() == 0 || lastName.length() == 0)
				return firstName + "." + "librarian";
			else
				return firstName + "." + lastName;
		} //In an existing object, return the existing value 
		else
			return obj.getAttribute("username").getValueString().toString();
		
	}

	

	

}