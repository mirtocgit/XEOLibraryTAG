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



public  class LIB_Movement extends boObject implements  Serializable {  

   
    public Handlerbooks books;       
   
   
    public netgest.bo.runtime.attributes.boAttributeNumber id;    
    public netgest.bo.runtime.attributes.boAttributeDate dueDate;    
    public netgest.bo.runtime.attributes.boAttributeNumber fine;    
    public netgest.bo.runtime.attributes.boAttributeObject user;    
    public netgest.bo.runtime.attributes.boAttributeString state;    
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
   
     
    public LIB_Movement() {
        super();
        bo_version      = "1.0";
        bo_name         = "LIB_Movement";
        bo_classregboui = "#BO.CLSREGBOUI#";
        bo_definition   = boDefHandler.getBoDefinition("LIB_Movement");
        bo_statemanager = bo_definition.getBoClsState() != null ? bo_definition.getBoClsState().getStateManager( this ) : null;  

        boAttributesArray atts = super.getAttributes();
        boAttributesArray stat = super.getStateAttributes();

       
        atts.add(books = new Handlerbooks(this));
        
        
        
        atts.add(new boBridgeMasterAttribute(this,this.getBoDefinition().getAttributeRef("returns"))); 

       
        
       
        atts.add(id = new netgest.bo.runtime.attributes.boAttributeNumber(this,"id"));
        
        atts.add(dueDate = new netgest.bo.runtime.attributes.boAttributeDate(this,"dueDate"));
        
        atts.add(fine = new netgest.bo.runtime.attributes.boAttributeNumber(this,"fine"));
        
        atts.add(user = new netgest.bo.runtime.attributes.boAttributeObject(this,"user"));
        
        atts.add(state = new netgest.bo.runtime.attributes.boAttributeString(this,"state"));
        
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
           
                
                    if( books.haveDefaultValue()) 
                    {
                    	if(books.defaultValue() != null)
                    	{
                        	books._setValue(verifyTransformer(books,boConvertUtils.convertToBigDecimal(books.defaultValue(), books)));
                        }
                        else
                        {
                       		books._setValue(null);
                       	}
                        books.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                    }
                
            
           
           
                if( id.haveDefaultValue()) 
                {
                	if(id.defaultValue() != null)
                	{
                    	id._setValue(boConvertUtils.convertToBigDecimal(id.defaultValue(), id));
                    }
                    else
                    {
                    	id._setValue(null);
                    }
                    id.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( dueDate.haveDefaultValue()) 
                {
                	if(dueDate.defaultValue() != null)
                	{
                    	dueDate._setValue(boConvertUtils.convertToTimestamp(dueDate.defaultValue(), dueDate));
                    }
                    else
                    {
                    	dueDate._setValue(null);
                    }
                    dueDate.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( fine.haveDefaultValue()) 
                {
                	if(fine.defaultValue() != null)
                	{
                    	fine._setValue(boConvertUtils.convertToBigDecimal(fine.defaultValue(), fine));
                    }
                    else
                    {
                    	fine._setValue(null);
                    }
                    fine.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( user.haveDefaultValue()) 
                {
                	if(user.defaultValue() != null)
                	{
                    	user._setValue(boConvertUtils.convertToBigDecimal(user.defaultValue(), user));
                    }
                    else
                    {
                    	user._setValue(null);
                    }
                    user.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( state.haveDefaultValue()) 
                {
                	if(state.defaultValue() != null)
                	{
                    	state._setValue(boConvertUtils.convertToString(state.defaultValue(), state));
                    }
                    else
                    {
                    	state._setValue(null);
                    }
                    state.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
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
            
    
            if("LIB_Movement".equals(this.getName()))
            {
                calculateFormula(null);
            }
        }
    }
   
    public bridgeHandler returns()
    {
        return getBridge("returns");        
    }
    
    
    public bridgeHandler getBridge( String bridgeName ) 
    {
        boBridgesArray    brig = super.getBridges();        	
        bridgeHandler     ret  = brig.get(bridgeName);
        if( ret == null )
        {
        
        	if( bridgeName.equals("returns") && (ret=brig.get("returns"))==null )
        	{
            	brig.add( ret=new BridgeHandlerreturns(new DataResultSet( getBridgeDataSet("returns") ) , this ) );        
        	}
        	if( ret == null )
        	{
				ret = super.getBridge( bridgeName );
        	}
        }
        return ret;
    }
   
    public final class Handlerbooks extends ObjAttHandler {
        public Handlerbooks(boObject parent) {
            super(parent,parent.getBoDefinition().getAttributeRef("books"));
        }

        
        public void setValueString(String value) {
            try {
                this.setValue(boConvertUtils.convertToBigDecimal(value,this));
                Hashtable table = new Hashtable();
                if(getParent().onChangeSubmit("books"))
                {
                    getParent().setCalculated(table, "books");
                    getParent().calculateFormula(table, "books");
                }
            } catch (Exception e) {
                super.setInvalid(e.getMessage(),value);
            }
        }
        public String getValueString() throws boRuntimeException {
            try {
                return boConvertUtils.convertToString(getValue(),this);
            } catch (Exception e) {
                String[] args = {getParent().getName(),"books"};
                throw new boRuntimeException(this.getClass().getName()+".load(EboContext,long)","BO-3003",e,args);
            }
        }
        public void setValueFormula(Hashtable table, String[]dependence) throws boRuntimeException
        {
            if(getParent().alreadyCalculated(table, dependence)) 
            {                       
                if(getParent().isWaiting(table, "books"))
                {
                    getParent().setCalculated(table, "books");
                    this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                }
                else if(!getParent().isCalculated(table, "books"))
                {
                    getParent().setCalculated(table, "books");
                    this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), table );
                }
            }
            else if(getParent().dependsFromWaiting(table, dependence))
            {
                if(getParent().onChangeSubmit("books"))
                {
                    getParent().setCalculated(table, "books");
                }
                else
                {
                    getParent().clear(table, "books");
                    this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                }
            }
            else
            {    
                this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );                                
                if(getParent().onChangeSubmit("books"))
                {
                    getParent().setWaiting(table, "books");
                    getParent().calculateFormula(new Hashtable(table), "books");
                }                
            }
        }

        public void setValue(BigDecimal newval, boolean recalc) throws boRuntimeException 
        {
        	
            this.setValue(newval, recalc?AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL); 
            
            if( recalc && getParent().onChangeSubmit("books"))
            {
                Hashtable table = new Hashtable();
                getParent().setCalculated(table, "books");
                getParent().calculateFormula(table, "books");
            }
            //vou verificar se o objecto se encontra numa bridge
			if ( getParent().p_parentBridgeRow != null )
	        {
				getParent().getParentBridgeRow().getBridge().lineChanged("books");
            }
		 }

        private void setValue(BigDecimal newval, Hashtable table) throws boRuntimeException 
        {
            this.setValue(newval, AttributeHandler.INPUT_FROM_INTERNAL);
            if( getParent().onChangeSubmit("books"))
            {
                getParent().setCalculated(table, "books");
                getParent().calculateFormula(table, "books");
            }
            
            
			//vou verificar se o objecto se encontra numa bridge
			if ( getParent().p_parentBridgeRow != null )
	        {
				getParent().getParentBridgeRow().getBridge().lineChanged("books");
            }



        }
        
        

        
public  String defaultValue() throws boRuntimeException {/*XEOJAVACLASS*/
return new 
xeo.code.java.lib1_0.
LIB_MovementHandler().
books_DefaultValue
(this.getParent(),this);}
public  boolean haveDefaultValue() {
return true;
}
public  String formula() throws boRuntimeException {
return null;
}



        
        
    }
    
    
    
    public final class BridgeHandlerreturns extends bridgeHandler {
        private DataResultSet p_node;
        private String        p_fatherfield;
        private String        p_childfield;
        private boObject      p_parent;

        
        public BridgeHandlerreturns(DataResultSet data,boObject parent) {
            super("returns",
                  data,
                  parent.getBoDefinition().getAttributeRef("returns").getBridge().getChildFieldName(),
                  parent
                 );

            p_fatherfield = parent.getBoDefinition().getAttributeRef("returns").getBridge().getFatherFieldName();
            p_childfield  = parent.getBoDefinition().getAttributeRef("returns").getBridge().getChildFieldName();
            
            p_parent = parent;
            p_node = data;

			refreshBridgeData();
            first();
        }
        public void add(BigDecimal boui, byte type) throws boRuntimeException {
            add(boui,-1, type);
        }
        public void add(BigDecimal boui,int xnrow, byte type) throws boRuntimeException {

              //tranform ao adicionar a brige
              netgest.bo.transformers.Transformer transfClass = null;
			  if ( getParent().getMode() != boObject.MODE_EDIT_TEMPLATE )
				{
				  if(( transfClass = this.getDefAttribute().getTransformClassMap()) != null)
				  {
					 if(boui != null)
					 {
						boui = new BigDecimal(transfClass.transform(getEboContext(), getParent(), boui.longValue()));
					 }
				  }
				}

			  boBridgeRow brow = createRow();
			  ObjAttHandler att = (ObjAttHandler)brow.getAttribute( this.getName() );

			  BigDecimal newval = boui;

			  if( att.fireBeforeChangeEvent( att, null, newval) ) 
			  {
				  
				  brow.getDataRow().updateBigDecimal(p_childfield,boui);
  		          this.insertRow( brow );
				  this.moveTo( this.getRowCount() );

				  if(xnrow !=-1)
                  {
                      this.moveRowTo(xnrow);
                  }

				  att.checkParent( null, newval );

				  getParent().setChanged( true );

                  if(getParent().onChangeSubmit("returns"))
                  {
                      getParent().calculateFormula("returns");
                  }
                  att.fireAfterChangeEvent( att, null, newval );
			  }

        }

		public boolean remove() throws boRuntimeException 
        {
            boolean ret = false;

          

                boBridgeRow rowtodelete = rows( this.getRow() );
				ObjAttHandler att = (ObjAttHandler)rowtodelete.getAttribute( this.getName() );

				BigDecimal chgval = this.getValue();

                if( att.fireBeforeChangeEvent( att, chgval, null) ) 
                {
					rowtodelete.getDataRow().updateBigDecimal(p_childfield,null);
					att.checkParent( chgval, null );

				    getParent().setChanged( true );

                    Enumeration  lineatts = rowtodelete.getLineAttributes().elements();

                    _remove( rowtodelete );  // remove the line

					
                    while( lineatts.hasMoreElements() )
                    {
                        AttributeHandler xatt = ( AttributeHandler )lineatts.nextElement();
                        if ( xatt.getValueObject() != null )
                        {
                            xatt.setValueObject( null );
                        }
                    }
                    
                    ret = true;
					//calculateFormulas
					if(getParent().onChangeSubmit("returns"))
					{
					   getParent().calculateFormula("returns");
					}

                    att.fireAfterChangeEvent( att, chgval, null);
				}
           
            return ret;
        }




		public boBridgeRow createRow( netgest.bo.data.DataRow row )
		{
			return new BridgeHanlderreturnsRow( this, row );
		}

		public final class BridgeHanlderreturnsRow extends boBridgeRow
		{
			public BridgeHanlderreturnsRow( bridgeHandler bridge, netgest.bo.data.DataRow row )
			{
				super( bridge , row );
				addLine();
			}

			public void addLine() 
			{
				boAttributesArray xatt = super.getLineAttributes();
			
				if(this.getAttribute("returns")==null)
					xatt.add( new Handlerreturns( getParent(), this ) );
				
		
			
				if(this.getAttribute("LIN")==null)
					xatt.add( new HandlerLIN( getParent(), this ) );
				
			} 


		}


        
public  boolean haveDefaultValue() {
return false;
}
public  String formula() throws boRuntimeException {
return null;
}



        
       
        public final class Handlerreturns extends BridgeObjAttributeHandler {
            public Handlerreturns( boObject parent , boBridgeRow bridgerow ) {
                super( parent , bridgerow , bridgerow.getBridge().getDefAttribute().getName().equals("returns")?parent.getBoDefinition().getAttributeRef("returns"):bridgerow.getBridge().getDefAttribute().getBridge().getAttributeRef("returns") );
            }
            public void setValueString(String value) {
                try {
                    this.setValue(boConvertUtils.convertToBigDecimal(value,this));
                    if(getParent().onChangeSubmit("returns"))
                    {
                        Hashtable table = new Hashtable();                    
                        getParent().setCalculated(table, "returns");
                        getParent().calculateFormula(table, "returns");
                    }
                } catch (Exception e) {
                    super.setInvalid(e.getMessage(),value);
                }
            }
            public String getValueString() throws boRuntimeException {
                try {
                    return boConvertUtils.convertToString(this.getValue(),this);
                } catch (Exception e) {
                    String[] args = {getParent().getName(),"returns"};
                    throw new boRuntimeException(this.getClass().getName()+".load(EboContext,long)","BO-3003",e,args);
                }
            }
            
            public void setValueFormula(Hashtable table, String[]dependence) throws boRuntimeException
            {
                if(getParent().alreadyCalculated(table, dependence)) 
                {                       
                    if(getParent().isWaiting(table, "returns"))
                    {
                       getParent().setCalculated(table, "returns");
                       this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    }
                    else if(!getParent().isCalculated(table, "returns"))
                    {
                        getParent().setCalculated(table, "returns");
                        this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), table );
                    }
                }
                else if(getParent().dependsFromWaiting(table, dependence))
                {
                    if(getParent().onChangeSubmit("returns"))
                    {
                        getParent().setCalculated(table, "returns"); 
                    }
                    else
                    {
                        getParent().clear(table, "returns"); 
                        this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    }
                }
                else
                {   
                    this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    if(getParent().onChangeSubmit("returns"))
                    {                 
                        getParent().setWaiting(table, "returns");
                        getParent().calculateFormula(new Hashtable(table), "returns");
                    }                    
                }
            }

            public void setValue(BigDecimal newval, boolean recalc) throws boRuntimeException 
            {
                this.setValue(newval, recalc ? AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL);
	        	
                if( recalc && getParent().onChangeSubmit("returns"))
                {
                    Hashtable table = new Hashtable();
                    getParent().setCalculated(table, "returns");
                    getParent().calculateFormula(table, "returns");
                }
        
				if ( getParent().p_parentBridgeRow != null )
				{
					getParent().getParentBridgeRow().getBridge().lineChanged("returns");
				}
            }

            private void setValue(BigDecimal newval, Hashtable table) throws boRuntimeException 
            {
                this.setValue(newval, AttributeHandler.INPUT_FROM_INTERNAL);
                if( getParent().onChangeSubmit("returns"))
                {
                    getParent().setCalculated(table, "returns");
                    getParent().calculateFormula(table, "returns");
                }
                
				//vou verificar se o objecto se encontra numa bridge
				if ( getParent().p_parentBridgeRow != null )
				{
					getParent().getParentBridgeRow().getBridge().lineChanged("returns");
				}

            }
            
            

            
        }
        

       
        public final class HandlerLIN extends BridgeAttributeHandler {

