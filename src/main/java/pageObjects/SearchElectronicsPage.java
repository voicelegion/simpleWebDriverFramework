package pageObjects;

import enums.ValueForDealTypeDropdown;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class SearchElectronicsPage extends SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//*[@name='topt[8][min]']")
    public WebElement priceMinTextBox;
    @FindBy(xpath = "//*[@name='topt[8][max]']")
    public WebElement priceMaxTextBox;
    @FindBy(name = "sid")
    public WebElement dealTypeDropdown;

    @FindBy(id = "s_region_select")
    public WebElement regionSelectDropdown;
    @FindBy(name = "sort")
    public WebElement sortByDropdown;

    public SearchElectronicsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectDealType(ValueForDealTypeDropdown value) {
        Select dropDown = new Select(dealTypeDropdown);
        switch (value) {
            case BUY: {
                dropDown.selectByVisibleText("Покупают");
                break;
            }
            case SELL: {
                dropDown.selectByVisibleText("Продают");
                break;
            }
            case DIFFERENT: {
                dropDown.selectByVisibleText("Разное");
                break;
            }
        }
    }

    public void selectRegionByValue(String cityValue) {
        Select dropDown = new Select(regionSelectDropdown);
        dropDown.selectByValue(cityValue);
    }

    public void selectSortingBy(String value) {
        Select dropDown = new Select(sortByDropdown);
        dropDown.selectByValue(value);
    }

    public void fillFieldsAndSubmit(HashMap hashMap) {
        if (hashMap.containsKey("searchFor")) {
            wait.until(ExpectedConditions.elementToBeClickable(searchTextBox));
            searchTextBox.sendKeys(hashMap.get("searchFor").toString());
        }
        if (hashMap.containsKey("priceMin")) {
            priceMinTextBox.sendKeys(hashMap.get("priceMin").toString());
        }
        if (hashMap.containsKey("priceMax")) {
            priceMaxTextBox.sendKeys(hashMap.get("priceMax").toString());
        }
        if (hashMap.containsKey("dealType")) {
            selectDealType(ValueForDealTypeDropdown.valueOf(hashMap.get("dealType").toString()));
        }
        if (hashMap.containsKey("region")) {
            selectRegionByValue(hashMap.get("region").toString());
        }
        if (hashMap.containsKey("period")) {
            selectSearchPeriod(hashMap.get("period").toString());
        }
        if (hashMap.containsKey("sortBy")){
            selectSortingBy(hashMap.get("sortBy").toString());
        }
        searchStartButton.click();
    }


}


