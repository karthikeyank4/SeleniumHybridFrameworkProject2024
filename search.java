package com.seleniumproject.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.seleniumproject.qa.base.Base;

public class Search extends Base {

	public Search() {
		super();
	}

	WebDriver driver;

	@AfterMethod
	public void teadDown() {

		System.out.println("After Method- Close Browser");

		driver.quit();

	}

	@BeforeMethod
	public void setUp() {
		loadPropertiesFile();
		driver = initializeBrowserAndOpenURL(prop.getProperty("browserName"));
		System.out.println("Before Method- Open URL and navigate");

	}

	@Test
	public void verifySearchWithValidProduct() {

		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("HP");
		driver.findElement(By.xpath("//button[contains(@class,'btn btn-default btn-lg')]")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());

	}

}
