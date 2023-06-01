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
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys("Kumar.hari324" + Utilities.generatetimestamp() + "@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class=\"btn btn-primary\"]")).click();
		String actualSucessHeading = driver.findElement(By.xpath("//div[@id=\"content\"]/h1")).getText();
		Assert.assertTrue(actualSucessHeading.contains(dataprop.getProperty("accountSucessfullyCreatedHeading")));
		driver.quit();
		
	}
    
	@Test(priority =2)
	public void verifyRegisteringAccountByProvidingAllFields()
	{
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys("Kumar.hari324" + Utilities.generatetimestamp() + "@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name=\"newsletter\"][@value=\"1\"]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class=\"btn btn-primary\"]")).click();
		String actualResult = driver.findElement(By.xpath("//div[@id=\"content\"]/h1")).getText();
		Assert.assertTrue(actualResult.contains(dataprop.getProperty("accountSucessfullyCreatedHeading")));
		driver.quit();
	}
	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() 
	{
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name=\"newsletter\"][@value=\"1\"]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@class=\"btn btn-primary\"]")).click();
		String Actualoutput = driver.findElement(By.xpath("//div[@class=\"alert alert-danger alert-dismissible\"]")).getText();
		Assert.assertEquals(Actualoutput, dataprop.getProperty("duplicateEmailWarning"));
		
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
		Assert.assertTrue(actualPrivacyPolicyWarningMessage.contains(dataprop.getProperty("privacyPolicyWarning")));
		
		String firstNameWarningMessage = driver.findElement(By.xpath("(//div[contains(@class,\"text-danger\")])[1]")).getText();
		Assert.assertEquals(firstNameWarningMessage,dataprop.getProperty("firstNameWarning"),"First name warning message is not displayed");
		
		
	    String lastNameWarningMessage = driver.findElement(By.xpath("(//div[contains(@class,\"text-danger\")])[2]")).getText();
	    Assert.assertEquals(lastNameWarningMessage,dataprop.getProperty("lastNameWarning"),"Last name warning message is not displayed");
		
	    
		String emailAddressWarningMessage = driver.findElement(By.xpath("(//div[contains(@class,\"text-danger\")])[3]")).getText();
		Assert.assertEquals(emailAddressWarningMessage,dataprop.getProperty("emailWarning"),"Email Address warning message is not displayed");
		
		
		String telephoneNumberWarningMessage = driver.findElement(By.xpath("(//div[contains(@class,\"text-danger\")])[4]")).getText();
		Assert.assertEquals(telephoneNumberWarningMessage,dataprop.getProperty("telephoneWarning"),"Telephone Number warning message is not displayed");
		
		
		String passwordWarningMessage = driver.findElement(By.xpath("(//div[contains(@class,\"text-danger\")])[5]")).getText();
		Assert.assertEquals(passwordWarningMessage,dataprop.getProperty("passwordWarning"),"Password warning message is not displayed");
		
		
	}
	
	
}
