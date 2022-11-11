package org.raqa.seleniumtestngtest.rough;

import java.io.IOException;
import java.util.Properties;

public class ResourcesTest {
public static void main(String[] args) throws IOException {
	Properties config = new Properties();
	config.load(ResourcesTest.class.getClassLoader().getResourceAsStream("config/execution.properties"));
    System.out.println(config.getProperty("browser"));
}
}