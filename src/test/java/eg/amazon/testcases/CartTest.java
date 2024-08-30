package eg.amazon.utils;

import eg.amazon.base.BaseTest;
import eg.amazon.config.EndPoint;
import eg.amazon.objects.Product;
import eg.amazon.pages.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Cart Feature")
public class CartTest extends BaseTest {

    @Story("Add Items To Cart")
    @Test(description = "(Verify that Items are added to cart correctly)")
    public void VerifyThatItemsAreAddedToCartCorrectly() {
        HomePage homePage = new HomePage(getDriver());

        TodayDealsPage todayDealsPage = homePage
                .load()
                .clickOnAllTab()
                .clickOnTodayDealsTab();
        softAssert.assertTrue(todayDealsPage.getCurrentPageUrl().contains(ConfigUtils.getInstance().getBaseUrl() + EndPoint.TODAYDEALS_PAGE_END_POINT));
        boolean isTodayDealsPageBannerDisplayed = todayDealsPage.isTodayDealsPageBannerDisplayed();
        softAssert.assertTrue(isTodayDealsPageBannerDisplayed);

        FlashDealsPage flashDealsPage = todayDealsPage.clickOnFlashDealsTab();
        softAssert.assertTrue(flashDealsPage.getCurrentPageUrl().contains(ConfigUtils.getInstance().getBaseUrl() + EndPoint.FLASHDEALS_PAGE_END_POINT));
        boolean isFlashDealsContainerDisplayed = flashDealsPage.isFlashDealsContainerDisplayed();
        softAssert.assertTrue(isFlashDealsContainerDisplayed);

        BrandPage brandPage = flashDealsPage.clickOnSecondBrand();
        softAssert.assertTrue(brandPage.getCurrentPageUrl().contains(ConfigUtils.getInstance().getBaseUrl() + EndPoint.BRAND_PAGE_END_POINT));
        boolean isProductsListDisplayed = brandPage.isProductsListDisplayed();
        softAssert.assertTrue(isProductsListDisplayed);

        ProductPage productPage = brandPage.clickOnFirstProduct();
        softAssert.assertTrue(productPage.getCurrentPageUrl().contains(ConfigUtils.getInstance().getBaseUrl() + EndPoint.PRODUCT_PAGE_END_POINT));
        boolean isProductsContainerDisplayed = productPage.isProductsContainerDisplayed();
        softAssert.assertTrue(isProductsContainerDisplayed);


        CartPage cartPage = productPage.addProductToCartWithQtyOfTwo();
        Product product = productPage.getProduct();
        softAssert.assertTrue(cartPage.getCurrentPageUrl().contains(ConfigUtils.getInstance().getBaseUrl() + EndPoint.CART_PAGE_END_POINT));
        boolean isSuccessMessageDisplayed = cartPage.isSuccessMessageDisplayed();
        softAssert.assertTrue(isSuccessMessageDisplayed);
        boolean isCartDisplayed = cartPage.clickOnContinueToCartButton().isCartDisplayed();
        softAssert.assertTrue(isCartDisplayed);
        softAssert.assertTrue(cartPage.getProductTitle().contains(product.getTitle()));
        softAssert.assertEquals(cartPage.getProductQty(), product.getQty());
        softAssert.assertEquals(cartPage.getProductSize(), product.getSize());
        softAssert.assertEquals(cartPage.getProductPrice(), product.getPrice());
        softAssert.assertEquals(cartPage.getCartSubTotalPrice(), product.getPrice() * product.getQty());

        softAssert.assertAll();


    }


}
