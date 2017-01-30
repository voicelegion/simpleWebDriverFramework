package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by roman.pipchenko on 1/27/2017.
 */
public class HomePage {
    private WebDriver driver;

    public WebElement switchLangToRuLink = driver.findElement(By.xpath("//*[@href='/ru/']"));
    public WebElement switchLangToLvLink = driver.findElement(By.xpath("//*[@href='/lv/']"));
    public WebElement GoToElectronicsLink = driver.findElement(By.xpath("//*[@class=\"a1\"][@href=\"/ru/electronics/\"]"));


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }



    }


