package pageObjects.orderPages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class SummaryPage extends OrderBasePage {
    private static final String SUMMARYPAGE_TITLE_MSG="ORDER CONFIRMATION";
    private static final String CONFIRMATION_MSG="Your order on My Store is complete.";

    @FindBy(xpath="//*[@id='cart_navigation']/button")
    WebElement confirmOrder;

    @FindBy(css="h1")
    WebElement header;

    @FindBy(xpath="//li[@class='step_done step_done_last four']")
    WebElement stepFour;

    @FindBy(xpath="//li[@id='step_end' and @class='step_current last']")
    WebElement stepLast;

    @FindBy(xpath="//*[@class='cheque-indent']/strong")
    WebElement chequeindent;

    public void waitUntilPageIsLoaded() {
        waitFor(urlContains("controller=order-confirmation"));
    }

    public void checkSummary() {
        assertEquals(SUMMARYPAGE_TITLE_MSG, header.getText());
        assertEquals(CONFIRMATION_MSG, chequeindent.getText());
        assertTrue(stepLast.isDisplayed());
        assertTrue(stepFour.isDisplayed());
    }
}