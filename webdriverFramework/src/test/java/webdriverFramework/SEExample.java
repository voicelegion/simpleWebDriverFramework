package webdriverFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by roman.pipchenko on 1/20/2017.
 */
public class SEExample {
    public static void main(String[] args) {
        System.setProperty("webdriver.firefox.bin",
                "C:\\tools\\Firefox\\firefox.exe");

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\roman.pipchenko\\Desktop\\geckodriver-v0.13.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("Page tittle is: " + driver.getTitle());

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });
        System.out.println("Page title is: " + driver.getTitle());

    }

}

