package org.raqa.seleniumtestngtest.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.raqa.seleniumtestngtest.lbase.Driverfactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {

	public static Properties repository = new Properties();
	//public static Properties config = new Properties();
	//private WebDriver driver = null;
	//private static ThreadLocal<WebDriver> driverpool = new ThreadLocal<WebDriver>();

	@BeforeSuite
	public void setup() throws FileNotFoundException, IOException {
	Driverfactory.initConfig();
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

	protected WebDriver getDriver() {
		return Driverfactory.getDriver();
	}

	protected void setDriver(WebDriver driver) {
		Driverfactory.setDriver(driver);
	}

	@BeforeMethod
	@Parameters("browser")
	public void initDriver(@Optional("chrome") String browser) throws URISyntaxException, MalformedURLException {
		// String browser = config.getProperty("browser");
		System.out.println("browser is " + browser);
		Driverfactory.initDriver(browser);
	}

	@AfterMethod
	public void cleanup() {
		System.out.println("cleanup");
		if (getDriver() != null) {
			getDriver().quit();
			setDriver(null);
		}

	}

}
