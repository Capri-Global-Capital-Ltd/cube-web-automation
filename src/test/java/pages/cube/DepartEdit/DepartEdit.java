package pages.cube.DepartEdit;
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

public class DepartEdit extends CommonKeyWords {

	public WebDriver driver;
	public WebDriverWait wait;
	ArrayList<String> credData = new ArrayList<String>();
	PropertyFile propReader = new PropertyFile();


	public DepartEdit(WebDriver driver, long timeoutInSeconds) {
		super(driver, timeoutInSeconds);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Online Requisitions')]")
	public WebElement online_re;

	@FindBy(xpath= "//a[contains(text(),'Department Control Management')]")
	public WebElement  Department_Control;

   @FindBy(xpath = "/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/ul[11]/li[30]/ul/li[1]")
	public WebElement Initiate;
   
   @FindBy(xpath = "/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[2]/table/tbody/tr[1]/td[2]/select")
   public WebElement select_drop;
	
   @FindBy(xpath="/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[2]/table/tbody/tr[1]/td[2]/select")
   public WebElement Dep;
   
   
  @FindBy(xpath="/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[2]/table/tbody/tr[6]/td/center/input[2]")
   public WebElement updateBtn;
  
   @FindBy(xpath="/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[2]/table/tbody/tr[3]/td[2]/input")
  public WebElement editSubName;

   
   @FindBy(xpath="//input[@type='submit']")
   public WebElement submitbtn;
   
   
 @FindBy(xpath="(//*[@value='Edit'])[1]")
		 public WebElement edit;
 
   @FindBy(xpath="//a[@class='lbtn']")
   public WebElement logoutint;
   
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