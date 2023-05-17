package com.tutorialsninja.qa.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Date;

import javax.xml.crypto.Data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v111.database.Database;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login {
    // Verify Login with valid credentials
	@Test
	public void verifyLoginWithValidCredentials() throws InterruptedException
	{
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	driver.get("https://tutorialsninja.com/demo/");
	driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
	driver.findElement(By.linkText("Login")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
	driver.findElement(By.id("input-password")).sendKeys("12345");
	driver.findElement(By.xpath("//input[@value='Login']")).click();
	Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	driver.quit();
	
	}
	
	// Verify Login with invalid credentials
	@Test(invocationCount = 2, priority = 2)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("input-email")).sendKeys("amotooric9"+generatetimestamp()+"@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualwarningmessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedwarningmessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualwarningmessage.contains(expectedwarningmessage));
		driver.quit();
		
	}
	@Test(invocationCount = 3,priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("input-email")).sendKeys("amotooric9"+generatetimestamp()+"@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualwarningmessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedwarningmessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualwarningmessage.contains(expectedwarningmessage));
		driver.quit();

	}
	@Test(priority = 4,invocationCount = 2)
	public void verifyLoginWithvalidEmailAndInvalidPassword() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("input-email")).sendKeys("amotooric9@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualwarningmessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedwarningmessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualwarningmessage.contains(expectedwarningmessage));
		driver.quit();
	}

	@Test(priority = 5, invocationCount = 2)
	public void verifyLoginWithoutProvidingCredentials() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualwarningmessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedwarningmessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualwarningmessage.contains(expectedwarningmessage));
		driver.quit();
	}
	

	public String generatetimestamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
		
	}
}
