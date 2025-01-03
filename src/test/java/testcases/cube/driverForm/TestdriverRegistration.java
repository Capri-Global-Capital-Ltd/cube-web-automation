package testcases.cube.driverForm;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import driver.BrowserFactory;
import excel.ExcelRead;
import listeners.ScreenshotListener;
import pages.cube.driverRegistrationForm.*;
import property.PropertyFile;
import utility.BaseFile;

public class  TestdriverRegistration extends BaseFile 
{
	private static final String TESTCASES_SHEET = "DRIVERFORM";
	private static final String SHEET_NAME = "DRIVERFORM_SHEET";
	private ExtentReports extent;
	private WebDriver driver;
	private ExtentTest test;
	public driverForm driverPages;
	private int rownum;
	private Map<String, Object>[][] empdata;
	public WebDriverWait wait;
	
	PropertyFile propReader = new PropertyFile();
	BrowserFactory browserFactory = new BrowserFactory();
	
	// This method is calling driver and extent report.
		@BeforeMethod
		private void navigateToBaseURL() throws Exception
		{
			driver = BaseFile.driver;
			//depPages = new driverRegstration(driver, 5); 
			driverPages= new driverForm(driver, 5);
			//driverPages = new driverForm(driver, 5); // Adjust arguments as needed for the constructor.

			extent = extentBase;
			ScreenshotListener.setDriver(driver);
		
	
		}
		//This method is used to read from excel.

		@DataProvider(name = "driverPageDataProvider")
		private Map<String, Object>[][] callTestDataFromExcel() throws Exception 
		{
			String testDataSheet = propReader.getProp().get(SHEET_NAME).toString().trim();
			String filePath = System.getProperty("user.dir") + propReader.getProp().get(TESTCASES_SHEET).toString().trim();

			{
				List<Map<String, Object>> dataList = ExcelRead.getExcelData(filePath, testDataSheet);
				rownum = dataList.size();
				empdata = (Map<String, Object>[][]) new HashMap[rownum][1];
				for (int i = 0; i < rownum; i++) {
				empdata[i][0] = dataList.get(i);
			}
				return (empdata);
			}
}

