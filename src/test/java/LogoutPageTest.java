import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import pages.LogoutPage;

class LogoutPageTest extends BaseTest {

    @Test
    void testLogoutButtonText() {
        LogoutPage logoutPage = new LogoutPage(driver, wait);

        String email = getLoginEmail();
        String password = getLoginPassword();
        logoutPage.login(email, password);

        Exception exception = Assertions.assertThrows(TimeoutException.class, logoutPage::getLoginPopupButton, "Login popup button is present");
        Assertions.assertTrue(exception.getMessage().contains(FAILED_VISIBILITY_MESSAGE));

        logoutPage.clickLogoutPopupButton();
        String logoutText = logoutPage.getLogoutText().trim();
        Assertions.assertEquals("Kilépés", logoutText, "Logout text is wrong.");
    }

    @Test
    void testLogout() {
        LogoutPage logoutPage = new LogoutPage(driver, wait);

        String email = getLoginEmail();
        String password = getLoginPassword();
        logoutPage.login(email, password);

        Exception exception = Assertions.assertThrows(TimeoutException.class, logoutPage::getLoginPopupButton, "Login popup button is present");
        Assertions.assertTrue(exception.getMessage().contains(FAILED_VISIBILITY_MESSAGE));

        logoutPage.logout();

        Assertions.assertDoesNotThrow(logoutPage::getLoginPopupButton, "Login popup button is not present");

    }
}
