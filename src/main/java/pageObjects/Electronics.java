package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class Electronics {
    private WebDriver driver;
    private WebElement searchElectronicsLink = driver.findElement(By.xpath("//*[@href='/ru/electronics/search/']"));

    public Electronics(WebDriver driver){
        this.driver = driver;
    }
}
