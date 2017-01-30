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
    private WebElement searchTextBox = driver.findElement(By.id("ptxt"));
    private Select searchPeriodDropdown = new Select(driver.findElement(By.name("pr]")));
    private WebElement searchButton = driver.findElement(By.id("sbtn"));

    public SearchPage(WebDriver driver){
        this.driver = driver;
    }

}
