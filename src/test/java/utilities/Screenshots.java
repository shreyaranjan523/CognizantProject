package utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
public class Screenshots{
 
 
	public void takeScreenshots(WebDriver driver, String ssname) throws IOException {
		String directoryPath = System.getProperty("user.dir");
		String relativePath = "/Screenshots/"+ssname+".png";
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(directoryPath+relativePath);
		FileUtils.copyFile(src, dest);
	}
}