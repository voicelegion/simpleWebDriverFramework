package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class ElectronicsShowSelectedPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[contains(@id, 'tr_')][contains(@style, 'cursor')]")
    public List<WebElement> selectedAds;

//    @FindBy(xpath = ".//a[@id and @class]")
//    public WebElement selectedAdvText;


    public ElectronicsShowSelectedPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, 5);
    }
    public String getSelectedAdvText(WebElement webElement){
        return webElement.findElement(By.xpath(".//a[@id and @class]")).getText();
    }
    public List<String> getOnlySelectedAdTexts(){
        List<String> selectedAdTexts = new ArrayList<>();
        for (int i = 0; i < selectedAds.size(); i++) {
            selectedAdTexts.add(getSelectedAdvText(selectedAds.get(i)));
        }
        return selectedAdTexts;
    }
}
