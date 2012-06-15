package xeo.code.java.demo1_0;

import netgest.bo.runtime.*;
import netgest.io.FSiFile;
import netgest.io.FSiFileProvider;

public class FileObjectHandler {
//TODO indicar o caminho para uma imagem e um document word
	//imagem em webapps/default/Extras/SampleImage
	//doc em webapps/default/Extras/SampleDocs
	public void onAfterCreate(boObject obj,boEvent event) throws boRuntimeException {
		obj.getAttribute("image").setValueiFile(new FSiFile(new FSiFileProvider(),"C:\\Users\ngrosskopf\\Desktop\\xeo.gif",null));
		obj.getAttribute("word").setValueiFile(new FSiFile(new FSiFileProvider(),"C:\\Users\ngrosskopf\\Desktop\\wordMacro.doc",null));
	}


}