package fiato.testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;


/**
 * Unit test for simple App.
 */
public class AppTest {
	AppiumDriver driver;
	Wait<MobileDriver> mobileWait;
	@Test
	public void aclickLogin()
	{
	/*	
		MobileBy etusername = (MobileBy) MobileBy.AccessibilityId("Username");
		MobileElement metusername= (MobileElement) driver.findElement(etusername);		
		if(true==		isVisible(etusername))
			metusername.sendKeys("0922224002");


		
		MobileBy etPasse = (MobileBy) MobileBy.AccessibilityId("Password");
		MobileElement metPasse= (MobileElement) driver.findElement(etPasse);		
		if(true==isVisible(etPasse))
			metPasse.sendKeys("aloha123");
		
		
		MobileBy loginBtnxxx = (MobileBy) MobileBy.AccessibilityId("Login click");				
		MobileElement mExx= (MobileElement) driver.findElement(loginBtnxxx);
		mExx.click();
	
		
		
		SleepUntil(etPasse,20);		
		metPasse= (MobileElement) driver.findElement(etPasse);		
		
		if(true==isVisible(etPasse))
		{
			metPasse.sendKeys("aloha123");
			
			MobileBy loginBtnSecond = (MobileBy) MobileBy.AccessibilityId("Login click");		
		
			MobileElement mloginBtnSecond= (MobileElement) driver.findElement(loginBtnSecond);
			mloginBtnSecond.click();
	
			
			
		}
		
		*/
//		By okButton=By.xpath("//android.widget.Button[@index='1']");
		By okButton=By.xpath("//android.widget.Button[@text='OK']");
		
	//	SleepUntil(etPasse,20);		
		driver.findElement(okButton).click();
		
	}
	
//	@Test
	public void bAddUserNameAndPass()
	{

		
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
	    capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.facebook.katana");
	    capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.facebook.katana.LoginActivity");  
	    
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
	
	public void SleepUntil(By object,int milisecs)
	{
		mobileWait=setupFluentWait(milisecs,1);
		mobileWait.until(ExpectedConditions.visibilityOfElementLocated(object));
	}
   
	
	public boolean isVisible(By object)
	{
		mobileWait=setupFluentWait(10,1);
		if(null!=mobileWait.until(ExpectedConditions.visibilityOfElementLocated(object)))
		{
			return true;
		}
		return false;
	}
	
	
	 public Wait<MobileDriver> setupFluentWait(int timeoutInSeconds, int pollingTimeInSeconds) {    
		 Wait<MobileDriver> fluentWait = new FluentWait<MobileDriver>(driver)
		        .withTimeout(Duration.ofSeconds(timeoutInSeconds))        
		        .pollingEvery(Duration.ofSeconds(pollingTimeInSeconds))
		        .ignoring(NoSuchElementException.class);
		    return fluentWait;
		  }
	
}
