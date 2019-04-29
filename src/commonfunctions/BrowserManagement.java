package commonfunctions;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserManagement {
	// @Test

	public WebDriver driver;

	/*
	 * Enter information about class: This class will select the browser and OS
	 * 
	 */
	public BrowserManagement(WebDriver driver) {
		this.driver = driver;
	}

	@BeforeClass
	@Parameters({ "Environment", "Browser", "OS" })
	@Test

	// Make sure to name your test below
	public void ChooseBrowser(String Browser, String OS) throws Exception {
		System.out.print("Browser passed through is" + Browser);
		if (Browser.equalsIgnoreCase("Chrome")) {

			DesiredCapabilities Capabilities = DesiredCapabilities.chrome();
			Capabilities.setCapability("acceptSslCerts", "true");

			if (OS.equalsIgnoreCase("Win7")) {
				Capabilities.setPlatform(Platform.VISTA);
			} else if (OS.equalsIgnoreCase("Win81")) {
				Capabilities.setPlatform(Platform.WIN8_1);
			} else if (OS.equalsIgnoreCase("Win10")) {
				Capabilities.setPlatform(Platform.WIN10);
			} else if (OS.equalsIgnoreCase("Win")) {
				Capabilities.setPlatform(Platform.WINDOWS);
			} else {
			}

			driver = new RemoteWebDriver(new URL("http://10.221.2.96:4444/wd/hub"), Capabilities);

		} else if (Browser.equalsIgnoreCase("IE")) {

			// Internet Explorer
			DesiredCapabilities Capabilities = DesiredCapabilities.internetExplorer();
			Capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			Capabilities.setCapability("ensureCleanSession", true);
			Capabilities.setCapability("ignoreZoomSetting", true);
			Capabilities.setCapability("ignoreProtectedModeSettings", true);
			Capabilities.setCapability("ignore-certificate-error", true);
			Capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			if (OS.equalsIgnoreCase("Win7")) {
				Capabilities.setPlatform(Platform.VISTA);
			} else if (OS.equalsIgnoreCase("Win81")) {
				Capabilities.setPlatform(Platform.WIN8_1);
			} else if (OS.equalsIgnoreCase("Win10")) {
				Capabilities.setPlatform(Platform.WIN10);
			} else {
			}

			driver = new RemoteWebDriver(new URL("http://10.221.2.96:4444/wd/hub"), Capabilities);

		} else if (Browser.equalsIgnoreCase("FF")) {

			// Firefox
			DesiredCapabilities Capabilities = DesiredCapabilities.firefox();
			Capabilities.setCapability("marionette", true);

			if (OS.equalsIgnoreCase("Win7")) {
				Capabilities.setPlatform(Platform.VISTA);
			} else if (OS.equalsIgnoreCase("Win81")) {
				Capabilities.setPlatform(Platform.WIN8_1);
			} else if (OS.equalsIgnoreCase("Win10")) {
				Capabilities.setPlatform(Platform.WIN10);
			} else {
			}
			driver = new RemoteWebDriver(new URL("http://10.221.2.96:4444/wd/hub"), Capabilities);

			// System.setProperty("webdriver.gecko.driver",
			// config.getFFDriver());
			// driver = new FirefoxDriver();
		} else if (Browser.equalsIgnoreCase("Local")) {
			// Chrome
			/*
			 * ChromeOptions opts = new ChromeOptions();
			 * opts.addArguments("start-maximized");
			 * 
			 * //ChromeOptions options = new ChromeOptions();
			 * opts.addArguments("auto-open-devtools-for-tabs", "true");
			 */

	/*		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\webdrivers\\chromedriver.exe");

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

			ChromeOptions opts = new ChromeOptions();
			opts.addArguments("start-maximized");
			opts.addArguments("enable-automation");
			// opts.addArguments("--headless");
			// opts.addArguments("--window-size=1920,1080");
			opts.addArguments("--window-size=2102,708");
			opts.addArguments("--no-sandbox");
			opts.addArguments("--disable-extensions");
			opts.addArguments("--dns-prefetch-disable");
			opts.addArguments("--disable-gpu");
			// opts.setPageLoadStrategy(PageLoadStrategy.NORMAL);

			capabilities.setCapability(ChromeOptions.CAPABILITY, opts);
			driver = new ChromeDriver(capabilities);
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);*/
			
			
			//Works
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\webdrivers\\chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);		
			ChromeOptions chromeOptions = new ChromeOptions();
			driver = new ChromeDriver();


		} else if (Browser.equalsIgnoreCase("LocalIE")) {
			// IE
			System.setProperty("webdriver.ie.driver", "C:\\Selenium\\webdrivers\\IEDriverServer.exe");
			DesiredCapabilities Capabilities = DesiredCapabilities.internetExplorer();
			Capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			Capabilities.setCapability("ensureCleanSession", true);
			Capabilities.setCapability("ignoreZoomSetting", true);
			Capabilities.setCapability("ignoreProtectedModeSettings", true);
			Capabilities.setCapability("ignore-certificate-error", true);
			Capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new InternetExplorerDriver(Capabilities);

		} else if (Browser.equalsIgnoreCase("LocalFF")) {
			// Chrome
			/*
			 * System.setProperty("geckodriver.chrome.driver",
			 * "C:\\Selenium\\webdrivers\\geckodriver.exe"); DesiredCapabilities
			 * capabilities = DesiredCapabilities.firefox();
			 * capabilities.setCapability(CapabilityType.ForSeleniumServer.
			 * ENSURING_CLEAN_SESSION, true); WebDriver driver = new
			 * FirefoxDriver(); driver.manage().deleteAllCookies();
			 */
			// driver.get("http://www.google.com");
			System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\webdrivers\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"); // Location
																						// where
																						// Firefox
																						// is
																						// installed

			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("moz:firefoxOptions", options);
			capabilities.setCapability("firefox_binary", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");

			// set more capabilities as per your requirements

			FirefoxDriver driver = new FirefoxDriver(capabilities);
			driver.get("http://www.google.com");

		} else if (Browser.equalsIgnoreCase("mobile")) {
	
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\webdrivers\\chromedriver.exe");

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

			Map<String, String> mobileEmulation = new HashMap<>();
			mobileEmulation.put("deviceName", "iPhone 8");

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

			driver = new ChromeDriver(chromeOptions);

		} else {

		}

	}

}
