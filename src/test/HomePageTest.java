package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest {
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
		login("mrsubrat1998@gmail.com", "Subrat@1598");
	}
	

	public void login(String username,String password) {
		
		WebElement loginBtn = driver.findElement(By.xpath("//a[text()='Login']"));
		loginBtn.click();
		
		driver.findElement(By.xpath("//input[@placeholder='E-mail address']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
		//login button:
		WebElement loginSubmitBtn=driver.findElement(By.xpath("//div[text()='Login']"));
//		JavascriptExecutor js =  (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();", loginSubmitBtn);
	}
	
	
	@Test
	public void clickOnContractLinkTest() {
		
	}
	
	@Test
	public void clickOnDealsLinkTest() {
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
