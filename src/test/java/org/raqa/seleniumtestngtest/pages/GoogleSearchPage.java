package org.raqa.seleniumtestngtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.raqa.seleniumtestngtest.base.TestBase;
import org.testng.Assert;

public class GoogleSearchPage extends TestBase{
	
	By txt_search_l = By.name("q");
	
	public void enterSearchText(String text) {
	    clearAndEnterText(txt_search_l, text);
	    clearAndEnterText(txt_search_l, text+"1");
	}

	public void validateSearchBox() {
		// TODO Auto-generated method stub
		WebElement searchTxt = findElement(txt_search_l);
		System.out.println("id: "+searchTxt.getAttribute("id")+"_"+searchTxt.getAttribute("name"));
	}

	public void validateSearchValue(String value) {
		// TODO Auto-generated method stub
		WebElement searchTxt = findElement(txt_search_l);
		Assert.assertEquals(searchTxt.getAttribute("value").trim(), value, "Searching for wrong value");
	}

	

}
