package org.xeoframework.data;

import javax.servlet.http.HttpServletResponse;

import netgest.bo.def.boDefAttribute;
import netgest.bo.def.boDefHandler;
import netgest.bo.runtime.EboContext;
import netgest.bo.runtime.boObject;
import netgest.bo.runtime.boObjectList;
import netgest.bo.runtime.boRuntimeException;

public class CheckAndFill {

	private EboContext ctx;

	public CheckAndFill(EboContext ctxt, HttpServletResponse res) {
		ctx = ctxt;
	}

	public String[] CheckXEOModelExists(String name) throws boRuntimeException {
		String boql = "select " + name;
		boObjectList list = boObjectList.list(ctx, boql);
		if (!list.isEmpty()) {
			list.beforeFirst();
			boObject object = list.getObject();
			boDefHandler h = object.bo_definition;
			boDefAttribute[] hand = h.getAttributesDef();
			String[] s = new String[hand.length];
			for (int i = 0; i < hand.length; i++) {
				s[i] = hand[i].getName();
			}
			return s;
		}
		return null;
	}
}