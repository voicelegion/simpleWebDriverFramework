package webdriverFramework;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Created by roman.pipchenko on 1/20/2017.
 */
public class SSMission {
    public static void main(String[] args) {

        File file = new File("C:\\tools\\Firefox\\", "firefox" + ".exe");
        if (file.exists()) {
            System.setProperty("webdriver.firefox.bin", "C:\\tools\\Firefox\\firefox.exe");
        }

        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();


        driver.get("https://www.ss.lv");
        driver.findElement(By.xpath("//*[@href='/ru/']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"a1\"][@href=\"/ru/electronics/\"]")));

        driver.findElement(By.xpath("//*[@class=\"a1\"][@href=\"/ru/electronics/\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@href='/ru/electronics/search/']")));
        driver.findElement(By.xpath("//*[@href='/ru/electronics/search/']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ptxt")));
        driver.findElement(By.xpath("//*[@id='ptxt']")).sendKeys("Лаптоп");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='cmp_1']")));
        driver.findElement(By.xpath("//*[@id='cmp_1']")).click();

//        Select dropDownPodrubrika = new Select(driver.findElement(By.name("'sid'")));
//        dropDownPodrubrika.selectByValue("1");


    }


}
