package commonfunctions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;

public class GetScreenshot 
{
public static String capture(WebDriver driver, String screenshotName) throws IOException
{
	
	TakesScreenshot ts = (TakesScreenshot)driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	//System.out.println("printing source"+source.getAbsolutePath());
	
	//new
	String timeStamp = new SimpleDateFormat("MMddyyyy_HHmmss").format(Calendar.getInstance().getTime());
	//new
	String dest = "\\Selenium\\common\\screenshots\\"+screenshotName+timeStamp+".png";
	//String dest = "\\10.221.2.96\\s\\screenshots\\"+screenshotName+timeStamp+".png";
	//System.out.println("printing destination"+dest);
	File destination = new File(dest);
	FileUtils.copyFile(source, destination);
	return dest;
}
}


