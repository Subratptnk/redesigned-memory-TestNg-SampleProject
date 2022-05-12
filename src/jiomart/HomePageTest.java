package jiomart;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
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
import org.testng.annotations.BeforeMethod;
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
		driver.get("https://www.jiomart.com/");	
			
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
	
	
	/*
	 * Test Case - 1 : Change the delivery address to 751030
	 */
	@Test(enabled = false)
	public void changeDeliverAddressTest() throws IOException {
		WebElement deliversTo= driver.findElement(By.xpath("//li[@id='pincode_section']"));
		deliversTo.click();
		takeScreenshot(0, "before changing the address");
		WebElement pincodeField = driver.findElement(By.cssSelector("div[class='pin_detail'] input[class*='inp_text']"));
		pincodeField.sendKeys("751030");
		driver.findElement(By.xpath("//button[text()='APPLY']")).click();
		takeScreenshot(0, "After changing the address");
	}
	
	/*
	 * Test case - 2: Click on groceries , go to atta, flour section, add 5kg ashirward aata to cart.. 
	 */
	@Test(enabled = false)
	public void addAttaFromGroceries() throws IOException, InterruptedException {
		WebElement groceries = driver.findElement(By.cssSelector("#nav_link_2"));
		groceries.click();
		scroll(50);
		driver.findElement(By.xpath("//a[@href='javascript:void(0)'][text()='Staples']")).click();
		WebElement attaButton = driver.findElement(By.xpath("//a[normalize-space()='Atta, Flours & Sooji']"));
		takeScreenshot(0, "aata,Flours&sooji");
		attaButton.click();
		String result = "Aashirvaad Whole Wheat Atta 5 kg";
		takeScreenshot(1, "aata,Flours&sooji");
		List<WebElement> productList = driver.findElements(By.xpath("//div[@class='cat-item viewed']//span[@class='clsgetname']"));
		for(WebElement option : productList) {
			String test = option.getText();
			if(test.equalsIgnoreCase(result)) {
				System.out.println(test);
				option.click();
				takeScreenshot(2, "aata,Flours&sooji");
				driver.findElement(By.xpath("//button[@class='toCart addtocartbtn prodbtn']//span[@class='txt_btn'][normalize-space()='Add to Cart']")).click();
				break;
			}
		}
			
}
	/*
	 * Test Case - 3 : Add apple,banana,dates into cart from fruits and vegitable section
	 */
	@Test
	public void addFruitsToCart() throws InterruptedException, IOException {
		
		WebElement groceries = driver.findElement(By.cssSelector("#nav_link_2"));
		groceries.click();
		scroll(50);
		takeScreenshot(0, "fruits");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//a[@href='javascript:void(0)'][text()='Fruits & Vegetables']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Fresh Fruits']")).click();
		
		int j = 0;
		String[] fruits = {"Apple","Banana","Dates"};
		
		List<WebElement> fruitList = driver.findElements(By.xpath("//div[@class='cat-item viewed']//span[@class='clsgetname']"));
		System.out.println(fruitList.size());
		scroll(500); //Need to implement from here
		for(int i=0;i<fruitList.size();i++) {
			System.out.println(fruitList.get(i).getText());
			
			String name[] = fruitList.get(i).getText().split(" ");
			String formattedName = name[0].trim();
			System.out.println("----name: "+name[0].trim());
			
			List<String> fruitNeeded = Arrays.asList(fruits);
			
			if(fruitNeeded.contains(formattedName)) {
				j++;
				driver.findElements(By.xpath("//button[contains(@title,'ADD TO CART')]")).get(i).click();
				
				System.out.println(formattedName + " --> Clicked");
				if(j==fruits.length) {
					break;
				}
			}
			
			
		}
		
		
	}
}
