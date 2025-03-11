package training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestMemory {

	public static void main(String args[]) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.freecodecamp.org/learn/");
		
		Thread.sleep(1000);
		
		//System.out.println(driver.getTitle().isEmpty());
		System.out.println(driver.getTitle().toUpperCase());
		
		driver.findElement(By.className("menu-btn-text")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//a[@data-test-label='dropdown-donate-button']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.className("menu-btn-text")).click();
		driver.findElement(By.xpath("//ul//li//a[text()='Curriculum']")).click();
		
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//a[@href='/learn/foundational-c-sharp-with-microsoft/']")).getText());
		driver.findElement(By.xpath("//a[@href='/learn/foundational-c-sharp-with-microsoft/']")).click();
		
		System.out.println("over");
		Thread.sleep(1000);
		//driver.close();
		

	}

}
