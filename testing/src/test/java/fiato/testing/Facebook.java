package fiato.testing;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class Facebook {

	private static final int DEFAULT_TIMEOUT = 10000;// 10 secs ;

	private static  String strusername = "";
	private static  String strpassword = "";

	private AppiumDriver driver;
	private Wait<MobileDriver> mobileWait;

	private static Facebook instance = null;

	private static DesiredCapabilities capabilities = null;

	static Facebook getInstance() {
		if (null == instance) {
			instance = new Facebook();
		}
		return instance;
	}

	private Facebook() {
		before();
	}
	
	public void SetUserNameAndPassword(String username,String password)
	{
		strusername=username;
		strpassword=password;
	}

	@BeforeMethod
	private void before() {
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Geny");
		capabilities.setCapability("automationName", "UiAutomator2");
//	    capabilities.setCapability("platformName", "Android");
//	    capabilities.setCapability("appPackage", "com.android.calculator2");
//	    capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
		capabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.facebook.katana");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.facebook.katana.LoginActivity");

		try {
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterMethod
	private void after() {
		driver.quit();
	}

	private Wait<MobileDriver> setupFluentWait(int timeoutInSeconds, int pollingTimeInSeconds) {
		Wait<MobileDriver> fluentWait = new FluentWait<MobileDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeoutInSeconds))
				.pollingEvery(Duration.ofSeconds(pollingTimeInSeconds)).ignoring(NoSuchElementException.class);
		return fluentWait;
	}

	public boolean isVisibleOnTimeOutInsecond(By object, int timeoutInSeconds, int pollingTimeInSeconds) {
		boolean ret = true;
		try {
			mobileWait = setupFluentWait(timeoutInSeconds, pollingTimeInSeconds);
			mobileWait.until(ExpectedConditions.visibilityOfElementLocated(object));
		} catch (Exception e) {
			ret = false;
		}
		return ret;
	}

	public void scrollBy(By locator, int xPixel, int yPixel) {
		try {
			Actions act = new Actions(driver);
			SleepUntilInmilisecs(locator, DEFAULT_TIMEOUT);
			act.moveToElement(driver.findElement(locator)).clickAndHold().moveByOffset(xPixel, yPixel).release()
					.pause(2000).perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SleepUntilInmilisecs(By object, int milisecs) {
		mobileWait = setupFluentWait(milisecs, 1);
		mobileWait.until(ExpectedConditions.visibilityOfElementLocated(object));
	}

	private MobileElement findElement(By locator, int timeout) {
		MobileElement ret = null;
		try {
			SleepUntilInmilisecs(locator, timeout);
			ret = (MobileElement) driver.findElement(locator);
		} catch (Exception e) {
		}
		return ret;
	}

	@Test
	public void login() {
		boolean passwordShowAtTheFirstTme = false;
		MobileBy etusername = (MobileBy) MobileBy.AccessibilityId("Username");
		MobileBy loginBtn = (MobileBy) MobileBy.AccessibilityId("Login click");
		MobileBy etPassword = (MobileBy) MobileBy.AccessibilityId("Password");

		By permissionDeny = By.id("com.android.packageinstaller:id/permission_deny_button");
		if (true == isVisibleOnTimeOutInsecond(etusername, 2, 1)) {

			MobileElement metusername = findElement(etusername, 1000);
			if (isVisibleOnTimeOutInsecond(etusername, 2, 1))
				metusername.sendKeys(strusername);

			if (false == isVisibleOnTimeOutInsecond(etPassword, 2, 1)) {
				MobileElement mLogin01 = findElement(loginBtn, 1000);
				mLogin01.click();

				MobileElement metPassword = findElement(etPassword, 1000);
				metPassword.sendKeys(strpassword);

				MobileElement mLogin02 = findElement(loginBtn, 1000);
				mLogin02.click();

			} else {
				MobileElement metPass0 = findElement(etPassword, 1000);
				metPass0.sendKeys(strpassword);
				MobileElement mLogin00 = findElement(loginBtn, 1000);
				mLogin00.click();
			}

		}
		// By okButton=By.xpath("//android.widget.Button[@index='1']");
		By okButton = By.xpath("//android.widget.Button[@text='OK']");

		if (true == isVisibleOnTimeOutInsecond(okButton, 2, 1)) {
			MobileElement ok = findElement(okButton, 2000);
			ok.click();

			if (true == isVisibleOnTimeOutInsecond(permissionDeny, 2, 1)) {
				MobileElement denyButton = findElement(permissionDeny, 1000);
				denyButton.click();
			}
		}
		//Scrool();
		//GetStory();
	}

	public void Scrool() {
		for (int i = 0; i < 5; i++) {
			By scrollup = By.xpath("(//*[@class='android.widget.FrameLayout'])[1]");
			scrollBy(scrollup, 0, -500);
		}

		for (int i = 0; i < 5; i++) {
			By scrolldown = By.xpath("(//*[@class='android.widget.FrameLayout'])[1]");
			scrollBy(scrolldown, 0, 500);
		}
		
	}
	
	public void GetStory(int index)
	{
		By storyParent = By.xpath("(//*[@class='androidx.recyclerview.widget.RecyclerView'])[2]/*");
		List<MobileElement> rows=driver.findElements(storyParent);
		int storySize=rows.size();
		System.out.println("size: "+storySize);
		if(index<storySize)
		{
			MobileElement element=rows.get(index);
			element.click();
		}
		
		
	}

	public void LogoutAndRelogin() {

		MobileBy newsFeed = (MobileBy) MobileBy.AccessibilityId("News Feed, Tab 1 of 6");
		MobileBy friends = (MobileBy) MobileBy.AccessibilityId("Friends, Tab 2 of 6");
		MobileBy groups = (MobileBy) MobileBy.AccessibilityId("Groups, Tab 3 of 6");
		MobileBy watch = (MobileBy) MobileBy.AccessibilityId("Watch, Tab 4 of 6, 2 new");
		MobileBy notifications = (MobileBy) MobileBy.AccessibilityId("	Notifications, Tab 5 of 6, 2 new");
		MobileBy menubutton = (MobileBy) MobileBy.AccessibilityId("Menu, Tab 6 of 6");

		MobileElement me_menu = findElement(menubutton, 10000);
		me_menu.click();

	
	
		
		By menuLayout = By.xpath("//*[@class='androidx.recyclerview.widget.RecyclerView']");
		scrollBy(menuLayout, 0, -500);
		scrollBy(menuLayout, 0, -500);
		scrollBy(menuLayout, 0, -500);
		MobileBy logout = (MobileBy) MobileBy.AccessibilityId("Log Out, Button 1 of 1");
		MobileElement btnLogout = findElement(logout, 1000);
		if (null != btnLogout)
			btnLogout.click();
		 Dimension dimensions = driver.manage().window().getSize();
		  int screenWidth = dimensions.getWidth();
		  int screenHeight = dimensions.getHeight();
		  System.out.println("w: "+screenWidth);
		  System.out.println("h: "+screenHeight);
		By relogin = By.xpath("(//*[@class='android.widget.LinearLayout' and @index='5'])");
		MobileElement btnRelogin = findElement(relogin, 1000);
		if (null != btnRelogin)
			btnRelogin.click();


	}

}
