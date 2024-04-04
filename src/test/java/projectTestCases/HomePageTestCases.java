package projectTestCases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;

import pageObjectModel.HomePage;

import utilities.ReportGenerator;

public class HomePageTestCases extends ReportGenerator {
	HomePage home;
	WebDriver driver;
	TakesScreenshot ts;

	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser) throws InterruptedException {

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://urbanladder.com/");

		driver.manage().window().maximize();
		Thread.sleep(2000);

	}

	@Test(priority = 1, groups = { "SmokeTest" })
	public void checkHomePageUrlTest() {
		test = extent.startTest("test10");
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.urbanladder.com/");
	}

	@Test(priority = 2, groups = { "SmokeTest" })
	public void checkHomePageTitleTest() {
		test = extent.startTest("test11");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "Buy Furniture Online and Get up to 50% Off | Shop Now - Urban Ladder");
	}

	@Test(priority = 3)
	public void ExtractDealTest() throws Exception {

		test = extent.startTest("test12");
		home = new HomePage(driver);
		List<String> L2 = home.extractDealsZone();

		List<String> L3 = new ArrayList<String>();
		L3.add("Blockbuster Deals");
		L3.add("Clearance Sale");
		L3.add("All Products on Sale");
		L3.add("All Decor on Sale");
		L3.add("UL Assured Picks");
		Boolean flag = true;
		Thread.sleep(2000);
		if (!L2.containsAll(L3)) {
			flag = false;
		}
		Assert.assertTrue(flag);
	}
	
	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		System.out.println("\nRunning test: " + testName);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.isSuccess()) {
			System.out.println("Result: PASSED");
		}else {
			System.out.println("Result: FAILED");			
		}
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
