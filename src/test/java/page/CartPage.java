package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage{
    @FindBy(xpath = "//button[@id=\"checkout\"]")
    private WebElement checkoutButton;

    public CartPage (){
        PageFactory.initElements(driver, this);
    }

    public void goToCheckout(){
        checkoutButton.click();
    }
}
