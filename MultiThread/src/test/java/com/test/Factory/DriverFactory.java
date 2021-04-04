package com.test.Factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
	
	private DriverFactory() {
		// do nothing..  do not allow initialize the class from outside
		
	}
	
	private static DriverFactory instance = new DriverFactory();
	
	public static DriverFactory getInstance() {
		return instance;
	}
	
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(); //thread local driver object for webdriver
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	public void setDriver(WebDriver driverParam) {  //call this method to set the driver object
		driver.set(driverParam);
	
	}
	public void removeDriver() { //Quit the driver and closes the browser
		driver.get().quit();
		driver.remove();
	}

}
