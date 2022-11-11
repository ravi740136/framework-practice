package org.raqa.seleniumtestngtest.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.raqa.seleniumtestngtest.base.TestBase;
import org.raqa.seleniumtestngtest.lbase.Driverfactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;


public class CustomListener extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("test " + result.getTestName() + " started ");
		
	}

	public void onTestSuccess(ITestResult result) {
		String filename = new Date().toString().replace(":", "-").replace(" ","-")+".jpg";
		File f = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f, new File(System.getProperty("user.dir")+"/target/surefire-reports/"+filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("test successful <br>");
		Reporter.log("<a href='"+filename+"' target='_blank'>success_screenshot</a>");
	    System.out.println("test success");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//Reporter.setEscapeHtml(false);
		//Reporter.setCurrentTestResult(result);
		String filename = new Date().toString().replace(":", "-").replace(" ","-")+".jpg";
		File f = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f, new File(System.getProperty("user.dir")+"/target/surefire-reports/"+filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("test failed <br>");
		Reporter.log("<a href='"+filename+"' target='_blank'>failed_screenshot</a>");
	    System.out.println("test failed");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
