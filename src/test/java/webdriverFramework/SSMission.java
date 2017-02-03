package webdriverFramework;


import enums.Language;
import com.google.common.base.Function;
import enums.ValueForDealTypeDropdown;
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
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


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
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.get("https://www.ss.lv/lv");
        driver.manage().window().maximize();

        HomePage homePage = new HomePage(driver);

        ElectronicsPage electronicsPage = new ElectronicsPage(driver);

        homePage.switchLangTo(Language.RU);

        wait.until(ExpectedConditions.elementToBeClickable(homePage.goToElectronicsLink));

        homePage.goToElectronicsLink.click();

        wait.until(ExpectedConditions.elementToBeClickable(electronicsPage.searchElectronicsLink));

        electronicsPage.searchElectronicsLink.click();

        SearchElectronicsPage searchElectronicsPage = new SearchElectronicsPage(driver);

        wait.until(ExpectedConditions.elementToBeClickable(searchElectronicsPage.searchTextBox));

        searchElectronicsPage.searchTextBox.sendKeys("Laptop");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(searchElectronicsPage.getSearchFirstSuggestion()));
        searchElectronicsPage.getSearchFirstSuggestion().click();

        wait.until(ExpectedConditions.elementToBeClickable(searchElectronicsPage.dealTypeDropdown));
        searchElectronicsPage.selectDealType(ValueForDealTypeDropdown.SELL);


        searchElectronicsPage.selectRegionByValue("riga_f");

        searchElectronicsPage.searchStartButton.click();

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


        wait.until(ExpectedConditions.urlContains("search/"));
        assertEquals("https://www.ss.lv/ru/electronics/search/", driver.getCurrentUrl());

        searchElectronicsPage.priceMinTextBox.sendKeys("0");

        searchElectronicsPage.priceMaxTextBox.sendKeys("300");

        searchElectronicsPage.selectSortingBy("8");

        searchElectronicsPage.searchStartButton.click();

        int [] selectedAdNumbers = electronicsSearchResultPage.getRandomNumberArray(3);
        int firstSelectedAd = randInt(electronicsSearchResultPage.allAdvList.size());
        int secondSelectedAd = randInt(electronicsSearchResultPage.allAdvList.size());
        while (secondSelectedAd == firstSelectedAd) {
            secondSelectedAd = randInt(electronicsSearchResultPage.allAdvList.size());
        }
        int thirdSelectedAd = randInt(electronicsSearchResultPage.allAdvList.size());
        while (thirdSelectedAd == secondSelectedAd || thirdSelectedAd == firstSelectedAd) {
            thirdSelectedAd = randInt(electronicsSearchResultPage.allAdvList.size());
        }


//        WebElement firstAd = electronicsSearchResultPage.allAdvList.get(firstSelectedAd);
//        WebElement secondAd = electronicsSearchResultPage.allAdvList.get(secondSelectedAd);
//        WebElement thirdAd = electronicsSearchResultPage.allAdvList.get(thirdSelectedAd);





/*
        List<WebElement> selectedAdsList = new ArrayList<>();
        selectedAdsList.add(firstAd);
        selectedAdsList.add(secondAd);
        selectedAdsList.add(thirdAd);


        String firstAdvText = electronicsSearchResultPage.getAdsText(firstAd);
        String secondAdvText = electronicsSearchResultPage.getAdsText(secondAd);
        String thirdAdvText = electronicsSearchResultPage.getAdsText(thirdAd);

        Roma thinks this is not necessary anymore. 02/03/16 16:00.
       */

        List<String> textsOfSelectedAds = new ArrayList<>();
        textsOfSelectedAds.add(electronicsSearchResultPage.getAdAndItsText(selectedAdNumbers[0]));
        textsOfSelectedAds.add(electronicsSearchResultPage.getAdAndItsText(selectedAdNumbers[1]));
        textsOfSelectedAds.add(electronicsSearchResultPage.getAdAndItsText(selectedAdNumbers[2]));










//        textsOfSelectedAds.add(firstAd.findElement(By.xpath(electronicsSearchResultPage.PATHTOADSTEXT)).getText());
//          this is not needed right now too



        electronicsSearchResultPage.selectAdsCheckbox(firstAd);
        electronicsSearchResultPage.selectAdsCheckbox(secondAd);
        electronicsSearchResultPage.selectAdsCheckbox(thirdAd);

        electronicsSearchResultPage.showSelectedAdsButton.click();

        ElectronicsShowSelectedPage electronicsShowSelectedPage = new ElectronicsShowSelectedPage(driver);

        List<String> textsOfSelectedFilteredAds = new ArrayList<>();
        textsOfSelectedFilteredAds.add(electronicsShowSelectedPage.getSelectedAdvText(electronicsShowSelectedPage.selectedAds.get(0)));
        textsOfSelectedFilteredAds.add(electronicsShowSelectedPage.getSelectedAdvText(electronicsShowSelectedPage.selectedAds.get(1)));
        textsOfSelectedFilteredAds.add(electronicsShowSelectedPage.getSelectedAdvText(electronicsShowSelectedPage.selectedAds.get(2)));





        Collections.sort(textsOfSelectedFilteredAds);
        Collections.sort(textsOfSelectedAds);

        for (int i = 0; i < textsOfSelectedAds.size(); i++) {
           assertTrue(textsOfSelectedAds.get(i).contains(textsOfSelectedFilteredAds.get(i)));
        }

    }


    public static int randInt(int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt(max);
        return randomNum;
    }

}
