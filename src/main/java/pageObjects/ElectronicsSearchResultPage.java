package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class ElectronicsSearchResultPage {
    private WebDriver driver;
    @FindBy(xpath = "//*[text()=\"Цена\"][ancestor::*[@id=\"head_line\"]]")
    public WebElement sortByPriceLink;

    @FindBy(xpath = "//*[@href='/ru/electronics/search/' and contains(text(), 'Расширенный поиск')]")
    public WebElement expandedSearchLink;

    @FindBy(id ="show_selected_a" )
    public WebElement showSelectedAdsButton;

    @FindBy(xpath = "//*[contains(@id, 'tr_')]")
    public List<WebElement> allAdvList;

    @FindBy(xpath = ".//input[@type='checkbox']")
    public WebElement adsCheckbox;

    @FindBy(xpath = ".//a[@id and @class]")
    public WebElement adsText;



    public String getAdsText(){
        return adsText.getText();
    }



    public ElectronicsSearchResultPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

}
