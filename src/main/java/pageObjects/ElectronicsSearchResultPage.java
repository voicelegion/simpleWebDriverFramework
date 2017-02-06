package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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

    public static final String PATHTOADSTEXT = ".//a[@id and @class]";

//    @FindBy(xpath = ".//input[@type='checkbox']")
//    public WebElement adsCheckbox;

//    @FindBy(xpath = ".//a[@id and @class]")
//    public WebElement adsText;


//    public String getAdsText(WebElement element){
//        return element.findElement(By.xpath(".//a[@id and @class]")).getText();
//    }

    public String getAdAndItsText(int index) {
        WebElement element = allAdvList.get(index);
        return element.findElement(By.xpath(PATHTOADSTEXT)).getText();
    }


    public ElectronicsSearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, 5);
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
    public List<String> createSelectedAdTextList(int[] selectedAdNumbers){
        List<String> adTexts = new ArrayList<>();
        for (int i = 0; i < selectedAdNumbers.length; i++){
            adTexts.add(getAdAndItsText(selectedAdNumbers[i]));
        }
        return adTexts;
    }

    public void selectSpecifiedAds(int[] adNumberList){
        WebElement element;
        for (int i = 0; i < adNumberList.length; i++){
            element = allAdvList.get(adNumberList[i]);
            selectAdsCheckbox(element);        }
    }

}
