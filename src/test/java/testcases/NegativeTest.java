package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.HomePage;

public class NegativeTest extends BaseTest {
	
	HomePage homePage;
	
	@BeforeClass
	public void createHomePageObject() {
		homePage = new HomePage(driver);
	}
	
	@Test (priority = -1, description = "Item should be saved only if enter is pressed in editing mode")
	public void itemNotSavedWithoutEnter() {
		String data = "Enter";
		homePage.addItem(data);
		String actualValue = homePage.editItemWithoutEnter(data);
		Assert.assertEquals(actualValue, data, "value doesn't match");
	}
	
	@Test (priority = -1, description = "Space character should be saved as item in the list")
	public void blankValueNotAllowed() {
		String data = " ";
		homePage.addItem(data);
		Assert.assertFalse(homePage.verifyTextInList(data), "Space is saved in the item list");
	}
	
	@Test (description = "Item list should handle long paragraphs")
	public void handleLongParagraphs() {
		String data = "A Hare was making fun of the Tortoise one day for being so slow."
				+ "Do you ever get anywhere? he asked with a mocking laugh."
				+ "Yes, replied the Tortoise, and I get there sooner than you think. I'll run you a race and prove it."
				+ "The Hare was much amused at the idea of running a race with the Tortoise, but for the fun of the thing he agreed. So the Fox, who had consented to act as judge, marked the distance and started the runners off."
				+ "The Hare was soon far out of sight, and to make the Tortoise feel very deeply how ridiculous it was for him to try a race with a Hare, he lay down beside the course to take a nap until the Tortoise should catch up."
				+ "The Tortoise meanwhile kept going slowly but steadily, and, after a time, passed the place where the Hare was sleeping. But the Hare slept on very peacefully; and when at last he did wake up, the Tortoise was near the goal. The Hare now ran his swiftest, but he could not overtake the Tortoise in time."
				+ "The race is not always to the swift. this is long para";
		homePage.addItem(data);
		Assert.assertTrue(homePage.verifyTextInList("long para"), data  + " is not visible");
	}
	
	@Test (description = "Editing marked item should not change completed status")
	public void editingMarkedItemNegative() {
		String data = "Mark completed";
		homePage.addItem(data);
		Assert.assertEquals(homePage.markItemCompleted(data), "completed", "Item is not marked completed");
		homePage.editItem(data, "new value");
		Assert.assertEquals(homePage.getListItemClassAttribute(data), "completed", "Item is not marked completed");
	}
}
