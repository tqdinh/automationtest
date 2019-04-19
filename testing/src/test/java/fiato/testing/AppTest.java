package fiato.testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;


/**
 * Unit test for simple App.
 */
public class AppTest {
	AppiumDriver driver;
	@Test
	public void clickLogin()
	{
		By loginBtn = By.id("kryptono.fiato:id/tv_login");
		MobileElement mE= (MobileElement) driver.findElement(loginBtn);
		mE.click();
		
		
		}
	
	
	
	@BeforeMethod
	public void before()
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("deviceName", "Geny");
	    capabilities.setCapability("automationName", "UiAutomator2");
//	    capabilities.setCapability("platformName", "Android");
//	    capabilities.setCapability("appPackage", "com.android.calculator2");
//	    capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
	    capabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, "Android");
	    capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "kryptono.fiato");
	    capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"kryptono.fiato.activity.SplashActivity");  
	    
	    try {
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void after()
	{
		//driver.quit();
	}
   
}
