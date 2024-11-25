package testcases.cube.departEdit;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
import pages.cube.DepartEdit.DepartEdit;
import pages.cube.department.department;
import pages.cube.login.LoginCube;
//import pages.cube.departEdit.*;


import property.PropertyFile;
import utility.BaseFile;

public class  DepartEditTest extends BaseFile {

	private static final String TESTCASES_SHEET = "DEPARTMENT";
	private static final String SHEET_NAME = "DEPARTEDIT_SHEET";
	private ExtentReports extent;
	private WebDriver driver;
	private ExtentTest test;
  
	public DepartEdit dEdit;
	private int rownum;
	private Map<String, Object>[][] empdata;
	public WebDriverWait wait;
	

    
	PropertyFile propReader = new PropertyFile();
	BrowserFactory browserFactory = new BrowserFactory();

	// This method is calling driver and extent report.
	@BeforeMethod
	private void navigateToBaseURL() throws Exception {
		driver = BaseFile.driver;
		dEdit = new DepartEdit(driver, 5); 
		extent = extentBase;
		ScreenshotListener.setDriver(driver);
		 
	}

	// This method is used to read from excel.

	@DataProvider(name = "depEditPageDataProvider")
	private Map<String, Object>[][] callTestDataFromExcel() throws Exception {
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

	@Test(dataProvider = "depEditPageDataProvider")
	private void cubeDepartEdit(Map<String, Object> fetchData) throws InterruptedException {

		if (fetchData.entrySet() != null) {
			for (Map.Entry<String, Object> entry : fetchData.entrySet()) {

			    String testCase= null;
				String testId = null;
				String subName = null;
		   

				try {
					JSONObject testData = (JSONObject) entry.getValue();
					testCase = new String((String) testData.get("TEST_CASES")).toString();
					subName =dEdit.generateRandomAlpha(); //random values 

				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
					test = extent.createTest(testCase);

				try {
					
					dEdit.clickelement(dEdit.online_re);
			         Thread.sleep(2000);
				    
					Actions actions = new Actions(driver);
					actions.scrollByAmount(1, 10000).perform(); // Scroll down by 500 pixels

					actions.moveToElement(dEdit.Department_Control).perform();
					//Thread.sleep(2000); //hover 
					dEdit.clickelement(dEdit.Initiate);
					Thread.sleep(2000);
					
					WebElement element = driver.findElement(By.xpath("(//*[@value='Edit'])[1]"));
					
					JavascriptExecutor js = (JavascriptExecutor) driver;
									
					js.executeScript("arguments[0].click();", element);

					
					if(testId.equals("1")) {

							dEdit.EnterText(subName,dEdit.editSubName);
							Thread.sleep(2000);
	
							dEdit.click(dEdit.updateBtn);
							Thread.sleep(2000);
							
							dEdit.waitForAlertAndAccept();
					}

					else if(testId.equals("2")) {
							dEdit.EnterText(subName,dEdit.editSubName);
							Thread.sleep(2000);
	
							dEdit.click(dEdit.updateBtn);
							Thread.sleep(2000);
							
							dEdit.waitForAlertAndAccept();

					}
			}
				catch (org.openqa.selenium.NoSuchElementException e) {
					
					test.log(Status.SKIP, e.getMessage());
					Assert.fail("Deliberate failure to capture screenshot");

				} catch (AssertionError e) {
					
					test.log(Status.FAIL, e.getMessage());
					Assert.fail("Deliberate failure to capture screenshot");
				}
				
			}
		}
	}
}