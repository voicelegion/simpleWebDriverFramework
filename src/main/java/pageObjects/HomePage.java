package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by roman.pipchenko on 1/27/2017.
 */
public class HomePage {
    public WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    WebElement switchLangToRu = driver.findElement(By.xpath("//*[@href='/ru/']"));
    WebElement switchLangToLv = driver.findElement(By.xpath("//*[@href='/lv/']"));
    }


