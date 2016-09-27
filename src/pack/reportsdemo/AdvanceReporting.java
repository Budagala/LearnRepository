package pack.reportsdemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AdvanceReporting {

	@Test
	public void verifySeleniumBlog() {

		ExtentReports extent = new ExtentReports("D:\\Ashok\\Report\\myreport.html", true);

		ExtentTest extentTest = extent.startTest("Verify Page Title");

		WebDriver driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		extentTest.log(LogStatus.INFO, "Browser started");

		driver.get("http://learn-automation.com");

		extentTest.log(LogStatus.INFO, "Get the Page Title");
		String title = driver.getTitle();

		extentTest.log(LogStatus.INFO, "checking condition");
		Assert.assertTrue(title.contains("Selenium"));

		driver.quit();

		extent.endTest(extentTest);

	}

}