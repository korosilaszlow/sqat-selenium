package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PCSearchPage extends BasePage {
    private final By advancedSearch = By.xpath("//a[contains(@data-target, 'advanced')]");
    private final By quickSearch = By.xpath("//input[@name='stext']");
    private final By countySearch = By.xpath("//input[@name='stcid_text']");
    private final By minPriceSearch = By.xpath("//input[@name='minprice']");
    private final By noIcedBox = By.xpath("//label[.//input[@name='noiced']]");
    private final By searchButton = By.xpath("//div[@class='input-group-append'][.//button[@type='submit']]");
    private final By foundSearchTitle = By.xpath("//div[@class='uad-list'][.//h3]");
    private final By notFoundSearchDiv = By.xpath("//div[@class='jumbotron']");

    public PCSearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "https://hardverapro.hu/aprok/pc_szerver/index.html");
    }

    public void clickAdvancedSearch() {
        getAdvancedSearchButton().click();
    }

    public void search(String searchedObject, String searchedCounty, String searchedMinPrice, boolean clickNoIcedBox) {
        if (searchedObject != null) {
            getQuickSearch().sendKeys(searchedObject);
        }
        if (searchedCounty != null) {
            getCountySearch().sendKeys(searchedCounty);
        }
        if (searchedMinPrice != null) {
            getMinPriceSearch().sendKeys(searchedMinPrice);
        }
        if (clickNoIcedBox) {
            getNoIcedBox().click();
        }
        getSearchButton().click();
    }

    public boolean isAdvancedSearchClosed() {
        String advancedSearchButtonAriaExpanded = getAdvancedSearchButton().getAttribute("aria-expanded");
        return advancedSearchButtonAriaExpanded == null || advancedSearchButtonAriaExpanded.equals("false");
    }

    public String getFoundSearchText() {
        return getText(waitUntilVisibleAndReturnElement(foundSearchTitle));
    }

    public String getNotFoundSearchText() {
        return getText(getInnerH1(waitUntilVisibleAndReturnElement(notFoundSearchDiv)));
    }

    private WebElement getAdvancedSearchButton() {
        return waitUntilVisibleAndReturnElement(advancedSearch);
    }

    private WebElement getQuickSearch() {
        return waitUntilVisibleAndReturnElement(quickSearch);
    }

    private WebElement getCountySearch() {
        return waitUntilVisibleAndReturnElement(countySearch);
    }

    private WebElement getMinPriceSearch() {
        return waitUntilVisibleAndReturnElement(minPriceSearch);
    }

    private WebElement getNoIcedBox() {
        return waitUntilVisibleAndReturnElement(noIcedBox);
    }

    private WebElement getSearchButton() {
        return waitUntilVisibleAndReturnElement(searchButton);
    }
}
