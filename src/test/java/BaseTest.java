import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected static Properties properties;

    protected final static String FAILED_VISIBILITY_MESSAGE = "Expected condition failed: waiting for visibility of element";

    protected WebDriver driver;

    protected WebDriverWait wait;

    @BeforeAll
    public static void loadProperties() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setup() throws MalformedURLException {
        String geckoPath = getGeckoPath();
        if (geckoPath != null) {
            System.setProperty("webdriver.gecko.driver", geckoPath);
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }
        else { // fall back to docker
            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        }
        driver.manage().window().setSize(new Dimension(1280, 720));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected String getLoginEmail() {
        return properties.getProperty("loginEmail");
    }

    protected String getLoginPassword() {
        return properties.getProperty("loginPassword");
    }

    protected String getGeckoPath() {
        return properties.getProperty("geckoPath");
    }
}