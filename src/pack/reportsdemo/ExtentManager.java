package pack.reportsdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	@SuppressWarnings("deprecation")
	public static ExtentReports instance() {
		ExtentReports extent;
		String Path = "./ExtentReport.html";
		System.out.println(Path);
		extent = new ExtentReports(Path, false);
		extent.config().documentTitle("Automation Report").reportName("Regression");
		extent.addSystemInfo("Selenium Version", "2.53.1");
		extent.addSystemInfo("Environment", "QA");

		return extent;

	}

	public static String captureScreenShot(WebDriver driver, String imagePath) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);
		FileInputStream fis;

		try {
			FileUtils.copyFile(src, new File(imagePath));
		} catch (IOException e) {
			System.out.println("Exception occured at CaptureScreenShot method:" + e.toString());
			e.printStackTrace();
		}
		return imagePath;

	}

}
