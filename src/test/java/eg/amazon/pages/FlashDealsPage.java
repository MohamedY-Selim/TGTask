package eg.amazon.pages;

import eg.amazon.base.BasePage;
import eg.amazon.config.EndPoint;
import eg.amazon.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class FlashDealsPage extends BasePage<FlashDealsPage> {

    //Constructor
    public FlashDealsPage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(xpath = "//div[contains(@class, 'a-container') and contains(@class, 'octopus-page-style')]")
    private WebElement flashDealsContainer;
    @FindBy(id = "sobe_d_b_1_2")
    private WebElement secondBrand;

    //Methods
    @Step("Load the Flash Deals Page")
    @Override
    public FlashDealsPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.FLASHDEALS_PAGE_END_POINT);
        return this;
    }

    public BrandPage clickOnSecondBrand() {
        secondBrand.click();
        return new BrandPage(driver);
    }

    public boolean isFlashDealsContainerDisplayed() {
        return explicitWait().until(ExpectedConditions.visibilityOf(flashDealsContainer)).isDisplayed();
    }
}
