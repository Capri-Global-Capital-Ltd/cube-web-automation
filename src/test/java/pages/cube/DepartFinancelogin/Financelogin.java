package pages.cube.DepartFinancelogin;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonKeyWords;
import property.PropertyFile;
public class Financelogin extends CommonKeyWords {
	public WebDriver driver;
	public WebDriverWait wait;
	ArrayList<String> credData = new ArrayList<String>();
	PropertyFile propReader = new PropertyFile();
	private static final String PAGE_URL = "url";

	public Financelogin(WebDriver driver, long timeoutInSeconds) {
		super(driver, timeoutInSeconds);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "/html/body/form/div[3]/div[2]/div[2]/input[1]")
	public WebElement LoginID;

	@FindBy(xpath = "/html/body/form/div[3]/div[2]/div[2]/input[2]")
	public WebElement Pass;

	@FindBy(xpath = "/html/body/form/div[3]/div[2]/div[2]/input[3]")
	public WebElement Submit;
	
	@FindBy(xpath ="/html/body/form/div[3]/div[2]/div[2]/font")
	public WebElement Error;
	
	
		public void openURL() throws Exception {
		driver.navigate().to(propReader.getProp().get(PAGE_URL).toString().trim());
	}

	public void clickloginButton() {
		click(Submit);
	}

	public String validatePopUPInvalidCredential() {
		Alert alert = driver.switchTo().alert();
		// Get the text of the alert
		String alertText = alert.getText();
		return alertText;
	}

	public String loginCredential(String Email) {
		enterText(LoginID, Email);
		return Email;
	}

	public String passWord(String Password) {
		enterText(Pass, Password);
		return Password;
	}
	public String gettext(WebElement element) {
		return getText(element);
	}
	public boolean isElementdisplayed(WebElement element) {
		return isDisplayed(element);
	}

}