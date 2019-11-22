package pageObjects.orderPages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShippingPage extends OrderBasePage {
    @FindBy(name="processCarrier")
    WebElement processCarrier;

    @FindBy(id="uniform-cgv")
    WebElement acceptTerms;

    public void waitUntilPageIsLoaded() {
        waitUntilPageIsLoaded("SHIPPING");
    }

    public void processCarrier() {
        element(acceptTerms).waitUntilClickable().click();
        element(processCarrier).waitUntilClickable().click();
    }
}