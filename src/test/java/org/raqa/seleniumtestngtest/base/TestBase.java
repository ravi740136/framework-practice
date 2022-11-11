package org.raqa.seleniumtestngtest.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {

	public static Properties repository = new Properties();
	public static Properties config = new Properties();
	private WebDriver driver = null;
	private static ThreadLocal<WebDriver> driverpool = new ThreadLocal<WebDriver>();

	@BeforeSuite
	public void setup() throws FileNotFoundException, IOException {
		// config.load(new FileInputStream(
		// System.getProperty("user.dir") +
		// "/src/test/resources/config/execution.properties"));

		config.load(getClass().getClassLoader().getResourceAsStream("config/execution.properties"));
	}

	protected WebElement findElement(By by) {
		return getDriver().findElement(by);
	}

	protected void clearAndEnterText(By by, String text) {
		// TODO Auto-generated method stub
		WebElement ele = findElement(by);
		ele.clear();
		ele.sendKeys(text);
	}

	public WebDriver getDriver() {
		return driverpool.get();
	}

	public void setDriver(WebDriver driver) {
		driverpool.set(driver);
	}

	@BeforeMethod
	@Parameters("browser")
	public void initDriver(@Optional String browser) throws URISyntaxException {
		// String browser = config.getProperty("browser");
		System.out.println("browser is " + browser);
		if (browser.contentEquals("chrome") || browser.contentEquals("ie") || browser.contentEquals("firefox")) {
			// System.setProperty("webdriver.chrome.driver",
			// System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
			File f = new File(getClass().getClassLoader().getResource("drivers/chromedriver").getFile());
			if (!f.canExecute()) {
				f.setExecutable(true);
			}
			System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
			driver = new ChromeDriver();
			setDriver(driver);
		} else {

		}
		getDriver().get(config.getProperty("siteurl"));
		prepareBrowser();

	}

	private void prepareBrowser() {
		System.out.println("maximize");
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		System.out.println("timeout");
		getDriver().manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("implicit.wait.time")),
				TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(config.getProperty("page.load.timeout")), TimeUnit.SECONDS);
	}

	@AfterMethod
	public void cleanup() {
		System.out.println("cleanup");
		if (getDriver() != null) {
			getDriver().quit();
			driverpool.set(null);
		}

	}

	@AfterSuite
	public void suiteCleanup() {
		if (getDriver() != null) {
			getDriver().quit();
			driverpool.remove();
		}
	}

	/*
	 * public WebDriver getDriver() { if (driver == null) { initDriver(); } return
	 * driver; }
	 * 
	 * public static void setDriver(WebDriver drivero) { driver=drivero; }
	 */
}
