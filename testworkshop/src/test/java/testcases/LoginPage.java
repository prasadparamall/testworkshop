package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import baseclass.baseclass;

public class LoginPage extends baseclass {
	
	
	@Test(priority = 1)
	//Click Login Link
	public void Login() {  
		
		driver.findElement(By.xpath(pro.getProperty("loginlink"))).click();
		log.info("Click login button");
		
	}
	
	@Test(priority = 2)
	//Enter Valid Credentials
	public void EnterValidCredentials() throws InterruptedException {
		
		driver.findElement(By.xpath(pro.getProperty("email"))).sendKeys(pro.getProperty("useremailid"));
		driver.findElement(By.xpath(pro.getProperty("password"))).sendKeys(pro.getProperty("userpassword"));
		driver.findElement(By.xpath(pro.getProperty("loginbtn"))).click();
		log.info("Enter valid user credentials");
		Thread.sleep(3000);
	}
	
}
