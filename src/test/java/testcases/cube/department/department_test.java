package testcases.cube.department;

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


import property.PropertyFile;
import utility.BaseFile;

public class  department_test extends BaseFile {

	private static final String TESTCASES_SHEET = "DEPARTMENT";
	private static final String SHEET_NAME = "DEPARTMENT_SHEET";
	private ExtentReports extent;
	private WebDriver driver;
	private ExtentTest test;
	public department depPages;
	private int rownum;
	private Map<String, Object>[][] empdata;
	public WebDriverWait wait;
	

	PropertyFile propReader = new PropertyFile();
	BrowserFactory browserFactory = new BrowserFactory();

	// This method is calling driver and extent report.
	@BeforeMethod
	private void navigateToBaseURL() throws Exception {
		driver = BaseFile.driver;
		depPages = new department(driver, 5); 
		extent = extentBase;
		ScreenshotListener.setDriver(driver);
	}

	// This method is used to read from excel.

	@DataProvider(name = "departmentPageDataProvider")
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

	@Test(dataProvider = "departmentPageDataProvider")
	private void cubeDepartment(Map<String, Object> fetchData) throws InterruptedException {

		if (fetchData.entrySet() != null) {
			for (Map.Entry<String, Object> entry : fetchData.entrySet()) {

			    String testCase= null;
				String testId = null;
				String depName = null;
				String depType = null;
			    String subname = null;
			    String remarks = null;
			    String enterSubname = null ;

				try {
					JSONObject testData = (JSONObject) entry.getValue();
					testCase = new String((String) testData.get("TEST_CASES")).toString();
					depType = new String((String) testData.get("Department Type")).toString();
					depName = new String((String) testData.get("Department Name")).toString();
					subname = new String((String) testData.get("Sub Department Name")).toString();
					enterSubname = depPages.generateRandomAlpha(); //random values
					testId = new String ((String) testData.get("TEST_ID"));
					remarks = new String((String) testData.get("Remarks")).toString();
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
					test = extent.createTest(testCase);

				try {
					driver.get("https://cubeuat.capriglobal.in:8012/Admin/Default.aspx");
				    depPages.clickelement(depPages.online_re);
					Actions actions = new Actions(driver);
					actions.scrollByAmount(1, 10000).perform(); // Scroll down by 500 pixels
					actions.moveToElement(depPages.Department_Control).perform();
					//Thread.sleep(2000); //hover 
					depPages.clickelement(depPages.Initiate);
					
						if(testId.equals("1")) {
						depPages.clickelement(depPages.select_drop);
						driver.findElement(By.xpath("//*[text()='" + depType + "']")).click();
						driver.findElement(By.xpath("//*[text()='" +  depName + "']")).click();
						//Thread.sleep(2000);
						depPages.click(depPages.sub_dep);
						depPages.EnterText(enterSubname,depPages.enter_sub);
						depPages.EnterText(remarks,depPages.remarkss);
						WebElement fileUploadField = driver.findElement(By.xpath("//input[@id='MainContent_col_2_file_upload']")); // Replace with the actual locator
						String filePath = "/home/chahetibhandari/ecliplesqa/CUBEautomation/image/testPIC.png"; // Replace with the actual file path
						depPages.filebtn.sendKeys(filePath);
						depPages.click(depPages.submitbtn);
						depPages.waitForAlertAndAccept();
					

				}
					else if(testId.equals("2")) {
						
						depPages.clickelement(depPages.select_drop);
						driver.findElement(By.xpath("//*[text()='" + depType + "']")).click();
						driver.findElement(By.xpath("//*[text()='" +  depName + "']")).click();
						depPages.click(depPages.sub_dep);
						depPages.EnterText(enterSubname,depPages.enter_sub);
						
						WebElement fileUploadField = driver.findElement(By.xpath("//input[@id='MainContent_col_2_file_upload']")); // Replace with the actual locator
						String filePath = "/home/chahetibhandari/ecliplesqa/CUBEautomation/image/testPIC.png"; // Replace with the actual file path
						depPages.filebtn.sendKeys(filePath);
						depPages.click(depPages.submitbtn);
						depPages.waitForAlertAndAccept();
					}
					else if(testId.equals("3")) {
						depPages.clickelement(depPages.select_drop);
						driver.findElement(By.xpath("//*[text()='" + depType + "']")).click();
						driver.findElement(By.xpath("//*[text()='" +  depName + "']")).click();
						depPages.click(depPages.sub_dep);
						depPages.EnterText(enterSubname,depPages.enter_sub);
						WebElement fileUploadField = driver.findElement(By.xpath("//input[@id='MainContent_col_2_file_upload']")); // Replace with the actual locator
						String filePath = propReader.getProp().get("Image").toString().trim(); // Replace with the actual file path
						depPages.filebtn.sendKeys(filePath);
						depPages.click(depPages.submitbtn);
						depPages.waitForAlertAndAccept();
					
				}
//					else if(testId.equals("4")) {
//						depPages.clickelement(depPages.select_drop);
//						driver.findElement(By.xpath("//*[text()='" + depType + "']")).click();
//						driver.findElement(By.xpath("//*[text()='" +  depName + "']")).click();
//						depPages.click(depPages.sub_dep);
//						depPages.EnterText(enterSubname,depPages.enter_sub);
//						depPages.EnterText(remarks,depPages.remarkss);
//						depPages.click(depPages.submitbtn);
//						depPages.waitForAlertAndAccept();
//					
//				}
					else if(testId.equals("5")) {
						depPages.clickelement(depPages.select_drop);
						driver.findElement(By.xpath("//*[text()='" + depType + "']")).click();
						driver.findElement(By.xpath("//*[text()='" +  depName + "']")).click();
						//Thread.sleep(2000);
						depPages.click(depPages.sub_dep);
						depPages.EnterText(enterSubname,depPages.enter_sub);

						depPages.click(depPages.submitbtn);
						depPages.waitForAlertAndAccept();
				}else if(testId.equals("6")) {
					depPages.clickelement(depPages.select_drop);
					driver.findElement(By.xpath("//*[text()='" + depType + "']")).click();
					driver.findElement(By.xpath("//*[text()='" +  depName + "']")).click();
					//Thread.sleep(2000);
					depPages.click(depPages.sub_dep);
					depPages.EnterText(enterSubname,depPages.enter_sub);

					depPages.click(depPages.submitbtn);
					depPages.waitForAlertAndAccept();
			}else if(testId.equals("7")) {
				depPages.clickelement(depPages.select_drop);
				driver.findElement(By.xpath("//*[text()='" + depType + "']")).click();
				driver.findElement(By.xpath("//*[text()='" +  depName + "']")).click();
				//Thread.sleep(2000);
				depPages.click(depPages.sub_dep);
				depPages.EnterText(enterSubname,depPages.enter_sub);

				depPages.click(depPages.submitbtn);
				depPages.waitForAlertAndAccept();
		}else if(testId.equals("8")) {
			depPages.clickelement(depPages.select_drop);
			driver.findElement(By.xpath("//*[text()='" + depType + "']")).click();
			driver.findElement(By.xpath("//*[text()='" +  depName + "']")).click();
			
			depPages.click(depPages.sub_dep);
			depPages.EnterText(enterSubname,depPages.enter_sub);
			depPages.click(depPages.submitbtn);
			depPages.waitForAlertAndAccept();

		}else if(testId.equals("9")) {
			depPages.clickelement(depPages.select_drop);
			driver.findElement(By.xpath("//*[text()='" + depType + "']")).click();
			driver.findElement(By.xpath("//*[text()='" +  depName + "']")).click();
			//Thread.sleep(2000);
			depPages.click(depPages.sub_dep);
			depPages.EnterText(enterSubname,depPages.enter_sub);
			depPages.click(depPages.submitbtn);
			depPages.waitForAlertAndAccept();
		}
		else if(testId.equals("10")) {
			depPages.clickelement(depPages.select_drop);
			driver.findElement(By.xpath("//*[text()='" + depType + "']")).click();
			driver.findElement(By.xpath("//*[text()='" +  depName + "']")).click();
			//Thread.sleep(2000);
			depPages.click(depPages.sub_dep);
			depPages.EnterText(enterSubname,depPages.enter_sub);
			depPages.click(depPages.submitbtn);
			depPages.waitForAlertAndAccept();
								
		    depPages.click(depPages.logoutint);
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