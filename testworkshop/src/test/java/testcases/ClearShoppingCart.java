package testcases;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.io.IOException;
import baseclass.baseclass;

public class ClearShoppingCart extends baseclass {
	
	
	
	
	
	@Test
	//If Already Add Any Items in Addtocart, it will remove and go to the books page
	public void ClearAddToCartAndGoToBooks() throws InterruptedException, IOException {
		
		
		WebElement clik = driver.findElement(By.xpath(pro.getProperty("num")));
		//System.out.println(clik.getText());
		String s="(0)";
		if(clik.getText().equals(s))
		{
			driver.findElement(By.xpath(pro.getProperty("books2"))).click();
			log.info("Go To Books Page.............");
		}
		else
		{
			
			WebElement shippingcart = driver.findElement(By.xpath(pro.getProperty("movetoshippingcartlink")));
			shippingcart.click();
			Thread.sleep(2000);
			
    		
    	List<WebElement>cartlist=driver.findElements(By.xpath("//tr[@class='cart-item-row']/td[1]"));
			
    	Iterator<WebElement> cart =cartlist.iterator();
    	
    	while(cart.hasNext())
    	{
    		cart.next().click();
    	}
    	
		driver.findElement(By.xpath(pro.getProperty("updateshippindcartbtn"))).click();
	
		}
	log.info("Clear Shipping Cart Items............");
	}

}
	
