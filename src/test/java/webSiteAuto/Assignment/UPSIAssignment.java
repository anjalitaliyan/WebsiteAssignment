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

public class UPSIAssignment extends UtilClass
{

	@Test
	public static void LoginMethod() throws InterruptedException {
		
		String email = "amit@parikhgroup.com";
		String password = "Sneha@123";

		// Login Process
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//div[@aria-haspopup='listbox']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@ng-reflect-ng-item-label='DigiCompany']")));
		driver.findElement(By.xpath("//span[@ng-reflect-ng-item-label='DigiCompany']")).click();
		driver.findElement(By.xpath("//*[@name='Password']")).sendKeys(password);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[contains(@class,btn-primary)])[1]"))).click();

		// Navigating to UPSI details from Homepage
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[contains(@class,'openbtn')]"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("UPSI"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("UPSI Details"))).click();

		// Adding details to UPSI
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Add UPSI Details")));
		driver.findElement(By.linkText("Add UPSI Details")).click();

		String Sharer = "Saish Khandare";
		String SharerEmail = "saish.khandare@harriersys.com";
		String Recipient = "Mr. Parikh sec";
		String RecipientEmail = "test@test.in";
		String UPSI_Nature = "Financial Audit";
		

		// Adding Sharer
		driver.findElement(By.xpath("//div[@class='md-radio']/input[@id='upsiSharer']/../label")).click();
		AddingSharerAndRecipient(Sharer, SharerEmail, true);


		// Adding Recipient
		driver.findElement(By.xpath("//div[@class='md-radio']/input[@id='govevaUser1']/../label")).click();
		AddingSharerAndRecipient(Recipient, RecipientEmail, false);	
		Select UPSI_Nature_dropdown = new Select(driver.findElement(By.id("NatureId")));
		UPSI_Nature_dropdown.selectByVisibleText(UPSI_Nature);

	}
	
	public static void AddingSharerAndRecipient(String name, String email, Boolean sender) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ngb-modal-window[@role='dialog']")));
		if (sender) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='sharerNonGovevaUserList_filter']//input"))).sendKeys(email);
		} else {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='recipientNonGovevaUserList_filter']//input"))).sendKeys(email);
		}
		wait.until(ExpectedConditions.textToBe(By.xpath("//tbody/tr[1]/td[2]"), name));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='md-checkbox']/label)[1]"))).click();
		driver.findElement(By.xpath("//ngb-modal-window//div[@class='modal-footer']/button[1]")).click();
		
		//calling placeholder method
		Placeholder();
	}
	
	public static void Placeholder() 
	{
		String Placeholder1="Adding details in first placeholder";
		String Placeholder2="Adding details in second placeholder";
		String Placeholder3="Adding details in third placeholder";
		driver.findElement(By.xpath("(//textarea[contains(@class, ng-pristine)])[1]")).sendKeys(Placeholder1);
		driver.findElement(By.xpath("(//textarea[contains(@class, ng-pristine)])[2]")).sendKeys(Placeholder2);
		driver.findElement(By.xpath("(//textarea[contains(@class, form-control)])[5]")).sendKeys(Placeholder3);
	}

}
