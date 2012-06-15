package org.xeoframework.data;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import netgest.bo.runtime.EboContext;
import netgest.bo.runtime.boObject;
import netgest.bo.runtime.boObjectList;
import netgest.bo.runtime.boRuntimeException;

public class DeleteData {

	private EboContext ctx;
	private HttpServletResponse response;
	
	public DeleteData(EboContext ectx, HttpServletResponse res){
		ctx = ectx;
		response = res;
	}
	
	
	public void deleteLibrary(){
		try {response.getWriter().write("<b>A apagar LIB_Book</b><br/>");		
				deleteXEOModel("LIB_Book");			
			response.getWriter().write("<b>A apagar LIB_Author</b><br/>");		
				deleteXEOModel("LIB_Author");
			response.getWriter().write("<b>A apagar LIB_BookCategory</b><br/>");
				deleteXEOModel("LIB_BookCategory");
			response.getWriter().write("<b>A apagar LIB_Publisher</b><br/>");
				deleteXEOModel("LIB_Publisher");
				response.getWriter().write("<b>A apagar LIB_Librarian</b><br/>");
				deleteXEOModel("LIB_Librarian");
				response.getWriter().write("<b>A apagar LIB_User</b><br/>");
				deleteXEOModel("LIB_User");
				ctx.close();
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void deleteXEOModel(String name){
		
		try {
			String boql = "select " + name;
			boObjectList list = boObjectList.list(ctx, boql);
			list.beforeFirst();
			while (list.next()){
				boObject obj = list.getObject();
				if (name.equals("LIB_Book")){
					response.getWriter().write( obj.getAttribute("title")+"<br/>");		
				}else
					if(name.equals("LIB_User")||name.equals("LIB_Librarian"))
					response.getWriter().write( obj.getAttribute("username")+"<br/>");
					else
						response.getWriter().write( obj.getAttribute("name")+"<br/>");
				obj.destroy();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (boRuntimeException e) {
			e.printStackTrace();
		}
	}
	
}
