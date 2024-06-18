package webSiteAuto.Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Navigate_UPSI_details extends UtilClass {

	public void navigatingToUPSI() {
		// Navigating to UPSI details from Homepage
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[contains(@class,'openbtn')]"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("UPSI"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("UPSI Details"))).click();

	}

}
