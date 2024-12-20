package testcases.cube.departmentfinanceScreen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
import pages.cube.login.LoginCube;

import property.PropertyFile;
import utility.BaseFile;

public class FinanceloginTest extends BaseFile {

	private static final String TESTCASES_SHEET = "DEPARTMENT";
	private static final String SHEET_NAME = "FINANCE_SHEET";
	private ExtentReports extent;
	private WebDriver driver;
	private LoginCube loginPage;
	private ExtentTest test;
	private int rownum;
	private Map<String, Object>[][] empdata;
	public WebDriverWait wait;

	PropertyFile propReader = new PropertyFile();
	BrowserFactory browserFactory = new BrowserFactory();

	// This method is calling driver and extent report.
	@BeforeMethod
	private void navigateToBaseURL() throws Exception {
		driver = BaseFile.driver;
		loginPage = new LoginCube(driver, 10);
		loginPage.openURL();
		extent = extentBase;
		ScreenshotListener.setDriver(driver);

	}

	// This method is used to read from excel.

	@DataProvider(name = "FinloginPageDataProvider")
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

	@Test(dataProvider = "FinloginPageDataProvider")
	private void cubeFinanceLogin(Map<String, Object> fetchData) throws InterruptedException {

		if (fetchData.entrySet() != null) {
			for (Map.Entry<String, Object> entry : fetchData.entrySet()) {

			    String testCase= null;
				String testId = null;
				String emailCred = null;
				String passCred = null;
			    String messageData = null;
	

				try {
					JSONObject testData = (JSONObject) entry.getValue();
					testCase = new String((String) testData.get("TESTCASE"));
					emailCred = new String((String) testData.get("LOGIN")).toString();
					passCred = new String((String) testData.get("PASS")).toString();
					messageData = new String((String) testData.get("MESSAGE"));
					testId = new String ((String) testData.get("TESTID"));
				

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				test = extent.createTest(testCase);

				try {
					if(testId.equals("1")) {

					
					loginPage.passWord(passCred);
					loginPage.loginCredential(emailCred);
					loginPage.clickloginButton();
					}
					else if(testId.equals("3")) {
						loginPage.passWord(passCred);
						loginPage.loginCredential(emailCred);
						loginPage.clickloginButton();
						Assert.assertEquals(loginPage.getText(loginPage.Error), messageData);
					
						}
					else if(testId.equals("2")) {

						loginPage.loginCredential(emailCred);
						loginPage.passWord(passCred);
						loginPage.clickloginButton();
						Assert.assertEquals(loginPage.getText(loginPage.Error), messageData);
					}else if(testId.equals("4")) {
						
						loginPage.passWord(passCred);
						loginPage.loginCredential(emailCred);
						loginPage.clickloginButton();
						Assert.assertEquals(loginPage.getText(loginPage.Error), messageData);
					
						}
					else if(testId.equals("5")) {
						
						loginPage.passWord(passCred);
						loginPage.loginCredential(emailCred);
						loginPage.clickloginButton();
						Assert.assertEquals(loginPage.getText(loginPage.Error), messageData);
						
						
						}
				else if(testId.equals("6")) {
					
					loginPage.passWord(passCred);
					loginPage.loginCredential(emailCred);
					loginPage.clickloginButton();
					//Assert.assertEquals(loginPage.getText(loginPage.Error), messageData);

					loginPage.logoutBtn();
					}
				
			}catch (org.openqa.selenium.NoSuchElementException e) {
					// Capture the NoSuchElementException message
					test.log(Status.SKIP, e.getMessage());
					Assert.fail("Deliberate failure to capture screenshot");

				} catch (AssertionError e) {
					// Capture the exception message
					test.log(Status.FAIL, e.getMessage());
					Assert.fail("Deliberate failure to capture screenshot");
				}
				 catch (Exception e) {
						// Capture the exception message
						test.log(Status.FAIL, e.getMessage());
						//Assert.fail("Deliberate failure to capture screenshot");
					}
			}
		}
	}
}