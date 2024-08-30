package eg.amazon.testcases;

import eg.amazon.config.EndPoint;
import eg.amazon.pages.HomePage;
import eg.amazon.utils.ConfigUtils;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import eg.amazon.base.BaseTest;
import eg.amazon.pages.LoginPage;
import org.testng.annotations.Test;

@Feature("Signup Feature")
public class LoginTest extends BaseTest {
    @Story("Login Page")
    @Test(description = "Verify that user can't Login with valid but not registered email")
    public void VerifyThatUserCantLoginWithValidButNotRegisteredEmail() {
        HomePage homePage = new HomePage(getDriver());
        LoginPage loginPage = homePage
                .load()
                .hoverOverAccountTab().clickOnLoginButton();
        softAssert.assertTrue(loginPage.getCurrentPageUrl().contains(ConfigUtils.getInstance().getBaseUrl() + EndPoint.LOGIN_PAGE_END_POINT));
        boolean cantUserLogin = loginPage.fillEmailInput().clickOnContinueButton().cantUserLogin();
        softAssert.assertTrue(cantUserLogin);
        softAssert.assertAll();
    }

}
