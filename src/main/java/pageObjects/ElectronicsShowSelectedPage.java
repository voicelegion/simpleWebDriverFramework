package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class ElectronicsShowSelectedPage {
    private WebDriver driver;
    @FindBy(xpath = "//*[contains(@id, 'tr_')][contains(@style, 'cursor')]")
    public List<WebElement> selectedAds;

    public String selectedAdvText = driver.findElement(By.xpath(".//a[@id and @class]")).getText();


    public ElectronicsShowSelectedPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
