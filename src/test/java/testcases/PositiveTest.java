package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HomePage;

public class PositiveTest extends BaseTest {

	HomePage homePage;
	
	@BeforeClass
	public void createHomePageObject() {
		homePage = new HomePage(driver);
	}
	
	@Test (priority = 0, description = "User should able to create items to-do list")
	public void createToDoList() {
		String data = "Welcome";
		homePage.addItem(data);
		Assert.assertTrue(homePage.verifyTextInList(data), data  + " is not visible");
		
		data = "Hello";
		homePage.addItem(data);
		Assert.assertTrue(homePage.verifyTextInList(data), data  + " is not visible");
		
		data = "World";
		homePage.addItem(data);
		Assert.assertTrue(homePage.verifyTextInList(data), data  + " is not visible");
		
	}
	
	@Test(dependsOnMethods = "createToDoList", priority = 1, description = "User should able to delete item from the list")
	public void deleteItemFromList() {
		String data = "World";
		homePage.deleteItem(data);
		Assert.assertFalse(homePage.verifyTextInList(data), data  + " is still visible");
	}
	
	@Test(dependsOnMethods = "createToDoList", priority = 2, description = "Item count should increase/decrease as per User actions")
	public void itemCountIncreaseDecrease() {
		BaseTest.driver.navigate().refresh();
		homePage = new HomePage(driver);
		String data = "Add";
		
		int beforeCount = homePage.getItemCount();
		
		homePage.addItem(data);
		Assert.assertTrue(homePage.verifyTextInList(data), data  + " is not visible");
		int afterAddCount = homePage.getItemCount();
		Assert.assertEquals(afterAddCount, beforeCount + 1, "Count has not increased");
		 
		homePage.deleteItem(data);
		Assert.assertFalse(homePage.verifyTextInList(data), data  + " is still visible");
		int afterDeleteCount = homePage.getItemCount();
		Assert.assertEquals(afterDeleteCount, afterAddCount - 1, "Count has not decreased");
	}
	
	@Test (priority = 3, description = "New added item in the list should be shown in the bottom")
	public void newItemIsLast() {
		BaseTest.driver.navigate().refresh();
		homePage = new HomePage(driver);
		String data = "Last Item";
		
		homePage.addItem(data);
		Assert.assertEquals(homePage.getLastItemName(), data, "Last Item doesn't match");
	}
	
	@Test (priority = 4, description = "Done item should be marked completed")
	public void strikeOutItem() {
		String data = "Strike out this";
		
		homePage.addItem(data);
		Assert.assertEquals(homePage.markItemCompleted(data), "completed", "Item is not marked completed");
		
	}
}
