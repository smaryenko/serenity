package pageObjects.orderPages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderBasePage extends PageObject {
    @FindBy(className="page-heading")
    WebElement heading;

    public void waitUntilPageIsLoaded(String expectedTitle) {
        waitForCondition().until(
                ExpectedConditions.textToBePresentInElement(heading, expectedTitle)
        );
    }
}
