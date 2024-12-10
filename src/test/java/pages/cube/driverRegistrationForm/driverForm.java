package pages.cube.driverRegistrationForm;

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

public class driverForm extends CommonKeyWords {

	public WebDriver driver;
	public WebDriverWait wait;
	ArrayList<String> credData = new ArrayList<String>();
	PropertyFile propReader = new PropertyFile();


	public driverForm(WebDriver driver, long timeoutInSeconds) {
		super(driver, timeoutInSeconds);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//nav//ul/li/a[text()='Online Requisitions']")
	public WebElement online_req;

	@FindBy(xpath= "//*[@id=\"sample-menu-3\"]/li[34]")
	public WebElement  Company_Driver_Management;

   @FindBy(xpath = "//*[@id=\"sample-menu-3\"]/li[34]/ul/li[1]/a")
	public WebElement DriverRegistrationForm;
   
   @FindBy(xpath = "//*[@id=\"MainContent_col_2_txtDirverName\"]")
   public WebElement driverName;
   
   @FindBy(xpath="//*[@id=\"MainContent_col_2_DOBDate_txt_Date\"]")
   public WebElement DOB;
	
   @FindBy(xpath="//*[@id=\"scwMonths\"]/option[5]")
   public WebElement month;
   
   @FindBy(xpath="//*[@id=\"scwYears\"]/option[4]")
   public WebElement year;
   
   @FindBy(xpath="//*[@id=\"scwCell_16\"]")
   public WebElement date;
   
   @FindBy(xpath="//*[@id=\"MainContent_col_2_txtCurrentAddress\"]")
   public WebElement address;
   
   @FindBy(xpath="//*[@id=\"MainContent_col_2_txtMobileNo\"]")
   public WebElement mobileNo;
   
   @FindBy(xpath="//*[@id=\"MainContent_col_2_txtDLNo\"]")
   public WebElement DLNO;
 
   @FindBy(xpath="//*[@id=\"MainContent_col_2_DLIssueDate_txt_Date\"]")
   public WebElement issueDate;
   
   @FindBy(xpath="//*[@id=\"scwYears\"]/option[105]")
   public WebElement issueDatemonth;
   
   @FindBy(xpath="//*[@id=\"scwYears\"]/option[10]")
   public WebElement issueDateyear;
   
   @FindBy(xpath="//*[@id=\"scwCell_16\"]")
   public WebElement issueDatedate;
   
   @FindBy(xpath="//*[@id=\"MainContent_col_2_DLExpiryDate_txt_Date\"]")
   public WebElement dlexpiryClan;
   
   @FindBy(xpath="//*[@id=\"scwMonths\"]/option[12]")
   public WebElement dlexpmonth;
   
   @FindBy(xpath="//*[@id=\"scwYears\"]/option[110]")
   public WebElement dlexpyear;
   
   @FindBy(xpath="//*[@id=\"scwCell_30\"]")
   public WebElement dlexpcladates;
   
   @FindBy(xpath="/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/fieldset/table/tbody/tr[5]/td[2]/input")
   public WebElement aadharupload;
   
   	@FindBy(xpath="/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/fieldset/table/tbody/tr[5]/td[4]/input")
	public WebElement dlupload;
 
    @FindBy(xpath="/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/fieldset/table/tbody/tr[6]/td[2]/input")
    public WebElement bankStat; 
    
    @FindBy(xpath="/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/fieldset/table/tbody/tr[6]/td[4]/input")
	 public WebElement driverPhoto;
 
    @FindBy(xpath="//*[@id=\"MainContent_col_2_txtRemarks\"]")
    public WebElement remark;
    
   @FindBy(xpath="//a[@class='lbtn']")
   public WebElement logoutint;
   
   @FindBy(xpath="/html/body/form/div[3]/div/div[2]/div[1]/div/div/div[2]/div[2]/div[2]/div[1]/input[1]")
   public WebElement submitbtn;
   
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