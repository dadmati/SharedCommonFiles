package commonfunctions;

import java.util.concurrent.TimeUnit;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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

//import Utility.BWConfigReader;
//import Utility.BWExtentManager;
//import Utility.BWExtentManagerBACKUP;
//import IDMSUtility.IDMSConfigReader;
//import IDMSUtility.IDMSExtentManager;
//import Utility.MKTConfigReader;
//import Utility.MKTExtentManager;
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
import oracle.jdbc.driver.OracleDriver;

/*
 * Test case name: IDMS SQL Query Validation CDC table
 *     Created by: Darin Admati
 *    Description: Validates that the email submitted from Apply Site is appearing in the CDC.GENERAL_GOREMAL_CT table and records the PIDM
 *     Data Input: Excel data inputs and hard coded data inputs
 * Variables used: Data submitted from Apply Script
 */

public class DeleteDBOracle {

	// Sets path to configreader object repository
	//BWConfigReader config = new BWConfigReader();

	// Call extent report
	ExtentReports extent;
	ExtentTest test;
	String timeStamp = new SimpleDateFormat("MMddyyyy_HHmmss").format(Calendar.getInstance().getTime());

	// DB Connection strings
	// SQL Server string values to use in test case

	// SQL query to run NEED TO GET THE DB
	// String queryString = "select * from guruobj where
	// GURUOBJ_USERID='DADMATI'";
	String queryString1 = "DELETE FROM SHBDIPL WHERE SHBDIPL_PIDM IN (SELECT spriden_pidm FROM spriden WHERE spriden_id IN ('N01848285', 'N01769649','N01108009', 'N00211129','N01672999') AND spriden_change_ind IS NULL)";

	String queryString2 = "DELETE FROM SHRTMCM WHERE SHRTMCM_PIDM IN (SELECT spriden_pidm FROM spriden WHERE spriden_id IN ('N01848285','N01769649','N01108009','N00211129','N01672999') AND spriden_change_ind IS NULL)";

	String queryString3 = "DELETE FROM SHRDGIH WHERE SHRDGIH_PIDM IN (SELECT spriden_pidm FROM spriden WHERE spriden_id IN ('N01848285','N01769649','N01108009','N00211129','N01672999') AND spriden_change_ind IS NULL)";

	String queryString4 = "DELETE FROM SHRTRCD WHERE SHRTRCD_PIDM IN (SELECT spriden_pidm FROM spriden WHERE spriden_id IN ('N01848285', 'N01769649','N01108009','N00211129','N01672999') AND spriden_change_ind IS NULL)";

	String queryString5 = "DELETE FROM SHRDGMR WHERE SHRDGMR_PIDM IN (SELECT spriden_pidm FROM spriden WHERE spriden_id IN ('N01848285', 'N01769649','N01108009', 'N00211129','N01672999') AND spriden_change_ind IS NULL)";

	String queryString6 = "DELETE FROM SPRCMNT WHERE SPRCMNT_PIDM IN (SELECT spriden_pidm FROM spriden WHERE spriden_id IN ('N01848285', 'N01769649','N01108009', 'N00211129','N01672999') AND spriden_change_ind IS NULL)";

	String queryString7 = "UPDATE SORLCUR SET SORLCUR_ROLLED_SEQNO = NULL WHERE SORLCUR_PIDM IN (SELECT spriden_pidm FROM spriden WHERE spriden_id IN ('N01848285','N01769649','N01108009','N00211129','N01672999') AND spriden_change_ind IS NULL)";

	String queryString8 = "UPDATE SORLCUR SET SORLCUR_GAPP_SEQNO = NULL WHERE SORLCUR_PIDM IN (SELECT spriden_pidm FROM spriden WHERE spriden_id IN ('N01848285',	'N01769649', 'N01108009','N00211129', 'N01672999') AND spriden_change_ind IS NULL)";

	String queryString9 = "COMMIT";
	// Connection string
	// String ConString =
	// "jdbc:oracle:thin:dadmati/Newuserpass_123@//fldvd-oran1.oit.nova.edu:1521/BANTEST";

	// Connection string
	//String ConString = "jdbc:oracle:thin:" + config.getQAUSERNAMEoracle() + "/" + config.getQAPASSWORDoracle() + "@"
	//		+ "//fldvd-oran1.oit.nova.edu:1521/BANTEST";

	/*
	 * String ConString =
	 * "jdbc:sqlserver://FLDVD-SQLIDMS01.ad.nova.edu\\IDMS:1776;databaseName=IDMS;user="
	 * + config.getQAUSERNAME() + ";" + "password=" + config.getQAPASSWORD();
	 */

	private StringBuffer verificationErrors = new StringBuffer();
	

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:MKYONG";
	private static final String DB_USER = "user";
	private static final String DB_PASSWORD = "password";

	// @Parameters({ "Environment" })
	@BeforeClass
	// public void setUp(String Environment) throws Exception {
	public static void main(String[] argv) {

		try {

			deleteRecordFromDbUserTable();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

	}

	private static void deleteRecordFromDbUserTable() throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;

		String deleteTableSQL = "DELETE DBUSER WHERE USER_ID = 1";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(deleteTableSQL);

			// execute delete SQL stetement
			statement.execute(deleteTableSQL);

			System.out.println("Record is deleted from DBUSER table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}

	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
                             DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}



	@AfterMethod

	// Captures test execution information to pass through in next step
	public void getResult(ITestResult result) throws IOException {

		// Captures result of test case
		if (result.getStatus() == ITestResult.FAILURE) {

			// Log failure in the event that a test step fails
			test.fail(result.getThrowable());

		}
	}

	@AfterClass

	public void tearDown() throws Exception {

		{
			// Closes out QA test execution reporting for test case
			// extent.endTest(test);
			extent.flush();
			// extent.close();

		}

		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
