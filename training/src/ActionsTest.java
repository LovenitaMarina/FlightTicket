import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.geko.driver", "C:\\Selenium\\driver\\geko.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		WebElement move1 = driver.findElement(By.id("nav-link-accountList"));
		
		Actions a = new Actions(driver);
		a.moveToElement(move1).build().perform();
		WebElement move2 =  driver.findElement(By.id("twotabsearchtextbox")) ;
		
		a.moveToElement(move2).click().keyDown(Keys.SHIFT).sendKeys("hardisk").doubleClick().build().perform();
		
		
		
	}
	

}
