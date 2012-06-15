package org.xeoframework.demos.data;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import netgest.bo.runtime.EboContext;
import netgest.bo.runtime.boObject;
import netgest.bo.runtime.boObjectList;
import netgest.bo.runtime.boRuntimeException;

public class DeleteDemos {

	
	private EboContext ctx;
	private HttpServletResponse response;
	
	public DeleteDemos(EboContext ectx, HttpServletResponse res){
		ctx = ectx;
		response = res;
	}
	
	public void Delete(){
	deleteDemo("Demo");
	}
		
		public void deleteDemo(String name){
			
			try {
				response.getWriter().write("Deleting Demos... <br/>");
				String boql = "select " + name;
				boObjectList list = boObjectList.list(ctx, boql);
				list.beforeFirst();
				while (list.next()){
					boObject obj = list.getObject();
							response.getWriter().write( obj.getAttribute("name")+"<br/>");
							
					obj.destroyForce();
					
				}
				response.getWriter().write("Deleted");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (boRuntimeException e) {
				e.printStackTrace();
			}
		}
		
		
	}
