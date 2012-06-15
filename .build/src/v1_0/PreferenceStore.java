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



public  class PreferenceStore extends boObject implements  Serializable {  

   
   
   
    public netgest.bo.runtime.attributes.boAttributeString name;    
    public netgest.bo.runtime.attributes.boAttributeString key;    
    public netgest.bo.runtime.attributes.boAttributeString contextKey;    
    public netgest.bo.runtime.attributes.boAttributeString user;    
    public netgest.bo.runtime.attributes.boAttributeString profile;    
    public netgest.bo.runtime.attributes.boAttributeString preferenceType;    
    public netgest.bo.runtime.attributes.boAttributeString valueType;    
    public netgest.bo.runtime.attributes.boAttributeString value;    
    public netgest.bo.runtime.attributes.boAttributeString isLob;    
    public netgest.bo.runtime.attributes.boAttributeString clobValue;    
    public netgest.bo.runtime.attributes.boAttributeObject PARENT;    
    public netgest.bo.runtime.attributes.boAttributeObject PARENTCTX;    
    public netgest.bo.runtime.attributes.boAttributeObject TEMPLATE;    
    public netgest.bo.runtime.attributes.boAttributeNumber BOUI;    
    public netgest.bo.runtime.attributes.boAttributeString CLASSNAME;    
    public netgest.bo.runtime.attributes.boAttributeObject CREATOR;    
    public netgest.bo.runtime.attributes.boAttributeDate SYS_DTCREATE;    
    public netgest.bo.runtime.attributes.boAttributeDate SYS_DTSAVE;    
    public netgest.bo.runtime.attributes.boAttributeString SYS_ORIGIN;    
    public netgest.bo.runtime.attributes.boAttributeObject SYS_FROMOBJ;    
   
     
    public PreferenceStore() {
        super();
        bo_version      = "1.0";
        bo_name         = "PreferenceStore";
        bo_classregboui = "#BO.CLSREGBOUI#";
        bo_definition   = boDefHandler.getBoDefinition("PreferenceStore");
        bo_statemanager = bo_definition.getBoClsState() != null ? bo_definition.getBoClsState().getStateManager( this ) : null;  

        boAttributesArray atts = super.getAttributes();
        boAttributesArray stat = super.getStateAttributes();

       
        
       

       
        
       
        atts.add(name = new netgest.bo.runtime.attributes.boAttributeString(this,"name"));
        
        atts.add(key = new netgest.bo.runtime.attributes.boAttributeString(this,"key"));
        
        atts.add(contextKey = new netgest.bo.runtime.attributes.boAttributeString(this,"contextKey"));
        
        atts.add(user = new netgest.bo.runtime.attributes.boAttributeString(this,"user"));
        
        atts.add(profile = new netgest.bo.runtime.attributes.boAttributeString(this,"profile"));
        
        atts.add(preferenceType = new netgest.bo.runtime.attributes.boAttributeString(this,"preferenceType"));
        
        atts.add(valueType = new netgest.bo.runtime.attributes.boAttributeString(this,"valueType"));
        
        atts.add(value = new netgest.bo.runtime.attributes.boAttributeString(this,"value"));
        
        atts.add(isLob = new netgest.bo.runtime.attributes.boAttributeString(this,"isLob"));
        
        atts.add(clobValue = new netgest.bo.runtime.attributes.boAttributeString(this,"clobValue"));
        
        atts.add(PARENT = new netgest.bo.runtime.attributes.boAttributeObject(this,"PARENT"));
        
        atts.add(PARENTCTX = new netgest.bo.runtime.attributes.boAttributeObject(this,"PARENTCTX"));
        
        atts.add(TEMPLATE = new netgest.bo.runtime.attributes.boAttributeObject(this,"TEMPLATE"));
        
        atts.add(BOUI = new netgest.bo.runtime.attributes.boAttributeNumber(this,"BOUI"));
        
        atts.add(CLASSNAME = new netgest.bo.runtime.attributes.boAttributeString(this,"CLASSNAME"));
        
        atts.add(CREATOR = new netgest.bo.runtime.attributes.boAttributeObject(this,"CREATOR"));
        
        atts.add(SYS_DTCREATE = new netgest.bo.runtime.attributes.boAttributeDate(this,"SYS_DTCREATE"));
        
        atts.add(SYS_DTSAVE = new netgest.bo.runtime.attributes.boAttributeDate(this,"SYS_DTSAVE"));
        
        atts.add(SYS_ORIGIN = new netgest.bo.runtime.attributes.boAttributeString(this,"SYS_ORIGIN"));
        
        atts.add(SYS_FROMOBJ = new netgest.bo.runtime.attributes.boAttributeObject(this,"SYS_FROMOBJ"));
        



       
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
           
           
           
                if( name.haveDefaultValue()) 
                {
                	if(name.defaultValue() != null)
                	{
                    	name._setValue(boConvertUtils.convertToString(name.defaultValue(), name));
                    }
                    else
                    {
                    	name._setValue(null);
                    }
                    name.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( key.haveDefaultValue()) 
                {
                	if(key.defaultValue() != null)
                	{
                    	key._setValue(boConvertUtils.convertToString(key.defaultValue(), key));
                    }
                    else
                    {
                    	key._setValue(null);
                    }
                    key.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( contextKey.haveDefaultValue()) 
                {
                	if(contextKey.defaultValue() != null)
                	{
                    	contextKey._setValue(boConvertUtils.convertToString(contextKey.defaultValue(), contextKey));
                    }
                    else
                    {
                    	contextKey._setValue(null);
                    }
                    contextKey.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( user.haveDefaultValue()) 
                {
                	if(user.defaultValue() != null)
                	{
                    	user._setValue(boConvertUtils.convertToString(user.defaultValue(), user));
                    }
                    else
                    {
                    	user._setValue(null);
                    }
                    user.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( profile.haveDefaultValue()) 
                {
                	if(profile.defaultValue() != null)
                	{
                    	profile._setValue(boConvertUtils.convertToString(profile.defaultValue(), profile));
                    }
                    else
                    {
                    	profile._setValue(null);
                    }
                    profile.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( preferenceType.haveDefaultValue()) 
                {
                	if(preferenceType.defaultValue() != null)
                	{
                    	preferenceType._setValue(boConvertUtils.convertToString(preferenceType.defaultValue(), preferenceType));
                    }
                    else
                    {
                    	preferenceType._setValue(null);
                    }
                    preferenceType.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( valueType.haveDefaultValue()) 
                {
                	if(valueType.defaultValue() != null)
                	{
                    	valueType._setValue(boConvertUtils.convertToString(valueType.defaultValue(), valueType));
                    }
                    else
                    {
                    	valueType._setValue(null);
                    }
                    valueType.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( value.haveDefaultValue()) 
                {
                	if(value.defaultValue() != null)
                	{
                    	value._setValue(boConvertUtils.convertToString(value.defaultValue(), value));
                    }
                    else
                    {
                    	value._setValue(null);
                    }
                    value.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( isLob.haveDefaultValue()) 
                {
                	if(isLob.defaultValue() != null)
                	{
                    	isLob._setValue(boConvertUtils.convertToString(isLob.defaultValue(), isLob));
                    }
                    else
                    {
                    	isLob._setValue(null);
                    }
                    isLob.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( clobValue.haveDefaultValue()) 
                {
                	if(clobValue.defaultValue() != null)
                	{
                    	clobValue._setValue(boConvertUtils.convertToString(clobValue.defaultValue(), clobValue));
                    }
                    else
                    {
                    	clobValue._setValue(null);
                    }
                    clobValue.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( PARENT.haveDefaultValue()) 
                {
                	if(PARENT.defaultValue() != null)
                	{
                    	PARENT._setValue(boConvertUtils.convertToBigDecimal(PARENT.defaultValue(), PARENT));
                    }
                    else
                    {
                    	PARENT._setValue(null);
                    }
                    PARENT.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( PARENTCTX.haveDefaultValue()) 
                {
                	if(PARENTCTX.defaultValue() != null)
                	{
                    	PARENTCTX._setValue(boConvertUtils.convertToBigDecimal(PARENTCTX.defaultValue(), PARENTCTX));
                    }
                    else
                    {
                    	PARENTCTX._setValue(null);
                    }
                    PARENTCTX.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( TEMPLATE.haveDefaultValue()) 
                {
                	if(TEMPLATE.defaultValue() != null)
                	{
                    	TEMPLATE._setValue(boConvertUtils.convertToBigDecimal(TEMPLATE.defaultValue(), TEMPLATE));
                    }
                    else
                    {
                    	TEMPLATE._setValue(null);
                    }
                    TEMPLATE.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( BOUI.haveDefaultValue()) 
                {
                	if(BOUI.defaultValue() != null)
                	{
                    	BOUI._setValue(boConvertUtils.convertToBigDecimal(BOUI.defaultValue(), BOUI));
                    }
                    else
                    {
                    	BOUI._setValue(null);
                    }
                    BOUI.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( CLASSNAME.haveDefaultValue()) 
                {
                	if(CLASSNAME.defaultValue() != null)
                	{
                    	CLASSNAME._setValue(boConvertUtils.convertToString(CLASSNAME.defaultValue(), CLASSNAME));
                    }
                    else
                    {
                    	CLASSNAME._setValue(null);
                    }
                    CLASSNAME.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( CREATOR.haveDefaultValue()) 
                {
                	if(CREATOR.defaultValue() != null)
                	{
                    	CREATOR._setValue(boConvertUtils.convertToBigDecimal(CREATOR.defaultValue(), CREATOR));
                    }
                    else
                    {
                    	CREATOR._setValue(null);
                    }
                    CREATOR.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( SYS_DTCREATE.haveDefaultValue()) 
                {
                	if(SYS_DTCREATE.defaultValue() != null)
                	{
                    	SYS_DTCREATE._setValue(boConvertUtils.convertToTimestamp(SYS_DTCREATE.defaultValue(), SYS_DTCREATE));
                    }
                    else
                    {
                    	SYS_DTCREATE._setValue(null);
                    }
                    SYS_DTCREATE.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( SYS_DTSAVE.haveDefaultValue()) 
                {
                	if(SYS_DTSAVE.defaultValue() != null)
                	{
                    	SYS_DTSAVE._setValue(boConvertUtils.convertToTimestamp(SYS_DTSAVE.defaultValue(), SYS_DTSAVE));
                    }
                    else
                    {
                    	SYS_DTSAVE._setValue(null);
                    }
                    SYS_DTSAVE.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( SYS_ORIGIN.haveDefaultValue()) 
                {
                	if(SYS_ORIGIN.defaultValue() != null)
                	{
                    	SYS_ORIGIN._setValue(boConvertUtils.convertToString(SYS_ORIGIN.defaultValue(), SYS_ORIGIN));
                    }
                    else
                    {
                    	SYS_ORIGIN._setValue(null);
                    }
                    SYS_ORIGIN.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( SYS_FROMOBJ.haveDefaultValue()) 
                {
                	if(SYS_FROMOBJ.defaultValue() != null)
                	{
                    	SYS_FROMOBJ._setValue(boConvertUtils.convertToBigDecimal(SYS_FROMOBJ.defaultValue(), SYS_FROMOBJ));
                    }
                    else
                    {
                    	SYS_FROMOBJ._setValue(null);
                    }
                    SYS_FROMOBJ.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
    
            if("PreferenceStore".equals(this.getName()))
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
