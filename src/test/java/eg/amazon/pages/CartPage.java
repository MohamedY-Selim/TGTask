package eg.amazon.pages;

import eg.amazon.base.BasePage;
import eg.amazon.config.EndPoint;
import eg.amazon.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class CartPage extends BasePage<CartPage> {

    //Constructor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(id = "NATC_SMART_WAGON_CONF_MSG_SUCCESS")
    private WebElement successMessage;
    @FindBy(css = "[href='/cart?ref_=sw_gtc']")
    private WebElement continueToCartButton;
    @FindBy(xpath = "//span[contains(@class, 'a-size-base-plus') and contains(@class, 'a-color-base') and contains(@class, 'sc-product-title')]")
    private WebElement productTitle;
    @FindBy(xpath = "(//li[@class='sc-product-variation']/span[@class='a-list-item']/span[@class='a-size-small'])[1]")
    private WebElement productSize;
    @FindBy(id = "quantity")
    private WebElement productQty;
    @FindBy(css = "div.sc-badge-price-to-pay span.sc-price")
    private WebElement productPrice;
    @FindBy(css = "#sc-subtotal-amount-buybox > span")
    private WebElement cartSubTotal;
    @FindBy(id = "sc-active-cart")
    private WebElement cart;

    //Methods
    @Step("Load the Brand Page")
    @Override
    public CartPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.FLASHDEALS_PAGE_END_POINT);
        return this;
    }

    public CartPage clickOnContinueToCartButton() {
        continueToCartButton.click();
        return this;
    }

    public boolean isSuccessMessageDisplayed() {
        return explicitWait().until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
    }

    public String getProductTitle() {
        return productTitle.getText();
    }

    public int getProductSize() {
        return Integer.parseInt(productSize.getText().replaceAll("\\D+", ""));
    }

    public int getProductQty() {
        return Integer.parseInt(interactWithDropDown(productQty).getFirstSelectedOption().getText().trim());
    }

    public float getProductPrice() {
        return Float.parseFloat(productPrice.getText().replaceAll("[^\\d.]", ""));
    }

    public float getCartSubTotalPrice() {
        return Float.parseFloat(cartSubTotal.getText().replaceAll("[^\\d.]", ""));
    }

    public boolean isCartDisplayed() {
        return cart.isDisplayed();
    }
}
