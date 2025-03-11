package training;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AirNZ {

	public static void main(String[] args) throws InterruptedException {
		
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.airnewzealand.co.nz/");
		driver.manage().window().maximize();
		
		WebElement origin = driver.findElement(By.id("cityPair__originCity"));
		
				
		if (origin.getAttribute("value").equalsIgnoreCase("Auckland")) {
			
			//driver.findElement(By.id("cityPair__originCity")).clear();
			
			origin.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			origin.sendKeys(Keys.DELETE);
			origin.sendKeys("a");
			
		}
			
			//WebElement citylist = driver.findElement(By.cssSelector("div#cityPair__originCity-item-0.pw-AutocompleteInput__option"));
			List<WebElement> originList = driver.findElements(By.cssSelector("div.pw-AutocompleteInput__option"));
			//div.pw-AutocompleteInput__option
			for(WebElement o : originList) {
			//System.out.println(d.getText());
			  if (o.getText().equalsIgnoreCase("auckland")) {
				o.click();
				break;
				}
			}
			  
			WebElement destination = driver.findElement(By.cssSelector("#cityPair__destinationCity.pw-AutocompleteInput__input"));
			destination.sendKeys("ko");
			 
			 
			  List<WebElement> destinationList = driver.findElements(By.cssSelector("div.pw-AutocompleteInput__option"));
			  
			  for(WebElement t : destinationList) {
				 // System.out.println(t.getText());
				  if(t.getText().contains("Kochi")) {
					t.click();
					break;
					  
				  }
			  }
			  
		Assert.assertEquals(origin.getAttribute("value"), "Auckland", "The origin is not Auckland");
		Assert.assertEquals(destination.getAttribute("value"), "Kochi (IN)", "The destination is not Kochi");
			
		if(driver.findElement(By.id("returnRadio")).isSelected()) {
			driver.findElement(By.id("onewayRadio")).click();
			
			Assert.assertTrue(driver.findElement(By.id("onewayRadio")).isSelected(), "It is a Two way trip");
		}
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='airnz-IconText airnz-IconText--icon-start airnz-DatePickerOverview__content']")).getText(), "Select date");
		driver.findElement(By.xpath("//div[@class='airnz-InnerFocus airnz-DatePicker']")).click();
		
		String s = null;
		while(s != "March 2025") {
			
			List<WebElement> Months = driver.findElements(By.cssSelector(".airnz-CalendarMonthHeader"));
			 
			for(int i = 0; i< Months.size();i++) {
				if (Months.get(i).getText().equals("March 2025")) {
					s = "March 2025";
					break;
				}
				else {
					
					//Thread.sleep(2000);)

					driver.findElement(By.cssSelector(".airnz-CalendarMonthHeader__nextButton")).click();
				}	
			}	
		}
		
		//By date = By.xpath("//div[@data-month='2025-0']/div[@class='airnz-CalendarDates']/div[text()='1']");
		By date = By.cssSelector("div[data-date='2025-03-01']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(date));
		
		driver.findElement(date).click();
		Thread.sleep(2000);
		//System.out.println(driver.findElement(By.xpath("//div[@class='airnz-IconText airnz-IconText--icon-start airnz-DatePickerOverview__content']")).getText());
		//Assert.assertEquals(driver.findElement(By.xpath("//div[@class='airnz-IconText airnz-IconText--icon-start airnz-DatePickerOverview__content']")).getText(), "Wed, 1 Jan 2025");
		
		By returnButton = By.id("returnRadio");
		wait.until(ExpectedConditions.elementToBeClickable(returnButton));
		
		driver.findElement(By.id("returnRadio")).click();
		Assert.assertTrue(driver.findElement(By.id("returnRadio")).isEnabled());
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='airnz-IconText airnz-IconText--icon-start airnz-DatePickerOverview__content']")));
		driver.findElement(By.xpath("//div[@class='airnz-IconText airnz-IconText--icon-start airnz-DatePickerOverview__content']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-date='2025-03-01']")));
		driver.findElement( By.cssSelector("div[data-date='2025-03-01']")).click();
		
		driver.findElement(By.xpath("//div[@class='airnz-Stepper pw-PassengerCountInput__adultStepper']/button[@aria-label='Add adult']")).click();
		
		Assert.assertFalse(driver.findElement(By.id("childAlone")).isSelected());
		
		driver.findElement(By.id("childAlone")).click();
		
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class='airnz-Stepper pw-PassengerCountInput__adultStepper']/button[@aria-label='Add adult']")).isEnabled());
		driver.findElement(By.id("childAlone")).click();	
		Assert.assertFalse(driver.findElement(By.id("childAlone")).isSelected());
		
		
		
		WebElement serviceClass = driver.findElement(By.id("serviceClass"));
		Select serviceDropdown = new Select(serviceClass);
		serviceDropdown.selectByIndex(2);
		
		for(WebElement w :serviceDropdown.getOptions()) {
			
			if(w.getText().startsWith("Economy") && ! (serviceDropdown.getFirstSelectedOption().getText() == w.getText()))
			{
				w.click();
				Assert.assertEquals(serviceDropdown.getFirstSelectedOption().getText(), "Economy - lowest");
				break;
			}
			
		}
		String currentWindow = driver.getWindowHandle();
		String searchWindow = null;
		
		
		driver.findElement(By.cssSelector("button[aria-controls='searchFlightsForm']")).click();
		
		Thread.sleep(5000);
		
		
		for(String handle : driver.getWindowHandles()) {
			//System.out.println(handle);
			if(handle != currentWindow) {
			searchWindow = handle;
			}
			
		}
		
		//System.out.println(searchWindow);
		driver.switchTo().window(currentWindow);
		System.out.println(searchWindow);
		
		
		driver.quit();
		
		
		
		
	}

		
		
		
	}

