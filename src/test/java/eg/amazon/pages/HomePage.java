package eg.amazon.pages;

import eg.amazon.base.BasePage;
import eg.amazon.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends BasePage<HomePage> {

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(id = "nav-link-accountList")
    private WebElement accountTab;
    @FindBy(xpath = "//div[@id='nav-flyout-ya-signin']//a[@data-nav-role='signin']")
    private WebElement signInButton;
    @FindBy(id = "nav-hamburger-menu")
    private WebElement allTab;
    @FindBy(id = "hmenu-canvas")
    private WebElement hamburgerMenu;
    @FindBy(xpath = "//a[@class='hmenu-item' and @href='/gp/goldbox/?ref_=nav_em_navm_deals_0_1_1_24']")
    private WebElement todayDealsTab;
    @FindBy(id = "navbar-backup-backup")
    private WebElement wrongPage;

    //Methods
    @Step("Load the Home Page")
    @Override
    public HomePage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl());
        if (isWrongPageDisplayed()) {
            refresh();
            this.load();
        }
        return this;
    }

    @Step("Hover Over Account Tab")
    public HomePage hoverOverAccountTab() {
        actions().moveToElement(accountTab).perform();
        return new HomePage(driver);
    }

    @Step("Click on Login button")
    public LoginPage clickOnLoginButton() {
        explicitWait().until(ExpectedConditions.visibilityOf(signInButton)).click();
        return new LoginPage(driver);
    }

    @Step("Click on all Tab")
    public HomePage clickOnAllTab() {
        allTab.click();
        return this;
    }

    @Step("Click on Today’s Deals Tab")
    public TodayDealsPage clickOnTodayDealsTab() {
        explicitWait().until(ExpectedConditions.visibilityOf(hamburgerMenu));
        todayDealsTab.click();
        return new TodayDealsPage(driver);
    }

    public boolean isWrongPageDisplayed() {
        try {
            return wrongPage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
