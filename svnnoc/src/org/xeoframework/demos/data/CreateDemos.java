package org.xeoframework.demos.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import netgest.bo.runtime.EboContext;
import netgest.bo.runtime.boObject;
import netgest.bo.runtime.boRuntimeException;

public class CreateDemos {
	private EboContext ctx;
	private HttpServletResponse response;
	private PrintWriter out;
	private int counter=0;
	boObject[] obj=new boObject[20];
	
	public CreateDemos(EboContext ectx, HttpServletResponse res) throws IOException {
		ctx = ectx;
		response = res;
		out=response.getWriter();
		
	}

	public void makeDemos() {
		try {
			Calendar birthDate =  Calendar.getInstance();
			Calendar birthDate1 =  Calendar.getInstance();
			Calendar birthDate2 =  Calendar.getInstance();
			Calendar birthDate3 =  Calendar.getInstance();
			Calendar birthDate4 =  Calendar.getInstance();
			Calendar date = Calendar.getInstance();
			Calendar date1 = Calendar.getInstance();
			Calendar date2 = Calendar.getInstance();
			Calendar date3 = Calendar.getInstance();
			Calendar date4 = Calendar.getInstance();
			String[] nomes={"","","","António","Pedro","John","Artur","Pacheco","Gonçalinho","André","Duarte","Maria","Joana","Cristina","Toni","Francisco","Luisa","Luis","Jorge","Yoshimitsu","Maria"};
			date1.set(1999, 8, 9);
			date2.set(2002, 1, 22);
			date3.set(2012, 11, 12);
			date4.set(1971, 10, 5);
			birthDate1.set(1960, 1, 7);
			birthDate2.set(1949, 8, 31);
			birthDate3.set(1971, 9, 22);
			birthDate4.set(1988, 11, 14); 
			int age=62;
			ctx.beginContainerTransaction();
			obj[0]=demos("João", age, "", "", date2, birthDate2, true);
			obj[1]=demos("Miguel", 39, obj[0], obj[0], date3, birthDate3,
					true);
			obj[2]=demos("Nuno", 22,  obj[0], obj[0], date4, birthDate4,
					true);
			int count=1;
			boolean yesNo=true;
			boolean sports=true;
			
			for (int i=3;i<obj.length;i++){
				if (count==1){
					date=date1;
					birthDate=birthDate1;
					age=51;
					count=2;
				}
				else if(count==2){
					date=date2;
					birthDate=birthDate2;
					age=62;
					count=3;
				}
				else if(count==3){
					date=date3;
					birthDate=birthDate3;
					age=39;
					count=4;
				}
				else if(count==4){
					date=date4;
					birthDate=birthDate4;
					age=22;
					count=1;
				}
					if(i>15)
						sports=false;
					
				if(yesNo){
				obj[i]=demos(nomes[i], age, obj[i-1], obj[i-1], date, birthDate,
						sports);
						yesNo=false;}
				else{
					obj[i]=demos(nomes[i], age, obj[i-2], obj[i-2], date, birthDate,
							sports);
							yesNo=true;}
				
				
			}
			ctx.commitContainerTransaction();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ctx.rollbackContainerTransaction();
			} catch (boRuntimeException e1) {
				e1.printStackTrace();
			}
			
		}
	}



	public boObject demos(String name, int age, Object bestFriend,
			Object allFriends, Calendar date, Calendar birthDate,
			boolean playsSports) {
		boObject obj = null;
		try {
			String biography = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.";
			obj = ctx.getApplication().getObjectManager().createObject(ctx,
					"Demo");
			obj.getAttribute("name").setValueString(name);
			obj.getAttribute("age").setValueString(String.valueOf(age));
			obj.getAttribute("favoriteDate").setValueDate(date.getTime());
			obj.getAttribute("dateBirth").setValueDate(birthDate.getTime());
			obj.getAttribute("playsSports").setValueBoolean(
					playsSports);
			obj.getAttribute("biography").setValueString(biography);
			
			if(counter>0){
				obj.getAttribute("bestFriend").setValueString(String.valueOf(bestFriend));
				obj.getAttribute("allFriends").setValueString(String.valueOf(allFriends));
				}			
			obj.update();
			out.write("Created: "+obj.getAttribute("name")+"<br/>");
			counter++;
		} catch (boRuntimeException e) {
			e.printStackTrace();		
		}
		return obj;
	}

}