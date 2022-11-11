package org.raqa.seleniumtestngtest.listeners;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.raqa.seleniumtestngtest.TestClass;
import org.testng.IAlterSuiteListener;
import org.testng.annotations.Listeners;
import org.testng.collections.Maps;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

@Listeners
public class MyAlterSuiteListener implements IAlterSuiteListener {

		public void alter(List<XmlSuite> suites) {
			// TODO Auto-generated method stub
			 XmlSuite suite = suites.get(0);
			 
	            //Check if there was a parameter named "browserFlavors" defined at the suite
	            String browserFlavors = suite.getParameter("browserFlavors");
	            if (browserFlavors == null || browserFlavors.trim().isEmpty()) {
	                //If no such parameter was found, then Try querying the JVM arguments to see if it contains
	                //value for it. Just to ensure we don't end up in a situation wherein there's no JVM also provided
	                //Lets add a default value for the JVM argument which in our case is "firefox"
	                browserFlavors = System.getProperty("browserFlavors", "chrome");
	            }
	            XmlSuite suitenew = new XmlSuite();
	           suitenew.setParallel(ParallelMode.METHODS);
	           suitenew.setThreadCount(6);
	            String[] browsers = browserFlavors.split(",");
	            System.out.println("browsers:"+Arrays.toString(browsers));
	          //  List<XmlTest> xmlTests = new ArrayList<>();
	            for (String browser : browsers) {
	                XmlTest xmlTest = new XmlTest(suitenew);
	                xmlTest.setName(browser + "_test");
	                
	                Map<String, String> parameters = Maps.newHashMap();
	                parameters.put("browser", browser);
	                xmlTest.setParameters(parameters);
	               
	                
	                XmlClass xmlClass = new XmlClass();
	                xmlClass.setName(TestClass.class.getCanonicalName());
	                
	                xmlTest.getClasses().add(xmlClass);
	                System.out.println("before:"+suitenew.getTests());
	                //suitenew.getTests().add(xmlTest);
	                System.out.println("after:"+suitenew.getTests());
	                //xmlTests.add(xmlTest);
	            }
	            //suitenew.setTests(xmlTests);
	            suitenew.setName("multibrowser suite");
	            suitenew.addListener(CustomListener.class.getCanonicalName());
	            //suitenew.setListeners(Arrays.asList("org.raqa.seleniumtestngtest.listeners.CustomListener"));
	            System.out.println();
	            suites.clear();
	            suites.add(suitenew);
		}
    }
