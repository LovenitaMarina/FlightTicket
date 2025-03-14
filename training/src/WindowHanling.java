import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class WindowHanling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/loginpagePractise/#");
		driver.manage().window().maximize();
		
		System.out.println(driver.getWindowHandle());
		//Actions a = new Actions(driver);
		//WebElement newWin = driver.findElement(By.className("blinkingText"));
		//a.moveToElement(newWin).keyDown(Keys.RIGHT).click().
		driver.findElement(By.className("blinkingText")).click();
		Set <String> window = driver.getWindowHandles();
		Iterator <String> it =window.iterator();
		String parentId = it.next();
		String childId = it.next();
		
		driver.switchTo().window(childId);
		
		String emailId = driver.findElement(By.cssSelector("p.im-para.red")).getText().split("at")[1].split(" ")[1];
	

		
		System.out.println(emailId);
		driver.switchTo().window(parentId);
		driver.findElement(By.cssSelector("input#username")).sendKeys(emailId);
		
		//driver.close();
	}

}
