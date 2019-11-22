package pageObjects.orderPages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddressPage extends OrderBasePage {

    @FindBy(name="processAddress")
    WebElement processAddress;

    public void waitUntilPageIsLoaded() {
        waitUntilPageIsLoaded("ADDRESSES");
    }

    public void processAddress() {
        element(processAddress).waitUntilVisible().click();
    }
}