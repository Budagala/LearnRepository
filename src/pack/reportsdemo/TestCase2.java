package pack.reportsdemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCase2 {

	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;

	@BeforeClass
	public void setUP() {
		
		extent=ExtentManager.instance();
		driver = new FirefoxDriver();

	}

	@Test
	public void firstTestCase() {

		try {

			driver.get("http://www.qavalidation.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			test = extent.startTest("Contact Page", "Verify Send Button");
			Assert.assertTrue(driver.getTitle().contains("qa"));
			test.log(LogStatus.INFO, "Site Opened");
			driver.findElement(By.linkText("Contact me!")).click();
			Thread.sleep(2000);
			WebElement send = driver.findElement(By.xpath("//*[@id='cntctfrm_submit_first_column']/div/input[6]"));
			if (send.isDisplayed()) {

				test.log(LogStatus.PASS, send.getAttribute("value") + " button found");
				test.log(LogStatus.INFO, test.addScreenCapture(ExtentManager.captureScreenShot(driver, "D:\\Selenium\\ExtentReportProjectDemo\\Send.jpg")));

			} else 
			  {
				test.log(LogStatus.FAIL, send.getAttribute("value") + " button not found");
		     }

		} catch (Exception e) {

			test.log(LogStatus.ERROR, e.getMessage());
		}

	}

	@AfterClass
	public void tearDown() {
		extent.endTest(test);
		extent.flush();
		extent.close();
		driver.quit();

	}

}
