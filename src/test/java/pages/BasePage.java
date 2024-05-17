package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait, String url) {
        this.driver = driver;
        this.wait = wait;
        this.driver.get(url);
        handleConstentPopup(driver);
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    protected WebElement waitUntilVisibleAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return findElementByLocator(locator);
    }

    protected WebElement waitUntilClickableAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.elementToBeClickable(locator));
        return findElementByLocator(locator);
    }

    protected WebElement findElementByLocator(By locator) {
        return driver.findElement(locator);
    }

    protected WebElement getInnerH1(WebElement element) {
        return element.findElement(By.tagName("h1"));
    }

    protected String getPlaceHolder(WebElement webElement) {
        return webElement.getAttribute("placeholder");
    }

    protected String getText(WebElement webElement) {
        return webElement.getText();
    }

    private void handleConstentPopup(WebDriver driver) {
        Cookie consent = getEuConsentCookie();
        driver.manage().addCookie(consent);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(String.format("window.localStorage.setItem('%s','%s');", "gbc_consent", "[{\"id\":1,\"defaultValue\":\"DENIED\",\"selected\":true}]"));
        javascriptExecutor.executeScript(String.format("window.localStorage.setItem('%s','%s');", "_cmpRepromptHash", "CP-tP0AP-tP0AAKA1AHUA0EsAP_gAEPgAA6gKKtV_H__bW1r8X73aft0eY1P9_j77sQxBhfJE-4FzLvW_JwXx2ExNA36tqIKmRIEu3bBIQNlHJDUTVCgaogVryDMakWcoTNKJ6BkiFMRO2dYCF5vmwtj-QKY5vr993dx2D-t_dv83dzyz4VHn3a5_2e0WJCdA58tDfv9bROb-9IPd_58v4v0_F_rE2_eT1l_tevp7D9-ct87_XW-9_fff79Ll9-goqAWYaFRAHWRISEGgYRQIAVBWEBFAgAAABIGiAgBMGBTsDAJdYSIAQAoABggBAACjIAEAAAkACEQAQAFAgAAgECgABAAgEAgAYGAAMAFgIBAACA6BCmBBAoFgAkZkRCmBCFAkEBLZUIJAECCuEIRZ4EEAiJgoAAASACsAAQFgsDiSQErEggS4g2gAAIAEAghAqEUnZgCCBM2WqvFE2jK0gLR84WAAAAA.YAAAAAAAAAAA.1.C5AwHLqo/pWA59C0gmSYpQ=="));
        this.driver.navigate().refresh();
    }

    private static Cookie getEuConsentCookie() {
        return new Cookie.Builder("euconsent-v2",
                "CP-tP0AP-tP0AAKA1AHUA0EsAP_gAEPgAA6gKKtV_H__bW1r8X73aft0eY1P9_j77sQxBhfJE-4FzLvW_JwXx2ExNA36tqIKmRIEu3bBIQNlHJDUTVCgaogVryDMakWcoTNKJ6BkiFMRO2dYCF5vmwtj-QKY5vr993dx2D-t_dv83dzyz4VHn3a5_2e0WJCdA58tDfv9bROb-9IPd_58v4v0_F_rE2_eT1l_tevp7D9-ct87_XW-9_fff79Ll9-goqAWYaFRAHWRISEGgYRQIAVBWEBFAgAAABIGiAgBMGBTsDAJdYSIAQAoABggBAACjIAEAAAkACEQAQAFAgAAgECgABAAgEAgAYGAAMAFgIBAACA6BCmBBAoFgAkZkRCmBCFAkEBLZUIJAECCuEIRZ4EEAiJgoAAASACsAAQFgsDiSQErEggS4g2gAAIAEAghAqEUnZgCCBM2WqvFE2jK0gLR84WAAAAA.YAAAAAAAAAAA")
                .domain("hardverapro.hu")
                .isSecure(true)
                .build();
    }
}

