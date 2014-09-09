package com.discovery.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Reporter;

public class UtilMethods {
	
			/*public static void main (String args[]){
				System.out.println(replaceSpacewith("hello there"));
			}*/
			public static String replaceSpacewith(String str){
				Pattern whitespace = Pattern.compile("\\s");
				Matcher matcher = whitespace.matcher(str); 
				return matcher.replaceAll("%20");
			}
			
			public static String GetValueFromPropFile(String key){
				Properties prop = new Properties();
				try{
		            //load a properties file
					prop.load(new FileInputStream("config.properties"));
			 	}catch (IOException ex) {
			 		ex.printStackTrace();
			    }
				return prop.getProperty(key);
				//return "http://api.media.dp.discovery.com";
			}
		
				 static String GetValueFromPropFileRes(String key){
			Properties prop = new Properties();
			
			try{
				//ClassLoader loader = Thread.currentThread().getContextClassLoader();           
				//InputStream stream = loader.getResourceAsStream("/RESTAssured/src/config.properties");
				//InputStream stream = Class.class.getResourceAsStream("/RESTAssured/src/config.properties");
				//InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
				//prop.load(stream);
				
				
				/*InputStream in = RequestResponseProd.class.getResourceAsStream("RESTAssured/src/com/discovery/restapi/helper/ctg.properties");
			    prop.load(in);
			    Reporter.log("key val is "+prop.getProperty(key));
			    */
				
				//in.close()
			    
			    Thread currentThread = Thread.currentThread();
			    ClassLoader contextClassLoader = currentThread.getContextClassLoader();
			    InputStream propertiesStream = contextClassLoader.getResourceAsStream("cofig.properties");
			    if (propertiesStream != null) {
			    	prop.load(propertiesStream);
			      // TODO close the stream
			    } else {
			    	Reporter.log("Properties file not found!");
			    }
				
				
			/*	Class c=null;
			    try {
			      c = Class.forName("com.discovery.restapi.helper.RequestResponseProd");
			    } catch (Exception ex) {
			      // This should not happen.
			    }
			    InputStream s = c.getResourceAsStream("/RESTAssured/config.properties");
			    // do something with it.
			    if (s != null) {
			    	prop.load(s);
			      // TODO close the stream
			    } else {
			    	Reporter.log("Properties file not found!");
			    }*/
			    
			    
			    
			    
		 	}catch (IOException ex) {
		 		ex.printStackTrace();
		    }
			return prop.getProperty(key);
			//return "http://api.media.dp.discovery.com";
			
		}
	

}
