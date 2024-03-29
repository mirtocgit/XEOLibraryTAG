package v1_0;

import netgest.bo.runtime.*;
import netgest.bo.data.*;
import netgest.bo.def.*;
import netgest.bo.security.*;
import netgest.utils.*;
import netgest.io.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;
import java.io.*;
import java.math.*;



public  class Ebo_Document extends Ebo_DocBase implements  Serializable {  

   
   
   
    public netgest.bo.runtime.attributes.boAttributeObject docTemplate;    
    public netgest.bo.runtime.attributes.boAttributeString gdid;    
   
     
    public Ebo_Document() {
        super();
        bo_version      = "1.0";
        bo_name         = "Ebo_Document";
        bo_classregboui = "#BO.CLSREGBOUI#";
        bo_definition   = boDefHandler.getBoDefinition("Ebo_Document");
        bo_statemanager = bo_definition.getBoClsState() != null ? bo_definition.getBoClsState().getStateManager( this ) : null;  

        boAttributesArray atts = super.getAttributes();
        boAttributesArray stat = super.getStateAttributes();

       
        
       

       
        
       
        atts.add(docTemplate = new netgest.bo.runtime.attributes.boAttributeObject(this,"docTemplate"));
        
        atts.add(gdid = new netgest.bo.runtime.attributes.boAttributeString(this,"gdid"));
        



       
    }
		private BigDecimal verifyTransformer(ObjAttHandler att, BigDecimal boui)
    {
        try
        {
            netgest.bo.transformers.Transformer transfClass = null;
            if((transfClass = att.getDefAttribute().getTransformClassMap()) != null)
            {
                if(boui != null && att.getParent().getMode() != boObject.MODE_EDIT_TEMPLATE )
                {
                    boui = new BigDecimal(transfClass.transform(getEboContext(), att.getParent(), boui.longValue()));
                }
            }
        }
        catch (boRuntimeException e)
        {
            
        }
        return boui;
    }
    public void init() throws boRuntimeException {
        super.init();
        setModeView();
        if(p_mode == MODE_NEW && !p_clone)
        {
           
           
           
                if( docTemplate.haveDefaultValue()) 
                {
                	if(docTemplate.defaultValue() != null)
                	{
                    	docTemplate._setValue(boConvertUtils.convertToBigDecimal(docTemplate.defaultValue(), docTemplate));
                    }
                    else
                    {
                    	docTemplate._setValue(null);
                    }
                    docTemplate.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( gdid.haveDefaultValue()) 
                {
                	if(gdid.defaultValue() != null)
                	{
                    	gdid._setValue(boConvertUtils.convertToString(gdid.defaultValue(), gdid));
                    }
                    else
                    {
                    	gdid._setValue(null);
                    }
                    gdid.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
    
            if("Ebo_Document".equals(this.getName()))
            {
                calculateFormula(null);
            }
        }
    }
   
    
    public bridgeHandler getBridge( String bridgeName ) 
    {
        boBridgesArray    brig = super.getBridges();        	
        bridgeHandler     ret  = brig.get(bridgeName);
        if( ret == null )
        {
        
        	if( ret == null )
        	{
				ret = super.getBridge( bridgeName );
        	}
        }
        return ret;
    }
   
    
   
    

   

   
        
    
public  String[] getDependences(String attributeName) {
return null;
}
public  String[] addDefaultDependencesFields() {
return null;
}
public  void calculateFormula(Hashtable table, String from) throws boRuntimeException {
if ((p_mode != MODE_EDIT_TEMPLATE)) 
{
callObjBridgeCalculate(from);
if ((from != null)) 
{
}
}
}
	
	
    
    
}
