package pageObjects.orderPages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentPage extends OrderBasePage {
    @FindBy(className="bankwire")
    WebElement bankwire;

    public void waitUntilPageIsLoaded() {
        waitUntilPageIsLoaded("PAYMENT METHOD");
    }

    public void selectBankware() {
        element(bankwire).waitUntilClickable().click();
    }
}