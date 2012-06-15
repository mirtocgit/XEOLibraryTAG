package org.xeoframework.library.modelRender;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * A class to convert a XML definition of a XEO Object Model into an HTML
 * render for previewing inside XEO Studio (uses CSS, Images and EXT JS framework)
 * Package includes a zip with the resources required and XSLT that transforms the
 * XML definition
 *
 *
 * @author Pedro Pereira
 *
 */
public class ModelToStringRender
{

   /**
    * The name of XSLT that will render the XEO Model definition as HTML
    */
   private static final String RENDER_MODEL_XSL = "renderModel.xsl";

   /**
    * Static instance to implement singleton
    */
   private static ModelToStringRender modelRender;

   /**
    * A Transformer Factory
    */
   private TransformerFactory factory;

   /**
    * The XSLT transformer to render the result
    */
   private Transformer xsltTransformer;

   /**
    * Private constructor
    */
   private ModelToStringRender() throws Exception
   {
       InputStream in = ModelToStringRender.class.getResource(RENDER_MODEL_XSL).openStream();
       Source xsltSource = new StreamSource(in);
       //System.setProperty("javax.xml.transform.TransformerFactory","net.sf.saxon.TransformerFactoryImpl");
       factory = TransformerFactory.newInstance();
       // get a transformer for this particular stylesheet
       xsltTransformer = factory.newTransformer(xsltSource);
   }

   /**
    *
    * Factory implementing the singleton pattern
    *
    * @return The instance of Model Render
    */
   public static ModelToStringRender getModelRender() throws Exception
   {
       if (modelRender == null)
           modelRender = new ModelToStringRender();
       return modelRender;
   }


   /**
    *
    * Renders a XML definition of a XEO Model to an HTML file to be created in the "path" directory
    *
    * @param path The path where the files should be created
    * @param xmlDefinition The XML definition of the XEO Model
    * @param name The name of the HTML file to be created
    *
    * @return True if the file was successfully created
    *
    * @throws {@link IOException} When error occurs while writing the file, {@link TransformerException} When the XSLT could not execute
    *
    */
   public String renderXEOModel(InputStream xmlDefinition) throws IOException, TransformerException
   {
       Writer w = new StringWriter();
       //Process the definition and output the result
       Source xmlSource = new StreamSource(xmlDefinition);
       Result outputTarget = new StreamResult(w);
       xsltTransformer.transform(xmlSource, outputTarget);
       return w.toString();
   }


}
