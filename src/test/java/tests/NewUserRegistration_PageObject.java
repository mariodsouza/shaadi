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

import forms.RegistrationPage;
import forms.SplashScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class NewUserRegistration_PageObject {
	
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
		SplashScreen splashScreen = new SplashScreen((AndroidDriver<MobileElement>) driver);
		RegistrationPage registrationPage = splashScreen.clickSignUpFree();
		registrationPage.enterEmail("pageobject@gmail.com");
		registrationPage.enterPassword("password");
		registrationPage.selectProfileFor("Self");
		registrationPage.enterFirstName("Page");
		registrationPage.enterLastName("Object");
		registrationPage.selectGender("Male");
		registrationPage.selectDay(10);
		registrationPage.selectMonth(9);
		registrationPage.selectYear("1980");
		registrationPage.selectReligion("Christian");
		registrationPage.selectMotherTongue("English");
		registrationPage.selectLivingIn("UAE");
		registrationPage.clickSubmit();
	}
	
	@AfterClass
    public void tearDown() {
        driver.quit();
    }

}
