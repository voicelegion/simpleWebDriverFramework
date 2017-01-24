package webdriverFramework;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SSMission {
    static WebDriver driver;

    @Rule
    public TestRule screenshotTaker = new ScreenshotTaker((TakesScreenshot) driver);

    @BeforeClass
    public static void setup() {
        File file = new File("C:\\tools\\Firefox\\", "firefox" + ".exe");
        if (file.exists()) {
            System.setProperty("webdriver.firefox.bin", "C:\\tools\\Firefox\\firefox.exe");
        }
        System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void testSS() {

        driver.get("https://www.ss.lv");
        assertTrue(driver.findElement(By.xpath("//*[@href='/ru/']")).isDisplayed());
        driver.findElement(By.xpath("//*[@href='/ru/']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"a1\"][@href=\"/ru/electronics/\"]")));

        driver.findElement(By.xpath("//*[@class=\"a1\"][@href=\"/ru/electronics/\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@href='/ru/electronics/search/']")));
        driver.findElement(By.xpath("//*[@href='/ru/electronics/search/']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ptxt")));
        driver.findElement(By.xpath("//*[@id='ptxt']")).sendKeys("Laptop");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='cmp_1']")));
        driver.findElement(By.xpath("//*[@id='cmp_1']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("sid")));
        Select dropDownPodrubrika = new Select(driver.findElement(By.name("sid")));
        dropDownPodrubrika.selectByValue("1");

        Select dropDownRegion = new Select(driver.findElement(By.id("s_region_select")));
        dropDownRegion.selectByValue("riga_f");

        driver.findElement(By.id("sbtn")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"a19\"]")));
        driver.findElement(By.xpath("//*[@class=\"a19\"]")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"a9a\"][@href=\"/ru/electronics/search/\"]")));

        try {
            driver.findElement(By.xpath("//*[@class=\"a9a\"][@href=\"/ru/electronics/search/\"]")).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(By.xpath("//*[@class=\"a9a\"][@href=\"/ru/electronics/search/\"]")).click();
        }
        
        assertEquals("https://www.ss.lv/ru/electronics/search/", driver.getCurrentUrl());
    }
}
