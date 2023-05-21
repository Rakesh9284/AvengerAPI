package com.vbrick.avenger.funUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.vbrick.avenger.setup.Setup;

/*public enum Language {
	ENGLISH,FRENCH,DEFAULT;*/

public  class  LanguageSelect implements Setup
	   {
		   private static Logger logger = Logger.getLogger(LanguageSelect.class);
		   
		   public enum Language {
				ENGLISH,FRENCH,DEFAULT;
		   }
		   
		   /**
			 * This Method is used to get the Absolute path of the File
			 * @param sFilepath
			 * @return :It returns the Path of the File
			 */
		   
		   public String getFilePath(String sFilepath) {
			    logger.info("In the Language class Get Method");
				char cforwardslash = (char) 47;
				char cbackslash = (char) 92;
				String sPath = System.getProperty("user.dir").replace(cbackslash,
						cforwardslash)
						+ sFilepath;

				File file = new File(sPath);
				if (file.exists()) {
					sPath = file.getAbsolutePath();
					logger.info("The File Path is " + sPath);
				} else {
					logger.info("File not Found");
				}
				return sPath;
			}
		   
		   /**
		    * This Method provide the facility to select the Language,in which we want our application.
		    * @param prop
		    * @param language
		    * @throws FileNotFoundException
		    * @throws IOException
		    */
		   public void getLanguage(Properties prop,String language) throws FileNotFoundException, IOException 
		   {
			   logger.info("The get Language Method is called--------------->");
			   switch(Language.valueOf(language.toUpperCase()))
			   {
			   case ENGLISH: 
				    logger.info("The ENGLISH PROPERTY IS LOADED");
					 prop.load(new FileInputStream(getFilePath(EN_PROPERTYFILEPATH)));
					 break;
			   case FRENCH:
				   
				    logger.info("The value is not Present");
					prop.load(new FileInputStream(getFilePath(FRENCH_PROPERTYFILEPATH)));
					break;
			   case DEFAULT:
					prop.load(new FileInputStream(getFilePath(EN_PROPERTYFILEPATH)));
				   break;	
			   }
			   
			   
		   }

		public Map<String, String> runtimeEnvironment() {
			// TODO Auto-generated method stub
			return null;
		}
		   
		     
	   }

	
	
