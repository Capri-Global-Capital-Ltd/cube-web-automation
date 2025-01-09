package driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import property.PropertyResources;

public class BrowserFactory {
	PropertyResources propReader = new PropertyResources();
	public WebDriver driver;
	private static final String GECKO_DRIVER = "geckoDriver";

	public WebDriver initializeDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--allow-insecure-localhost");
			options.addArguments("--incognito");
			options.addArguments("--disable-extensions");
			 options.addArguments("--ignore-certificate-errors");
	  //     options.addArguments("--headless");
//			options.addArguments("--disable-gpu"); // Disable GPU hardware acceleration
//			options.addArguments("--no-sandbox"); // Added for Linux to avoid sandbox issues
//			options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecko.driver", propReader.getProp().get(GECKO_DRIVER).toString().trim());
			// Configure Firefox options for headless mode
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless"); // Run in headless mode
			options.addArguments("--disable-gpu"); // Disable GPU hardware acceleration
			options.addArguments("--no-sandbox"); // Added for Linux to avoid sandbox issues
			options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
			driver = new FirefoxDriver(options);
		} else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			// Configure Edge options if needed
			driver = new EdgeDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

}
