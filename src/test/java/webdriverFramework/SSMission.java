package webdriverFramework;


import enums.Browser;
import enums.Language;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import pageObjects.*;

import java.io.File;
import java.util.*;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class SSMission {

    static WebDriver driver;

    public static Browser browser = Browser.FIREFOX;

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
        driver.get("https://www.ss.lv/lv");
        driver.manage().window().maximize();

        HomePage homePage = new HomePage(driver);
        homePage.switchLangTo(Language.RU);

        SearchElectronicsPage searchElectronicsPage = homePage.goToSearchElectronicsPage();

        HashMap firstValueSetHashMap = new HashMap();
        firstValueSetHashMap.put("searchFor", "Laptop");
        firstValueSetHashMap.put("dealType","SELL");
        firstValueSetHashMap.put("region", "riga_f");

        searchElectronicsPage.fillFieldsAndSubmit(firstValueSetHashMap);

        ElectronicsSearchResultPage electronicsSearchResultPage = new ElectronicsSearchResultPage(driver);
        assertFalse(electronicsSearchResultPage.isListSorted(electronicsSearchResultPage.getPriceList()));
        electronicsSearchResultPage.sortByPrice();
        Assert.assertTrue(electronicsSearchResultPage.isListSorted(electronicsSearchResultPage.getPriceList()));

        electronicsSearchResultPage.getFixedExpandedSearchLink().click();

        HashMap secondValueSetHashMap = new HashMap();
        secondValueSetHashMap.put("priceMin","0");
        secondValueSetHashMap.put("priceMax","300");
        secondValueSetHashMap.put("sortBy","8");

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
    }

}
