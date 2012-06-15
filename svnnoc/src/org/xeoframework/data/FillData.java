package org.xeoframework.data;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import netgest.bo.runtime.EboContext;
import netgest.bo.runtime.boObject;
import netgest.bo.runtime.boRuntimeException;

public class FillData {

	private long isbn =100001;
	private EboContext ctx;
	private HttpServletResponse response;
	private int numAuthors;
	private boObject[] authors= new boObject[4];
	public FillData(EboContext ectx, HttpServletResponse res){
		ctx = ectx;
		response = res;
	}
	
	
	public void fillLibrary(){
		try {response.getWriter().write("<b>Criados Autores:</b><br/>");
		
			ctx.beginContainerTransaction();
			
			boObject a1 = createAuthor("Jerry Lee Ford");					
			boObject a2 = createAuthor("Wallace Wang");				
			boObject a3 = createAuthor("Greg M. Perry");			
			boObject a4 = createAuthor("Michael Dawson");
			boObject a5 = createAuthor("Alexander Stepanov");						
			boObject a6 = createAuthor("Paul McJones");
			boObject a7 = createAuthor("Warren Sande");
			boObject a8 = createAuthor("Carter Sande");
			boObject a9 = createAuthor("Steve McConnell");
			boObject a10 = createAuthor("John M. Zelle");
			boObject a11 = createAuthor("Brian W. Kernigham");
			boObject a12 = createAuthor("Dennis M. Ritchie");
			boObject a13 = createAuthor("Joe Conway");
			boObject a14 = createAuthor("Aaron Hillegass");
			boObject a15 = createAuthor("Mark Rippetoe");
			boObject a16 = createAuthor("Lon Kilgore");
			boObject a17 = createAuthor("Andrew Hunt");
			boObject a18 = createAuthor("David Thomas");
			boObject a19 = createAuthor("Stephen G. Cochan");
			boObject a20 = createAuthor("Michael L. Scott");
			boObject a21 = createAuthor("John Mongan");
			boObject a22 = createAuthor("Noah Suojanen");
			boObject a23 = createAuthor("Erik Giguerè");
			boObject a24 = createAuthor("Joyce Farrell");
			boObject a25 = createAuthor("Gayle Laakman");
			boObject a26 = createAuthor("Jon Bentley");
			boObject a27 = createAuthor("Toby Segaran");
			boObject a28 = createAuthor("Bjarne Stroustrup");
			boObject a29 = createAuthor("Kathy Sierra");
			boObject a30 = createAuthor("Bert Bates");
			boObject a31 = createAuthor("Ian Cinnamon");
			boObject a32 = createAuthor("Jennifer Campbell");
			boObject a33 = createAuthor("Paul Gries");
			boObject a34 = createAuthor("Jason Montojo");
			boObject a35 = createAuthor("Greg Wilson");
			boObject a36 = createAuthor("Kevin Beaver");
			boObject a37 = createAuthor("Jacob Habgood");
			boObject a38 = createAuthor("Mark Overmars");
			boObject a39 = createAuthor("Phil Wilson");
			
			//Authors created
			response.getWriter().write("<br/><b>Criados Publishers:</b><br/>");
			boObject p1 = createPublisher("Thomson course technology PTR");	
			boObject p2 = createPublisher("Wiley Publishing");			
			boObject p3 = createPublisher("Publication Services, Inc");
			boObject p4 = createPublisher("Pearson Education, Inc");
			boObject p5 = createPublisher("Manning");
			boObject p6 = createPublisher("Microsoft Press");
			boObject p7 = createPublisher("Franklin, Beedle & Associates Inc.");
			boObject p8 = createPublisher("Prentice Hall PTR Prentice-Hall inc.");
			boObject p9 = createPublisher("Big Nerd Ranch Inc.");
			boObject p10= createPublisher("The Aasgaard Company");
			boObject p11= createPublisher("Addison-Wesley Professional");
			boObject p12= createPublisher("Sams");
			boObject p13= createPublisher("Morgan Kaufman");
			boObject p14= createPublisher("Wrox");
			boObject p15= createPublisher("Course Technology");
			boObject p16= createPublisher("Create Space");
			boObject p17= createPublisher("O'Reily Media");
			boObject p18= createPublisher("McGray-Hill/TAB Electronics");
			boObject p19= createPublisher("Pragmatic Bookshelf");
			createPublisher("Apress");
			
			//Created some publishers
			response.getWriter().write("<br/><b>Criada BookCategory:</b><br/>");
			boObject cat =  createBookCategory("Programming");
			 createBookCategory("Technology");
			createBookCategory("Horror");
			 createBookCategory("Romance");
			
			//create a librarian
			response.getWriter().write("<br/><b>Criado Bibliotecario:</b><br/>");	 
	createLibrarian(123, "libraran@library.xxx", "librarian");
			
			//create a user
			response.getWriter().write("<br/><b>Criado Utilizador:</b><br/>");
	createUser(111, "user@library.xxx", "User");
			
			//created Programing BookCategory
			response.getWriter().write("<br/><b>Criados Books:</b><br/>");
			createBook("Programming for the absolute beginer",a1, "1", p1, cat, "1");		
			 createBook("Beginning Programming All-In-One Desk reference for Dummies",a2, "1", p2, cat, "1");
		 createBook("Sams Teach Yourself Beginning Programming in 24 Hours",a3, "2", p3, cat, "1");
			 createBook("Beginning C++ Through Game Programming",a4, "3", p2, cat, "1");	
			 createBook("Beginning Programming for Dummies",a2, "4", p2, cat, "1");
			authors[0]=a5;
			authors[1]=a6;
			numAuthors=2;
			 createBook("Elements of Programming",authors, "1", p4, cat, "1");	
			authors[0]=a7;
			authors[1]=a8;
			 createBook("Hello World, Programmingor kids and other beginners",authors, "1", p5, cat, "1");	
			createBook("Code Complete: A Practical HandBook of Software Construction",a9, "2", p6, cat, "1");	
			 createBook("Python Programming: An Introduction to Computer Science",a10, "2", p7, cat, "1");	
			authors[0]=a11;
			authors[1]=a12;
			createBook("C Programming Language",authors, "2", p8, cat, "1");
			authors[0]=a13;
			authors[1]=a14;
			createBook("iPhone Programming: The Big Nerd Guide",authors, "1", p9, cat, "1");
			authors[0]=a15;
			authors[1]=a16;
			createBook("Practical Programming fo Strength Training",authors, "1", p10, cat, "1");
			createBook("Python Programming for The Abslute Beginner",a4, "3", p10, cat, "1");
			authors[0]=a17;
			authors[1]=a18;
			createBook("The Pragmatic Programmer: From Journeyman to Master",authors, "1", p11, cat, "1");
			createBook("Programming in C",a19, "3", p12, cat, "1");
			createBook("Programming Language Pragmatics",a20, "3", p13, cat, "1");
			authors[0]=a21;
			authors[1]=a22;
			authors[2]=a23;
			numAuthors=3;
			createBook("Programming Interviews Exposed: Landing Your Next Job",authors, "2", p14, cat, "1");
			createBook("Programming in Objective-C 2.0",a19, "2", p11, cat, "1");
			createBook("Programming Logic and Design Comprehensive",a24, "5", p15, cat, "1");
			createBook("Cracking the Coding Interview, Fourth Edition: 150 Programming Interview Questions and Solutions",a25, "4", p16, cat, "1");
			createBook("Programming Pearls",a26, "2", p11, cat, "1");
			createBook("Programming Collective Intelligence: Building Smart Web 2.0 Applications",a27, "1", p17, cat, "1");
			createBook("Programming: Principles and Practice Using C++",a28, "1", p11, cat, "1");
			authors[0]=a29;
			authors[1]=a30;
			numAuthors=2;
			createBook("Head First Java, 2nd Edition",authors, "1", p11, cat, "1");
			createBook("Programming Video Games for the Evil Genius",a31, "1", p18, cat, "1");
			authors[0]=a32;
			authors[1]=a33;
			authors[2]=a34;
			authors[3]=a35;
			numAuthors=4;
		createBook("Pragmatic Bookshelf",authors, "1", p19, cat, "1");
			createBook("Hacking for Dummies",a36, "3", p2, cat, "1");
			authors[0]=a37;
			authors[1]=a38;
			authors[2]=a39;
	
			numAuthors=3;
			createBook("The Game Maker's Apprentice: Game Development",authors, "1", p19, cat, "1");
			ctx.commitContainerTransaction();
			ctx.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (boRuntimeException e) {
			try {
				ctx.rollbackContainerTransaction();
			} catch (boRuntimeException e1) {
				e1.printStackTrace();
			}
		}	
		if (ctx != null)
			ctx.close();
	}
	
	
	public boObject createAuthor(String name){
		
		try {
			boObject obj = ctx.getApplication().getObjectManager().createObject(ctx, "LIB_Author");
			obj.getAttribute("name").setValueString(name);
			response.getWriter().write(name+"<br/>");
					obj.update();
			return obj;
		} catch (boRuntimeException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
public boObject createPublisher(String name) throws IOException{
		
		try {
			boObject obj = ctx.getApplication().getObjectManager().createObject(ctx, "LIB_Publisher");
			obj.getAttribute("name").setValueString(name);
			obj.getAttribute("address").setValueString("Address");
			obj.update();
			response.getWriter().write(name+"<br/>");
			return obj;
		} catch (boRuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}
public boObject createBookCategory(String name) throws IOException{
	
	try {
		boObject obj = ctx.getApplication().getObjectManager().createObject(ctx, "LIB_BookCategory");
		obj.getAttribute("name").setValueString(name);
		obj.update();
		response.getWriter().write(name+"<br/>");
		return obj;
	} catch (boRuntimeException e) {
		e.printStackTrace();
		return null;
	}
}
public boObject createBook(String title,boObject[] author,String edition,boObject publisher,boObject BookCategory,String state){
	
	try {
		boObject obj = ctx.getApplication().getObjectManager().createObject(ctx, "LIB_Book");
		int i=1;
		obj.getAttribute("title").setValueString(title);
		obj.getAttribute("isbn").setValueString(String.valueOf(isbn));
		obj.getAttribute("edition").setValueString(edition);
		obj.getAttribute("publisher").setValueString(String.valueOf(publisher));
		obj.getAttribute("categories").setValueString(String.valueOf(BookCategory));
		obj.getAttribute("state").setValueString(state);
		while( i<=numAuthors){
		obj.getBridge("authors").add(author[i-1].getAttribute("BOUI").getValueLong());
		i++;
		}
		isbn++;
		response.getWriter().write(obj.getAttribute("title")+"<br/>");
		obj.update();
		return obj;
	} catch (boRuntimeException e) {
		e.printStackTrace();
		return null;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
}
public boObject createBook(String title,boObject aut,String edition,boObject publisher,boObject BookCategory,String state){
	
	try {
		boObject obj = ctx.getApplication().getObjectManager().createObject(ctx, "LIB_Book");
		obj.getAttribute("title").setValueString(title);
		obj.getAttribute("isbn").setValueString(String.valueOf(isbn));
		obj.getAttribute("edition").setValueString(edition);
		obj.getAttribute("publisher").setValueString(String.valueOf(publisher));
		obj.getAttribute("categories").setValueString(String.valueOf(BookCategory));
		obj.getAttribute("state").setValueString(state);		
		obj.getAttribute("authors").setValueString(String.valueOf(aut));
		isbn++;
		response.getWriter().write(obj.getAttribute("title")+"<br/>");
		obj.update();
		
		return obj;
		
	} catch (boRuntimeException e) {
		e.printStackTrace();
		return null;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
}
public boObject createLibrarian(int employeeId,String email,String username){
	
	try {
		boObject obj = ctx.getApplication().getObjectManager().createObject(ctx, "LIB_Librarian");
		obj.getAttribute("employeeId").setValueLong(121);
		obj.getAttribute("email").setValueString(email);
		obj.getAttribute("username").setValueString(username);
		response.getWriter().write(obj.getAttribute("username")+"<br/>");
		obj.update();
		
		return obj;
		
	} catch (boRuntimeException e) {
		e.printStackTrace();
		return null;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
}
public boObject createUser(int userIdentifier,String email,String username){
	
	try {
		boObject obj = ctx.getApplication().getObjectManager().createObject(ctx, "LIB_User");
		obj.getAttribute("userIdentifier").setValueString(String.valueOf(userIdentifier));
		obj.getAttribute("email").setValueString(email);
		obj.getAttribute("username").setValueString(username);
		response.getWriter().write(obj.getAttribute("username")+"<br/>");
		obj.update();
		
		return obj;
		
	} catch (boRuntimeException e) {
		e.printStackTrace();
		return null;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
}



	
}
