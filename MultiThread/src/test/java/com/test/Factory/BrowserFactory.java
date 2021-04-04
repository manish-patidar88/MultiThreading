package com.test.Factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	public static WebDriver createInstance(String browser) {
		WebDriver driver = null;
		if(browser.equals("Mozilla")){
			//Add capabilities for mozilla firefox
		}
		else if (browser.equals("IE")) {
			//System.setProperty("webdriver.ie.driver",CONFIG.getProperty("driverPath")+"IEDriverServer.exe");
			DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			cap.setCapability("nativeEvents", true);    
			cap.setCapability("unexpectedAlertBehaviour", "accept");
			cap.setCapability("ignoreProtectedModeSettings", true);
			cap.setCapability("disable-popup-blocking", true);
			cap.setCapability("enablePersistentHover", true);
			cap.setCapability("ensureCleanSession", true);
			cap.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);				

		}else if(browser.equals("Edge")) {
			//System.setProperty("webdriver.edge.driver",CONFIG.getProperty("driverPath")+"msedgedriver.exe");					
			HashMap<String, Object> edgePrefs = new HashMap<String, Object>();
			edgePrefs.put("profile.default_content_setting_values.notifications", 0);				
			//edgePrefs.put("download.default_directory", downloadFilepath);				
			//edgePrefs.put("browser.set_download_behavior", "{ behavior: 'allow' , downloadPath: '"+downloadFilepath+"'}"); 
			edgePrefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			edgePrefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);		      
			edgePrefs.put("credentials_enable_service", false);
			edgePrefs.put("profile.password_manager_enabled", false);
			//edgePrefs.put("PluginsAllowedForUrls", appUrl);									
			EdgeOptions options = new EdgeOptions();			
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			// Enable Flash for this site			

			cap.setCapability("browserstack.edge.enablePopups", "false");
			//cap.setCapability(EdgeOptions.CAPABILITY, edgePrefs);
			cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);		      
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//cap.setCapability("download.default_directory", downloadFilepath);
			//cap.setCapability("browser.set_download_behavior", "{ behavior: 'allow' , downloadPath: '"+downloadFilepath+"'}");
			cap.setCapability("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			cap.setCapability("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
			//cap.setCapability("PluginsAllowedForUrls", appUrl);
			//cap.setCapability(EdgeOptions.CAPABILITY, options);	
			//cap.setCapability(EdgeOptions.CAPABILITY,edgePrefs);
			driver= new EdgeDriver(cap);

		}else if (browser.equals("Chrome")){				
			//System.setProperty("webdriver.chrome.driver",CONFIG.getProperty("driverPath")+"chromedriver.exe");						 
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups",0);
			//1-Allow, 2-Block, 0-default
			chromePrefs.put("profile.default_content_setting_values.notifications", 0);
			//chromePrefs.put("download.default_directory", downloadFilepath);
			//chromePrefs.put("browser.set_download_behavior", "{ behavior: 'allow' , downloadPath: '"+downloadFilepath+"'}"); 
			chromePrefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
			chromePrefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);	
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("profile.password_manager_enabled", false);
			//chromePrefs.put("PluginsAllowedForUrls", appUrl); 					
			ChromeOptions options = new ChromeOptions();
			//HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
			options.setExperimentalOption("prefs", chromePrefs);			
			options.addArguments("disable-popup-blocking");					
			options.addArguments("--disable-web-security");
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("--test-type");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-features=VizDisplayCompositor");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation")); 					
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");
			options.addArguments("force-device-scale-factor=0.85");
			options.addArguments("high-dpi-support=0.85");
			//options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080","--ignore-certificate-errors", "--silent","start-maximized","--disable-extensions");

			// Enable Flash for this site		       			      
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);					
			cap.setCapability(ChromeOptions.CAPABILITY, chromePrefs);
			cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);		      
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);		      
			cap.setCapability(ChromeOptions.CAPABILITY, options);		     
			System.setProperty("webdriver.chrome.silentOutput","true");
			WebDriverManager.chromedriver().setup();			
			driver = new ChromeDriver();				

			driver.manage().timeouts().pageLoadTimeout(150, TimeUnit.SECONDS);
		}
		driver.manage().window().maximize();						 				
		driver.manage().timeouts().pageLoadTimeout(150, TimeUnit.SECONDS);

		return driver;	
	}
}
