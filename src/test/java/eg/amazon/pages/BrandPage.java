package eg.amazon.pages;

import eg.amazon.base.BasePage;
import eg.amazon.config.EndPoint;
import eg.amazon.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BrandPage extends BasePage<BrandPage> {

    //Constructor
    public BrandPage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(id = "productInfoList")
    private WebElement productsList;
    @FindBy(xpath = "//ul[@id='productInfoList']/li[@name='productGrid'][1]//div[@name='productImageBox']")
    private WebElement firstProduct;

    //Methods
    @Step("Load the Brand Page")
    @Override
    public BrandPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.BRAND_PAGE_END_POINT);
        return this;
    }

    public ProductPage clickOnFirstProduct() {
        explicitWait().until(ExpectedConditions.visibilityOf(firstProduct));
        actions().scrollToElement(firstProduct).perform();
        firstProduct.click();
        closePreviousTabAndKeepCurrent();
        return new ProductPage(driver);
    }

    public boolean isProductsListDisplayed() {
        return explicitWait().until(ExpectedConditions.visibilityOf(productsList)).isDisplayed();
    }
}
