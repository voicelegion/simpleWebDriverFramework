package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class ElectronicsPage {
    private WebDriver driver;
    @FindBy(xpath = "//*[@href='/ru/electronics/search/']")
    public WebElement searchElectronicsLink;

    public ElectronicsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
