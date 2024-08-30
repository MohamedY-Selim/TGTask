package eg.amazon.pages;

import eg.amazon.base.BasePage;
import eg.amazon.config.EndPoint;
import eg.amazon.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class TodayDealsPage extends BasePage<TodayDealsPage> {

    //Constructor
    public TodayDealsPage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(xpath = "//div[@class='bxc-grid__image   bxc-grid__image--light']")
    private WebElement todayDealsPageBanner;
    @FindBy(css = "[data-testid='filter-bubble-deals-collection-bxgy']")
    private WebElement flashDealsTab;

    //Methods
    @Step("Load the Today's Deals Page")
    @Override
    public TodayDealsPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.TODAYDEALS_PAGE_END_POINT);
        return this;
    }

    public FlashDealsPage clickOnFlashDealsTab() {
        flashDealsTab.click();
        return new FlashDealsPage(driver);
    }

    public boolean isTodayDealsPageBannerDisplayed() {
        return explicitWait().until(ExpectedConditions.visibilityOf(todayDealsPageBanner)).isDisplayed();
    }
}
