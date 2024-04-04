package projectTestCases;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;

import pageObjectModel.GiftCardPage;
import pageObjectModel.HomePage;
import utilities.ApachePOI;
import utilities.ReportGenerator;

public class GiftCardPageTestCases extends ReportGenerator {
	WebDriver driver;
	HomePage home;
	GiftCardPage giftCardPage;
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
		test = extent.startTest("test13");
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.urbanladder.com/");
	}

	@Test(priority = 2)
	public void checkClickGiftCardTest() throws IOException, InterruptedException {

		test = extent.startTest("test14");

		Boolean clickBook = driver.findElement(By.xpath("//a[@href='../../gift-cards?src=header']")).isDisplayed();
		Assert.assertTrue(clickBook);
		home = new HomePage(driver);
		home.clickGiftCard();

	}

	@Test(priority = 3, groups = { "SmokeTest" })
	public void checkGiftCardUrlTest() {
		test = extent.startTest("test15");
		String url = driver.getCurrentUrl();
//		System.out.println(url);
		Assert.assertEquals(url, "https://www.urbanladder.com/gift-cards?src=header");
	}

	@Test(priority = 5, groups = { "SmokeTest" })
	public void checkGiftCardTitleTest() {
		test = extent.startTest("test16");
		String url = driver.getTitle();
		Assert.assertEquals(url, "Gift Card - Buy Gift Cards & Vouchers Online for Wedding, Birthday | Urban Ladder");
	}

	@Test(priority = 6)
	public void OccasionHandleTest() throws Exception {
		test = extent.startTest("test17");
		giftCardPage = new GiftCardPage(driver);
		giftCardPage.scrollHandleToHeading1();
		giftCardPage.birthdayCardHover();
		Boolean occasion = driver.findElement(By.xpath("//*[@class=\"_11b4v\"][3]")).isDisplayed();
		Assert.assertTrue(occasion);
		giftCardPage.clickOccasionHandle();
	}

	@Test(priority = 7)
	public void AmountHandlerTest() {
		test = extent.startTest("test18");

		giftCardPage = new GiftCardPage(driver);
		Boolean amount = driver.findElement(By.xpath("//*[@class=\"HuPJS\"][1]")).isDisplayed();
		Assert.assertTrue(amount);
		giftCardPage = new GiftCardPage(driver);
		giftCardPage.AmountHandler();
	}

	@Test(priority = 8)
	public void DropDownHandlerTest() {
		test = extent.startTest("test19");

		giftCardPage = new GiftCardPage(driver);
		giftCardPage.DropDownHandler(2, 2);
		List<Boolean> L1 = new ArrayList<Boolean>();
		for (int i = 1; i < 3; i++) {
			Boolean select = driver.findElement(By.xpath("//*[@class=\"Upz18 _1hLiD UJU2v\"][" + i + "]"))
					.isDisplayed();
			L1.add(select);
		}
		Boolean flag = true;
		if (L1.contains(false)) {
			flag = false;
		}
		Assert.assertTrue(flag);
	}

	@Test(priority = 9)
	public void ButtonHandleTest() throws IOException {
		test = extent.startTest("test20");
		Boolean select = driver.findElement(By.xpath("//*[contains(text(),\"Next\")][@type=\"button\"]")).isEnabled();
		Assert.assertTrue(select);
		giftCardPage = new GiftCardPage(driver);
		giftCardPage.ButtonHandle();

	}

	@Test(priority = 10, dataProvider = "enterFormDetailsTest")
	public void enterFormDetailsTest(String rname, String remail, String rmobile, String yname, String yemail,
			String ymobile, String yaddress, String pincode) throws Exception {
		test = extent.startTest("test21");
		giftCardPage = new GiftCardPage(driver);
		double d1 = Double.parseDouble(rmobile);
		Long n1 = (long) d1;
		String sm = n1.toString();
		double d3 = Double.parseDouble(ymobile);
		Long n3 = (long) d3;
		String sym = n3.toString();
		double d2 = Double.parseDouble(pincode);
		Integer n2 = (int) d2;
		String sp = n2.toString();
		giftCardPage.enterFormDetails(rname, remail, sm, yname, yemail, sym, yaddress, sp);
		Thread.sleep(5000);
		giftCardPage.clickConfirmButtonHandler();
		WebElement errorElement = driver.switchTo().activeElement();
		String actualMsg = errorElement.getAttribute("validationMessage");
		Thread.sleep(5000);

		Boolean flag = true;
		if (actualMsg.isEmpty()) {
			flag = false;
		}
		Assert.assertTrue(flag);
		System.out.println();
		System.out.println("Actual Error Message is: "+actualMsg);
		System.out.println();

	}

	@DataProvider(name = "enterFormDetailsTest")
	public Object[][] dataProvider() throws Exception {
		ApachePOI ap = new ApachePOI();
		Object[][] testData = ap.getData(
				"C:\\Users\\2319953\\eclipse-workspace\\DisplayBookselvesProject\\src\\test\\resources\\ExcelSheets\\",
				"TestData1", "Sheet2");
		return testData;
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
