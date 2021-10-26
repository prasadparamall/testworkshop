package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import baseclass.baseclass;

public class Logout extends baseclass {
	@Test
	public void logout() {
		
		driver.findElement(By.xpath(pro.getProperty("userlogout"))).click();
		log.info("Logout user....................");
		
		
	}  

}
