package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class ElectronicsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//*[@href='/ru/electronics/search/']")
    public WebElement searchElectronicsLink;

    public ElectronicsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    public  SearchElectronicsPage goToElectronicSearchPage(){
        wait.until(ExpectedConditions.elementToBeClickable(searchElectronicsLink));
        searchElectronicsLink.click();
        return new SearchElectronicsPage(driver);
    }
}
