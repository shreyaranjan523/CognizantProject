package utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
 
import org.testng.annotations.BeforeSuite;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
 
public class ReportGenerator {
	public static ExtentReports extent;
	public static ExtentTest test;
  @AfterMethod
  public void getResult(ITestResult result) 
  {
	  if(result.getStatus() == ITestResult.FAILURE)
	  {
		  test.log(LogStatus.FAIL, "Test is failed");
	  }
	  if(result.getStatus() == ITestResult.SUCCESS)
	  {
		  test.log(LogStatus.PASS, "Test is pass");
	  }
  }
  @BeforeSuite
  public void startReport() 
  {
	  String directoryPath = System.getProperty("user.dir");
	  String relativePath = "/test-results/test-results.html";
	  extent = new ExtentReports(directoryPath+relativePath, true);
  }
  @AfterSuite
  public void tearDown() 
  {
	  extent.flush();
  }
}
