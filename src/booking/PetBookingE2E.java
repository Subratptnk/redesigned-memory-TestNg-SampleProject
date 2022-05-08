package booking;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PetBookingE2E {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Subrat2022/SELENIUM/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://katalon-demo-cura.herokuapp.com/");	
		
		
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	public void takeScreenshot(int count,String message) throws IOException {
		
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("D:/Subrat2022/SELENIUM/Screenshots/"+message+count+".png"));

	}
	
	public void login(String username,String password)  {
		driver.findElement(By.xpath("//a[@id='menu-toggle']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Login']")).click();
		driver.findElement(By.xpath("//input[@id='txt-username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txt-password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='btn-login']")).click();

	}
	
	@Test
	public void loginTest() throws IOException {
		takeScreenshot(1,"before Login");
		login("John Doe", "ThisIsNotAPassword");
		takeScreenshot(2,"After Login");
	}
	
	@Test
	public void makeAppointmentTest() throws IOException {
		WebElement facility = driver.findElement(By.xpath("//select[@id='combo_facility']"));
		Select facilitySelect = new Select(facility);
		facilitySelect.selectByIndex(2);
		
		WebElement readdmissionCheckbox = driver.findElement(By.xpath("//input[@id='chk_hospotal_readmission']"));
		readdmissionCheckbox.click();
		
		WebElement healthCareProgram = driver.findElement(By.xpath("//input[@id='radio_program_medicaid']"));
		healthCareProgram.click();
		
		WebElement dateOfAppointment = driver.findElement(By.xpath("//input[@id='txt_visit_date']"));
		dateOfAppointment.click();
		driver.findElement(By.xpath("//td[normalize-space()='9']")).click();
		
		WebElement comments = driver.findElement(By.xpath("//textarea[@id='txt_comment']"));
		String msg = "need a special treatment for a cat";
		comments.sendKeys(msg);
		takeScreenshot(3,"appointment detail before saving");
		WebElement bookBtn = driver.findElement(By.xpath("//button[@id='btn-book-appointment']"));
		bookBtn.click();
		takeScreenshot(4,"appointment is done");
		
		boolean successmsg = driver.findElement(By.xpath(" //h2[normalize-space()='Appointment Confirmation']")).isDisplayed();
		Assert.assertTrue(successmsg);
		
		
	}
}
