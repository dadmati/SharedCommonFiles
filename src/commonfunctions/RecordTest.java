package commonfunctions;

import java.text.DateFormat; 
import java.text.SimpleDateFormat; 
import java.util.Date; 
import java.util.concurrent.TimeUnit; 
import org.openqa.selenium.Dimension; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.firefox.FirefoxDriver; 
import org.testng.annotations.AfterTest; 
import org.testng.annotations.BeforeTest; 
import org.testng.annotations.Test; 
import atu.testrecorder.ATUTestRecorder;

public  class  RecordTest  
{    

WebDriver  driver;    
ATUTestRecorder  recorder;    
@BeforeTest    
public  void  setup()  throws  Exception  
{  
	DateFormat  dateFormat  =  new  SimpleDateFormat("yy-MM-dd  HH-mm-ss");  
	Date  date  =  new  Date();  //Created  object  of  ATUTestRecorder  
	//Provide  path  to  store  videos  and  file  name  format.  
	recorder  =  new  ATUTestRecorder("D:\\ScriptVideos\\","TestVideo-"+dateFormat.format(date),false); 
	//To  start  video  recording.  
	recorder.start();     
	driver  =  new  FirefoxDriver();  
	driver.manage().window().maximize(); 
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
	driver.get("http://google.com/");   
	}  


@Test    
public  void  getScrollStatus()  throws  Exception  
{      
	driver.manage().window().setSize(new Dimension(400,768));  
	Thread.sleep(2000);     
	driver.manage().window().setSize(new Dimension(400,400));  
	Thread.sleep(2000); 
	driver.manage().window().setSize(new Dimension(1024,400));          
	}    
@AfterTest    
public  void  Close()  throws  Exception  
{  driver.quit(); 
//To  stop  video  recording.  
recorder.stop();;   
}  
} 
