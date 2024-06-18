package webSiteAuto.Assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UPSIAssignment extends UtilClass {

	// This method run the process of Login
	@Test
	public static void loginPage() {
		String email = "amit@parikhgroup.com";
		String password = "Sneha@123";
		String URI = "https://staging.digicompany.in/#/login";
		LoginPage LoginObj = new LoginPage();
		LoginObj.Login(email, password, URI);
	}

	// This method will navigate to page where we add UPSI details
	@Test(dependsOnMethods = { "loginPage" })
	public void UPSInavigation() {
		Navigate_UPSI_details navigation = new Navigate_UPSI_details();
		navigation.navigatingToUPSI();
	}

	// This method will add details to the UPSI form
	@Test(dependsOnMethods = { "UPSInavigation" })
	public static void Form() throws InterruptedException, IOException {

		// Fetching data from excel and executing test
		try {
			DataFormatter formatter = new DataFormatter();
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/test_data.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getCell(0) == null)
					break;
				String Sharer = formatter.formatCellValue(row.getCell(0));
				String SharerEmail = formatter.formatCellValue(row.getCell(1));
				String Recipient = formatter.formatCellValue(row.getCell(2));
				String RecipientEmail = formatter.formatCellValue(row.getCell(3));
				String UPSI_Nature = formatter.formatCellValue(row.getCell(4));
				String UPSI_Details = formatter.formatCellValue(row.getCell(5));
				String UPSI_Purpose = formatter.formatCellValue(row.getCell(6));
				String Sharing_Mode = formatter.formatCellValue(row.getCell(7));
				String Date_String = formatter.formatCellValue(row.getCell(8));
				String Time_String = formatter.formatCellValue(row.getCell(9));

				Add_UPSI_Form_Data(Sharer, SharerEmail, Recipient, RecipientEmail, UPSI_Nature, UPSI_Details, UPSI_Purpose, Sharing_Mode, Date_String, Time_String);
			}
		} catch (IOException e) {
			System.out.println("Excel File not found");
		}

	}

	public static void Add_UPSI_Form_Data(String Sharer, String SharerEmail, String Recipient, String RecipientEmail, String UPSI_Nature, String UPSI_Details, String UPSI_Purpose,
			String Sharing_Mode, String Date_String, String Time_String) throws IOException {

		// Wait and open UPSI form
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Add UPSI Details")));
		driver.findElement(By.linkText("Add UPSI Details")).click();

		// Adding Sharer user
		driver.findElement(By.xpath("//div[@class='md-radio']/input[@id='upsiSharer']/../label")).click();
		AddingSharerAndRecipient(Sharer, SharerEmail, true);

		// Adding Recipient user
		driver.findElement(By.xpath("//div[@class='md-radio']/input[@id='govevaUser1']/../label")).click();
		AddingSharerAndRecipient(Recipient, RecipientEmail, false);
		Select UPSI_Nature_dropdown = new Select(driver.findElement(By.id("NatureId")));
		UPSI_Nature_dropdown.selectByVisibleText(UPSI_Nature);

		// Adding textbox details
		driver.findElement(By.xpath("(//textarea[contains(@class, ng-pristine)])[1]")).sendKeys(UPSI_Details);
		driver.findElement(By.xpath("(//div/div/label[text()='Purpose for Sharing:']/../..//textarea)[1]")).sendKeys(UPSI_Purpose);
		driver.findElement(By.xpath("(//div/div/label[text()='Purpose for Sharing:']/../..//textarea)[2]")).sendKeys(Sharing_Mode);

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

		// Handling Time input based on data
		String[] time_temp = Time_String.split(":");
		String hour = time_temp[0];
		String minutes = time_temp[1];
		String AM_PM = "AM";
		if (Integer.parseInt(hour) > 12) {
			AM_PM = "PM";
			hour = String.valueOf(Integer.parseInt(hour) - 12);
		}
		driver.findElement(By.xpath("(//input[contains(@class,'timepicker-init')])[1]")).click();
		WebElement am_pm_selector = driver.findElement(By.xpath("//button[@title='Toggle Period']"));
		WebElement increment_hour = driver.findElement(By.xpath("//a[@title='Increment Hour']"));
		WebElement increment_minute = driver.findElement(By.xpath("//a[@title='Increment Minute']"));
		WebElement hour_text = driver.findElement(By.xpath("//span[@title='Pick Hour']"));
		WebElement minute_text = driver.findElement(By.xpath("//span[@title='Pick Minute']"));
		boolean loop = true;
		while (loop) {
			if (hour_text.getText().equals(hour) && minute_text.getText().equals(minutes) && am_pm_selector.getText().equals(AM_PM)) {
				loop = false;
			}
			if (!hour_text.getText().equals(hour)) {
				increment_hour.click();
			}
			if (!minute_text.getText().equals(minutes)) {
				increment_minute.click();
			}
			if (!am_pm_selector.getText().equals(AM_PM)) {
				am_pm_selector.click();
			}
		}

		// Click on Sucess button
		driver.findElement(By.xpath("(//textarea[contains(@class,form-control)])[6]")).click();
		driver.findElement(By.xpath("(//button[@class='btn btn-sm btn-primary'])[1]")).click();
		
		// Screenshot of Submission status
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='success-alert2']//h5[@class='modal-title' and text() = 'Success']")));
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "//screenshot//" + "snapshot" + ".png");
		FileUtils.copyFile(source, destination);
		
		// Assertion of success message
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='success-alert2']//div[@class='modal-body']//span")).getText(), "UPSI Detail is added successfully.");
		driver.findElement(By.xpath("//div[@id='success-alert2']//div[@class='modal-footer']//button")).click();

	}

	// Helper method to add user in Sharer or Recipient based on name and email
	public static void AddingSharerAndRecipient(String name, String email, Boolean sender) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ngb-modal-window[@role='dialog']")));
		if (sender) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='sharerNonGovevaUserList_filter']//input")));
			WebElement senderInput = driver.findElement(By.xpath("//div[@id='sharerNonGovevaUserList_filter']//input"));
			senderInput.clear();
			senderInput.sendKeys(email);
		} else {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='recipientNonGovevaUserList_filter']//input")));
			WebElement recipientInput = driver.findElement(By.xpath("//div[@id='recipientNonGovevaUserList_filter']//input"));
			recipientInput.clear();
			recipientInput.sendKeys(email);
		}
		wait.until(ExpectedConditions.textToBe(By.xpath("//tbody/tr[1]/td[2]"), name));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='md-checkbox']/label)[1]"))).click();
		driver.findElement(By.xpath("//ngb-modal-window//div[@class='modal-footer']/button[1]")).click();

	}

}
