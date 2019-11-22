package pageObjects.orderPages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage extends PageObject {
    @FindBy(id="cart_title")
    WebElement carttitle;

    @FindBy(xpath="//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
    WebElement checkout;

    public void waitUntilPageIsLoaded() {
        element(carttitle).waitUntilVisible();
    }

    public void checkoutFromCart() {
        element(checkout).waitUntilVisible().click();
    }
}