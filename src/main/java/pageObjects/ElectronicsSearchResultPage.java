package pageObjects;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by roman.pipchenko on 1/30/2017.
 */
public class ElectronicsSearchResultPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//*[text()=\"Цена\"][ancestor::*[@id=\"head_line\"]]")
    public WebElement sortByPriceLink;

    @FindBy(xpath = "//*[@href='/ru/electronics/search/' and contains(text(), 'Расширенный поиск')]")
    public WebElement expandedSearchLink;

    @FindBy(id = "show_selected_a")
    public WebElement showSelectedAdsButton;

    @FindBy(xpath = "//*[contains(@id, 'tr_')]")
    public List<WebElement> allAdvList;
    @FindBy(xpath = "//*[contains(@id, 'tr_')]//td[last()]/a")
    public List<WebElement> allAdPriceList;
    public static final String PATHTOADSTEXT = ".//a[@id and @class]";


    public String getAdAndItsText(int index) {
        WebElement element = allAdvList.get(index);
        return element.findElement(By.xpath(PATHTOADSTEXT)).getText();
    }

    public ElectronicsSearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void selectAdsCheckbox(WebElement webElement) {
        webElement.findElement(By.xpath(".//input[@type='checkbox']")).click();
    }

    private int getRandInt(int max, int arrayOfRandomNumbers[]) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max);
        if (IntStream.of(arrayOfRandomNumbers).anyMatch(x -> x == randomNum)) {
            return getRandInt(max, arrayOfRandomNumbers);
        }
        return randomNum;
    }


    public int[] getRandomNumberArray(int elementAmount) {
        int[] randomAdsNumbers = new int[elementAmount];
        for (int i = 0; i < elementAmount; i++) {
            randomAdsNumbers[i] = getRandInt(allAdvList.size(), randomAdsNumbers);
        }
        return randomAdsNumbers;
    }

    public List<String> createSelectedAdTextList(int[] selectedAdNumbers) {
        List<String> adTexts = new ArrayList<>();
        for (int i = 0; i < selectedAdNumbers.length; i++) {
            adTexts.add(getAdAndItsText(selectedAdNumbers[i]));
        }
        return adTexts;
    }

    public void selectSpecifiedAds(int[] adNumberList) {
        WebElement element;
        for (int i = 0; i < adNumberList.length; i++) {
            element = allAdvList.get(adNumberList[i]);
            selectAdsCheckbox(element);
        }
    }

        public void sortByPrice(){
        wait.until(ExpectedConditions.elementToBeClickable(sortByPriceLink));
        sortByPriceLink.click();
        }
    public List<Float> getPriceList() {
        List<Float> priceList = new ArrayList<>();
        for (WebElement element : allAdPriceList) {
            String string=element.getText();
            string = string.replaceAll("[^0-9]", "");
            Float x = Float.valueOf(string);
            priceList.add(x);
        }
        priceList = priceList.subList(0, 10);
        return priceList;
    }

    public boolean isListSorted(List<Float> advPriceList) {
        boolean listSorted = true;

        for (int i = 0; i < advPriceList.size() - 1; i++) {
            if (advPriceList.get(i) > advPriceList.get(i + 1)) {
                listSorted = false;
            }
        }
        return listSorted;
    }

    public Wait<WebDriver> getFluentWait(WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(IndexOutOfBoundsException.class);
    }

    public WebElement getFixedExpandedSearchLink(){
        return getFluentWait(driver).until(driver -> expandedSearchLink);
    }

}
