package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private final By loginPopupButton = By.xpath("//button[contains(@data-modal-open,'belepes.php')]");
    private final By loginField = By.xpath("//input[@type='email']");
    private final By passwordField = By.xpath("//input[@name='pass']");
    private final By loginButton = By.cssSelector(".btn-primary.btn-block");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "https://hardverapro.hu/");
    }

    public void login(String email, String password) {
        clickLoginPopupButton();
        getLoginField().sendKeys(email);
        getPasswordField().sendKeys(password);
        getLoginButton().click();
    }

    public void clickLoginPopupButton() {
        getLoginPopupButton().click();
    }

    public String getLoginFieldPlaceHolder() {
        return getPlaceHolder(getLoginField());
    }

    public String getPasswordFieldPlaceHolder() {
        return getPlaceHolder(getPasswordField());
    }

    public String getLoginButtonText() {
        return getText(getLoginButton());
    }

    public WebElement getLoginPopupButton() {
        return waitUntilVisibleAndReturnElement(loginPopupButton);
    }

    private WebElement getLoginField() {
        return waitUntilVisibleAndReturnElement(loginField);
    }

    private WebElement getPasswordField() {
        return waitUntilVisibleAndReturnElement(passwordField);
    }

    private WebElement getLoginButton() {
        return waitUntilVisibleAndReturnElement(loginButton);
    }

}
