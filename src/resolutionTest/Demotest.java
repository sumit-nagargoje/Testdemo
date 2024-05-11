package resolutionTest;

import java.io.File;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

public class Demotest {

	public static void main(String[] args) throws Exception {


		System.setProperty("webdriver.chrome.driver","C:\\Users\\nagar\\Desktop\\software\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com/");
		
		
		//To set the browser window size to a specific width height(resolution)
		
		driver.manage().window().setSize(new Dimension(1980, 1080));
		
		File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(f,new File("C:\\Users\\nagar\\Downloads\\Sample\\ebayscreenshot.png"));
}
}
 