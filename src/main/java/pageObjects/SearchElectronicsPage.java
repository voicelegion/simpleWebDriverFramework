package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class SearchElectronicsPage extends SearchPage{
    private WebDriver driver;
    public WebElement priceMinTextBox = driver.findElement(By.xpath("//*[@name=\"topt[8][min]\"]"));
    public WebElement priceMaxTextBox = driver.findElement(By.xpath("//*[@name=\"topt[8][max]\"]"));
    public Select dealTypeDropdown = new Select(driver.findElement(By.name("sid")));
    public Select regionDropdown = new Select(driver.findElement(By.id("s_region_select")));
    public Select sortByDropdown = new Select(driver.findElement(By.name("sort")));
    public WebElement searchSubmitButton = driver.findElement(By.id("sbtn"));


    public SearchElectronicsPage(WebDriver driver) {
        super(driver);

    }
}
