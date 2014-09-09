package com.discovery.pageobjects;

import com.discovery.content.*;

import org.openqa.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Page {

	private static WebElement element = null;
	
	public static boolean SelectPageTypeValue(WebDriver driver, String PageTypeValue){
		
		Select select = new Select(driver.findElement(By.name("featured-links[post_type]")));
		//select.deselectAll();
		select.selectByVisibleText(PageTypeValue);
		return true;
	}
	
	public static String GetPageTypeValue(WebDriver driver){
		Select select = new Select(driver.findElement(By.name("featured-links[post_type]")));
		//select.deselectAll();
		return select.getFirstSelectedOption().getText();		 
	}
}
