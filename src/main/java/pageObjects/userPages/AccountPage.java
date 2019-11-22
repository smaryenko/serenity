package pageObjects.userPages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class AccountPage extends PageObject {

    private static final String WELCOME_MSG="Welcome to your account.";
    private static final String ACCOUNTPAGE_TITLE_MSG="MY ACCOUNT";

    @FindBy(className="account")
    WebElement account;

    @FindBy(className="info-account")
    WebElement info;

    @FindBy(className="logout")
    WebElement logout;

    @FindBy(css="h1")
    WebElement header;

    public void waitUntilPageIsLoaded() {
        waitFor(urlContains("controller=my-account"));
    }

    public void checkAccountPage(String fullName) {
        assertEquals(ACCOUNTPAGE_TITLE_MSG, header.getText());
        assertEquals(fullName, account.getText());
        assertTrue(info.getText().contains(WELCOME_MSG));
        assertTrue(logout.isDisplayed());
    }

}