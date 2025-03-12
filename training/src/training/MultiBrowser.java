package training;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class MultiBrowser {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		
		
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://rahulshettyacademy.com/");
		
				
		
		String firstCourse = driver.findElements(By.cssSelector("#courses-block h2")).get(0).getText();
	
		
		WebElement home = driver.findElement(By.xpath("//a[contains(text(), 'Home')]"));
		driver.findElement(with(By.tagName("a")).toRightOf(home)).click();
		
		
		 Set<String> tabs = driver.getWindowHandles();
		 Iterator<String> tab = tabs.iterator();
		 String firstHandle = tab.next();
		 String secondHandle = tab.next();
		 
		 driver.switchTo().window(firstHandle);
		 
		 WebElement name = driver.findElement(By.cssSelector("[name='name']"));
		 name.sendKeys(firstCourse);
		 
		 
//Takes screenshot of the screen
File screenshot = ((TakesScreenshot)driver).getScreenshotAs (OutputType.FILE);
FileUtils.copyFile(screenshot, new File("c:\\Users\\loven\\Downloads\\screenshot.png"));

//Takes screenshot of the specified element 
File elementScreenshot = name.getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(elementScreenshot, new File("c:\\Users\\loven\\Downloads\\elementScreenshot.png"));


//Verifying the dimensions of a web element

System.out.println(name.getRect().getHeight());
System.out.println(driver.findElement(By.id("inlineRadio1")).getRect().getDimension());
driver.quit();

		
	}

}
