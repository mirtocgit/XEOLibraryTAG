package org.xeoframework.data;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import netgest.bo.def.boDefAttribute;
import netgest.bo.def.boDefHandler;
import netgest.bo.runtime.EboContext;
import netgest.bo.runtime.boAttributesArray;
import netgest.bo.runtime.boObject;
import netgest.bo.runtime.boObjectList;
import netgest.bo.runtime.boRuntimeException;

public class ListObject {

	private HttpServletResponse response;
	private EboContext ctx;

	public ListObject(EboContext ctxt, HttpServletResponse res) {
		response = res;
		ctx = ctxt;
	}

	public void LoadXEOModelInstances(String name) {
		int counter1 = 0;
		int counter2;
		try {
			String boql = "select " + name+" order by SYS_DTCREATE DESC";
			boObjectList list = boObjectList.list(ctx, boql);
			String[] clName=name.split("LIB_");
			
			response.getWriter().write("<br/><h1 class='text'>" + clName[1] + "</b>    <div align='center'><a href='edit"+clName[1]+".jsp'>NEW</a></div>");
			if(list!=null&&!list.isEmpty()){
				list.beforeFirst();
			boObject object = list.getObject();
			boDefHandler defHandler = object.bo_definition;
			boDefAttribute[] attributesHandler = defHandler.getAttributesDef();
			response.getWriter().write("<table border='1'id='background-image'><tr>");
			for (int i = 0; i < attributesHandler.length; i++) {
				{
					String attName = attributesHandler[i].getName();
					if (!attName.equals("summary") && !attName.equals("PARENT"))
						if (!attName.equals("TEMPLATE") && !attName.equals("PARENTCTX"))
							if (!attName.equals("frontCover")
									&& !attName.equals("backCover"))
								if(counter1<8){
								response.getWriter().write("<th>"+
										attributesHandler[i].getLabel() + "</th>");
								counter1++;
								}
				}

			}
			response.getWriter().write("<th></th> </tr> ");
			list.beforeFirst();
			while (list.next()) {
				counter2 = 0;
				boObject obj = list.getObject();
				boAttributesArray attArray = obj.getAttributes();
				response.getWriter().write(" <tr> ");
				for (int i = 0; i < attributesHandler.length; i++) {
					String attName = attributesHandler[i].getName();
					if (!attName.equals("summary") && !attName.equals("PARENT"))
						if (!attName.equals("TEMPLATE") && !attName.equals("PARENTCTX"))
							if (!attName.equals("frontCover")
									&& !attName.equals("backCover")){
								if(counter2<8){
								response.getWriter().write("<td>"+
										attArray.get(attName).toString() + "</td> ");
									counter2++;
								}
				}
						
				}
				String clss=obj.getClass().toString();
				String[] cls=clss.split("LIB_");
				response.getWriter().write("<td><a href='edit"+cls[1]+".jsp?boui="+obj.getBoui()+"'>edit</a></td>");
				response.getWriter().write(" </tr> ");			
			}
			response.getWriter().write("</table>");
		} else {
			response.getWriter().write("No data in DB");
		}
			}catch (IOException e) {
			e.printStackTrace();
			if (ctx != null)
				ctx.close();
		} catch (boRuntimeException e) {
			e.printStackTrace();
			if (ctx != null)
			ctx.close();
		}
		if (ctx != null)
			ctx.close();	

	}

}
