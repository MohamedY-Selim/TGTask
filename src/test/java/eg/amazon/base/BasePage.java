package eg.amazon.base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BasePage<T extends BasePage<T>> {

    //Driver
    protected WebDriver driver;

    //Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public T load() {
        return (T) this;
    }

    @Step("get Current Url")
    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    public Select interactWithDropDown(WebElement dropDownElement) {
        return new Select(dropDownElement);
    }

    public WebDriverWait explicitWait() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(15));

    }

    public Actions actions() {
        return new Actions(this.driver);

    }


    public void selectOptionByIndexFromDropDown(WebElement dropDownElement, int index) {
        Select dropDown = interactWithDropDown(dropDownElement);
        dropDown.selectByIndex(index);
    }

    public void selectOptionByValueFromDropDown(WebElement dropDownElement, String value) {
        Select dropDown = interactWithDropDown(dropDownElement);
        dropDown.selectByValue(value);
        WebElement quantityElement = driver.findElement(By.xpath("//a[@data-value='{\"stringVal\":\"" + value + "\"}']"));
        quantityElement.click();
    }

    public void closePreviousTabAndKeepCurrent() {
        explicitWait().until(ExpectedConditions.numberOfWindowsToBe(2));
        String originalWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(originalWindowHandle)) {
                driver.switchTo().window(handle);
                driver.switchTo().window(originalWindowHandle).close();
                break;
            }
        }
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    public void refresh() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

}