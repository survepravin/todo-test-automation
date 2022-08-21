package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utils.BrowserFactory;
import utils.EnvironmentVariables;

public class BaseTest {

	public static WebDriver driver;
	
	@BeforeSuite()
	public void beforeWeb() {
		
		String toggle = System.getProperty("headless");
		String browserState = toggle != null ? toggle : EnvironmentVariables.HEADLESS;
		
		driver = BrowserFactory.getDriver(EnvironmentVariables.BROWSER, browserState);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(EnvironmentVariables.BASE_URL);
		
	}

	@AfterSuite()
	public void afterWeb() {
		if (driver != null) driver.quit();
	}
}
