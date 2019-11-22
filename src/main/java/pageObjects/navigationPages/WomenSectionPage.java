package pageObjects.navigationPages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WomenSectionPage extends PageObject {

    String itemXpath = "//a[@title='%s']/ancestor::li";

    @FindBy(className="cat-name")
    WebElement catname;

    @FindBy(name="Submit")
    WebElement submit;

    @FindBy(id="layer_cart")
    WebElement layercart;

    private String checkoutLink = "//a[@class and @title='Proceed to checkout']";

    public void waitUntilPageIsLoaded() {
        waitForCondition().until(
                ExpectedConditions.textToBePresentInElement(catname, "WOMEN")
        );
    }

    public void addToCart(String itemToOrder) {
        String xpath = String.format(itemXpath, itemToOrder);
        WebElement itemElement = find(By.xpath(xpath));
        itemElement.click();
        itemElement.click();
        element(submit).waitUntilClickable().click();
    }

    public void checkoutFromLayerCart() {
        element(layercart).waitUntilVisible().find(By.xpath(checkoutLink)).click();
    }
}
