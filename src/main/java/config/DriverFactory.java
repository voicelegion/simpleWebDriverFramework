package config;

import enums.Browser;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;



/**
 * Created by roman.pipchenko on 2/28/2017.
 */
public class DriverFactory {

    public static WebDriver initiliazeDriver(Browser browser)  {
        WebDriver webDriver = null;
        switch (browser)
        {
            case CHROME: {
                System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
                webDriver =  new ChromeDriver();
                break;
            }
            case FIREFOX: {
                File file = new File("C:\\tools\\Firefox\\", "firefox.exe");
                if (file.exists()) {
                    System.setProperty("webdriver.firefox.bin", "C:\\tools\\Firefox\\firefox.exe");
                }
                System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
                webDriver =  new FirefoxDriver();
                break;
            }
        }
        return  webDriver;
    }

}
