package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.base;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends base{
	public Register() {
		super();
	}
	
        WebDriver driver;
        @BeforeMethod
        public void setup()
        {
	    driver = initiallizeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.linkText("Register")).click();
        }
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields()
	{
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Katike");
		driver.findElement(By.id("input-lastname")).sendKeys("Hari Kumar");
		driver.findElement(By.id("input-email")).sendKeys("Kumar.hari324" + Utilities.generatetimestamp() + "@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("9603698960");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class=\"btn btn-primary\"]")).click();
		WebElement actualResult = driver.findElement(By.xpath("//div[@id=\"content\"]/h1"));
		Assert.assertTrue(actualResult.isDisplayed());
		driver.quit();
		
	}
    
	@Test(priority =2)
	public void verifyRegisteringAccountByProvidingAllFields()
	{
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Katike");
		driver.findElement(By.id("input-lastname")).sendKeys("Hari Kumar");
		driver.findElement(By.id("input-email")).sendKeys("Kumar.hari324" + Utilities.generatetimestamp() + "@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("9603698960");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name=\"newsletter\"][@value=\"1\"]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class=\"btn btn-primary\"]")).click();
		WebElement actualResult = driver.findElement(By.xpath("//div[@id=\"content\"]/h1"));
		Assert.assertTrue(actualResult.isDisplayed());
		driver.quit();
	}
	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() 
	{
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Katike");
		driver.findElement(By.id("input-lastname")).sendKeys("Hari Kumar");
		driver.findElement(By.id("input-email")).sendKeys("Kumar.hari324@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("9603698960");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name=\"newsletter\"][@value=\"1\"]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class=\"btn btn-primary\"]")).click();
		String Actualoutput = driver.findElement(By.xpath("//div[@class=\"alert alert-danger alert-dismissible\"]")).getText();
		Assert.assertEquals(Actualoutput, "Warning: E-Mail Address is already registered!");
		
	}
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails()
	{
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("");
		driver.findElement(By.id("input-lastname")).sendKeys("");
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-telephone")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.id("input-confirm")).sendKeys("");
		driver.findElement(By.xpath("//input[@name=\"newsletter\"][@value=\"1\"]"));
		driver.findElement(By.name("agree"));
		driver.findElement(By.xpath("//input[@class=\"btn btn-primary\"]")).click();
		
		String actualPrivacyPolicyWarningMessage = driver.findElement(By.xpath("//div[contains(@class,\"alert alert-danger alert-dismissible\")]")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarningMessage.contains("Warning: You must agree to the Privacy Policy!"));
		
		String firstNameWarningMessage = driver.findElement(By.xpath("(//div[contains(@class,\"text-danger\")])[1]")).getText();
		Assert.assertEquals(firstNameWarningMessage,"First Name must be between 1 and 32 characters!","First name warning message is not displayed");
		
		
	    String lastNameWarningMessage = driver.findElement(By.xpath("(//div[contains(@class,\"text-danger\")])[2]")).getText();
	    Assert.assertEquals(lastNameWarningMessage,"Last Name must be between 1 and 32 characters!","Last name warning message is not displayed");
		
	    
		String emailAddressWarningMessage = driver.findElement(By.xpath("(//div[contains(@class,\"text-danger\")])[3]")).getText();
		Assert.assertEquals(emailAddressWarningMessage,"E-Mail Address does not appear to be valid!","Email Address warning message is not displayed");
		
		
		String telephoneNumberWarningMessage = driver.findElement(By.xpath("(//div[contains(@class,\"text-danger\")])[4]")).getText();
		Assert.assertEquals(telephoneNumberWarningMessage,"Telephone must be between 3 and 32 characters!","Telephone Number warning message is not displayed");
		
		
		String passwordWarningMessage = driver.findElement(By.xpath("(//div[contains(@class,\"text-danger\")])[5]")).getText();
		Assert.assertEquals(passwordWarningMessage,"Password must be between 4 and 20 characters!","Password warning message is not displayed");
		
		
	}
	
	
}
