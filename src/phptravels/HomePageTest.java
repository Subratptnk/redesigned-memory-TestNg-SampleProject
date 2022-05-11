package phptravels;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTest {

WebDriver driver;
	
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Subrat2022/SELENIUM/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.phptravels.net/login");	
			
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public void takeScreenshot(int count,String message) throws IOException {
		
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("D:/Subrat2022/SELENIUM/Screenshots/"+message+count+".png"));

	}
	
	public void scroll(int x) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,"+x+")");
	}
	
	@Test(priority = 1)
	public void signUpTest() throws IOException, InterruptedException {
		WebElement signUpBtn = driver.findElement(By.xpath("//a[@class='theme-btn theme-btn-small waves-effect']"));
		signUpBtn.click();
		takeScreenshot(1,"SignUpPage");
		
		WebElement firstName = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
		WebElement lastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
		WebElement phone = driver.findElement(By.xpath("//input[@placeholder='Phone']"));
		WebElement email = driver.findElement(By.xpath("//input[@placeholder='Email']"));
		WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		List<WebElement> accountType = driver.findElements(By.cssSelector("ul[id='select2-account_type-results'] li"));
		for(WebElement options: accountType) {
			if(options.getText().equalsIgnoreCase("Customer")) {
				options.click();
			}
		}
		WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		
		firstName.sendKeys("Rajat");
		lastName.sendKeys("Rawal");
		phone.sendKeys("7709821878");
		email.sendKeys("rajat.rawal5@sinecos.com");
		password.sendKeys("Rajat@sineCos");
		scroll(500);
		takeScreenshot(2, "Before Submit Button is clicked");
		submitBtn.click();
		takeScreenshot(3, "After Submit Button is clicked");
		
	}

	@Test(priority = 2)
	public void loginTest() throws IOException, InterruptedException {
		scroll(250);
		WebElement email = driver.findElement(By.xpath("//input[@placeholder='Email']"));
		WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
		WebElement saveMe = driver.findElement(By.xpath("//label[normalize-space()='Remember Me']"));
		WebElement login = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg btn-block effect ladda-button waves-effect']"));
		
		takeScreenshot(4, "Before Login");
		email.sendKeys("rajat.rawal5@sinecos.com");
		password.sendKeys("Rajat@sineCos");
		saveMe.click();
		login.click();
		takeScreenshot(5, "After Login");
	}
	@Test(priority = 3)
	public void dashboardTest() throws IOException, InterruptedException {
		driver.findElement(By.xpath("//a[normalize-space()='Home']")).click();
	}
	
}
