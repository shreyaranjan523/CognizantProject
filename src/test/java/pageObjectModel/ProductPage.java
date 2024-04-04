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

public class ProductPage extends BasePage {

	JavascriptExecutor jse = (JavascriptExecutor) driver;
	Actions actions = new Actions(driver);
	TakesScreenshot ts;
	Screenshots ss;

	WebElement priceElement, popup, slider, storage, storageType, excludeOutOfStock, productName, productPrice, subMenu,
			subMenuElements, scrollPageGiftcard, giftCardLink;
	int i, j;

	By popup1 = By.xpath("//*[@class='close-reveal-modal hide-mobile']");

	By priceElement1 = By.cssSelector("li[data-group = 'price'] div.dropdown-content");

	By slider1 = By.xpath(
			"//*[@id=\"filters-form\"]/div[1]/div/div/ul/li[1]/div[2]/div/div/ul/li[1]/div/div[2]/div[2]/div/div[2]/div");

	By storage1 = By.cssSelector("li[data-group='storage type'] div.dropdown-content");

	By storageType1 = By.id("filters_storage_type_Open");

	By excludeOutOfStock1 = By.id("filters_availability_In_Stock_Only");

	By productName1 = By.xpath("//div[@class='categories row']//li[" + i + "]//a[@class=\"product-title-block\"]");

	By productPrice1 = By
			.xpath("//div[@class='categories row']//li[" + i + "]//a[@class=\"product-title-block\"]//meta[1]");

	By subMenu1 = By.xpath("//*[@class=\"topnav_item dealzoneunit\"]");

	By subMenuElements1 = By.xpath("//*[@class=\"topnav_item dealzoneunit\"]//li[" + j + "]//span[1]");

	By scrollPageGiftcard1 = By.xpath("//*[@data-type=\"faq\"]");

	By giftCardLink1 = By.xpath("//a[@href='/gift-cards?src=g_footer']");

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	public void Popup() throws InterruptedException {
		Thread.sleep(5000);
		popup = driver.findElement(popup1);
		popup.click();

	}

	public void PriceSlider() throws InterruptedException {

		priceElement = driver.findElement(priceElement1);
		jse.executeScript("arguments[0].style.display = 'block'", priceElement);
		Thread.sleep(3000);

		slider = driver.findElement(slider1);
		actions.dragAndDropBy(slider, -274, 0).perform();
		jse.executeScript("arguments[0].style.display = 'none'", priceElement);
		Thread.sleep(7000);

	}

	public void StorageType() {

		storage = driver.findElement(storage1);
		jse.executeScript("arguments[0].style.display = 'block'", storage);
		storageType = driver.findElement(storageType1);
		actions.click(storageType).perform();
		jse.executeScript("arguments[0].style.display = 'none'", storage);

	}

	public void ExcludeOutOfStock() {

		excludeOutOfStock = driver.findElement(excludeOutOfStock1);
		excludeOutOfStock.click();

	}

	public List<String> listOfProducts() throws InterruptedException, IOException {
		List<String> L1 = new ArrayList<String>();
		
		System.out.println();
		for (int i = 1; i < 4; i++) {

			String productName = driver
					.findElement(By
							.xpath("//div[@class='categories row']//li[" + i + "]//a[@class=\"product-title-block\"]"))
					.getAttribute("title");
			String productPrice = driver.findElement(By
					.xpath("//div[@class='categories row']//li[" + i + "]//a[@class=\"product-title-block\"]//meta[1]"))
					.getAttribute("content");
			L1.add(i+"."+productName + " - Rs." + productPrice);
			productName = "";
			productPrice = "";
		}
		for (String x : L1) {
			System.out.println(x);
		}
		System.out.println();
		Thread.sleep(2000);

		ss = new Screenshots();
		ss.takeScreenshots(driver, "afterFilterScreenshot");

		return L1;

	}

}
