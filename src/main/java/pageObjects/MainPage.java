package pageObjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.WebElement;

public class MainPage extends PageObject {

    @FindBy(className="login")
    WebElement login;

    @FindBy(linkText="Women")
    WebElement womenSection;

    @WhenPageOpens
    public void waitUntilPageIsLoaded() {
        element(login).waitUntilVisible();
    }

    public void goToLoginPage() {
        login.click();
    }

    public void goToWomenPage() {
        womenSection.click();
    }
}