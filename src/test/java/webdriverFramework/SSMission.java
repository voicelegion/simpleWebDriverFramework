package webdriverFramework;


import enums.Browser;
import enums.Language;
import com.google.common.base.Function;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import pageObjects.*;

import java.io.File;
import java.util.*;


import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.TestCase.assertTrue;



public class SSMission {

    static WebDriver driver;

    public static Browser browser = Browser.CHROME;

    @Rule
    public TestRule screenshotTaker = new ScreenshotTaker((TakesScreenshot) driver);

    @BeforeClass
    public static void setup() {
        switch (browser){
            case CHROME:{
            File file = new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            if (file.exists()) {
                System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
            }
            break;
        }
            case FIREFOX:{
                File file = new File("C:\\tools\\Firefox\\", "firefox.exe");
            if (file.exists()) {
                System.setProperty("webdriver.firefox.bin", "C:\\tools\\Firefox\\firefox.exe");
            }
            System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
            }
        }
    }

    @Test
    public void testSS() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get("https://www.ss.lv/lv");
        driver.manage().window().maximize();

        HomePage homePage = new HomePage(driver);
        homePage.switchLangTo(Language.RU);

        SearchElectronicsPage searchElectronicsPage = homePage.goToSearchElectronicsPage();

        HashMap hashMap = new HashMap();
        hashMap.put("searchFor", "Laptop");
        hashMap.put("dealType","SELL");
        hashMap.put("region", "riga_f");

        searchElectronicsPage.fillFieldsAndSubmit(hashMap);

        ElectronicsSearchResultPage electronicsSearchResultPage = new ElectronicsSearchResultPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(electronicsSearchResultPage.sortByPriceLink));
        electronicsSearchResultPage.sortByPriceLink.click();

        Wait<WebDriver> stubbornWait = new FluentWait<>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        WebElement foo = stubbornWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return electronicsSearchResultPage.expandedSearchLink;
            }
        });
        foo.click();


//        wait.until(ExpectedConditions.urlContains("search/"));
//        assertEquals("https://www.ss.lv/ru/electronics/search/", driver.getCurrentUrl());
        searchElectronicsPage.priceMinTextBox.sendKeys("0");

        searchElectronicsPage.priceMaxTextBox.sendKeys("300");

        searchElectronicsPage.selectSortingBy("8");

        searchElectronicsPage.searchStartButton.click();

        int[] selectedAdNumbers = electronicsSearchResultPage.getRandomNumberArray(3);

        List<String> textsOfSelectedAds = electronicsSearchResultPage.createSelectedAdTextList(selectedAdNumbers);


        electronicsSearchResultPage.selectSpecifiedAds(selectedAdNumbers);
        electronicsSearchResultPage.showSelectedAdsButton.click();

        ElectronicsShowSelectedPage electronicsShowSelectedPage = new ElectronicsShowSelectedPage(driver);

        List<String> textsOfSelectedFilteredAds = electronicsShowSelectedPage.getOnlySelectedAdTexts();

        Collections.sort(textsOfSelectedFilteredAds);
        Collections.sort(textsOfSelectedAds);

        for (int i = 0; i < textsOfSelectedAds.size(); i++) {
            assertTrue(textsOfSelectedAds.get(i).contains(textsOfSelectedFilteredAds.get(i)));
        }
    }

}
