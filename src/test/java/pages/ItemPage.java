package pages;

import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

@Log
public class ItemPage extends BasePage{
    @FindBy(xpath = "//button[@name=\"add-to-cart\"]")
    private WebElement addToCardButton;

    @FindBy(xpath = "//a[@class=\"shopping_cart_link\"]")
    private WebElement basketButton;

    public ItemPage (){
        PageFactory.initElements(driver, this);
    }
    @Step("добавляем в корзину")
    public void addToCard(){
        addToCardButton.click();
    }

    @Step("перейти в корзину")
    public void goToCard(){
        basketButton.click();
    }

    public boolean checkIfItemAdded(){
        try {
            WebElement element = driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]"));
            return element.getText().equals("1");
        }catch (Exception e){
            log.warning(e.getMessage());
            return false;
        }
    }
    public boolean checkIfButtonDisplayed(){
        return driver.findElement(By.id("remove")).isDisplayed();
    }
}