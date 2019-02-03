package forms;

import java.util.Set;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RegistrationPage {
	
	public RegistrationPage(AndroidDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        //switch to webview context
        Set<String> contextNames = driver.getContextHandles();
    	for (String wv : contextNames){
    		if(wv.contains("WEBVIEW")) {
    			driver.context(wv);
        		break;
    		}
    	}
    }
    
    @FindBy(id="email")
    private AndroidElement emailField;
    
    @FindBy(id="password1")
    private AndroidElement passwordField;
    
    @FindBy(id="postedby")
    private AndroidElement postedByDD;
    
    @FindBy(id="first_name")
    private AndroidElement firstNameField;
    
    @FindBy(id="last_name")
    private AndroidElement lastNameField;
    
    @FindBy(id="gender")
    private AndroidElement genderDD;
    
    @FindBy(id="day")
    private AndroidElement dayDD;
    
    @FindBy(id="month")
    private AndroidElement monthDD;
    
    @FindBy(id="year")
    private AndroidElement yearDD;
    
    @FindBy(id="community")
    private AndroidElement religionDD;
    
    @FindBy(id="mother_tongue")
    private AndroidElement motherTongueDD;
    
    @FindBy(id="countryofresidence")
    private AndroidElement livingInDD;
    
    @FindBy(id="sign-up-btn")
    private AndroidElement submitBtn;
    
    public void enterEmail(String email) {
    	emailField.clear();
    	emailField.sendKeys(email);
    }
    
    public void enterPassword(String password) {
    	passwordField.clear();
    	passwordField.sendKeys(password);
    }
    
    public void selectProfileFor(String relation) {
    	try {
    		new Select(postedByDD).selectByValue(relation);
    	} catch (Exception e) {
			System.out.println("No such Relation present");
			e.printStackTrace();
		}
    }
    
    public void enterFirstName(String fname) {
    	firstNameField.clear();
    	firstNameField.sendKeys(fname);
    }
    
    public void enterLastName(String lname) {
    	lastNameField.clear();
    	lastNameField.sendKeys(lname);
    }
    
    public void selectGender(String gender) {
    	try {
    		new Select(genderDD).selectByVisibleText(gender);
    	} catch (Exception e) {
			System.out.println("No such Gender present");
			e.printStackTrace();
		}
    }
    
    public void selectDay(int day) {
    	try {
    		new Select(dayDD).selectByIndex(day);
    	} catch (Exception e) {
			System.out.println("Are you sure you're entering the right Day");
			e.printStackTrace();
		}
    }
    
    public void selectMonth(int month) {
    	try {
    		new Select(monthDD).selectByIndex(month);
    	} catch (Exception e) {
			System.out.println("Are you sure you're entering the right Month");
			e.printStackTrace();
		}
    }
    
    public void selectYear(String year) {
    	try {
    		new Select(yearDD).selectByValue(year);
    	} catch (Exception e) {
			System.out.println("Are you sure you're entering the right Year");
			e.printStackTrace();
		}
    }
    
    public void selectReligion(String religion) {
    	try {
    		new Select(religionDD).selectByValue(religion);
    	} catch (Exception e) {
			System.out.println("No such Religion present");
			e.printStackTrace();
		}
    }
    
    public void selectMotherTongue(String mt) {
    	try {
    		new Select(motherTongueDD).selectByValue(mt);
    	} catch (Exception e) {
			System.out.println("No such Mother Tongue present");
			e.printStackTrace();
		}
    }
    
    public void selectLivingIn(String country) {
    	try {
    		new Select(livingInDD).selectByVisibleText(country);
    	} catch (Exception e) {
			System.out.println("No such Country present");
			e.printStackTrace();
		}
    }
    
    public void clickSubmit() {
    	submitBtn.click();
    }

}
