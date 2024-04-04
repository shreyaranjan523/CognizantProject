package pageObjectModel;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utilities.Screenshots;

public class GiftCardPage extends BasePage {

	TakesScreenshot ts;
	Screenshots ss;

	public GiftCardPage(WebDriver driver) {
		super(driver);
	}

	WebElement occasion, amount, ddownMon, ddownDate, button, recepientName, fromName, recepientAddress, fromEmail,
			recepientPhone, fromNumber, address, pincode, confirmButton;

	By occasion1 = By.xpath("//*[@class=\"_11b4v\"][3]");
	By amount1 = By.xpath("//*[@class=\"HuPJS\"][1]");
	By ddownMon1 = By.xpath("//*[@class=\"Upz18 _1hLiD UJU2v\"][1]");
	By ddownDate1 = By.xpath("//*[@class=\"Upz18 _1hLiD UJU2v\"][2]");
	By button1 = By.xpath("//*[contains(text(),\"Next\")][@type=\"button\"]");
	By recepientName1 = By.xpath("//*[@name=\"recipient_name\"]");
	By fromName1 = By.xpath("//*[@name=\"customer_name\"]");
	By recepientAddress1 = By.xpath("//*[@name=\"recipient_email\"]");
	By fromEmail1 = By.xpath("//*[@name=\"customer_email\"]");
	By recepientPhone1 = By.xpath("//*[@name=\"recipient_mobile_number\"]");
	By fromNumber1 = By.xpath("//*[@name=\"customer_mobile_number\"]");
	By address1 = By.xpath("//*[@name=\"customer_address\"]");
	By pincode1 = By.xpath("//*[@name=\"zip\"]");
	By heading1 = By.xpath("//*[@name=\"city\"]");
	By confirmButton1 = By.xpath("//*[contains(text(),Confirm)][@type=\"submit\"]");

	public void scrollHandleToHeading1() {
		WebElement elementHeading1 = driver.findElement(heading1);
		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scroll(0,550)", "");
		js.executeScript("arguments[0].scrollIntoView();", elementHeading1);
	}

	public void birthdayCardHover() {
		// Creating object of an Actions class
		Actions action = new Actions(driver);
		WebElement birthdayCard = driver.findElement(occasion1);

		// Performing the mouse hover action on the target element.
		action.moveToElement(birthdayCard).perform();
//		birthdayCard.click();
	}

	public void clickOccasionHandle() throws IOException {
		occasion = driver.findElement(occasion1);
		occasion.click();

		ss = new Screenshots();
		ss.takeScreenshots(driver, "occasionHandleScreenshot");
	}

	public void AmountHandler() {
		amount = driver.findElement(amount1);
		amount.click();
	}

	public void DropDownHandler(int month, int date) {
		ddownMon = driver.findElement(ddownMon1);
		Select selectM = new Select(ddownMon);
		selectM.selectByIndex(month);

		ddownDate = driver.findElement(ddownDate1);
		Select selectD = new Select(ddownDate);
		selectD.selectByIndex(date);
	}

	public void ButtonHandle() throws IOException {
		button = driver.findElement(button1);
		ss = new Screenshots();
		ss.takeScreenshots(driver, "form1Screenshot");
		button.click();
	}

	public void enterFormDetails(String r_name, String r_email, String r_phone, String f_name, String f_email,
			String f_phone, String f_address, String f_pincode) {
		recepientName = driver.findElement(recepientName1);
		recepientName.sendKeys(r_name);
		fromName = driver.findElement(fromName1);
		fromName.sendKeys(f_name);
		recepientAddress = driver.findElement(recepientAddress1);
		recepientAddress.sendKeys(r_email);
		fromEmail = driver.findElement(fromEmail1);
		fromEmail.sendKeys(f_email);
		recepientPhone = driver.findElement(recepientPhone1);
		recepientPhone.sendKeys(r_phone);
		fromNumber = driver.findElement(fromNumber1);
		fromNumber.sendKeys(f_phone);
		WebElement address = driver.findElement(address1);
		address.sendKeys(f_address);
		pincode = driver.findElement(pincode1);
		pincode.sendKeys(f_pincode);
	}

	public void clickConfirmButtonHandler() throws IOException {
		confirmButton = driver.findElement(confirmButton1);

		confirmButton.click();

		ss = new Screenshots();
		ss.takeScreenshots(driver, "form2Screenshot");
	}
}
