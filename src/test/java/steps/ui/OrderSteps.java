package steps.ui;

import net.thucydides.core.annotations.Step;
import pageObjects.MainPage;
import pageObjects.orderPages.*;
import pageObjects.navigationPages.WomenSectionPage;

public class OrderSteps {
    MainPage mainPage;
    WomenSectionPage womenSectionPage;
    ShoppingCartPage shoppingCartPage;
    AddressPage addressPage;
    ShippingPage shippingPage;
    PaymentPage paymentPage;
    ConfirmationPage confirmationPage;
    SummaryPage summaryPage;

    @Step("Go to section")
    public void goToSection() {
        mainPage.goToWomenPage();
    }

    @Step("Order {0} from women section")
    public void makeOrder(String itemToOrder) {
        womenSectionPage.waitUntilPageIsLoaded();
        womenSectionPage.addToCart(itemToOrder);
    }

    @Step("Go to shopping cart")
    public void goToShoppingCart() {
        womenSectionPage.checkoutFromLayerCart();
        shoppingCartPage.waitUntilPageIsLoaded();
    }

    @Step("Complete flow")
    public void completeFlow() {
        shoppingCartPage.checkoutFromCart();
        addressPage.waitUntilPageIsLoaded();
        addressPage.processAddress();
        shippingPage.waitUntilPageIsLoaded();
        shippingPage.processCarrier();
        paymentPage.waitUntilPageIsLoaded();
        paymentPage.selectBankware();
        confirmationPage.waitUntilPageIsLoaded();
        confirmationPage.confirmOrder();
    }

    @Step("Check order Summary")
    public void checkSummary() {
        summaryPage.waitUntilPageIsLoaded();
        summaryPage.checkSummary();
    }
}
