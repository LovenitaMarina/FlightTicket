//import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class UpdateDropDownSpicejet{

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.spicejet.com/");
		driver.manage().window().maximize();
		
		//driver.findElement(By.cssSelector("div[data-testid='round-trip-radio-button']")).click();
		Thread.sleep(2000);
		try {
		System.out.println(driver.findElement(By.cssSelector("div[data-testid='one-way-radio-button'] div svg g")).isDisplayed());
		}
		catch (Exception e) {
			System.out.println("one way trip not selected");
		}
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.cssSelector("div[data-testid='round-trip-radio-button']")).isSelected());
		
		if(!driver.findElement(By.cssSelector("div[data-testid='one-way-radio-button']")).isSelected()) {
			driver.findElement(By.cssSelector("div[data-testid='round-trip-radio-button']")).click();
			System.out.println(driver.findElement(By.cssSelector("div[data-testid='one-way-radio-button']")).isSelected());
			Assert.assertFalse(driver.findElement(By.cssSelector("div[data-testid='round-trip-radio-button']")).isSelected());
			
		}
		
		
		driver.findElement(By.cssSelector("div[class='css-1dbjc4n'] div div[class='css-1dbjc4n r-14lw9ot r-11u4nky r-z2wwpe r-1phboty r-rs99b7 r-1loqt21 r-13awgt0 r-ymttw5 r-5njf8e r-1otgn73'] div[class='css-1dbjc4n']")).click();
		
		//div[@class='css-1dbjc4n'] //div[@data-testid='home-page-travellers']/div[@class='css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep']
		By numberofadults = By.cssSelector("div[class='css-1dbjc4n'] div div[data-testid='home-page-travellers'] div[class='css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep']");
		
		Assert.assertEquals(driver.findElement(numberofadults).getText(),"1 Adult");
		
		int i =1;
		while(i<5) {
			
			driver.findElement(By.cssSelector("div[data-testid='Adult-testID-plus-one-cta']")).click();
			i++;
		}
		
		Assert.assertEquals(driver.findElement(numberofadults).getText(),"5 Adults");
		
		By from = By.cssSelector("input.css-1cwyjr8.r-homxoj.r-ubezar.r-10paoce.r-13qz1uu");
		driver.findElement(from).click();
		
		System.out.println(driver.findElement(By.xpath("//div[text()='Bengaluru']")).getText());
		driver.findElement(By.xpath("//div[text()='Bengaluru']")).click();
		
		WebElement elementfrom = driver.findElement(By.xpath("//div[@data-testid='to-testID-origin']//div[@class='css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep']/input"));
		Assert.assertTrue(elementfrom.getAttribute("value").contains("Bengaluru"), "The origin city chosen is not Bengaluru ");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='Bhopal']")).click();
		
		WebElement elementto = driver.findElement(By.xpath("//div[@data-testid='to-testID-destination']//div[@class='css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep']/input"));
		Assert.assertFalse(elementto.getAttribute("value").contains("Bengaluru"), "The destination city chosen is not Bengaluru ");
		
		By swipeCalendar= By.xpath("//div[@class='css-1dbjc4n r-1niwhzg r-z2wwpe r-17b9qp5 r-1g94qm0 r-h3f8nf r-u8s1d r-u3yave r-8fdsdq']/div/div[1]");
		//By fromMonth = By.xpath("//div[contains(@data-testid,'December')]");
		driver.findElement(swipeCalendar).click();
		
		driver.findElement(By.xpath("//div[contains(@data-testid,'December')]//div[contains(@data-testid,'14')]")).click();
		
		Thread.sleep(5000);
		
		//driver.findElement(By.xpath("//div[text()='Select Date']")).click();
		
		
		
		driver.findElement(By.xpath("//div[contains(@data-testid,'December')]//div[contains(@data-testid,'27')]")).click();
		
		driver.findElement(By.xpath("//div[contains(text(),'Currency')]")).click();
		
		driver.findElement(By.xpath("//div[text()='USD']")).click();
		
		driver.findElement(By.xpath("//div[@class='css-1dbjc4n']/div[contains(text(),'Senior Citizen')]")).click();
		
	driver.findElement(By.cssSelector("div[data-testid='home-page-flight-cta']")).click();
		
	/*	
	try {
		driver.quit();
	}
	catch(Exception e) {
		System.out.println("Browser closed");
	}*/
	
	}
	
	
	}
