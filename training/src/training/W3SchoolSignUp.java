package training;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class W3SchoolSignUp {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.w3schools.com/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//html[1]/body[1]/div[2]/div[1]/div[3]/a[2]")).click();
		
		driver.findElement(By.cssSelector("input[class='SignUpForm_signup_input__6r1Lr']:nth-child(1)")).sendKeys("marinalovenita28@gmail.com");
		driver.findElement(By.xpath("//input[@class='SignUpForm_signup_input__6r1Lr'][2]")).sendKeys("LearnSelenium55");
		driver.findElement(By.xpath("//div/input[3]")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("div input:nth-child(4)")).sendKeys("Tester");
		
		

		
		List<WebElement> social = driver.findElements(By.cssSelector("div.LoginPanel_provider_text__R-NWt"));
		//System.out.println(social.size());
		
		for(int i = 1;i<=social.size();i++) {
			
			String css = "div[class='LoginPanel_social_login_container__C+lzO'] button:nth-child("+i+")";
			By element = By.cssSelector(css);
		
			System.out.println(driver.findElement(element).getText());
						
			
			try {
			Assert.assertEquals(driver.findElement(element).getText(), "Google") ;
			}
			catch (AssertionError e) {
				System.out.println("the button does not match");
			}
			
		
			
					
		}
		System.out.println(driver.findElement(By.cssSelector("div[class='LoginPanel_social_login_container__C+lzO'] button:nth-child(2)")).getText());
		driver.findElement(By.xpath("//div[text()='Facebook']")).click();
		
		driver.navigate().back();
		
		//driver.findElement(By.xpath("//html[1]/body[1]/div[2]/div[1]/div[3]/a[2]")).click();
		
		driver.findElement(By.cssSelector("input[class='SignUpForm_signup_input__6r1Lr']:nth-child(1)")).sendKeys("marinalovenita28@gmail.com");
		driver.findElement(By.xpath("//input[@class='SignUpForm_signup_input__6r1Lr'][2]")).sendKeys("LearnSelenium@55");
		driver.findElement(By.xpath("//div/input[3]")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("div input:nth-child(4)")).sendKeys("Tester");
		
		//WebElement checkbox = driver.findElement(By.xpath("//div[@class='SignUpForm_email_consent_checkbox__O3Dxt']"));
		//System.out.println( driver.findElement(By.cssSelector("input[type='checkbox']")).getText());
		//driver.findElement(By.xpath("//span[text()='Email me with news and updates']"));
		//Thread.sleep(3000);
		//if (checkbox.isEnabled()) {
		//	checkbox.click();
		//	}
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
	}

}
