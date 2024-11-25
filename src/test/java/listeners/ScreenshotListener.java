package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotListener implements ITestListener {

	private static WebDriver driver;

	// No-argument constructor
	public ScreenshotListener() {
	}

	// Setter method to provide the WebDriver instance
	public static void setDriver(WebDriver driver) {
		ScreenshotListener.driver = driver;
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (driver != null) {
			// Take screenshot on test failure
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			String fileName = result.getName() + "_" + timeStamp + ".png";
			File screenshotsDir = new File("Screenshots");
	        if (!screenshotsDir.exists()) {
	            screenshotsDir.mkdirs(); // Create the directory if it doesn't exist
	        }

			try {
				FileHandler.copy(srcFile, new File("Screenshots/" + fileName));
				System.out.println("Screenshot saved as: " + fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}
}
