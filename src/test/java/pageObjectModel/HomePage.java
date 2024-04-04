package pageObjectModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utilities.Screenshots;

public class HomePage extends BasePage {
	TakesScreenshot ts;
	Screenshots ss;

	// By element = By.xpath("//*[@id=\"home\"]/div[1]/div[4]/div[3]/div[1]");
	By bookShelf = By.xpath("//a[@href='/bookshelf?src=explore_categories']");
	By giftCard = By.xpath("//*[@id=\"header\"]/section/div/ul[2]/li[3]/a");
	By dealzone = By.xpath("//li[@class='topnav_item dealzoneunit']");
	int j;
	By dealZoneData = By.xpath("//li[@class='topnav_item dealzoneunit']//li[1]//li[" + j + "]//span");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickBookshelf() throws IOException {

		WebElement scroll = driver.findElement(By.xpath("//*[@class=\"with-stroke module-heading\"]"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", scroll);

		WebElement clickBookShelfs = driver.findElement(bookShelf);

		clickBookShelfs.click();

		ss = new Screenshots();
		ss.takeScreenshots(driver, "clickBookselfScreenshot");
	}

	public void clickGiftCard() throws IOException, InterruptedException {
		WebElement clickGiftCard = driver.findElement(giftCard);
		clickGiftCard.click();
		Thread.sleep(5000);
		ss = new Screenshots();
		ss.takeScreenshots(driver, "clickGiftCardScreenshot");
	}

	public List<String> extractDealsZone() throws Exception {
		WebElement submenu = driver.findElement(dealzone);
		Actions moveCurser = new Actions(driver);

		moveCurser.moveToElement(submenu).perform();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.display='block'", submenu);
		Thread.sleep(6000);

		List<String> L2 = new ArrayList<String>();
		
		for (j = 1; j < 6; j++)
		{
			String x = driver.findElement(By.xpath("//li[@class='topnav_item dealzoneunit']//li[1]//li[" + j + "]//span")).getText();
			L2.add(x);
			x = "";
		}
		System.out.println();
		for (String y : L2)
		{
			System.out.println(y);
		}
		System.out.println();
		
		jse.executeScript("arguments[0].style.display = 'block'", submenu);

		ss = new Screenshots();
		ss.takeScreenshots(driver, "extractDealZoneScreenshot");

		return L2;

	}

}
