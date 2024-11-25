package pages.cube.DepartFinanceScreen;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import common.CommonKeyWords;
import property.PropertyFile;

public class FinanceScreen extends CommonKeyWords {

	public WebDriver driver;
	public WebDriverWait wait;
	ArrayList<String> credData = new ArrayList<String>();
	PropertyFile propReader = new PropertyFile();


	public FinanceScreen(WebDriver driver, long timeoutInSeconds) {
		super(driver, timeoutInSeconds);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[contains(text(),'Online Requisitions')]")
	public WebElement online_re;
	
	@FindBy(xpath= "//a[contains(text(),'Department Control Management')]")
	public WebElement  Department_Control;

	@FindBy(xpath= "/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/ul[11]/li[30]/ul/li[1]/a")
	public WebElement fin;
	
	@FindBy(xpath= "/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/ul[11]/li[30]/ul/li[1]/ul/li[1]/a")
	public WebElement pendingReq;
			
	@FindBy(xpath="/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[2]/div/div/table/tbody/tr[2]")
	public WebElement startclick;
   
	@FindBy(xpath="/html/body/div[8]/div/form/div[3]/div[4]/fieldset/table/tbody/tr[1]/td[2]/input")
	public WebElement depCode;
	
	
	@FindBy(xpath="/html/body/div[8]/div/form/div[3]/div[4]/fieldset/table/tbody/tr[1]/td[4]/input")
	public WebElement SubdepCode;
	
	@FindBy(xpath="/html/body/div[8]/div/form/div[3]/div[4]/fieldset/table/tbody/tr[2]/td[2]/textarea")
	public WebElement remarks_HR;
	
	@FindBy(xpath="/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/ul[11]/li[30]/ul/li[1]/ul/li[2]/a")
	public WebElement FinArchive;
	
	@FindBy(xpath="//input[@id='mainContent_btnApprove']")
	public WebElement approvalBtn;
	
	@FindBy(xpath="//input[@id='mainContent_btnReject']")
	public WebElement disapprovalBtn;
	
	
    @FindBy(xpath="//a[@class='lbtn']")
    public WebElement exitbtn;
   
	public String validatePopUPInvalidCredential() {
		Alert alert = driver.switchTo().alert();
		// Get the text of the alert
		String alertText = alert.getText();
		return alertText;
	}
	public void selectDropdown(WebElement element , String Text) {
		click(element);
		
		}

	public void EnterText(String Password,WebElement element) {
		enterText(element, Password);
	
	}
	
	public void clickelement(WebElement element) {
		click (element);
	}

}