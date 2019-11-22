package steps.ui;

import net.thucydides.core.annotations.Step;
import pageObjects.userPages.AccountPage;
import pageObjects.userPages.LoginPage;
import pageObjects.MainPage;

public class LoginSteps {
    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Step("Login to account with user {0}")
    public void login(String userName, String password) {
        mainPage.open();
        mainPage.goToLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.login(userName, password);
        accountPage.waitUntilPageIsLoaded();
    }

    @Step("Check account page")
    public void checkAccount(String fullName) {
        accountPage.checkAccountPage(fullName);
    }
}
