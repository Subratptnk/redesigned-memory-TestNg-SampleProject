package trainbooking;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RailyatriHomePage {
	WebDriver driver;
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:/Subrat2022/SELENIUM/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		String url = "https://www.railyatri.in/";
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);	
			
	}
	//@AfterTest
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
	
	/*
	 * TC-01: Visit to homepage
	 */
	@Test
	public void VistHomePage() {
		String pageName = driver.getCurrentUrl();
		Assert.assertEquals(pageName,"https://www.railyatri.in/");
	}
	
	/*
	 * TC-02: check the train status of NEW DELHI - BHUBANESWAR Rajdhani Express
	 */
	@Test
	public void checkTrainStatus() throws IOException {
		String trainNumber = "22824";
		WebElement trainNo = driver.findElement(By.xpath("//input[@id='search_status']"));
		trainNo.sendKeys(trainNumber);
		trainNo.sendKeys(Keys.ENTER);
		takeScreenshot(1, "trainStatus");
		driver.findElement(By.xpath("//img[@alt='RailYatri Logo']")).click();
		
	}
	/*
	 * TC-03: search Train from bhubaneswar to new delhi 
	 */
	
	@Test
	public void searchTrain() {
		WebElement source = driver.findElement(By.xpath("//input[@id='from_stn_input']"));
		source.sendKeys("BBS");
		
		List<WebElement> sourceList = driver.findElements(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//li"));
		
		for(WebElement option : sourceList ) {
			
			String sourcePlace = option.getText();
			
			if(sourcePlace.equalsIgnoreCase("BHUBANESWAR | BBS")) {
				System.out.println(sourcePlace);
				driver.findElement(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//li")).click();
			}
		}
		WebElement destination = driver.findElement(By.xpath("//input[@id='to_stn_input']"));
		destination.sendKeys("NDLS");
		
		driver.findElement(By.xpath(" (//ul[@id='ui-id-6'])[1]")).click();
//		List<WebElement> destList = driver.findElements(By.cssSelector("ul[class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'] li"));
		
//		for(WebElement options : destList ) {
//			
//			String destPlace = options.getText();
//			
//			if(destPlace.equalsIgnoreCase("NEW DELHI | NDLS")) {
//				System.out.println(destPlace);
//				driver.findElement(By.cssSelector("ul[class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'] li")).click();
//			}
//		}
		driver.findElement(By.xpath("//input[@id='datepicker_train']")).click();
		driver.findElement(By.xpath("//td[normalize-space()='13']")).click();
		driver.findElement(By.xpath("//button[@id='search_btn_dweb']")).click();
	}
	@Test
	public void selectTrain() {
		List<WebElement> trainList = driver.findElements(By.cssSelector("div[class='TrainType_Detail'] div[class='namePart'] p"));
		for(WebElement train : trainList) {
			String trainName = train.getText();
				driver.findElement(By.xpath("//div[@class='customDivTbs Result_Info_Box tbs-main-row 12281 alternate_12281 avail_yes']//a[@class='ViewTimeTable']")).click();
				String resultString = driver.findElement(By.xpath("//h1[@class='black-txt']")).getText();
				String expectedString = "Bbs Duronto Express (12281) Train Time Table";
				assertEquals(resultString,expectedString );
			
		}	
	}

}
