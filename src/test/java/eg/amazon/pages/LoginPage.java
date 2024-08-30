package eg.amazon.pages;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import eg.amazon.base.BasePage;
import eg.amazon.config.EndPoint;
import eg.amazon.utils.ConfigUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage<LoginPage> {
    Faker faker = new Faker();

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Elements
    @FindBy(xpath = "//div[@class='a-box']")
    private WebElement loginForm;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//span[@id='continue']")
    private WebElement continueButton;

    @FindBy(id = "auth-error-message-box")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@id='intent-confirmation-container']/h1")
    private WebElement intentMessage;

    @FindBy(xpath = "//div[@class='a-box-inner']/h1")
    private WebElement registrationHeader;

    // Methods
    @Step("Load the Login Page")
    @Override
    public LoginPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl()+EndPoint.LOGIN_PAGE_END_POINT);
        return this;
    }

    @Step("Fill Email Input with valid but not registered email")
    public LoginPage fillEmailInput() {
        emailInput.sendKeys(faker.internet().emailAddress());
        return this;
    }

    @Step("Check if login form displayed")
    public boolean isLoginFormDisplayed() {
        return loginForm.isDisplayed();
    }

    @Step("Click on Continue Button")
    public LoginPage clickOnContinueButton() {
        continueButton.click();
        return this;
    }

    @Step("Verify that user can't login")
    public boolean cantUserLogin() {
        return isErrorMessageDisplayed() || isIntentMessageDisplayed() || isRegistrationHeaderDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isIntentMessageDisplayed() {
        try {
            return intentMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isRegistrationHeaderDisplayed() {
        try {
            return registrationHeader.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
