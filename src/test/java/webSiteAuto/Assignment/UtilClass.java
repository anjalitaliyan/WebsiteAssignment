package webSiteAuto.Assignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UtilClass
{
   public String url="https://staging.digicompany.in/#/login";
	@BeforeTest
	public void LandingPage()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}
	
}
