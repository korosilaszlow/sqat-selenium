import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.PCSearchPage;

import java.time.Duration;

class PCSearchPageTest extends BaseTest {

    @Test
    void testAdvancedSearchButton() throws InterruptedException {
        PCSearchPage pcSearchPage = new PCSearchPage(driver, wait);

        Assertions.assertTrue(pcSearchPage.isAdvancedSearchClosed(), "AdvancedSearch button should not be opened by default");

        pcSearchPage.clickAdvancedSearch();

        Assertions.assertFalse(pcSearchPage.isAdvancedSearchClosed(), "AdvancedSearch button should be opened after clicking");

        Thread.sleep(Duration.ofSeconds(1));

        pcSearchPage.clickAdvancedSearch();

        Assertions.assertTrue(pcSearchPage.isAdvancedSearchClosed(), "AdvancedSearch button should be closed after clicking again");
    }

    @Test
    void testSearchWithResults() {
        PCSearchPage pcSearchPage = new PCSearchPage(driver, wait);

        pcSearchPage.clickAdvancedSearch();
        pcSearchPage.search("PC", "Budapest", "1000", true);

        Assertions.assertTrue(pcSearchPage.getFoundSearchText().contains("találat erre:"), "Found Search Text should contain \"találat erre:\"");
    }

    @Test
    void testSearchWithoutResults() {
        PCSearchPage pcSearchPage = new PCSearchPage(driver, wait);

        pcSearchPage.clickAdvancedSearch();
        pcSearchPage.search("Unreal Technology", "Budapest", "100000000", false);

        Assertions.assertEquals(pcSearchPage.getNotFoundSearchText(), "Hoppá!");
    }
}