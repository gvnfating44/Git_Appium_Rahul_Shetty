package com.appium.testUtils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.appium.pageObjects.android.FormPage;
import com.appium.utils.AppiumUtils;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest extends AppiumUtils{

	public AndroidDriver driver;
//	public AppiumDriverLocalService service;
	public FormPage formpage;
	
	@BeforeClass(alwaysRun = true)
	public void configureAppium() throws URISyntaxException, IOException {
		
				Properties prop = new Properties();
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java/com//appium//resources//data.properties");
				prop.load(fis);
				
//				String ipAddress = prop.getProperty("ipAddress");
				String ipAddress = System.getProperty("ipAddress") !=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress"); // ipAddress through mvn commands or properties file
				String port = prop.getProperty("port");
		
				service = startAppiumServer(ipAddress, Integer.parseInt(port));
				

				UiAutomator2Options options = new UiAutomator2Options();
				options.setDeviceName(prop.getProperty("androidDeviceName"));
				options.setChromedriverExecutable("//Users//govind.fating//Downloads//chromedriver-mac-arm64_2//chromedriver"); ///Users/govind.fating/Downloads/chromedriver-mac-arm64 2
//				options.setApp(System.getProperty("user.dir")+"//src//test//java//com//appium//resources//ApiDemos-debug.apk");
				options.setApp(System.getProperty("user.dir")+"//src//test//java//com//appium//resources//General-Store.apk");
			
				
//				driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
				driver = new AndroidDriver(service.getUrl(), options);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				formpage = new FormPage(driver);
	}
	
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}
	
	
	
	
}
