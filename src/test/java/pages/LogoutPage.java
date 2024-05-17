package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage extends LoginPage {
    private final By logoutPopupButton = By.xpath("//li[contains(@class,'dropdown-user')]/a");
    private final By logoutButton = By.xpath("//a[contains(@href,'kilepes.php') and @class='list-group-item']");

    public LogoutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getLogoutText() {
        return getLogoutButton().getAttribute("textContent");
    }

    public void logout() {
        clickLogoutPopupButton();
        // this is a hacky solution, I could not make it work directly with Selenium
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getLogoutButton());
    }

    public void clickLogoutPopupButton() {
        getLogoutPopupButton().click();
    }

    private WebElement getLogoutPopupButton() {
        return waitUntilClickableAndReturnElement(logoutPopupButton);
    }

    private WebElement getLogoutButton() {
        return findElementByLocator(logoutButton);
    }

}