            private boolean isdbbinding=true;
            private BigDecimal fieldvalue;
            private boBridgeRow p_bridgerow;

			public HandlerLIN(boObject parent, boBridgeRow bridgerow ) {
                super( parent , bridgerow ,bridgerow.getBridge().getDefAttribute().getBridge().getAttributeRef("LIN") );
                p_bridgerow = bridgerow;
            }
            
            public BigDecimal getValue() throws boRuntimeException {
                BigDecimal ret = null;

                fireEvent( boEvent.EVENT_BEFORE_GETVALUE, null );
                if(isdbbinding) {
					ret=p_bridgerow.getDataRow().getBigDecimal("LIN");
                } else {
                    ret = fieldvalue;
                }
                fireEvent( boEvent.EVENT_AFTER_GETVALUE, ret );
                return ret;
            }
            public void setValueObject(Object value) throws boRuntimeException {
                this.setValue((BigDecimal)value);
            }
            public Object getValueObject() throws boRuntimeException 
            {
                return this.getValue();
            }
            public String getValueString() throws boRuntimeException {
                return boConvertUtils.convertToString(this.getValue(),this);
            }
            
            public void setValueFormula(Hashtable table, String[]dependence) throws boRuntimeException
            {
                if(getParent().alreadyCalculated(table, dependence)) 
                {                       
                    if(getParent().isWaiting(table, "LIN"))
                    {
                       getParent().setCalculated(table, "LIN");
                       setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    }
                    else if(!getParent().isCalculated(table, "LIN"))
                    {
                        getParent().setCalculated(table, "LIN");
                        setValue(boConvertUtils.convertToBigDecimal(formula(), this), table );
                    }
                }
                else if(getParent().dependsFromWaiting(table, dependence))
                {
                    if(getParent().onChangeSubmit("LIN"))
                    {
                        getParent().setCalculated(table, "LIN"); 
                    }
                    else
                    {
                        getParent().clear(table, "LIN"); 
                        setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    } 
                }
                else
                {    
                    setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );                
                    if(getParent().onChangeSubmit("LIN"))
                    {
                        getParent().setWaiting(table, "LIN");
                        getParent().calculateFormula(new Hashtable(table), "LIN");
                    }                    
                }
            }

