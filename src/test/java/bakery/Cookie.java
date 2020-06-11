package bakery;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Cookie {
	
	WebDriver driver;
	private static ExtentReports report;
	private ExtentTest test;
	Page homePage;
	
	@BeforeClass
	public static void setup() {
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter("test-reports/ShoppingWebsite/extentReport.html");
		htmlReport.config().setAutoCreateRelativePathMedia(true);
		report.attachReporter(htmlReport);
	}
	
	@Before
	public void init() {
		driver = new ChromeDriver();
	}

	@Test
	public void test() {
		
		driver.get("https://orteil.dashnet.org/cookieclicker/");
		
		homePage = new Page(driver);
		assertTrue(homePage.areWeReady());
		
		for(int i=1; i<Integer.MAX_VALUE; i++) {
			homePage.clickCookie();
			
			if ((i%100)==50) {
//				System.out.println("lala");
				homePage.clickCheapest();
			}
			
			if (i%100==0) {
				try {
					driver.findElement(By.id("upgrade0")).click();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		try {
			driver.wait(500L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(homePage.cookieCount());
	}
	
	@After
	public void endit() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		driver.close();
	}
	
//	@AfterClass
	public static void finishUp() {
		report.flush();
		

	}

}
