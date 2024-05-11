package resolutionTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class TestDemo {

    WebDriver driver;

    @BeforeTest
    @Parameters({"browser", "desktopWidth", "desktopHeight", "mobileWidth", "mobileHeight"})
    public void setup(String browser, @Optional("1280") int desktopWidth, @Optional("800") int desktopHeight, @Optional("360") int mobileWidth, @Optional("640") int mobileHeight) {
        // Your setup code here        System.out.println("Browser: " + browser);
        System.out.println("Desktop Width: " + desktopWidth);
        System.out.println("Desktop Height: " + desktopHeight);
        System.out.println("Mobile Width: " + mobileWidth);
        System.out.println("Mobile Height: " + mobileHeight);

        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\nagar\\Desktop\\software\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "msedge":
                System.setProperty("webdriver.edge.driver", "C:\\Users\\nagar\\Desktop\\software\\edgedriver_win64\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browser);
        }

        // Set dimensions based on browser type
        if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("msedge")) {
            driver.manage().window().setSize(new Dimension(desktopWidth, desktopHeight));
        } else if (browser.equalsIgnoreCase("mobile")) {
            driver.manage().window().setSize(new Dimension(mobileWidth, mobileHeight));
        } else {
            throw new IllegalArgumentException("Invalid browser type: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void verifyTitleAndTakeScreenshot() throws Exception {
        // Open URL
        driver.get("https://www.getcalley.com/");
        String expectedTitle = "Calley Automatic Call Dialer For Android & IOS Phones";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedTime = currentTime.format(formatter);

        // Create a map of URLs with their corresponding titles
        Map<String, String> urlTitles = new HashMap<>();
        urlTitles.put("https://www.getcalley.com/", "Calley Automatic Call Dialer For Android & IOS Phones");
        urlTitles.put("https://www.getcalley.com/calley-call-from-browser/", "Calley Call From Browser");
        urlTitles.put("https://www.getcalley.com/calley-pro-features/", "Best Automatic Calling App For Business Owners - Calley PRO");
        urlTitles.put("https://www.getcalley.com/how-calley-auto-dialer-app-works/", "How Calley Auto Dialer App Work");
        // Iterate through the URLs from the map
        for (Map.Entry<String, String> entry : urlTitles.entrySet()) {
            String url = entry.getKey();
            String pageTitle = entry.getValue();

            // Open the URL
            driver.get(url);

            // Take screenshot
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Define the file path for the screenshot
            String filePath = "C:\\Users\\nagar\\Downloads\\Sample\\" + pageTitle + "_" + formattedTime + ".png";

            // Save the screenshot
            try {
                Files.copy(screenshotFile, new File(filePath));
                System.out.println("Screenshot captured for: " + pageTitle);
            } catch (IOException e) {
                System.out.println("Failed to capture screenshot for: " + pageTitle);
                e.printStackTrace();
            }
        }
    }

    @AfterMethod
    public void Quit() {
        driver.quit();
    }
}
