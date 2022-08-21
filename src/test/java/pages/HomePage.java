package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WebDriverWrapper;

public class HomePage {

	@FindBy(xpath = "//header[@class='header']//input")
	WebElement txtInput;
	
	private String listItemName = "//ul[@class='todo-list']//li[contains(.,'<value>')]";
	private String btnDelete = "//button";
	private String input = "//input";
	private String label = "//label";
	private String lblItemCount = "//span[@class='todo-count']//strong";
	private String listItems = "//ul[@class='todo-list']//li";
	private String itemNameByIndex = "//ul[@class='todo-list']//li[<index>]";

	private WebDriver driver;
	WebDriverWrapper wrapper;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
		System.out.println(this.driver.getCurrentUrl());
	}
	
	public void addItem(String text) {
		txtInput.sendKeys(text);
		txtInput.sendKeys(Keys.ENTER);
		wrapper.wait(1000);
	}
	
	public boolean verifyTextInList(String text) {
		String txtElement = listItemName.replace("<value>", text);
		try {
			return driver.findElement(By.xpath(txtElement)).isDisplayed();
		} catch (Exception ex) {
			return false;
		}
	}
	
	public void deleteItem(String text) {
		String txtElement = listItemName.replace("<value>", text);
		WebElement element =  driver.findElement(By.xpath(txtElement));
		element.click();
		driver.findElement(By.xpath(txtElement + btnDelete)).click();
	}
	
	public int getItemCount() {
		String text = driver.findElement(By.xpath(lblItemCount)).getText();
		return Integer.parseInt(text);
	}
	
	private int getNumberOfItems() {
		return driver.findElements(By.xpath(listItems)).size();
	}
	
	public String getLastItemName() {
		itemNameByIndex = itemNameByIndex.replace("<index>", getNumberOfItems() + "");
		return driver.findElement(By.xpath(itemNameByIndex + label)).getText();
	}
	
	public String markItemCompleted(String text) {
		String txtElement = listItemName.replace("<value>", text);
		driver.findElement(By.xpath(txtElement + input)).click();
		return driver.findElement(By.xpath(txtElement)).getAttribute("class");
	}
	
	public String getListItemClassAttribute(String text) {
		String txtElement = listItemName.replace("<value>", text);
		return driver.findElement(By.xpath(txtElement)).getAttribute("class");
	}
	
	public String editItemWithoutEnter(String value) {
		listItemName = listItemName.replace("<value>", value);
		WebElement element = driver.findElement(By.xpath(listItemName));
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
		action.click(element).build().perform();
		action.sendKeys("new value").build().perform();
		txtInput.click();
		driver.navigate().refresh();
		return getLastItemName();
	}
	
	public String editItem(String value, String newValue) {
		listItemName = listItemName.replace("<value>", value);
		WebElement element = driver.findElement(By.xpath(listItemName));
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
		action.click(element).build().perform();
		action.sendKeys(newValue).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
		return getLastItemName();
	}

}