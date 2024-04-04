package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

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
