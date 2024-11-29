//package testcases.cube.departmentfinance;
package testcases.cube.departmentfinance;
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
import pages.cube.department.department;
import pages.cube.login.LoginCube;
import pages.cube.DepartFinanceScreen.*;
import pages.cube.DepartHRScreen.*;


import property.PropertyFile;
import testcases.cube.login.CubeLoginTest;
import utility.BaseFile;

public class  FinanceScreenTest extends BaseFile {

	private static final String TESTCASES_SHEET = "DEPARTMENT";
	private static final String SHEET_NAME = "FINANCEScreen_SHEET";
	private ExtentReports extent;
	private WebDriver driver;
	private ExtentTest test;
	public FinanceScreen FinPages;
	private int rownum;
	private Map<String, Object>[][] empdata;
	public WebDriverWait wait;
	

	PropertyFile propReader = new PropertyFile();
	BrowserFactory browserFactory = new BrowserFactory();

	// This method is calling driver and extent report.
	@BeforeMethod
	private void navigateToBaseURL() throws Exception {
		driver = BaseFile.driver;
		FinPages = new FinanceScreen(driver, 5); 
		//new LoginCube(driver, 5);
		extent = extentBase;
		ScreenshotListener.setDriver(driver);
		 
	}

	// This method is used to read from excel.

	@DataProvider(name = "FinPageDataProvider")
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

	@Test(dataProvider = "FinPageDataProvider")
	public void cubeFinanceScreen(Map<String, Object> fetchData) throws InterruptedException {
		   CubeLoginTest clogin = new CubeLoginTest();
		   
		   
		if (fetchData.entrySet() != null) {
			for (Map.Entry<String, Object> entry : fetchData.entrySet()) {

			    String testCase= null;
				String testId = null;
				String deptCode = null;
				String subDeptCode = null;
			    String Remark = null;

				try {
					JSONObject testData = (JSONObject) entry.getValue();
					testCase = new String((String) testData.get("TEST_CASES")).toString();
					deptCode = new String((String) testData.get("ERP Dept Code")).toString();
			    	subDeptCode = new String((String) testData.get("ERP Sub Dept Code")).toString();
					testId = new String ((String) testData.get("TEST_ID"));
					Remark = new String((String) testData.get("Remarks")).toString();

				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
					test = extent.createTest(testCase);

				try {
					driver.get("https://cubeuat.capriglobal.in:8012/Admin/Default.aspx");
					 FinPages.clickelement(FinPages.online_re);
			         Thread.sleep(2000);

					Actions actions = new Actions(driver);
					actions.scrollByAmount(1, 10000).perform(); // Scroll down by 500 pixels

					
					actions.moveToElement(FinPages.Department_Control).perform();
					 //hover 
					
					actions.moveToElement(FinPages.fin).perform();
					//hover 
					
					FinPages.click(FinPages.pendingReq);
					
					
					FinPages.click(FinPages.startclick);
					
					
					if(testId.equals("1")) 
					{
						List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
						System.out.println("Number of iframes on the page: " + iframes.size());
						driver.switchTo().frame("jsFrame"); 
						FinPages.EnterText(deptCode,FinPages.depCode);
						FinPages.EnterText(subDeptCode,FinPages.SubdepCode);
						FinPages.EnterText(Remark,FinPages.remarks_HR);         
						
						FinPages.JavaScriptclick(FinPages.approvalBtn);
		   				
		   				
		   				FinPages.waitForAlertAndAccept();
						driver.switchTo().defaultContent();
						
					
				
			}
				else if(testId.equals("2")) {
					
					
					List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
					System.out.println("Number of iframes on the page: " + iframes.size());
					driver.switchTo().frame("jsFrame"); 
					FinPages.EnterText(deptCode,FinPages.depCode);
					FinPages.EnterText(subDeptCode,FinPages.SubdepCode);
					FinPages.EnterText(Remark,FinPages.remarks_HR);         
					
					FinPages.JavaScriptclick(FinPages.disapprovalBtn);
	   		
	   				FinPages.waitForAlertAndAccept();
					driver.switchTo().defaultContent();
					
						
					}
					else if(testId.equals("3")) {
					
					
					List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
					System.out.println("Number of iframes on the page: " + iframes.size());
					driver.switchTo().frame("jsFrame"); 
					
					FinPages.EnterText(deptCode,FinPages.depCode);
					FinPages.EnterText(subDeptCode,FinPages.SubdepCode);
					FinPages.EnterText(Remark,FinPages.remarks_HR);         
					
					FinPages.JavaScriptclick(FinPages.disapprovalBtn);
	   				
	   				
	   				FinPages.waitForAlertAndAccept();
					
					
					driver.switchTo().defaultContent();
				
					
					
					}
					else if(testId.equals("4")) {
						
						
						List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
						System.out.println("Number of iframes on the page: " + iframes.size());
						driver.switchTo().frame("jsFrame"); 
						
						FinPages.EnterText(deptCode,FinPages.depCode);
						FinPages.EnterText(subDeptCode,FinPages.SubdepCode);
						FinPages.EnterText(Remark,FinPages.remarks_HR);         
						
						FinPages.JavaScriptclick(FinPages.disapprovalBtn);
		   				
		   				
		   				FinPages.waitForAlertAndAccept();
						
						
						driver.switchTo().defaultContent();
					
						
						FinPages.click(FinPages.exitbtn);
						}
				}catch (org.openqa.selenium.NoSuchElementException e) {
					
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