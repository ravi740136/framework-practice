package org.raqa.seleniumtestngtest.lbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driverfactory {
private static WebDriver driver;
public static WebDriver getDriver() {
	if (driver == null) {
		
		System.setProperty("webdriver.chrome.driver", 
				System.getProperty("user.dir")+"/drivers/chromedriver");
		driver = new ChromeDriver();
	}
	return driver;
}

public static void setDriver(WebDriver drivero) {
	driver=drivero;
}

}
