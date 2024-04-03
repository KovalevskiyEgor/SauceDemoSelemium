package page;

import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log
public class ItemPage extends BasePage{
    @FindBy(xpath = "//button[@name=\"add-to-cart\"]")
    private WebElement addToCardButton;

    @FindBy(xpath = "//a[@class=\"shopping_cart_link\"]")
    private WebElement basketButton;

    public ItemPage (){
        PageFactory.initElements(driver, this);
    }

    public void addToCard(){
        addToCardButton.click();
    }

    public void goToCard(){
        basketButton.click();
    }

    public boolean checkIfItemAdded(){
        try {
            WebElement element = driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]"));
            driver.findElement(By.id("remove"));
            return element.getText().equals("1");
        }catch (Exception e){
            log.warning(e.getMessage());
            return false;
        }
    }
}
