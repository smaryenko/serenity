package tests.ui.practice;


import models.ui.User;
import models.ui.UsersProvider;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.ui.LoginSteps;
import steps.ui.OrderSteps;
import tests.ui.TestBaseUi;

@RunWith(SerenityRunner.class)
@WithTag(type="ui", name="practice")
public class PracticeTests extends TestBaseUi{

    @Managed
    WebDriver driver;

    @Steps
    LoginSteps loginSteps;

    @Steps
    OrderSteps orderSteps;

    @Test
    public void logInTest() {
        User testUser = UsersProvider.getUser("existing");
        loginSteps.login(testUser.getUserName(), testUser.getPassword());
        loginSteps.checkAccount(testUser.getFullName());
    }

    @Test
    public void checkoutTest() {
        User testUser = UsersProvider.getUser("existing");
        loginSteps.login(testUser.getUserName(), testUser.getPassword());
        orderSteps.goToSection();
        orderSteps.makeOrder("Faded Short Sleeve T-shirts");
        orderSteps.goToShoppingCart();
        orderSteps.completeFlow();
        orderSteps.checkSummary();
    }

    @Test
    public void signIn() {
        //TODO:implement sign in logic
    }

    @Test
    public void logInTestFailForScreenshot() {
        loginSteps.login("qwerr", "qwer");
    }
}