            public void setValue(BigDecimal newval ) throws boRuntimeException
            {
                setValue( newval, true );
            }

            public void setValue(BigDecimal newval, boolean recalc) throws boRuntimeException 
            {
                if (getParent().isCheckSecurity() && !this.hasRights())
                    	throw new boRuntimeException(HandlerLIN.class.getName() +
	        	".setValue()", "BO-3230", null, "");                      	                   	                                   	
                if(!ClassUtils.compare(newval,this.getValue())) {
                    BigDecimal chgval = this.getValue();

                    if ( fireBeforeChangeEvent( this, chgval, newval) )
                    {
                            if(canAlter(recalc?AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL))
                            {
    	                        _setValue(newval);
                                setInputType( recalc?AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL );
	                    	                        
                                if( recalc && getParent().onChangeSubmit("LIN"))
                                {
                                    Hashtable table = new Hashtable();
                                    getParent().setCalculated(table, "LIN");
                                    getParent().calculateFormula(table, "LIN");
                                }
	                        
                                //vou verificar se o objecto se encontra numa bridge
                                if ( getParent().p_parentBridgeRow != null )
                                {
                                    getParent().getParentBridgeRow().getBridge().lineChanged("LIN");
                                }

                                fireAfterChangeEvent( this, chgval, newval);
                            }
                    }
                }
            }

