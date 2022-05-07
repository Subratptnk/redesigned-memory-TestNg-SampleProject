package test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Subrat2022/SELENIUM/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.freecrm.com/");
		login("mrsubrat1998@gmail.com", "15@Sp#98");
	}
	

	public void login(String username,String password) {
		
		WebElement loginBtn = driver.findElement(By.xpath("//a[text()='Login']"));
		loginBtn.click();
	//	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@placeholder='Password']"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@placeholder='E-mail address']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
		//login button:
	WebElement loginSubmitBtn =driver.findElement(By.xpath("//div[text()='Login']"));
	loginSubmitBtn.click();
//		JavascriptExecutor js =  (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click();", loginSubmitBtn);
	}
	
	public void takeScreenshot() throws IOException {
		int count =0;
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("D:/Subrat2022/SELENIUM/Screenshots/crm"+count+".png"));
		count++;
	}
	
	
	@Test
	public void clickOnProductLinkTest() throws IOException {
		
		WebElement gearIcon = driver.findElement(By.xpath("//div[@role='listbox']"));
		WebElement productsLink = driver.findElement(By.xpath("//span[text()='Products']"));
		
		gearIcon.click();
		productsLink.click();
		
		String visibleText =  driver.findElement(By.xpath("//div[text()='Products']")).getText();
		takeScreenshot();
		Assert.assertEquals(visibleText, "Products");
		
	}
	
	@Test(enabled = false)
	public void addNewProductTest() throws IOException {
		
		String[] names = {"Pen","Pencil","Eraser","Sharpner","Scale","pencilBox"};
		String[] costs = {"10","5","5","5","10","50"};
		WebElement newBtn = driver.findElement(By.xpath("//button[text()='New']"));
		newBtn.click();
		WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
		WebElement listPrice = driver.findElement(By.xpath("//input[@name='list_price']"));
		WebElement wholesalePrice = driver.findElement(By.xpath("//input[@name='wholesale_price']"));
		WebElement cost = driver.findElement(By.xpath("//input[@name='cost']"));
		WebElement category = driver.findElement(By.xpath("//div[@class='ui selection dropdown']"));
		Select categorySelect = new Select(category);
		categorySelect.selectByValue("Product");
		for(int i=0;i<=names.length;i++) {
				name.sendKeys(names[i]);
				cost.sendKeys(costs[i]);
				listPrice.sendKeys(costs[i]);
				wholesalePrice.sendKeys(costs[i]);
				driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
				takeScreenshot();
				driver.findElement(By.xpath("//a[@class='section']")).click();
				
				
		}
	
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
