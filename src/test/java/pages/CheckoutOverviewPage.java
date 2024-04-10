package pages;

import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
@Log
public class CheckoutOverviewPage extends BasePage{
    @FindBy(xpath = "//button[@name=\"finish\"]")
    private WebElement finishButton;

    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotalPriceWebElement;

    @FindBy(className = "summary_tax_label")
    private WebElement taxPriceWebElement;

    @FindBy(className = "summary_total_label")
    private WebElement totalPriceWebElement;

    @FindBy(className = "inventory_item_price")
    private WebElement originalPriceWebElement;
    public CheckoutOverviewPage(){
        PageFactory.initElements(driver, this);
    }
    public void finish(){
        finishButton.click();
    }
    public boolean checkIfPriceCorrect(){
        double itemTotalPrice = Double.parseDouble(itemTotalPriceWebElement.getText().split("\\$")[1].trim());
        double taxPrice = Double.parseDouble(taxPriceWebElement.getText().split("\\$")[1].trim());
        double totalPrice = Double.parseDouble(totalPriceWebElement.getText().split("\\$")[1].trim());
        double originalPrice = Double.parseDouble(originalPriceWebElement.getText().split("\\$")[1].trim());
        double result = itemTotalPrice+taxPrice;
        String stringResult = String.valueOf(result).substring(0,String.valueOf(result).indexOf(".")+3);
        log.info("ПРОВЕРКА ЦЕНЫ: цена товара+налог: "+ (itemTotalPrice)+"+"+(taxPrice)+"=="+(stringResult));
        log.info(itemTotalPrice+"=="+originalPrice);
        return (itemTotalPrice==originalPrice&&totalPrice==Double.parseDouble(stringResult));
    }
}