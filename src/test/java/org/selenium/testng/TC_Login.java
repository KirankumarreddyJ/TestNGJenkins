package org.selenium.testng;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_Login extends BaseClass{
	String loginURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	
	@BeforeClass
	public void beforeClass() {
		driver.get(loginURL);
		Assert.assertEquals(driver.getCurrentUrl(), loginURL, "Page not loaded");
	}
	
	@Test
	public void verifyValidLogin() {
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[contains(.,'Logi n')]")).click();
		
		assertTrue(driver.findElement(By.xpath("//h6[contains(.,'Dashboard')]")).isDisplayed(), "Unable to login with valid credentials");
	}
	
	@Test(dependsOnMethods = {"verifyValidLogin"})
	public void verifyLogout() {
		driver.findElement(By.xpath("//img[@alt='profile picture']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		assertEquals(driver.getCurrentUrl(), loginURL,"User not loggedout successfully!");
	}
}
