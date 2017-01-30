package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class ElectronicsShowSelectedPage {
    private WebDriver driver;
    private List<WebElement> selectedAds = driver.findElements(By.xpath("//*[contains(@id, 'tr_')][contains(@style, 'cursor')]"));
    private String selectedAdvText = driver.findElement(By.xpath(".//a[@id and @class]")).getText();


    public ElectronicsShowSelectedPage(WebDriver driver){
        this.driver=driver;
    }
}
