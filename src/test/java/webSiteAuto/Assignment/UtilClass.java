package webSiteAuto.Assignment;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UtilClass {
	public static WebDriverWait wait;
	public static WebDriver driver;
	
@BeforeMethod
	public void LaunchingApplication() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://staging.digicompany.in/#/login");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(7));

	}
}
