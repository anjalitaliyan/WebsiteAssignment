package webSiteAuto.Assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UPSIAssignment {
	
	
	
	@Test
	public void LoginMethod() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://staging.digicompany.in/#/login");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("amit@parikhgroup.com");
		driver.findElement(By.xpath("//div[@aria-haspopup='listbox']")).click();	
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(7));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@ng-reflect-ng-item-label='DigiCompany']")));
		driver.findElement(By.xpath("//span[@ng-reflect-ng-item-label='DigiCompany']")).click();
		driver.findElement(By.xpath("//*[@name='Password']")).sendKeys("Sneha@123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[contains(@class,btn-primary)])[1]")).click();
		
		//HomePage
		Thread.sleep(7000);
		driver.findElement(By.xpath("//i[contains(@class,'openbtn')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("UPSI")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("UPSI Details")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Add UPSI Details")).click();
		WebElement text=driver.findElement(By.xpath("(//textarea[contains(@class,'ng-pristine')])[1]"));
		text.sendKeys("Entered all the details");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@id='govevaUser1']")).click();
//		Thread.sleep(3000);
		
		WebElement drop=driver.findElement(By.id("NatureId"));
		Select cs=new Select(drop);
		Thread.sleep(3000);
		cs.selectByIndex(2);
		
		
		Thread.sleep(2000);
		
		WebElement secondtext=driver.findElement(By.xpath("(//textarea[contains(@class,'form-control')])[2]"));
		secondtext.sendKeys("filling this form by automation");
		
		WebElement thirdbox=driver.findElement(By.xpath("(//textarea[contains(@class,'form-control')])[4]"));
		thirdbox.sendKeys("Automation testScript");
		WebElement fourthbox=driver.findElement(By.xpath("(//textarea[contains(@class,'form-control')])[5]"));
		fourthbox.sendKeys("Automation is a mode");
		Thread.sleep(2000);

		driver.findElement(By.xpath("(//span[contains(@class,'fa-calendar')])[1]")).click();
		
		
		Thread.sleep(2000);
		driver.close();
		
	}

}
