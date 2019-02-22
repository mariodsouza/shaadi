package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Formatter;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
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
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.shaadi.android");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "com.shaadi.android.ui.achivement_splash.SplashActivity");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
    	WebElement register = driver.findElement(By.id("registerfree"));
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
	}
	
	@DataProvider(name="userDetails")
	public static Object[][] userDetails() throws IOException{
		
		File src=new File("C:\\Users\\dsouzam2\\Desktop\\shaadi.com\\shaadi\\src\\test\\resources\\test_data.xlsx");
		DataFormatter formatter = new DataFormatter(Locale.US);

		// load file
		FileInputStream fis=new FileInputStream(src);

		// Load workbook
		XSSFWorkbook wb=new XSSFWorkbook(fis);

		// Load sheet- Here we are loading first sheet only
		XSSFSheet sh1= wb.getSheetAt(0);
		int rows = sh1.getLastRowNum();
		int cells = sh1.getRow(0).getLastCellNum();
		Object [][] data = new Object[rows][cells];
		for (int i=1; i<=rows; i++) {
			cells = sh1.getRow(i).getLastCellNum();
			for (int j=0; j<cells; j++) {
				data[i-1][j] = formatter.formatCellValue(sh1.getRow(i).getCell(j));
				
			}
		}
		
		return data;

		
		 /*return new Object[][] {
			   { "Cedric@"},
			   { "@Anne"},
			 };*/
	}
	
	@Test(dataProvider="userDetails")
	public void test1(String email, String password, String profileFor, 
			String fName, String lName, String gender, 
			String day, String month, String year, 
			String religion, String mt, String country) {
		
    	
    	driver.findElement(By.id("email")).sendKeys(email);
    	driver.findElement(By.id("password1")).sendKeys(password);
    	
    	new Select(driver.findElement(By.id("postedby"))).selectByValue(profileFor);
    	
    	driver.findElement(By.id("first_name")).sendKeys(fName);
    	driver.findElement(By.id("last_name")).sendKeys(lName);
    	
    	new Select(driver.findElement(By.id("gender"))).selectByVisibleText(gender);
    	
    	new Select(driver.findElement(By.id("day"))).selectByValue(day); //select day as 05 
    	
    	new Select(driver.findElement(By.id("month"))).selectByValue(month); //select month as Jan
    	
    	new Select(driver.findElement(By.id("year"))).selectByValue(year);
    	
    	new Select(driver.findElement(By.id("community"))).selectByValue(religion);
    	
    	new Select(driver.findElement(By.id("mother_tongue"))).selectByValue(mt);
    	
    	new Select(driver.findElement(By.id("countryofresidence"))).selectByVisibleText(country);
    	
    	driver.findElement(By.id("sign-up-btn")).click();
	}
	
	//@Test
	public void test() throws IOException {
		File src=new File("C:\\Users\\dsouzam2\\Desktop\\shaadi.com\\shaadi\\src\\test\\resources\\test_data.xlsx");
		DataFormatter formatter = new DataFormatter(Locale.US);
		Object [][] data = null;

		// load file
		FileInputStream fis=new FileInputStream(src);

		// Load workbook
		XSSFWorkbook wb=new XSSFWorkbook(fis);

		// Load sheet- Here we are loading first sheet only
		XSSFSheet sh1= wb.getSheetAt(0);
		int rows = sh1.getLastRowNum();
		int cells = 0;
		for (int i=1; i<=rows; i++) {
			cells = sh1.getRow(i).getLastCellNum();
			for (int j=0; j<cells; j++) {
				data[i][j] = formatter.formatCellValue(sh1.getRow(i).getCell(j));
//				System.out.println(formatter.formatCellValue(sh1.getRow(i).getCell(j)));
				
			}
		}
		
//		String email = sh1.getRow(1).getCell(1).getStringCellValue();
//		String password = sh1.getRow(1).getCell(2).getStringCellValue();
		
//		System.out.println(rows);
	}
	
	//@AfterClass
    public void tearDown() {
        driver.quit();
    }

}
