package com.seleniumproject.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.seleniumproject.qa.base.Base;
import com.seleniumproject.qa.tests.Utilities;

//import com.seleniumproject.qa.tests.Utilities;

public class Login extends Base {

	public Login() {

		super();
	}

	@AfterMethod
	public void teadDown() {

		System.out.println("After Method- Close Browser");

		driver.quit();

	}

	@BeforeMethod
	public void setUp() {

		loadPropertiesFile();
		driver = initializeBrowserAndOpenURL(prop.getProperty("browserName"));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		System.out.println("Before Method- Open URL and navigate");

	}

	WebDriver driver;

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() throws InterruptedException {

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		System.out.println("Test Method-1");

	}

	@Test(priority = 2)
	public void VerifyLoginWithValidEmailInvalidPassword() throws InterruptedException {

		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailTimestamp());
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234556789");
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert')]")).getText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password";
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		System.out.println("Test Method-2");

	}

	@Test(priority = 3)
	public void VerifyLoginWithInValidEmaiValidPassword() throws InterruptedException {

		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailTimestamp());
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234556789");
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert')]")).getText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password";
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		System.out.println("Test Method-3");

	}

	@Test(priority = 4)
	public void VerifyLoginWithInValidEmaiInValidPassword() throws InterruptedException {

		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailTimestamp());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234556789");
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert')]")).getText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password";
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		System.out.println("Test Method-4");

	}

	@Test(priority = 5)
	public void VerifyLoginWithBlankEmailAndPassword() throws InterruptedException {

		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("");
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		String actualMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert')]")).getText();
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password";
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		System.out.println("Test Method-5");

	}

}
