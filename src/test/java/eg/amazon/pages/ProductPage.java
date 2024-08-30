package eg.amazon.pages;

import eg.amazon.base.BasePage;
import eg.amazon.config.EndPoint;
import eg.amazon.objects.Product;
import eg.amazon.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class ProductPage extends BasePage<ProductPage> {
    Product product;

    //Constructor
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    //Elements
    @FindBy(xpath = "//div[@id='ppd']")
    private WebElement productContainer;
    @FindBy(id = "productTitle")
    private WebElement productTitle;
    @FindBy(xpath = "//span[@class='a-price-whole']")
    private WebElement productPriceWhole;
    @FindBy(xpath = "//span[@class='a-price-fraction']")
    private WebElement productPriceFraction;
    @FindBy(id = "native_dropdown_selected_size_name")
    private WebElement sizeDropdown;
    @FindBy(css = "[name='quantity']")
    private WebElement qtyDropdown;
    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    //Methods
    @Step("Load the Product Page")
    @Override
    public ProductPage load() {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + EndPoint.PRODUCT_PAGE_END_POINT);
        return this;
    }

    public CartPage addProductToCartWithQtyOfTwo() {
        selectOptionByIndexFromDropDown(sizeDropdown, 1);
        explicitWait().until(ExpectedConditions.visibilityOf(qtyDropdown));
        selectOptionByValueFromDropDown(qtyDropdown, "2");
        product = new Product(getTitle(), getSize(), 2, getPrice());
        addToCartButton.click();
        return new CartPage(driver);
    }

    public boolean isProductsContainerDisplayed() {
        return explicitWait().until(ExpectedConditions.visibilityOf(productContainer)).isDisplayed();
    }

    public Product getProduct() {
        return this.product;
    }

    public String getTitle() {
        String[] titleParts = productTitle.getText().split(" ");
        return titleParts[0] + " " + titleParts[1];
    }

    public float getPrice() {
        return Float.parseFloat(productPriceWhole.getText().replace(",", "") + "." + productPriceFraction.getText());
    }

    public int getSize() {
        return Integer.parseInt(new Select(sizeDropdown).getFirstSelectedOption().getText().replaceAll("\\D+", ""));
    }
}
