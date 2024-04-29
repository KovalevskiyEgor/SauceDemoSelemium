package pages;

import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

@Log
public class CheckoutCompletePage extends BasePage {
    @FindBy(xpath = "//button[@id=\"back-to-products\"]")
    private WebElement backToProductsButton;

    public CheckoutCompletePage(){
        PageFactory.initElements(driver, this);
    }

    public boolean checkIfBasketIsEmpty(){
        try {
            driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]"));
            return false;
        }catch (Exception e){
            return true;
        }
    }
    public boolean checkIfOrderCompleted(){
        try {
            String text = driver.findElement(By.xpath("//div[@class=\"complete-text\"]")).getText();
            boolean isOrderSuccess = text.equals("Your order has been dispatched, and will arrive just as fast as the pony can get there!");
            return isOrderSuccess;
        }catch (Exception e){
            return false;
        }
    }
    @Step("переходим обратно на страницу с вещами")
    public void goBackToProducts(){
        backToProductsButton.click();
    }
}