package pageObjects;

import enums.Language;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by roman.pipchenko on 1/27/2017.
 */
public class HomePage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@href='/ru/']")
    public WebElement switchLangToRuLink;
    @FindBy(xpath = "//*[@href='/lv/']")
    public WebElement switchLangToLvLink;
    @FindBy(xpath = "//*[@class=\"a1\"][@href=\"/ru/electronics/\"]")
    public WebElement goToElectronicsLink;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void switchLangTo(Language language) {
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
    }
}


