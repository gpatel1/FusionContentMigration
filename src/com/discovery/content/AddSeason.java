package com.discovery.content;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AddSeason {
	protected WebDriver driver;
	Proxy proxy;
	
	  
	@Test
	public void googlesearch()
	{
		
		 proxy = new Proxy();
		 //baseUrl = "http://preview-science.discovery.com/";
		 proxy.setHttpProxy("staging.proxy.dp.discovery.com:80");
		  // We use firefox as an example here.
		  DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		  capabilities.setCapability(CapabilityType.PROXY, proxy);
		  this.driver=new FirefoxDriver(capabilities);
		  

	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	// Navigate to the site
	driver.get("http://preview.tlc.com/wp-admin");
	 
	driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("gpatel");
	driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys("Abc123");
	driver.findElement(By.xpath("//input[@id='wp-submit']")).click();
	 
	try {
		IAddSeasonForEachShow();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	driver.quit();
	}
	
	
	public void IAddSeasonForEachShow() throws InterruptedException{
 		//driver.findElement(By.xpath("//li[@id='menu-posts-author']/a")).click(); // click on left hand nav "taxonomy"
 		//driver.pause(3000);
 		
 		
 		driver.findElement(By.xpath("//*[@id='toplevel_page_fusion_taxonomy']/a/div[3]")).click(); // click on left hand nav "taxonomy"
 		
 		
 		WebDriverWait wait = new WebDriverWait(driver, 600);
 		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='txm_menu']/select")));
 		
 		new Select(driver.findElement(By.xpath("//*[@id='txm_menu']/select"))).selectByVisibleText("Season"); // click season in dropdown
 		driver.findElement(By.xpath("//button[@class='btn btn-primary action']")).click();  // click add button next dropdown where we selected 'season' value in prev step 
 		
 		Select se = new Select(driver.findElement(By.xpath("//select[@ng-model='selectedItem.parent_id']"))); // Parent dropdown 
 		
			Thread.sleep(1000);
 		List<WebElement> l = se.getOptions();	
 		int numOfShows = l.size(); // this integer stores # of shows.
 		System.out.println("no of shows are"+numOfShows);
 		String[] ShowNames = new String[200]; // get all shows names in java array.
 		for(int x=1;x<numOfShows;x++){
 			ShowNames[x]= l.get(x).getText();
 		}
 		for(int x=1;x<numOfShows;x++){
 			String ShowName = ShowNames[x];
 			//			String ShowName = l.get(x).getText();
 			for(int y=1;y<=25;y++)// This is the for loop too add 25 seasons for the given show
 			{
 				new Select(driver.findElement(By.xpath("//select[@ng-model='selectedItem.parent_id']"))).selectByVisibleText(ShowName); // select show from the parent dropdwn menu
 				new Select(driver.findElement(By.xpath("//select[@ng-model='selectedItem.numInSeries']"))).selectByIndex(y); // select season #
 				driver.findElement(By.xpath("//*[@id='txm_add']")).click(); // click add
 				
 				Thread.sleep(1000);
 			}
 			System.out.println("Show name done-> "+ShowName);
 			
			Thread.sleep(1000);
 		}
 		System.out.println("All shows done ");		
 		System.out.println("Select options size is"+ l.size());
 		System.out.println("First option is"+ l.get(0).getText()+"Second value is"+l.get(2).getText());
 		System.out.println("second last is"+ l.get(155).getText()+"Last value is"+l.get(156).getText());
 		
 	}
 	public void I_set_show_name_to(String arg1){
 		
 		driver.findElement(By.xpath("//input[@id='taxonomy_show_name']")).clear();
 		driver.findElement(By.xpath("//input[@id='taxonomy_show_name']")).click();
 		driver.findElement(By.xpath("//input[@id='taxonomy_show_name']")).sendKeys(arg1);
 		
 	}
 	
 	public void I_get_all_show_names(){
 		driver.findElement(By.xpath("//input[@id='taxonomy_show_name']")).click();
 		
 		List<WebElement> ShowNames =  driver.findElements(By.xpath("//ul[contains(@id,'ui-id')]/li"));
 		System.out.println("Shownames size is"+ShowNames.size());

 		for(int x =1 ;x<=ShowNames.size();x++)
 		{
// 			driver.findElement(By.xpath("//ul[contains(@id,'ui-id')]/li["+x+"]/a")).click();
 			String TextTo = driver.findElement(By.xpath("//ul[contains(@id,'ui-id')]/li["+x+"]/a")).getText();
 			
 			//String TextTo = driver.findElement(By.xpath("//input[@id='taxonomy_show_name']")).getText();
 			System.out.println("| "+TextTo+" |");
 			
 		}

 	}
}
