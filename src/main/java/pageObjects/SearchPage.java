package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class SearchPage {
    private WebDriver driver;
    public WebElement searchTextBox = driver.findElement(By.id("ptxt"));
    public Select searchPeriodDropdown = new Select(driver.findElement(By.name("pr]")));
    public WebElement searchButton = driver.findElement(By.id("sbtn"));

    public SearchPage(WebDriver driver){
        this.driver = driver;
    }

}
