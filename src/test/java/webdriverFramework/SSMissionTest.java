package webdriverFramework;


import config.DriverFactory;
import enums.Browser;
import enums.Language;
import org.junit.*;
import org.junit.rules.TestRule;
import org.openqa.selenium.*;

import pageObjects.*;
import utils.ConfigReader;
import utils.ScreenshotTaker;

import java.io.*;
import java.util.*;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class SSMissionTest {

    private Browser browser = Browser.valueOf(!(System.getProperty("browser")== null) ? System.getProperty("browser").toUpperCase() : ConfigReader.getConfigValue("browser").toUpperCase());
    private WebDriver driver = DriverFactory.initiliazeDriver(browser);

    @Rule
    public TestRule screenshotTaker = new ScreenshotTaker((TakesScreenshot) driver);

//    public SSMissionTest() throws IOException {
//    }


    @Test
    public void testSS() {
        driver.get("https://www.ss.lv/lv");
        driver.manage().window().maximize();

        HomePage homePage = new HomePage(driver);
        homePage.switchLangTo(Language.RU);

        SearchElectronicsPage searchElectronicsPage = homePage.goToSearchElectronicsPage();

        HashMap firstValueSetHashMap = new HashMap();
        firstValueSetHashMap.put("searchFor", "Laptop");
        firstValueSetHashMap.put("dealType", "SELL");
        firstValueSetHashMap.put("region", "riga_f");

        searchElectronicsPage.fillFieldsAndSubmit(firstValueSetHashMap);

        ElectronicsSearchResultPage electronicsSearchResultPage = new ElectronicsSearchResultPage(driver);
        assertFalse(electronicsSearchResultPage.isListSorted(electronicsSearchResultPage.getPriceList()));
        electronicsSearchResultPage.sortByPrice();
        Assert.assertTrue(electronicsSearchResultPage.isListSorted(electronicsSearchResultPage.getPriceList()));

        electronicsSearchResultPage.getFixedExpandedSearchLink().click();

        HashMap secondValueSetHashMap = new HashMap();
        secondValueSetHashMap.put("priceMin", "0");
        secondValueSetHashMap.put("priceMax", "300");
        secondValueSetHashMap.put("sortBy", "8");

        searchElectronicsPage.fillFieldsAndSubmit(secondValueSetHashMap);

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
        driver.quit();
        assertTrue(driver.toString().contains("null"));
    }

}