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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FlightBookingTest {
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
	
	@Test
	public void seachFlightTest() throws IOException, InterruptedException {
		WebElement flightBtn = driver.findElement(By.xpath("//a[normalize-space()='flights']"));
		flightBtn.click();
		WebElement flighType = driver.findElement(By.xpath("//select[@id='flight_type']"));
		Select flightypeSelect = new Select(flighType);
		flightypeSelect.selectByIndex(3);
		
		scroll(100);
		
		WebElement sourcePlace = driver.findElement(By.xpath("//input[@placeholder='Flying From']"));
		sourcePlace.sendKeys("Pune");
		List<WebElement> sourcePlaceList = driver.findElements(By.cssSelector("div[class='autocomplete-result'] strong"));
		for(WebElement option: sourcePlaceList) {	
			System.out.println(option.getText());
			String places = option.getText();
			if(places.equalsIgnoreCase("Pune")) {
				option.click();
				break;
			}
		}
		
		WebElement destPlace = driver.findElement(By.xpath("//input[@id='autocomplete2']"));
		destPlace.sendKeys("BLR");
		List<WebElement> destPlaceList = driver.findElements(By.cssSelector("div[class='autocomplete-result'] strong"));
		for(WebElement option:destPlaceList) {
			System.out.println(option.getText());
			if(option.getText().equals("Bangalore")) {
				option.click();
				
				break;
			}
		}
		
		
		driver.findElement(By.cssSelector("input[class='depart form-control']")).click();
		driver.findElement(By.xpath("//tr[position()=5]//td[text()='26']")).click();
		takeScreenshot(0,"FinalPage");
		driver.findElement(By.xpath("//i[@class='mdi mdi-search']")).click();
		takeScreenshot(0, "Done");
	}
	
	@Test
	public void loginTest() {
		
	}
	
}
