package org.raqa.seleniumtestngtest.lbase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driverfactory {

	private static ThreadLocal<WebDriver> driverpool = new ThreadLocal<WebDriver>();
	public static Properties config = new Properties();

	public static void initConfig() throws FileNotFoundException, IOException {
		config.load(Driverfactory.class.getClassLoader().getResourceAsStream("config/execution.properties"));
	}

	public static void initDriver(String browser) throws MalformedURLException {
		WebDriver driver;
		File f = null;
		switch (browser) {

		case "chrome":
		case "ie":
			f = new File(Driverfactory.class.getClassLoader().getResource("drivers/chromedriver").getFile());
			if (!f.canExecute()) {
				f.setExecutable(true);
			}
			System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
			// System.setProperty("webdriver.chrome.driver",
			// System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.setCapability("browserName", "chrome");
			options.setCapability("browserVersion", "107");
			options.setCapability("platform", Platform.MAC);
			// Proxy proxy = new Proxy();
			// proxy.setHttpProxy("http://localhost:8080");
			// options.setProxy(proxy);
			//options.setBinary("");
			options.setHeadless(false);
			options.addArguments("start-maximized");
			// options.addArguments("window-size=1920, 1920");
			// options.addArguments("disable-infobars");
			System.out.println("options " + options.toJson());
			// DesiredCapabilities caps = DesiredCapabilities.chrome();
			// options.merge(caps);
			// options.addArguments("user-data-dir="+System.getProperty("user.dir")+"/target/chromeravi");
			options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
			Map<String, String> prefs = new HashMap<>();
			prefs.put("default.download_directory", System.getProperty("user.dir") + "/target/downloads");
			options.setExperimentalOption("prefs", prefs);
			System.out.println(options.toString());
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			f = new File(Driverfactory.class.getClassLoader().getResource("drivers/geckodriver").getFile());
			if (!f.canExecute()) {
				f.setExecutable(true);
			}
			System.setProperty("webdriver.gecko.driver", f.getAbsolutePath());
			FirefoxOptions fop = new FirefoxOptions();
			fop.setCapability("browserName", "firefox");
			fop.setCapability("platform", "MAC");
			fop.addArguments("--disable-infobars");
			driver = new FirefoxDriver(fop);
			break;
		case "remotechrome":
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability("browserName", "chrome");
			cap.setCapability("platform", "MAC");
			driver = new RemoteWebDriver(new URL("http://192.168.16.161:5557/wd/hub"), cap);
			break;
		case "remotefirefox":
			DesiredCapabilities op = DesiredCapabilities.firefox();
			op.setCapability("browserName", "firefox");
			op.setCapability("platform", "MAC");
			driver = new RemoteWebDriver(new URL("http://192.168.16.161:5555/wd/hub"), op);

			break;
		case "safari":
			f = new File(Driverfactory.class.getClassLoader().getResource("drivers/safaridriver").getFile());
			f.setExecutable(true);
			System.setProperty("webdriver.safari.driver", f.getAbsolutePath());
			driver = new SafariDriver();

			break;
		default:
			throw new IllegalStateException("unsupported browser " + browser);
		}
		setDriver(driver);
		prepareBrowser();
		getDriver().get(config.getProperty("siteurl"));
	}

	private static void prepareBrowser() {
		System.out.println("maximize");
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		System.out.println("timeout");
		getDriver().manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("implicit.wait.time")),
				TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(config.getProperty("page.load.timeout")),
				TimeUnit.SECONDS);
	}

	public static WebDriver getDriver() {
		return driverpool.get();
	}

	public static void setDriver(WebDriver driver) {
		driverpool.set(driver);
	}

}
