package com.tutorialsninja.qa.testcases;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login {

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
	
	
}
