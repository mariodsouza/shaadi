package forms;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SplashScreen {
	
	private AndroidDriver<MobileElement> driver;
	WebDriverWait wait;
	
    public SplashScreen(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    @AndroidFindBy(id="com.shaadi.android:id/registerfree")
    private AndroidElement signUpFree;
    
    public RegistrationPage clickSignUpFree() {
    	wait = new WebDriverWait(driver, 30);
    	wait.until(ExpectedConditions.attributeToBe(signUpFree, "clickable", "true"));
    	signUpFree.click();
    	return new RegistrationPage(driver);
    }

}
