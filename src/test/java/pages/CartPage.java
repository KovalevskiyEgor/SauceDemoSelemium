package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class CartPage extends BasePage{
    @FindBy(xpath = "//button[@id=\"checkout\"]")
    private WebElement checkoutButton;

    public CartPage (){
        PageFactory.initElements(driver, this);
    }
    @Step("Перейти на страницу заполнения информации о себе")
    public void goToCheckout(){
        checkoutButton.click();
    }
}