		@Test(dataProvider = "driverPageDataProvider")
		private void driverReg(Map<String, Object> fetchData) throws InterruptedException {

			if (fetchData.entrySet() != null) {
				for (Map.Entry<String, Object> entry : fetchData.entrySet()) {

					 String testCase= null;
						String testId = null;
						String driverName = null;
						String dob = null;
					    String address = null;
					    String remarks = null;
					    String mobileNo = null ;
					    String dl = null ;
					    String dlIssused = null ;
					    String dlExpriy = null ;
					    

						try {
							JSONObject testData = (JSONObject) entry.getValue();
							testCase = new String((String) testData.get("TEST_CASES")).toString();
							testId = new String ((String) testData.get("TEST_ID"));
							driverName = new String((String) testData.get("Driver's Name")).toString();
							dob = new String((String) testData.get("DOB")).toString();
							address = new String((String) testData.get("Current Address")).toString();
							mobileNo =  new String((String) testData.get("Driver's Mobile No."));
//							String month=new String((String) testData.get("month"));
//							String year=new String((String) testData.get("YEAR"));
//							String date=new String((String) testData.get("date"));
							dl = new String((String) testData.get(" DL No.")).toString();
							dlIssused = new String((String) testData.get("DL Issue Date")).toString();
							dlExpriy = new String((String) testData.get(" DL Expiry Date (Minimum 1 Year)")).toString();
							//enterSubname = depPages.generateRandomAlpha(); //random values
							
							remarks = new String((String) testData.get("Remarks")).toString();
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
						test = extent.createTest(testCase);

					try {
						
						driver.get("https://cubeuat.capriglobal.in:8012/Admin/Default.aspx");
						driverPages.click(driverPages.online_req);
						Actions actions = new Actions(driver);
						actions.scrollByAmount(1, 10000).perform(); // Scroll down by 500 pixels
						driverPages.click(driverPages.Company_Driver_Management);
						Thread.sleep(2000); //hover 
						driverPages.click(driverPages.DriverRegistrationForm);
						String projectDir = System.getProperty("user.dir");
						 String relativePath = "/PHOTO/PIC.png";
						if(testId.equals("3")) {
							
						driverPages.EnterText(driverName,driverPages.driverName);
						driverPages.click(driverPages.DOB);
						driverPages.click(driverPages.month);
						driverPages.click(driverPages.year);
						driverPages.click(driverPages.date);
						
						driverPages.EnterText(address,driverPages.address);
						
						driverPages.EnterText(mobileNo,driverPages.mobileNo);
						
						driverPages.EnterText(dl,driverPages.DLNO);
						//depPages.EnterText(dlIssused,depPages.issueDate);
						driverPages.click(driverPages.issueDate);
						driverPages.click(driverPages.issueDatemonth);
						driverPages.click(driverPages.issueDateyear);
						driverPages.click(driverPages.issueDatedate);
						
						driverPages.click(driverPages.dlexpiryClan);
						
						driverPages.click(driverPages.dlexpmonth);
						
						driverPages.click(driverPages.dlexpcladates);

						String filePath = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
						driverPages.aadharupload.sendKeys(projectDir + relativePath);
						
						
						
						String filePath1 = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
						driverPages.dlupload.sendKeys(projectDir + relativePath);
						
									
						String filePath2 = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
						driverPages.bankStat.sendKeys(projectDir + relativePath);
				
						
						String filePath3= propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
						driverPages.driverPhoto.sendKeys(projectDir + relativePath);
						
						
						driverPages.EnterText(remarks,driverPages.remark);
						driverPages.click(driverPages.submitbtn);
						driverPages.waitForAlertAndAccept();
						
						}
						if(testId.equals("2")) {
							
							driverPages.EnterText(driverName,driverPages.driverName);
							driverPages.click(driverPages.DOB);
							driverPages.click(driverPages.month);
							driverPages.click(driverPages.year);
							driverPages.click(driverPages.date);
							driverPages.EnterText(address,driverPages.address);
							driverPages.EnterText(mobileNo,driverPages.mobileNo);
							driverPages.EnterText(dl,driverPages.DLNO);
							//depPages.EnterText(dlIssused,depPages.issueDate);
							driverPages.click(driverPages.issueDate);
							driverPages.click(driverPages.issueDatemonth);
							driverPages.click(driverPages.issueDateyear);
							driverPages.click(driverPages.issueDatedate);
							
							driverPages.click(driverPages.dlexpiryClan);
							driverPages.click(driverPages.dlexpmonth);
							
							driverPages.click(driverPages.dlexpcladates);
						
							
							String filePath = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.aadharupload.sendKeys(projectDir + relativePath);
				
							String filePath1 = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.dlupload.sendKeys(projectDir + relativePath);
				
							String filePath2 = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.bankStat.sendKeys(projectDir + relativePath);
							String filePath3= propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.driverPhoto.sendKeys(projectDir + relativePath);
							driverPages.EnterText(remarks,driverPages.remark);
							driverPages.click(driverPages.submitbtn);
							driverPages.waitForAlertAndAccept();
							
							}
						if(testId.equals("1")) {
							
							driverPages.EnterText(driverName,driverPages.driverName);
							Thread.sleep(2000);
						
							driverPages.click(driverPages.DOB);
							driverPages.click(driverPages.month);
							driverPages.click(driverPages.year);
							driverPages.click(driverPages.date);
							

							driverPages.EnterText(mobileNo,driverPages.mobileNo);
						
							driverPages.EnterText(dl,driverPages.DLNO);
							
							driverPages.click(driverPages.issueDate);
							driverPages.click(driverPages.issueDatemonth);
							driverPages.click(driverPages.issueDateyear);
							driverPages.click(driverPages.issueDatedate);
							
							driverPages.click(driverPages.dlexpiryClan);
						
							driverPages.click(driverPages.dlexpmonth);
						
							driverPages.click(driverPages.dlexpcladates);
						
						
							String filePath = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.aadharupload.sendKeys(projectDir + relativePath);
						
							String filePath1 = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.dlupload.sendKeys(projectDir + relativePath);
			
							String filePath2 = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.bankStat.sendKeys(projectDir + relativePath);
						
							String filePath3= propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.driverPhoto.sendKeys(projectDir + relativePath);
							
							driverPages.EnterText(remarks,driverPages.remark);
							driverPages.click(driverPages.submitbtn);
							driverPages.waitForAlertAndAccept();
						
							driverPages.EnterText(address,driverPages.address);
							
							driverPages.click(driverPages.submitbtn);
							
							driverPages.waitForAlertAndAccept();
							}
						if(testId.equals("4")) {
							
							driverPages.EnterText(driverName,driverPages.driverName);
							
						
							driverPages.click(driverPages.DOB);
							driverPages.click(driverPages.month);
							driverPages.click(driverPages.year);
							driverPages.click(driverPages.date);
							driverPages.EnterText(address,driverPages.address);
							

							driverPages.EnterText(mobileNo,driverPages.mobileNo);
							
							driverPages.EnterText(dl,driverPages.DLNO);
							
							driverPages.click(driverPages.issueDate);
							driverPages.click(driverPages.issueDatemonth);
							driverPages.click(driverPages.issueDateyear);
							driverPages.click(driverPages.issueDatedate);
							
							driverPages.click(driverPages.dlexpiryClan);
							
							driverPages.click(driverPages.dlexpmonth);
						
							driverPages.click(driverPages.dlexpcladates);
							
							
							String filePath = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.aadharupload.sendKeys(projectDir + relativePath);
					
							String filePath1 = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.dlupload.sendKeys(projectDir + relativePath);
				
										
							String filePath2 = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.bankStat.sendKeys(projectDir + relativePath);
						
							
							String filePath3= propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
							driverPages.driverPhoto.sendKeys(projectDir + relativePath);
						
							driverPages.click(driverPages.submitbtn);
							driverPages.waitForAlertAndAccept();
						
							driverPages.EnterText(remarks,driverPages.remark);
							
							driverPages.click(driverPages.submitbtn);
							
							driverPages.waitForAlertAndAccept();
							}
						
					}
					catch (org.openqa.selenium.NoSuchElementException e) {
						
						test.log(Status.SKIP, e.getMessage());
					//	Assert.fail("Deliberate failure to capture screenshot");

					
					}
				 
				}
			}
		}

	
	}
						
