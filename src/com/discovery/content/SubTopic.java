package com.discovery.content;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Capabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.*;

import com.discovery.helper.AbstractDiscoveryDigital;
import com.discovery.helper.DiscoveryDataProvider;
import com.discovery.pageobjects.Page;



public class SubTopic {
	// create a WebDriver
	public WebDriver driver;
	Proxy proxy;
	String baseUrl;
	FileOutputStream out = null;
	
	// use the Firefox browser and go to the wikipedia site
	//@BeforeMethod(alwaysRun=true)
	public void setUp() throws InterruptedException{
		 proxy = new Proxy();
		 //baseUrl = "http://preview-science.discovery.com/";
		 proxy.setHttpProxy("qa-013.proxy.dp.discovery.com:80");
		  // We use firefox as an example here.
		  DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		  capabilities.setCapability(CapabilityType.PROXY, proxy);
		  driver=new FirefoxDriver(capabilities);
	//driver = new FirefoxDriver();
	Reporter.log("driver created");
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	//driver.get("http://preview-science.discovery.com/wp-admin");
	//I_am_logged_as_user();
	}
	 
	// quit from WebDriver
	@AfterTest
	public void tearDown(){
	driver.quit();
	}
	 
	
	
	public void I_am_logged_as_user() throws InterruptedException  {
		//if(!AbstractDiscoveryDigital.IsElementPresent(By.xpath("//input[@id='user_login']")))
			//I_am_logged_out();
		//AbstractDiscoveryDigital.IsElementPresent(By.xpath("//input[@id='user_login']"));
		driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("gpatel");
		driver.findElement(By.xpath("//input[@id='user_pass']")).sendKeys("Abc123");
		driver.findElement(By.xpath("//input[@id='wp-submit']")).click();
		//if (AbstractDiscoveryDigital.IsElementPresent(By.xpath("//div[@id='login_error']")))
		//	org.testng.Assert.fail("The login details were incorrect");
		if (AbstractDiscoveryDigital.IsElementPresent(By.xpath("//a[contains(.,'try again')]")))
			I_am_logged_as_user();		
		
	}
	
	@DataProvider(name = "mdp")
	public Object[][] createData() throws Exception {
        DiscoveryDataProvider dp = new DiscoveryDataProvider();
        String path = "src"+File.separator+"com"+File.separator+"discovery"+File.separator+"content"+File.separator+"PagesToChange.xls";
        Object[][] retObjArr = dp.ReadDatafrmExcel("promotedata", "Entity",path);
        return (retObjArr);
	}
	
	@Test( dataProvider="mdp",description = "")
	public void ChangePageType(String urlToChnagePageType,String TargetPageType) throws InterruptedException{
		setUp();
		//driver.get("http://preview-science.discovery.com/wp-admin");
		//I_am_logged_as_user();
		//Thread.sleep(1000);
		//driver.get("http://preview-science.discovery.com/wp-admin/post.php?post=10480&action=edit");
		//driver.get("http://preview-science.discovery.com/wp-admin");
		//I_am_logged_as_user();
		driver.get(urlToChnagePageType);
		I_am_logged_as_user();
		
		driver.findElement(By.className("Settings")).click();
		//String selectedValue = Page.GetPageTypeValue(driver);
		//Reporter.log("Selected value is "+ selectedValue+"for the url->"+urlToChnagePageType);
		//if(selectedValue.equalsIgnoreCase("Topic")){
		Page.SelectPageTypeValue(driver,TargetPageType);
		//Page.SelectPageTypeValue(driver,"Article");
		driver.findElement(By.id("publish")).click();
		Thread.sleep(500);
		//now write the report file that its successful . Before class will delete existing file, create new one. This test method will call function to write file in apending mode.
		
		}
	
	
	

	public void I_am_logged_out() {
		driver.get(baseUrl + "wp-login.php?action=logout");
		if(AbstractDiscoveryDigital.IsElementPresent((By.xpath("//body[@id='error-page']//p//a[contains(text(),'log out')]"))))
			driver.findElement(By.xpath("//body[@id='error-page']//p//a[contains(text(),'log out')]")).click();
	}	
	}

