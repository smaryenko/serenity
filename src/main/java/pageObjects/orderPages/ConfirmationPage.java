package pageObjects.orderPages;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebElement;

public class ConfirmationPage extends OrderBasePage {
    @FindBy(xpath="//*[@id='cart_navigation']/button")
    WebElement confirmOrder;

    public void waitUntilPageIsLoaded() {
        waitUntilPageIsLoaded("ORDER SUMMARY");
    }

    public void confirmOrder() {
        element(confirmOrder).waitUntilClickable().click();
    }
}