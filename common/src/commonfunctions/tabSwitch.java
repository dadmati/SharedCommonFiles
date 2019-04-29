package commonfunctions;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class tabSwitch {
	// @Test

	WebDriver driver;

	/*
	Enter information about class:
	This class will click on the go button for every Banner 9 page.

	 */
	public tabSwitch(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = ".//*[@class='ui-button-text'][contains(text(),'Go')]")
	@CacheLookup
	WebElement clickGo;


	
	
	public void switchTab() throws Exception{
		
		
		//Get the current window handle
		String windowHandle = driver.getWindowHandle();

		//Get the list of window handles
		@SuppressWarnings("rawtypes")
		ArrayList tabs = new ArrayList (driver.getWindowHandles());
		System.out.println(tabs.size());
		//Use the list of window handles to switch between windows
		driver.switchTo().window((String) tabs.get(0));

		//Switch back to original window
		driver.switchTo().window(windowHandle);
	}
	
	
	
	
	

}
