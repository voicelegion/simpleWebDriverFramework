package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class ElectronicsPage {
    private WebDriver driver;
    public WebElement searchElectronicsLink = driver.findElement(By.xpath("//*[@href='/ru/electronics/search/']"));

    public ElectronicsPage(WebDriver driver){
        this.driver = driver;
    }
}
