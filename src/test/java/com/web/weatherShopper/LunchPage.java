package com.web.weatherShopper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LunchPage {

	ChromeOptions options;
	WebDriver driver;
	Properties props;
	FileInputStream objfile;

	@BeforeTest
	public void lunch() throws IOException {
		WebDriverManager.chromedriver().setup();
		options = new ChromeOptions();
		// To stop unwanted pop-up of chrome browser
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		props = new Properties();

		// locators file
		objfile = new FileInputStream(System.getProperty("user.dir") + "//application.properties");
		props.load(objfile);
	}

	@AfterTest
	public void quit() {
		driver.quit();

	}

}