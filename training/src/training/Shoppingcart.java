package training;

import java.time.Duration;
import java.time.Month;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Shoppingcart {

	public static void main(String[] args) throws InterruptedException {

		// String[] x = "Tomato - 1 Kg".split(" - ");
		// System.out.println(x[0]);

		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://rahulshettyacademy.com/seleniumPractise");
		String[] toBeAdded = { "Cucumber", "Brinjal", "Musk Melon", "Mango" };
		Thread.sleep(1000);
		// AddToBasket(driver, toBeAdded);
		Shoppingcart cart = new Shoppingcart();
		//cart.addToBasket(driver, toBeAdded);
		//cart.checkOut(driver);
		//cart.calendarCheck(driver);
		cart.calendarUpdate(driver);

		// driver.close();
	}

	public void calendarUpdate(WebDriver driver) {
		String monthNumber = "8";
		String date = "31";
		String year = "2027";

		String[] expectedList = { monthNumber, date, year };
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.findElement(By.cssSelector(".react-date-picker__inputGroup")).click();
		driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
		driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
		driver.findElement(By.xpath("//button[text()='" + year + "']")).click();
		driver.findElements(By.cssSelector(".react-calendar__year-view__months__month"))
				.get(Integer.parseInt(monthNumber) - 1).click();

		driver.findElement(By.xpath("//abbr[text()='" + date + "']")).click();
		List<WebElement> actualList = driver.findElements(By.cssSelector(".react-date-picker__inputGroup__input"));

		for (int i = 0; i < actualList.size(); i++)

		{

			System.out.println(actualList.get(i).getAttribute("value"));

			Assert.assertEquals(actualList.get(i).getAttribute("value"), expectedList[i]);
		}
	}

	public void calendarCheck(WebDriver driver) {

		// driver.navigate().back();
		driver.findElement(By.linkText("Top Deals")).click();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);

		String monthNumber = "08";
		String date = "31";
		String year = "2027";

		driver.findElement(By.cssSelector("button.react-date-picker__calendar-button.react-date-picker__button"))
				.click();
		// WebElement monthAndYear =
		// driver.findElement(By.cssSelector("span.react-calendar__navigation__label__labelText"));

		int selectedYear = Integer
				.parseInt(driver.findElement(By.cssSelector("span.react-calendar__navigation__label__labelText"))
						.getText().split(" ")[1]);
		// int selectedDate =
		// Integer.parseInt(driver.findElement(By.cssSelector("span.react-calendar__navigation__label__labelText")).getText());
		while (selectedYear != Integer.parseInt(year)) {
			if (selectedYear > Integer.parseInt(year)) {
				driver.findElement(By.cssSelector(
						"button.react-calendar__navigation__arrow.react-calendar__navigation__prev2-button")).click();
				selectedYear -= 1;
			}
			if (selectedYear < Integer.parseInt(year)) {
				driver.findElement(By.cssSelector(
						"button.react-calendar__navigation__arrow.react-calendar__navigation__next2-button")).click();
				selectedYear += 1;
				// System.out.println(selectedYear +" " + year);

			}
		}

		// String monthNumber = "6";
		// Month month = Month.of(Integer.parseInt(monthNumber));

		int selectedMonth = 0;
		String currentMonth = driver.findElement(By.cssSelector("span.react-calendar__navigation__label__labelText"))
				.getText().split(" ")[0];
		// Month selectedMonth = currentMonth.;
		try {
			Month month = Month.valueOf(currentMonth.toUpperCase(Locale.ENGLISH));
			selectedMonth = month.getValue();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid month name: " + currentMonth, e);
		}

		while (Integer.parseInt(monthNumber) != selectedMonth) {
			if (Integer.parseInt(monthNumber) > selectedMonth) {
				driver.findElement(By.cssSelector(
						"button.react-calendar__navigation__arrow.react-calendar__navigation__next-button")).click();
				selectedMonth++;

			}
			if (Integer.parseInt(monthNumber) < selectedMonth) {
				driver.findElement(By.cssSelector(
						"button.react-calendar__navigation__arrow.react-calendar__navigation__prev-button")).click();
				selectedMonth--;

			}
		}

		// date = //div[@class='react-calendar__month-view__days']/button[11]
		String x = Month.of(Integer.parseInt(monthNumber)).name();
		x = x.substring(0, 1) + x.substring(1).toLowerCase();

		driver.findElement(By.xpath("//button/abbr[@aria-label='" + x + " " + date + ", " + year + "']")).click();

		// System.out.println(
		// driver.findElement(By.cssSelector("input[name=date]")).getAttribute("value"));
		String selectedDay = driver.findElement(By.cssSelector("input[name=date]")).getAttribute("value");
		Assert.assertEquals(selectedDay, year + "-" + monthNumber + "-" + date,
				"You have successfully selected the correct date" + selectedDay);

	}

	public void addToBasket(WebDriver driver, String[] toBeAdded) throws InterruptedException {

		driver.findElement(By.xpath("//h4[text()='Beetroot - 1 Kg']/parent:: div/div/button")).click();

		driver.findElement(By.xpath("//h4[text()='Tomato - 1 Kg']/parent:: div/div/button")).click();

		List<WebElement> items = driver.findElements(By.cssSelector("h4.product-name"));
		for (WebElement item : items) {

			List<String> groceriesList = Arrays.asList(toBeAdded);
			// System.out.println(groceriesList.contains(item.getText()));
			// System.out.print(item.getText() +
			// item.getText().contains(groceriesList.get(0)));
			String[] name = item.getText().split(" - ");

			if (groceriesList.contains(name[0])) {
				int i = items.indexOf(item) + 1;
				System.out.println(item.getText() + " " + items.indexOf(item));
				driver.findElement(By.xpath("//div[@class='products']/div[@class='product'][" + i + "]/div/button"))
						.click();

			}

		}

	}

	public void checkOut(WebDriver driver) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("//a[@class='cart-icon']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));

		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("button.promoBtn")).click();

		By promoInfo = By.cssSelector("span.promoInfo");

		wait.until(ExpectedConditions.visibilityOfElementLocated(promoInfo));

		String promoinfo = driver.findElement(By.cssSelector("span.promoInfo")).getText();
		System.out.println(promoinfo);

	}
}
