package pageObjects.userPages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageObject {

    @FindBy(id="email")
    WebElement email;

    @FindBy(id="passwd")
    WebElement pass;

    @FindBy(id="SubmitLogin")
    WebElement submit;

    public void waitUntilPageIsLoaded() {
        element(email).waitUntilVisible();
    }

    public void login(String userName, String password) {
        email.sendKeys(userName);
        pass.sendKeys(password);
        submit.click();
    }
}