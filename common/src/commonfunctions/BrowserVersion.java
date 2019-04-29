package commonfunctions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;



public class BrowserVersion {
private static WebDriver browserDriver;

public static String getBrowserAndVersion() {
String browser_version = null;
Capabilities cap = ((RemoteWebDriver) browserDriver).getCapabilities();
String browsername = cap.getBrowserName();
// This block to find out IE Version number
if ("internet explorer".equalsIgnoreCase(browsername)) {
String uAgent = (String) ((JavascriptExecutor) browserDriver).executeScript("return navigator.userAgent;");
System.out.println(uAgent);
//uAgent return as "MSIE 8.0 Windows" for IE8
if (uAgent.contains("MSIE") && uAgent.contains("Windows")) {
browser_version = uAgent.substring(uAgent.indexOf("MSIE")+5, uAgent.indexOf("Windows")-2);
} else if (uAgent.contains("Trident/7.0")) {
browser_version = "11.0";
} else {
browser_version = "0.0";
}
} else
{
//Browser version for Firefox and Chrome
browser_version = cap.getVersion();// .split(".")[0];
}
String browserversion = browser_version.substring(0, browser_version.indexOf("."));
return browsername + " " + browserversion;
}

public static String OSDetector () {
String os = System.getProperty("os.name").toLowerCase();
if (os.contains("win")) {
return "Windows";
} else if (os.contains("nux") || os.contains("nix")) {
return "Linux";
}else if (os.contains("mac")) {
return "Mac";
}else if (os.contains("sunos")) {
return "Solaris";
}else {
return "Other";
}
}
}
