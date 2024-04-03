package page;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
            log.info("КОРЗИНА ПОСЛЕ ЗАКАЗА ПУСТА:" +false);
            return false;
        }catch (Exception e){
            log.info("КОРЗИНА ПОСЛЕ ЗАКАЗА ПУСТА:" +true);
            return true;
        }
    }
    public boolean checkIfOrderCompleted(){
        try {
            String text = driver.findElement(By.xpath("//div[@class=\"complete-text\"]")).getText();
            boolean isOrderSuccess = text.equals("Your order has been dispatched, and will arrive just as fast as the pony can get there!");
            log.info("\nСРАВНЕНИЕ ТЕКСТА ПРИ УСПЕШНОМ ЗАКАЗЕ:"+isOrderSuccess);
            return isOrderSuccess;
        }catch (Exception e){
            return false;
        }
    }
    public void goBackToProducts(){
        backToProductsButton.click();
    }
}
