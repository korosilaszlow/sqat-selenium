import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.MainPage;

import java.util.ArrayList;
import java.util.List;

class MainPageTest extends BaseTest {

    @Test
    void verifyTitle() {
        MainPage mainPage = new MainPage(driver, wait);

        String title = mainPage.getTitle();
        Assertions.assertFalse(title.isEmpty(), "Title is not displayed.");
        Assertions.assertEquals("Itt megtal�lod, amit keresel!", title, "Title does not equals \"Itt megtal�lod, amit keresel!\".");
    }

    @Test
    void verifyNavigationElements() {
        MainPage mainPage = new MainPage(driver, wait);
        List<WebElement> navigationElements = mainPage.getNavigationsBelowSearchBar();
        List<String> visitedUrls = new ArrayList<>();

        String mainPageUrl = driver.getCurrentUrl();

        String currentUrl;
        for (WebElement element : navigationElements) {
            element.click();
            currentUrl = driver.getCurrentUrl();
            visitedUrls.add(currentUrl);
            Assertions.assertTrue(mainPage.getPageTitle().contains("HardverApr�"), "Page title does not contain HardverApr�.");
            Assertions.assertTrue(mainPage.getPageTitle().contains("hirdet�sek"), "Page title does not contain hirdet�sek.");
        }

        String urlForBackNavigation;
        for (String url : visitedUrls) {
            urlForBackNavigation = driver.getCurrentUrl();
            Assertions.assertEquals(url, urlForBackNavigation, "Back navigation failed. The url is not correct.");
            driver.navigate().back();
        }

        Assertions.assertEquals(mainPageUrl, driver.getCurrentUrl(), "After back navigations, did not went back to the main page.");
    }
}