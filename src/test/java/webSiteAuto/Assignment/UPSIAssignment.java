package webSiteAuto.Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


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
		String UPI_Details = "This is Financial Audit report";
		String UPI_Purpose = "For acknowledgement";
		String Sharing_Mode = "Shared via Email";
		String Date_String = "08-Dec-2022";
		String Time_String = "23:30";
		
		

		// Adding Sharer
//		driver.findElement(By.xpath("//div[@class='md-radio']/input[@id='upsiSharer']/../label")).click();
//		AddingSharerAndRecipient(Sharer, SharerEmail, true);


		// Adding Recipient
//		driver.findElement(By.xpath("//div[@class='md-radio']/input[@id='govevaUser1']/../label")).click();
//		AddingSharerAndRecipient(Recipient, RecipientEmail, false);	
//		Select UPSI_Nature_dropdown = new Select(driver.findElement(By.id("NatureId")));
//		UPSI_Nature_dropdown.selectByVisibleText(UPSI_Nature);
		
		//Adding textbox details
		driver.findElement(By.xpath("(//textarea[contains(@class, ng-pristine)])[1]")).sendKeys(UPI_Details);
		driver.findElement(By.xpath("(//textarea[contains(@class, ng-pristine)])[2]")).sendKeys(UPI_Purpose);
		driver.findElement(By.xpath("(//div//textarea[contains(@class, ng-pristine)])[3]")).sendKeys(Sharing_Mode);
		
		// Handling Calender Dates
		String[] date_temp = Date_String.split("-");
		int date = Integer.parseInt(date_temp[0]);
		String month = date_temp[1];
		String year = date_temp[2];
		driver.findElement(By.xpath("//input[@name='sharerDate']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ngb-datepicker")));
		Select Year_Dropdown = new Select(driver.findElement(By.xpath("//ngb-datepicker-navigation-select/select[2]")));
		Select Month_Dropdown = new Select(driver.findElement(By.xpath("//ngb-datepicker-navigation-select/select[1]")));
		Year_Dropdown.selectByVisibleText(year);
		Month_Dropdown.selectByVisibleText(month);
		String date_select_xpath = "//ngb-datepicker//div[@class='btn-light' and text()='" + date + "']";
		driver.findElement(By.xpath(date_select_xpath)).click();
		
		// Handling Time input
		String[] time_temp = Time_String.split(":");
		String hour = time_temp[0];
		String minutes = time_temp[1];
		String AM_PM = "AM";
		if(Integer.parseInt(hour)>12) {
			AM_PM = "PM";
			hour = String.valueOf(Integer.parseInt(hour)-12);
		}
		driver.findElement(By.xpath("(//input[contains(@class,'timepicker-init')])[1]")).click();
		
		WebElement am_pm_selector = driver.findElement(By.xpath("//button[@title='Toggle Period']"));
		WebElement increment_hour = driver.findElement(By.xpath("//a[@title='Increment Hour']"));
		WebElement increment_minute = driver.findElement(By.xpath("//a[@title='Increment Minute']"));
		WebElement hour_text = driver.findElement(By.xpath("//span[@title='Pick Hour']"));
		WebElement minute_text = driver.findElement(By.xpath("//span[@title='Pick Minute']"));
		boolean loop = true;
		while (loop) {
			if(hour_text.getText().equals(hour) && minute_text.getText().equals(minutes) && am_pm_selector.getText().equals(AM_PM)) {
				loop = false;
			}
			if(!hour_text.getText().equals(hour)) {
				increment_hour.click();
			}
			if(!minute_text.getText().equals(minutes)) {
				increment_minute.click();
			}
			if(!am_pm_selector.getText().equals(AM_PM)) {
				am_pm_selector.click();
			}
		}
		
		
		

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
		
	}
	

}
