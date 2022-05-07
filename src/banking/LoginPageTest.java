package banking;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//TestNg1234@567
public class LoginPageTest {
	WebDriver driver;
	
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Subrat2022/SELENIUM/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://parabank.parasoft.com/parabank/overview.htm");	
		
		login("Sanjeev1002", "Sanjeev@1001");
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public void takeScreenshot() throws IOException {
		int count =0;
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("D:/Subrat2022/SELENIUM/Screenshots/Demobank"+count+".png"));
		count++;
	}
	
	public void login(String username,String password)  {
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Log In']")).click();

	}
	
	@Test
	public void accountOverviewTest() throws InterruptedException {
		try {
			takeScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				}
			//new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//b[@class='ng-binding']"))));
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@class='ng-binding']")).click();
			WebElement balance = driver.findElement(By.xpath("//td[@id='balance']"));
			
			Assert.assertEquals(balance.getText(), "$1111.00");
			
	
	
	}	
	@Test
	public void accountActivityTest() {
		WebElement available = driver.findElement(By.xpath("//td[@id='availableBalance']"));
		Assert.assertNotEquals(available.getText(), null);
		driver.findElement(By.xpath("//input[@value='Go']")).click();
		
		WebElement date = driver.findElement(By.xpath("//tr[@class='ng-scope']//td[1]"));
		System.out.println(date.getText());
		WebElement transaction = driver.findElement(By.xpath("//tr[@class='ng-scope']//td[2]"));
		System.out.println(transaction.getText());
		WebElement creditAmt = driver.findElement(By.xpath("//tr[@class='ng-scope']//td[3]"));
		System.out.println(creditAmt.getText());
		
		boolean isPresentDate =date.isDisplayed();
		boolean isPresentTransaction = transaction.isDisplayed();
		
		Assert.assertTrue(isPresentDate);
		Assert.assertTrue(isPresentTransaction);
		
		try {
			takeScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	

}
