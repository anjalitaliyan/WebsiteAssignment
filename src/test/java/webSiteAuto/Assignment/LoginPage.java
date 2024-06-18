package webSiteAuto.Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends UtilClass {

	public void Login(String email, String password, String URI) {

		// Login Process
		UtilClass util = new UtilClass();
		util.LaunchingApplication();
		driver.get(URI);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//div[@aria-haspopup='listbox']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@ng-reflect-ng-item-label='DigiCompany']")));
		driver.findElement(By.xpath("//span[@ng-reflect-ng-item-label='DigiCompany']")).click();
		driver.findElement(By.xpath("//*[@name='Password']")).sendKeys(password);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[contains(@class,btn-primary)])[1]"))).click();
	}

}
