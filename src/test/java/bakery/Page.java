package bakery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	
	public Page(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 15L);

		PageFactory.initElements(driver, this);
	}

	WebDriver driver;
	WebDriverWait wait;
	
	
	
	@FindBy(xpath="//*[@id=\"bigCookie\"]")
	WebElement bigCookie;
	
	@FindBy(xpath="//*[@id=\"cookies\"]")
	WebElement cookieCount;

	
	public void clickCookie() {
		bigCookie.click();
	}
	
	public boolean areWeReady() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(bigCookie));
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String cookieCount() {
		return cookieCount.getText();
	}
	
	public WebElement findCheapestElement() {
		List<WebElement> pricesList = driver.findElements(By.xpath("/html/body/div/div[2]/div[19]/div[3]/div[6]/div[*]/div[3]/span[2]"));
		WebElement cheapestElement = pricesList.get(0);
		
//		System.out.println(pricesList.size());

		int divCount = 1;
		
		for (WebElement priceElement : pricesList) {
			int cheapestPrice = 0;
			int priceOfElement = 0;
			
			try {
				cheapestPrice = Integer.parseInt(cheapestElement.getText().replace(",", ""));
				priceOfElement = Integer.parseInt(priceElement.getText().replace(",", ""));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
//				System.out.println("endingit");
				break;
			}
						
//			System.out.println(priceOfElement + " " + cheapestPrice);
			
			
			if (priceOfElement < cheapestPrice) {
				cheapestElement = priceElement;
			}
		}
		
		return cheapestElement;
	}
	
	public void clickCheapest() {
		WebElement cheapestElement = findCheapestElement();
//		String cheapestID = cheapestElement.getAttribute("id");
//		cheapestID.replace("Price", "Name");
//		
//		int cookiesNeeded = Integer.parseInt(cheapestElement.getText().split(" ")[0].replace(",", "") );
//		
//		if (Integer.parseInt(cookieCount()) > cookiesNeeded) {
//			System.out.println("clicking");
//			driver.findElement(By.id(cheapestID)).click();
//		}
		System.out.println(cheapestElement.getText());
		
		try {
			cheapestElement.findElement(By.xpath("./../..")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ahh");
		}	
		
	}
	
}
