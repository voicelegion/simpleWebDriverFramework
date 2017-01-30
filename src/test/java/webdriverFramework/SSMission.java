package webdriverFramework;


import com.google.common.base.Function;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import pageObjects.HomePage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

public class SSMission {

    static WebDriver driver;

    public static String browser = "chrome";


    @Rule
    public TestRule screenshotTaker = new ScreenshotTaker((TakesScreenshot) driver);

    @BeforeClass
    public static void setup() {
        if (browser.equals("chrome")) {

            File file = new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            if (file.exists()) {
                System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
            }
        } else {
            File file = new File("C:\\tools\\Firefox\\", "firefox" + ".exe");
            if (file.exists()) {
                System.setProperty("webdriver.firefox.bin", "C:\\tools\\Firefox\\firefox.exe");
            }
            System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
    }

    @Test
    public void testSS() throws InterruptedException {

        driver.get("https://www.ss.lv");
        driver.manage().window().maximize();
        assertTrue(driver.findElement(By.xpath("//*[@href='/ru/']")).isDisplayed());
        driver.findElement(By.xpath("//*[@href='/ru/']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class=\"a1\"][@href=\"/ru/electronics/\"]")));

        driver.findElement(By.xpath("//*[@class=\"a1\"][@href=\"/ru/electronics/\"]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@href='/ru/electronics/search/']")));
        driver.findElement(By.xpath("//*[@href='/ru/electronics/search/']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ptxt")));



        driver.findElement(By.xpath("//*[@id='ptxt']")).sendKeys("Laptop");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='cmp_1']")));
        driver.findElement(By.xpath("//*[@id='cmp_1']")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.name("sid")));
        Select dropDownPodrubrika = new Select(driver.findElement(By.name("sid")));
        dropDownPodrubrika.selectByValue("1");

        Select dropDownRegion = new Select(driver.findElement(By.id("s_region_select")));
        dropDownRegion.selectByValue("riga_f");

        driver.findElement(By.id("sbtn")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()=\"Цена\"][ancestor::*[@id=\"head_line\"]]")));
        driver.findElement(By.xpath("//*[text()=\"Цена\"][ancestor::*[@id=\"head_line\"]]")).click();

        Wait<WebDriver> stubbornWait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        WebElement foo = stubbornWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//*[@class=\"a9a\"][@href=\"/ru/electronics/search/\"]"));
            }
        });
        foo.click();


        wait.until(ExpectedConditions.urlContains("search/"));
        assertEquals("https://www.ss.lv/ru/electronics/search/", driver.getCurrentUrl());

        driver.findElement(By.xpath("//*[@name=\"topt[8][min]\"]")).sendKeys("0");

        driver.findElement(By.xpath("//*[@name=\"topt[8][max]\"]")).sendKeys("300");

        Select sortByPrice = new Select(driver.findElement(By.xpath("//*[@name=\"sort\"]")));
        sortByPrice.selectByValue("8");

        driver.findElement(By.id("sbtn")).click();

        driver.findElements(By.xpath("//*[contains(@id, 'tr_')]"));


        List<WebElement> elementList;
        elementList = driver.findElements(By.xpath("//*[contains(@id, 'tr_')]"));



        int firstSelectedAd = randInt(elementList.size());
        int secondSelectedAd = randInt(elementList.size());
        while (secondSelectedAd == firstSelectedAd) {
            secondSelectedAd = randInt(elementList.size());
        }
        int thirdSelectedAd = randInt(elementList.size());
        while (thirdSelectedAd == secondSelectedAd || thirdSelectedAd == firstSelectedAd) {
            thirdSelectedAd = randInt(elementList.size());
        }

        WebElement firstAd = elementList.get(firstSelectedAd);
        WebElement secondAd = elementList.get(secondSelectedAd);
        WebElement thirdAd = elementList.get(thirdSelectedAd);


        String firstAdvText = firstAd.findElement(By.xpath(".//a[@id and @class]")).getText();
        String secondAdvText = secondAd.findElement(By.xpath(".//a[@id and @class]")).getText();
        String thirdAdvText = thirdAd.findElement(By.xpath(".//a[@id and @class]")).getText();

        List<String> selectedElementsText = new ArrayList<String>();
        selectedElementsText.add(firstAdvText);
        selectedElementsText.add(secondAdvText);
        selectedElementsText.add(thirdAdvText);

        firstAd.findElement(By.xpath(".//input[@type=\"checkbox\"]")).click();
        secondAd.findElement(By.xpath(".//input[@type=\"checkbox\"]")).click();
        thirdAd.findElement(By.xpath(".//input[@type=\"checkbox\"]")).click();

        driver.findElement(By.xpath("//*[@id='show_selected_a']")).click();


        List<WebElement> elementListOfSelectedItems;
        elementListOfSelectedItems = driver.findElements(By.xpath("//*[contains(@id, 'tr_')][contains(@style, 'cursor')]"));

        String firstNewAdText = elementListOfSelectedItems.get(0).findElement(By.xpath(".//a[@id and @class]")).getText();
        String secondNewAdText = elementListOfSelectedItems.get(1).findElement(By.xpath(".//a[@id and @class]")).getText();
        String thirdNewAdText = elementListOfSelectedItems.get(2).findElement(By.xpath(".//a[@id and @class]")).getText();


        int selectedElementAmount = selectedElementsText.size();
        for (int i = 0; i < selectedElementAmount; i++) {
            assertTrue(selectedElementsText.get(i).contains(firstNewAdText) || selectedElementsText.get(i).contains(secondNewAdText) || selectedElementsText.get(i).contains(thirdNewAdText));
        }
    }


    public static int randInt(int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt(max);
        return randomNum;
    }

}
