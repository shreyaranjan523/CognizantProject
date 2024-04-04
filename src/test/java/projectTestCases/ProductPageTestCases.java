package projectTestCases;

import org.testng.annotations.Test;

import pageObjectModel.HomePage;
import pageObjectModel.ProductPage;

import utilities.ReportGenerator;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class ProductPageTestCases extends ReportGenerator {
	WebDriver driver;
	HomePage page;
	ProductPage page2;
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
		test = extent.startTest("test1");
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.urbanladder.com/");
	}

	@Test(priority = 2)
	public void clickBookShelfTest() throws IOException, InterruptedException {
		test = extent.startTest("test2");

		Boolean clickBook = driver.findElement(By.xpath("//a[@href='/bookshelf?src=explore_categories']"))
				.isDisplayed();
		Assert.assertTrue(clickBook);
		page = new HomePage(driver);
		page.clickBookshelf();

	}

	@Test(priority = 4, groups = { "SmokeTest" })
	public void checkProductPageUrlTest() {
		test = extent.startTest("test3");
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.urbanladder.com/bookshelf?src=explore_categories");
	}

	@Test(priority = 5, groups = { "SmokeTest" })
	public void checkProductPageTitleTest() {
		test = extent.startTest("test4");
		String url = driver.getTitle();
		Assert.assertEquals(url, "Up to 70% off on Bookshelves at Color Crush Sale - Urban Ladder");
	}

	@Test(priority = 6)
	public void popupTest() throws InterruptedException {
		test = extent.startTest("test5");
		page2 = new ProductPage(driver);
		page2.Popup();
		Boolean popup = driver.findElement(By.xpath("//*[@class='close-reveal-modal hide-mobile']")).isDisplayed();
		Assert.assertTrue(popup);

	}

	@Test(priority = 7)
	public void PriceSliderTest() throws InterruptedException {
		test = extent.startTest("test6");
		page2 = new ProductPage(driver);
		page2.PriceSlider();
		List<String> L1 = new ArrayList<String>();
		for (int i = 1; i < 4; i++) {
			String price = driver
					.findElement(By.xpath(
							"//div[@class='categories row']//li[" + i + "]//a[@class='product-title-block']//meta[1]"))
					.getAttribute("content");
			L1.add(price);
			price = "";
		}
		boolean flag = true;
		for (String x : L1) {
			if (Integer.parseInt(x) > 15000) {
				flag = false;
			}
		}
		Assert.assertTrue(flag);

	}

	@Test(priority = 8)
	public void StorageTypeTest() {
		test = extent.startTest("test7");
		page2 = new ProductPage(driver);
		page2.StorageType();
		Boolean flag = driver.findElement(By.id("filters_storage_type_Open")).isSelected();
		Assert.assertTrue(flag);
	}

	@Test(priority = 9)
	public void ExcludeOutOfStockTest() {
		test = extent.startTest("test8");
		page2 = new ProductPage(driver);
		page2.ExcludeOutOfStock();
		Boolean outOfStock = driver.findElement(By.id("filters_availability_In_Stock_Only")).isSelected();
		Assert.assertTrue(outOfStock);
	}

	@Test(priority = 10)
	public void listOfProductsTest() throws InterruptedException, IOException {
		test = extent.startTest("test9");
		page2 = new ProductPage(driver);
		List<String> L2 = page2.listOfProducts();
		boolean flag = true;
		if (L2.isEmpty())
			flag = false;
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
