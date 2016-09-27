package pack.reportsdemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCase1 {

	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;

	@BeforeClass
	public void setUP() {

		extent=ExtentManager.instance();
		driver = new FirefoxDriver();
	}

	@Test
	public void OpenAUT() {
		try {
			driver.get("http://www.qavalidation.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			test = extent.startTest("OpenUT", "Verify HomePage");
			if (driver.getTitle().contains("qavalidation"))
				test.log(LogStatus.PASS, driver.getTitle() + " contain " + "qavalidation");
			else
				test.log(LogStatus.FAIL, driver.getTitle() + " doesn't contain " + "qavalidation");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, e.getMessage());
		}
	}
	
	
	@AfterClass
	public void tearDown()
	{
		extent.endTest(test);
		extent.flush();
		extent.close();
		driver.quit();
		
	}

}
