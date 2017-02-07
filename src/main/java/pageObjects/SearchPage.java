package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(id = "ptxt")
    public WebElement searchTextBox;
    @FindBy(name = "pr")
    public WebElement searchPeriodDropdown;
    @FindBy(id = "sbtn")
    public WebElement searchStartButton;
    @FindBy (xpath = "//*[@id='cmp_1']")
    public List<WebElement> searchSuggestionList;

    public WebElement getSearchFirstSuggestion() {
       return searchSuggestionList.get(0);
    }

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }
    public void selectSearchPeriod(String value) {
        Select dropDown = new Select(searchPeriodDropdown);
        dropDown.selectByValue(value);
    }


}
