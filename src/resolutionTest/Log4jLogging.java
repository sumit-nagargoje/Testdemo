package resolutionTest;

import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Log4jLogging {

	public static void main(String[] args) {
		
		//create logger instance
		//Logger logger = Logger.getLogger("Log4JLogging");
		
		//configure log4j.properties file
		//PropertyConfigurator.configure("C:\\Users\\nagar\\eclipse-workspace\\Automation_Test\\log4j.properties");
 
		//Open Browser Instance
		System.setProperty("webdriver.chrome.driver","C:\\Users\\nagar\\Desktop\\software\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.ebay.com/");
		//logger.info("chromebrowser Instance Opened");
		
		// Maximize the window 
		driver.manage().window().maximize();
		//logger.info("Window Maximized");

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		//logger.info("Implicit wait given");
		
		// Check if the web element is displayed or not
		try {
		driver.findElement(By.id("email")).isDisplayed(); 
		//logger.info("Web Element Found");
		} catch (Exception e) {
		//logger.info("Failure - Web Element Not Found");
		}
		}
	}
	

