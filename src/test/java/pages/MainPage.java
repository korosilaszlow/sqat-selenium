package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends BasePage {

    private final By title = By.xpath("//h3[@class='mb-3']");
    private final By navigationsBelowSearchBar = By.xpath("//div[contains(@class, 'col-6')][.//span[starts-with(@class,'icon-')]]");

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "https://hardverapro.hu/");
    }

    public String getTitle() {
        return waitUntilVisibleAndReturnElement(title).getText();
    }

    public List<WebElement> getNavigationsBelowSearchBar() {
        return waitUntilVisibleAndReturnElement(navigationsBelowSearchBar).findElements(By.tagName("a"));
    }
}
