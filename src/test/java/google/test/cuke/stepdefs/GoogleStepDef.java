package google.test.cuke.stepdefs;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GoogleStepDef {

	private static RemoteWebDriver driver;
	private static WebElement targ;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
		driver.manage().window().setSize(new Dimension(1366, 768));

	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Given("^That I can access google$")
	public void that_I_can_access_google() throws Throwable {
		driver.get("https://google.com");
	}

	@When("^I search for kittens$")
	public void i_search_for_kittens() throws Throwable {
		targ = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input"));

		// enter search term
		targ.sendKeys("Kittens");
		targ.submit();
	}

	@When("^I select the images tab$")
	public void i_select_the_images_tab() throws Throwable {
		targ = driver.findElement(By.xpath("//*[@id=\"iur\"]/div[2]/div/div/div[10]/a/g-img/div"));
		targ.click();
	}

	@Then("^I should be able to view images of kittens$")
	public void i_should_be_able_to_view_images_of_kittens() throws Throwable {
		String result = driver.getTitle();
		assertEquals("Kittens - Google Search", result);
	}

	public static ChromeOptions chromeCfg() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);

		return cOptions;
	}

}
