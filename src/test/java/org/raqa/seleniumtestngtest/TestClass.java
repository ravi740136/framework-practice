package org.raqa.seleniumtestngtest;

import java.io.IOException;

import org.raqa.seleniumtestngtest.base.TestBase;
import org.raqa.seleniumtestngtest.pages.GoogleSearchPage;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestClass extends TestBase {

	@Test
	public void test1() throws IOException, InterruptedException {
		// System.out.println("getting webdriver..");
		System.out.println(getDriver().getTitle());
		// Assert.fail("assertion failed");
		Reporter.log(Thread.currentThread().getName());

	}

	@Test
	public void test2() throws IOException, InterruptedException {
		// WebDriver driver = getDriver();
		Reporter.log(Thread.currentThread().getName());
		GoogleSearchPage searchP = new GoogleSearchPage();

		searchP.enterSearchText("Ravi");
		searchP.validateSearchBox();
		searchP.validateSearchValue("Ravi1");

	}

	/*@Test
	public void test3() throws IOException, InterruptedException {
		// System.out.println("getting webdriver..");
		System.out.println(getDriver().getTitle());
		// Assert.fail("assertion failed");
		Reporter.log(Thread.currentThread().getName());

	}

	@Test
	public void test4() throws IOException, InterruptedException {
		// WebDriver driver = getDriver();
		Reporter.log(Thread.currentThread().getName());
		GoogleSearchPage searchP = new GoogleSearchPage();

		searchP.enterSearchText("Ravi");
		searchP.validateSearchBox();
		searchP.validateSearchValue("Ravi1");

	}*/

}
