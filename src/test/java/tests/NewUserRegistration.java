package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class NewUserRegistration {
	
	public AppiumDriver<MobileElement> driver;
    WebDriverWait wait;
	
	@BeforeClass
    public void setUp() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/shaadiDebug_Shaadi_02-Jan-2019_05_11-1.apk");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung");
        capabilities.setCapability("appWaitPackage", "com.shaadi.android");
        capabilities.setCapability("appWaitActivity", "com.shaadi.android.ui.achivement_splash.SplashActivity");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void test1() {
		wait = new WebDriverWait(driver, 30);
    	WebElement register = driver.findElement(By.id("com.shaadi.android:id/registerfree"));
    	wait.until(ExpectedConditions.attributeToBe(register, "clickable", "true"));
    	register.click();
    	
    	//switch to webview context
    	Set<String> contextNames = driver.getContextHandles();
    	for (String wv : contextNames){
    		if(wv.contains("WEBVIEW")) {
    			driver.context(wv);
        		break;
    		}
    	}
    	
    	driver.findElement(By.id("email")).sendKeys("testuser@gmail.com");
    	driver.findElement(By.id("password1")).sendKeys("testuserpassword");
    	
    	new Select(driver.findElement(By.id("postedby"))).selectByValue("Brother");
    	
    	driver.findElement(By.id("first_name")).sendKeys("First");
    	driver.findElement(By.id("last_name")).sendKeys("Last");
    	
    	new Select(driver.findElement(By.id("gender"))).selectByVisibleText("Male");
    	
    	new Select(driver.findElement(By.id("day"))).selectByIndex(5); //select day as 05 
    	
    	new Select(driver.findElement(By.id("month"))).selectByIndex(1); //select month as Jan
    	
    	new Select(driver.findElement(By.id("year"))).selectByValue("1990");
    	
    	new Select(driver.findElement(By.id("community"))).selectByValue("Christian");
    	
    	new Select(driver.findElement(By.id("mother_tongue"))).selectByValue("English");
    	
    	new Select(driver.findElement(By.id("countryofresidence"))).selectByVisibleText("UAE");
    	
//    	driver.findElement(By.id("sign-up-btn")).click();
	}
	
	@AfterClass
    public void tearDown() {
        driver.quit();
    }

}
