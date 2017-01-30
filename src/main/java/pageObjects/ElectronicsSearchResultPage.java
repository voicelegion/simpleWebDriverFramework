package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class ElectronicsSearchResultPage {
    private WebDriver driver;
    public WebElement sortByPriceLink = driver.findElement(By.xpath("//*[text()=\"Цена\"][ancestor::*[@id=\"head_line\"]]"));
    public WebElement expandedSearchLink = driver.findElement(By.xpath("//*[@href='/ru/electronics/search/' and contains(text(), 'Расширенный поиск')]"));
    public WebElement showSelectedAdsButton = driver.findElement(By.id("show_selected_a"));
    public List<WebElement> allAdvList = driver.findElements(By.xpath("//*[contains(@id, 'tr_')]"));
    public WebElement adsCheckbox = driver.findElement(By.xpath(".//input[@type='checkbox']"));
    public String adsText = driver.findElement(By.xpath(".//a[@id and @class]")).getText();



    public ElectronicsSearchResultPage(WebDriver driver){
        this.driver = driver;
    }

}
