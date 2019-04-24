package fiato.testing;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
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
	static AppiumDriver driver;
	static Wait<MobileDriver> mobileWait;
	static final String strusername = "0922224002";
	static final String strpassword = "aloha123";

	@Test
	public static void aclickLogin() {
		boolean passwordShowAtTheFirstTme = false;
		MobileBy etusername = (MobileBy) MobileBy.AccessibilityId("Username");
		MobileBy loginBtn = (MobileBy) MobileBy.AccessibilityId("Login click");
		MobileBy etPassword = (MobileBy) MobileBy.AccessibilityId("Password");

		By permissionDeny = By.id("com.android.packageinstaller:id/permission_deny_button");
		if (true == isVisible(etusername)) {

			MobileElement metusername = (MobileElement) driver.findElement(etusername);
			metusername.sendKeys(strusername);

			if (false == isVisible(etPassword)) {
				MobileElement mLogin01 = (MobileElement) driver.findElement(loginBtn);
				mLogin01.click();

				MobileElement metPassword = (MobileElement) driver.findElement(etPassword);
				metPassword.sendKeys(strpassword);

				MobileElement mLogin02 = (MobileElement) driver.findElement(loginBtn);
				mLogin02.click();

			} else {
				MobileElement metPass0 = (MobileElement) driver.findElement(etPassword);
				metPass0.sendKeys(strpassword);
				MobileElement mLogin00 = (MobileElement) driver.findElement(loginBtn);
				mLogin00.click();
			}

		} else {

		}

		// By okButton=By.xpath("//android.widget.Button[@index='1']");
		By okButton = By.xpath("//android.widget.Button[@text='OK']");
		SleepUntil(okButton, 5);
		driver.findElement(okButton).click();
		if (true == isVisible(permissionDeny)) {
			MobileElement denyButton = (MobileElement) driver.findElement(permissionDeny);
			denyButton.click();
		}
		
		

//		By scrol = By.xpath("(//android.view.ViewGroup[@content-desc='Go to profile'])[1]/..");
		
		By scrolldown = By.xpath("(//*[@class='android.widget.FrameLayout'])[1]");
		scrollBy(scrolldown, 0, 500);

		By scrolldown1 = By.xpath("(//*[@class='android.widget.FrameLayout'])[1]");
		scrollBy(scrolldown1, 0, 500);

		By scrolldown2 = By.xpath("(//*[@class='android.widget.FrameLayout'])[1]");
		scrollBy(scrolldown2, 0, 500);

		By scrollup1 = By.xpath("(//*[@class='android.widget.FrameLayout'])[1]");
		scrollBy(scrollup1, 0, -500);
		
		
	}

//	@Test
	public void bAddUserNameAndPass() {

	}

	@BeforeMethod
	public static void before() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
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
	public static void after() {
		driver.quit();
	}

	public static void SleepUntil(By object, int milisecs) {
		mobileWait = setupFluentWait(milisecs, 1);
		mobileWait.until(ExpectedConditions.visibilityOfElementLocated(object));
	}

	public static boolean isVisible(By object) {
		boolean ret = true;
		try {
			mobileWait = setupFluentWait(3, 1);
			mobileWait.until(ExpectedConditions.visibilityOfElementLocated(object));
		} catch (Exception e) {
			ret = false;
			// e.printStackTrace();
		}
		return ret;
	}

	public static Wait<MobileDriver> setupFluentWait(int timeoutInSeconds, int pollingTimeInSeconds) {
		Wait<MobileDriver> fluentWait = new FluentWait<MobileDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeoutInSeconds))
				.pollingEvery(Duration.ofSeconds(pollingTimeInSeconds)).ignoring(NoSuchElementException.class);
		return fluentWait;
	}

	public void main() {
		aclickLogin();
	}

	public static void mainx(String[] args) {
		before();
		aclickLogin();
		after();
	}

	
	
	
	public static void scrollBy(By locator, int xPixel, int yPixel) {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(locator)).clickAndHold().moveByOffset(xPixel, yPixel).release().pause(2000).perform();
	}

}