            private void setValue(BigDecimal newval, Hashtable table) throws boRuntimeException 
            {
                if (getParent().isCheckSecurity() && !this.hasRights())
                    	throw new boRuntimeException(HandlerLIN.class.getName() +
	        	".setValue()", "BO-3230", null, "");                      	                   	                                   	
                if(!ClassUtils.compare(newval,this.getValue())) {
                    BigDecimal chgval = this.getValue();

                    if ( fireBeforeChangeEvent( this, chgval, newval) )
                    {
											  if(canAlter(AttributeHandler.INPUT_FROM_INTERNAL))
												{
	                        _setValue(newval);
	                        setInputType( AttributeHandler.INPUT_FROM_INTERNAL );
	                        if( getParent().onChangeSubmit("LIN"))
	                        {
	                            getParent().setCalculated(table, "LIN");
	                            getParent().calculateFormula(table, "LIN");
	                        }

							//vou verificar se o objecto se encontra numa bridge
							if ( getParent().p_parentBridgeRow != null )
							{
								getParent().getParentBridgeRow().getBridge().lineChanged("LIN");
							}
	
                            fireAfterChangeEvent( this, chgval, newval);
	                    }
	                }
							}
            }

            public void _setValue(BigDecimal newval) throws boRuntimeException {
                if(isdbbinding) {

                        p_bridgerow.getDataRow().updateBigDecimal("LIN",newval);
                        getParent().setChanged(true);
                        super.setValid();

                } else {
                    fieldvalue = newval;
                }
            }
            public void setValueString(String value)  throws boRuntimeException 
            {
                this.setValue(boConvertUtils.convertToBigDecimal(value,this));
            }

            //@IF SIMPLEDATATYPE
            public double getValueDouble() throws boRuntimeException {
                return boConvertUtils.convertTodouble(this.getValue(),this);
            }
            public void setValue(double value)  throws boRuntimeException 
            {
                this.setValue(boConvertUtils.convertToBigDecimal(value,this));
            }
            public void setValuedouble(double value)  throws boRuntimeException 
            {
                this.setValue(boConvertUtils.convertToBigDecimal(value,this));
            }
            //@ENDIF SIMPLEDATATYPE

            
            
public  boolean haveDefaultValue() {
return false;
}
public  String formula() throws boRuntimeException {
return null;
}



            
        }
        
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
	
	public void onAfterLoad(boEvent event) throws boRuntimeException {
	/*XEOJAVACLASS*/
new xeo.code.java.lib1_0.LIB_MovementHandler().onAfterLoad(this,event);
}

    
    
}
