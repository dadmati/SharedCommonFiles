package commonfunctions;

import java.util.concurrent.TimeUnit;

import javax.mail.Address;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.ini4j.Config;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.lang.String;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;

import com.aventstack.extentreports.*;

import com.aventstack.extentreports.ExtentReporter;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.ExtentXReporter;

import com.thoughtworks.selenium.webdriven.commands.Click;

//import WebstarUtility.WebstarConfigReader;
//import WebstarUtility.WebstarConfigReader;
//import WebstarUtility.WebstarExtentManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.seleniumhq.jetty7.util.log.Log;
import java.util.UUID;
import commonfunctions.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import oracle.jdbc.driver.OracleDriver;

/*
 * Test case name: Confirm SSBWF Email After Form
 *     Created by: Darin Admati
 *    Description: Validates that the email submitted from form appeared
 *     Data Input: Excel data inputs and hard coded data inputs
 * Variables used: Data submitted from Apply Script
 */

public class Confirm_Email {

	Folder inbox;
	
	// Sets path to configreader object repository
	//WebstarConfigReader config = new WebstarConfigReader();

	// Call extent report
	ExtentReports extent;
	ExtentTest test;
	String timeStamp = new SimpleDateFormat("MMddyyyy_HHmmss").format(Calendar.getInstance().getTime());

	

	private StringBuffer verificationErrors = new StringBuffer();
	
	@BeforeClass
	//public void setUp(String Environment) throws Exception {
    public void setUp() throws Exception {
		// Sets Extent reporting instance
		//extent = WebstarExtentManager.Instance();
		//extent = WebstarExtentManager.GetExtent();
		
		// Sets path to configreader object repository
		//WebstarConfigReader config = new WebstarConfigReader();

		
	}
	
	public Message Login_Access_Email() throws Exception {
		//FileInputStream file = new FileInputStream(new File(config.getExcelPathfromscript()));
		//HSSFWorkbook workbook = new HSSFWorkbook(file);

		//HSSFSheet worksheet = workbook.getSheetAt(0);
		
		
		System.out.println("Inside MailReader()...");
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        //test = extent.startTest("Confirm Webstar Payment Email " + timeStamp);
        test = extent.createTest("Confirm Webstar Payment Email " + timeStamp);
        		
        Properties props = System.getProperties();
        // Set manual Properties
        props.setProperty("mail.imaps.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.imaps.socketFactory.fallback", "false");
        props.setProperty("mail.imaps.port", "993");
        props.setProperty("mail.imaps.socketFactory.port", "993");
        props.put("mail.imaps.host", "imap-mail.outlook.com");


        try {
            /* Create the session and get the store for read the mail. */

            Session session = Session.getDefaultInstance(System.getProperties(), null);
            Store store = session.getStore("imaps");

            //store.connect("imap-mail.outlook.com", 993, "dadmati@nova.edu", "Welcome78");
            store.connect("outlook.office365.com", 143, "SMTPTEST@LIVEROOT.NOVA.EDU", "W5q*82.\".6889~6W");
            /* Mention the folder name which you want to read. */

            // inbox = store.getDefaultFolder();
            // inbox = inbox.getFolder("INBOX");
            inbox = store.getFolder("INBOX");

            /* Open the inbox using store. */

            inbox.open(Folder.READ_ONLY);

            Message messages[] = inbox.search(new FlagTerm(new Flags(
                    Flags.Flag.SEEN), false));
            //System.out.println("No. of Unread Messages : " + inbox.getFullName());
            
        	//String WFAUTHID = ExcelReader.ReadCellValue(worksheet, 1, 15);
			SearchTerm sTerm = new SubjectTerm("Thank you for your payment"); //this fails
            
            Message messages1[] = inbox.search(sTerm);  //no results found !
            
          
            /* Use a suitable FetchProfile */
            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);

            inbox.fetch(messages1, fp);
           
            
            try {

                printAllMessages(messages1);
                System.out.println("COUNT " + messages1);
                inbox.close(true);
                store.close();

            } catch (Exception ex) {
                System.out.println("Exception arise at the time of read mail");
                ex.printStackTrace();
            }

        } catch (MessagingException e) {
            System.out.println("Exception while connecting to server: " + e.getLocalizedMessage());
            e.printStackTrace();
            System.exit(2);
        }
		return null;

    }

    public void printAllMessages(Message[] msgs) throws Exception {
        for (int i = 0; i < msgs.length; i++) {
            System.out.println("MESSAGE #" + (i + 1) + ":");
            printEnvelope(msgs[i]);
        }
    }

    public void printEnvelope(Message message) throws Exception {
    	//FileInputStream file = new FileInputStream(new File(config.getExcelPathfromscript()));
		//HSSFWorkbook workbook = new HSSFWorkbook(file);

		//HSSFSheet worksheet = workbook.getSheetAt(0);
        Address[] a;

        // FROM
        if ((a = message.getFrom()) != null) {
            for (int j = 0; j < a.length; j++) {
                System.out.println("From : " + a[j].toString());
                
              test.info("Confirm that email from appears = " + a[j].toString());  
              Assert.assertEquals("bursar@nova.edu", a[j].toString());
              test.pass("Confirm that email from appears passed = " + a[j].toString()); 
            }
        }

        String subject = message.getSubject();
        //String WFAUTHID = ExcelReader.ReadCellValue(worksheet, 1, 15);
        
        
        test.info("Confirm that email subject appears = " + message.getSubject());  
        Assert.assertEquals("Thank you for your payment", message.getSubject());
        test.pass("Confirm that email subject appears passed = " + message.getSubject()); 
        
        //WebstarGetDateinJava.main
        SimpleDateFormat DtFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        System.out.println("DATE = "+DtFormat.format(date).toString());
        
        Date receivedDate = message.getReceivedDate();
        
        test.info("Confirm date received = "+receivedDate);  
        Assert.assertEquals(DtFormat.format(date).toString(), DtFormat.format(receivedDate).toString());
        test.pass("Confirm date received passed = "+receivedDate); 
        
        //

        //
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Subject: " + subject);
        
        

        if (receivedDate != null) {
            System.out.println("Received: " + df.format(receivedDate));
        }
        
        
        //System.out.println("Sent: " + df.format(sentDate));
    }


    //public static void main(String args[]) {
      //  new READEMAILBANNERSSWF();
   // }


	@AfterMethod

	// Captures test execution information to pass through in next step
	public void getResult(ITestResult result) throws IOException {

		// Captures result of test case
		if (result.getStatus() == ITestResult.FAILURE) {

			// Log failure in the event that a test step fails
			//test.log(LogStatus.FAIL, result.getThrowable());
			test.fail(result.getThrowable());
		}
	}

	@AfterClass

	public void tearDown() throws Exception {

		{
			// Closes out QA test execution reporting for test case
			//extent.endTest(test);
			extent.flush();
			//extent.close();

		}

		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
