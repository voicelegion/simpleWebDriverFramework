package pageObjects;

import enums.ValueForDealTypeDropdown;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class SearchElectronicsPage extends SearchPage{
    private WebDriver driver;
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
        PageFactory.initElements(driver,this);
    }

    public void selectDealType(ValueForDealTypeDropdown value){
        Select dropDown = new Select(dealTypeDropdown);
            switch (value){
                case BUY:{
                    dropDown.selectByVisibleText("Покупают");
                    break;
                }
                case SELL:{
                    dropDown.selectByVisibleText("Продают");
                    break;
                }
                case DIFFERENT:{
                    dropDown.selectByVisibleText("Разное");
                    break;
                }
            }
    }
    public void selectRegionByValue(String cityValue){
        Select dropDown = new Select(regionSelectDropdown);
            dropDown.selectByValue(cityValue);
    }

    public void selectSortingBy(String value){
        Select dropDown = new Select(sortByDropdown);
        dropDown.selectByValue(value);
    }
}
