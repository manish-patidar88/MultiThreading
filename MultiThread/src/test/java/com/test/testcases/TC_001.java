package com.test.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.Factory.BrowserFactory;
import com.test.Factory.DriverFactory;

import net.bytebuddy.asm.Advice.Enter;

public class TC_001 {

	WebDriver driver = null;
	
	
	@BeforeClass	
	void setup() {
		driver = BrowserFactory.createInstance("Chrome");
		DriverFactory.getInstance().setDriver(driver);
		driver = DriverFactory.getInstance().getDriver();
		
		SessionId session = ((ChromeDriver)driver).getSessionId();
		System.out.println("Session id of TC001: "+session);
	}
	
	@Test
	@Parameters({"URL","Search"})
	public void search(String url,String search) {
		driver.get(url);
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(search);
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);		
		
	}
}
