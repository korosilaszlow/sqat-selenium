
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import pages.LoginPage;

class LoginPageTest extends BaseTest {

    @Test
    void testLoginPopupButtonIsDisplayed() {
        LoginPage loginPage = new LoginPage(driver, wait);

        Assertions.assertNotNull(loginPage.getLoginPopupButton(), "LoginPopupButton is not present.");
    }

    @Test
    void testLoginFieldsPlaceholders() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.clickLoginPopupButton();

        String loginFieldPlaceholder = loginPage.getLoginFieldPlaceHolder();
        String passwordFieldPlaceholder = loginPage.getPasswordFieldPlaceHolder();
        String loginButtonText = loginPage.getLoginButtonText();

        Assertions.assertEquals("Belépési email", loginFieldPlaceholder, "Unexpected login field placeholder.");
        Assertions.assertEquals("Jelszó", passwordFieldPlaceholder, "Unexpected password field placeholder.");
        Assertions.assertEquals("Belépés", loginButtonText, "Unexpected login button text.");
    }

    @Test
    void testLoginFunctionality() {
        LoginPage loginPage = new LoginPage(driver, wait);

        String email = getLoginEmail();
        String password = getLoginPassword();
        loginPage.login(email, password);

        Exception exception = Assertions.assertThrows(TimeoutException.class, loginPage::getLoginPopupButton, "Login button is present after login");
        Assertions.assertTrue(exception.getMessage().contains(FAILED_VISIBILITY_MESSAGE));
    }
}