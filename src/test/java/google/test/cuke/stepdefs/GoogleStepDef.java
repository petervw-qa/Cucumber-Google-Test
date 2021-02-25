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

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

	@Given("That I can access {string}")
	public void that_i_can_access(String string) {
		driver.get("https://google.com");
	}

	@When("I search for {string}")
	public void i_search_for(String string) throws Throwable {
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

	@Then("I should be able to view images of {string}")
	public void i_should_be_able_to_view_images_of(String string) throws Throwable {
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
