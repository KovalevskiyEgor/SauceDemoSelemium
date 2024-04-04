package page;

import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
@Log
public class CheckoutOverviewPage extends BasePage{
    @FindBy(xpath = "//button[@name=\"finish\"]")
    private WebElement finishButton;
    public CheckoutOverviewPage(){
        PageFactory.initElements(driver, this);
    }
    public void finish(){
        finishButton.click();
    }
    public boolean checkIfPriceCorrect(){
        double itemTotalPrice = Double.parseDouble(driver.findElement(By.className("summary_subtotal_label")).getText().split("\\$")[1].trim());
        double taxPrice = Double.parseDouble(driver.findElement(By.className("summary_tax_label")).getText().split("\\$")[1].trim());
        double totalPrice = Double.parseDouble(driver.findElement(By.className("summary_total_label")).getText().split("\\$")[1].trim());
        double originalPrice = Double.parseDouble(driver.findElement(By.className("inventory_item_price")).getText().split("\\$")[1].trim());
        double result = itemTotalPrice+taxPrice;
        String stringResult = String.valueOf(result).substring(0,String.valueOf(result).indexOf(".")+3);
        log.info("ПРОВЕРКА ЦЕНЫ: цена товара+налог: "+ (itemTotalPrice)+"+"+(taxPrice)+"=="+(stringResult));
        log.info(itemTotalPrice+"=="+originalPrice);
        return (itemTotalPrice==originalPrice&&totalPrice==Double.parseDouble(stringResult));
    }
}
