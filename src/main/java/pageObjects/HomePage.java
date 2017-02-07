package pageObjects;

import enums.Language;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by roman.pipchenko on 1/27/2017.
 */
public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//*[@href='/ru/']")
    public WebElement switchLangToRuLink;
    @FindBy(xpath = "//*[@href='/lv/']")
    public WebElement switchLangToLvLink;
    @FindBy(xpath = "//*[@class=\"a1\"][@href=\"/ru/electronics/\"]")
    public WebElement goToElectronicsLink;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
         wait = new WebDriverWait(driver, 5);
    }


    public HomePage switchLangTo(Language language) {
        switch (language) {
            case LV:
                if (switchLangToLvLink.isDisplayed()) {
                    switchLangToLvLink.click();
                    break;
                }
            case RU:
                if (switchLangToRuLink.isDisplayed()) {
                    switchLangToRuLink.click();
                }
        }
        return this;
    }
    public SearchElectronicsPage goToSearchElectronicsPage(){
        SearchElectronicsPage searchElectronicsPage = goToElectronicsPage().goToElectronicSearchPage();
        wait.until(ExpectedConditions.elementToBeClickable(searchElectronicsPage.searchTextBox));
        return searchElectronicsPage;
    }

    public ElectronicsPage goToElectronicsPage(){
        wait.until(ExpectedConditions.elementToBeClickable(goToElectronicsLink));
        goToElectronicsLink.click();
        return new ElectronicsPage(driver);
    }
}


