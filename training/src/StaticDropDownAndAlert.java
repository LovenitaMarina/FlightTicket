import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class StaticDropDownAndAlert {

	public static void main(String[] args) throws AssertionError, InterruptedException {
		// TODO Auto-generated method stub
		// ChromeOptions options = new ChromeOptions();
		// options.addArguments("--remote-allow-origins=*");
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.manage().window().maximize();

		WebElement staticdropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));

		Select dropdown = new Select(staticdropdown);

		dropdown.selectByIndex(3);

		System.out.println(dropdown.getFirstSelectedOption().getText());
		Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "USD");

		/*
		 * for(int i = 0 ; i< dropdown.getOptions().size();i++) {
		 * dropdown.selectByIndex(i);
		 * System.out.println(dropdown.getAllSelectedOptions().size());
		 * System.out.println(dropdown.getFirstSelectedOption().getText());
		 * 
		 * }
		 */

		dropdown.selectByVisibleText("AED");
		System.out.println(dropdown.getFirstSelectedOption().getText());

		dropdown.selectByValue("INR");
		System.out.println(dropdown.getFirstSelectedOption().getText());

		try {
			Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "USD");
		} catch (AssertionError e) {
			System.out.println("The currency selected is " + dropdown.getFirstSelectedOption().getText());
		}

		driver.findElement(By.id("autosuggest")).sendKeys("Bah");

		Thread.sleep(2000);
		List<WebElement> options = driver.findElements(By.cssSelector("a.ui-corner-all"));
		System.out.println(options.size());

		for (WebElement option : options) {
			System.out.println(option.getText());

			if (option.getText().equalsIgnoreCase("Bahrain")) {
				option.click();

				break;

			}

		}

		driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice/");

		WebElement checkbox1 = driver.findElement(By.xpath("//fieldset/label[1]/input[@type='checkbox']"));

		checkbox1.click();
		Assert.assertTrue(checkbox1.isSelected());

		Thread.sleep(5000);
		checkbox1.click();
		Assert.assertFalse(checkbox1.isSelected());

		List<WebElement> checkbox = driver.findElements(By.xpath("//input[@type='checkbox']"));

		System.out.println("The number of check boxes in the page = " + checkbox.size());

		String text = "Wohooo";
		driver.findElement(By.id("name")).sendKeys(text);
		driver.findElement(By.id("alertbtn")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();

		driver.findElement(By.id("confirmbtn")).click();

		driver.switchTo().alert().dismiss();

		// driver.navigate().to("https://rahulshettyacademy.com/angularpractice/");
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.findElement(By.xpath("//div[@class='form-group']/input[@name='name']")).sendKeys("Rahul");
		driver.findElement(By.cssSelector("input[name='email']")).sendKeys("abc.com");
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("ABC123");
		driver.findElement(By.id("exampleCheck1")).click();

		WebElement gender = driver.findElement(By.cssSelector("#exampleFormControlSelect1"));

		Select genders = new Select(gender);

		genders.selectByVisibleText("Female");
		driver.findElement(By.id("inlineRadio1")).click();
		driver.findElement(By.cssSelector("input[name='bday']")).sendKeys("21/09/2024");
		driver.findElement(By.cssSelector(".btn.btn-success")).click();

		System.out.println(driver.findElement(By.className("strong")).getText());

		driver.quit();
	}

}
