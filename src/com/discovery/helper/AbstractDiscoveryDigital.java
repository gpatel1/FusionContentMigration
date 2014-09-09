package com.discovery.helper;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AbstractDiscoveryDigital {

	public static WebDriver driver;
	public static WebElement element;
	//public static String base_url = "http://www.howstuffworks.com";
	 // This runs before each test method runs
	 /* @BeforeTest
	  public void OpenBrowser() 
	  {
		  driver=new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		//  driver.get(base_url);
	  }*/
	  
	  // this runs at the end of each test
	  
	 
	  public static boolean IsElementPresent(By by)
      {
          try
          {
              driver.findElement(by);
              return true;
          }
          catch (Exception e)
          {
              return false;
          }
      }
	  
	  public static boolean isTextPresent(String what) { 
	        try { 
	            	driver.findElement(By.tagName("body")).getText().contains(what);
	                return true;
	        } catch (NoSuchElementException e) { 
	            Reporter.log(e.toString());
	            return false; 
	        }        
	}
	  
	  
	  public static String getTextPresent(String what) { 
	        try { 
	            
	                element = driver.findElement(By.cssSelector("h1"));
	                return element.getText();
	        } catch (NoSuchElementException e) { 
	            Reporter.log(e.toString());
	            return null; 
	        } 
	    }
	  
	  public static String GetPass(){
		  return "DevTest108!";
	  }
	  
	  
	  public static boolean SelectDropDownText(String DDname, String TextToSelect) {
	       
	       try {
	           element = driver.findElement(By.id(DDname));
	           Select select = new Select(element);
	           select.selectByVisibleText(TextToSelect);
	           return true;
	       }catch(Exception e)
	       {
	           System.out.println("something wrong while going to page and selecting text '"+TextToSelect+" 'from DropDown name' "+DDname+" ' .Exception message is "+ e.toString());
	            return false;
	       }
	   }

	  }
