import java.time.Duration;



import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;





public class Locators2 {



public static void main(String[] args) throws InterruptedException {

// TODO Auto-generated method stub

//implicit wait - 2 seconds time out

System.setProperty("webdriver.chrome.driver", "C:/Selenium/driver/chromedriver.exe");

WebDriver driver = new ChromeDriver();
//System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\driver\\geckodriver.exe");

//WebDriver driver = new FirefoxDriver();

driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

driver.get("https://rahulshettyacademy.com/locatorspractice/");

String name = "rahul";
driver.findElement(By.id("inputUsername")).sendKeys(name );

driver.findElement(By.name("inputPassword")).sendKeys("rahulshettyacademy");

driver.findElement(By.className("signInBtn")).click();

By offer = By.cssSelector("div p");

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
wait.until(ExpectedConditions.elementToBeClickable(offer));

//if (offers != null ) 
System.out.println(driver.findElement(offer).getText());

Assert.assertEquals(driver.findElement(offer).getText(), "You are successfully logged in.");
System.out.println(driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText());
Assert.assertEquals(driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText(), "Hello " +name +"," );

driver.findElement(By.xpath("//*[text()='Log Out']")).click();

driver.findElement(By.className("submit")).click();

driver.findElement(By.linkText("Forgot your password?")).click();

driver.findElement(By.xpath("//form/input[1]")).sendKeys(name);
driver.findElement(By.xpath("//input[contains(@type,'text')][2]")).sendKeys("abc.com");
driver.findElement(By.xpath("//div[@class='form-container sign-up-container']/form/input[contains(@placeholder, 'Phone Number')]")).sendKeys("12345");

Thread.sleep(2000);
driver.findElement(By.cssSelector("div form div button[class*=reset]")).click();
String s = driver.findElement(By.className("infoMsg")).getText();

System.out.println(s);


/*
String password = s.split("'");

for(int i = 1;i<password.length;i++) {
	
	System.out.println(password[1]);
	
	
}
*/

s= getPwd(driver);
driver.findElement(By.cssSelector(".go-to-login-btn")).click();

driver.findElement(By.id("inputUsername")).sendKeys(name );
driver.findElement(By.name("inputPassword")).sendKeys(s);

Thread.sleep(2000);
driver.findElement(By.cssSelector(".submit")).click();

driver.close();
}


public static String getPwd(WebDriver driver) throws InterruptedException {
	
	driver.get("https://rahulshettyacademy.com/locatorspractice/");
	driver.findElement(By.linkText("Forgot your password?")).click();
	Thread.sleep(2000);
	driver.findElement(By.cssSelector("div form div button[class*=reset]")).click();
	String pwdtext = driver.findElement(By.className("infoMsg")).getText();
	String[] password = pwdtext.split("'");
	
	return password[1];
	
}

}