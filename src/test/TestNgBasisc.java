package test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNgBasisc {
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Subrat2022/SELENIUM/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.freecrm.com/");
	}
	
	@Test(priority = 1)
	public void verifyPageTitleTest() throws IOException {
		String ExpectedTitle = "Free CRM software for customer relationship management, sales, marketing campaigns and support.";
		String pageTitile =  driver.getTitle();
		//System.out.println("The page tittle is "+pageTitile);		
		Assert.assertEquals(pageTitile, ExpectedTitle);
	}
	
	@Test(priority = 2)
	public void verifyLogovisibilityTest() throws IOException {
		boolean flag = driver.findElement(By.cssSelector("img[src='https://freecrm.com/images/freecrm_logo.png']")).isDisplayed();
		Assert.assertTrue(flag);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
