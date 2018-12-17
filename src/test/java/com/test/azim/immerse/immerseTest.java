package com.test.azim.immerse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class immerseTest {
	WebDriver driver = null;
	Actions action = null;
	ExtentReports report = null;
	ExtentTest test = null;

	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");

		driver = new ChromeDriver();
		
		action = new Actions(driver);

		report = new ExtentReports("C:\\Users\\Azim\\eclipse-workspace\\immerse\\target\\" + "index.html", true);

		test = report.startTest("Entering a VR WebGL session");

		driver.manage().window().maximize();

		driver.get("https://prism-staging.immerse.io/s/SJ81j6iEb");
		test.log(LogStatus.INFO, "Enter login");
	}

	@Test
	public void login() {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		
		WebElement guestLogin;
		WebElement continueBtn;
		guestLogin= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("guestnameinput")));
		 
		guestLogin.sendKeys("QA_Test");
		action.sendKeys(Keys.TAB).perform();

		action.sendKeys(Keys.ENTER).perform();
		
		continueBtn= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ImmerseApp\"]/div[2]/ng-component/div/div[1]/onboarding/div/div/logo/div/img")));
		
		continueBtn.findElement(By.xpath("//*[@id=\"devices-continue\"]"));
		report.endTest(test);
		report.flush();
	}

	@After
	public void teardown() {
		driver.close();
	}
}
