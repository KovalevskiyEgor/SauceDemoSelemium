package page;

import lombok.extern.java.Log;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Random;

@Log
public class ProductsPage extends BasePage{

    public void goToItemPage(){
        List<WebElement> items = driver.findElements(By.xpath("//div[@class=\"inventory_item_name \"]"));
        Random rand = new Random();
        int randomNumber = rand.nextInt(5);
        WebElement item  =  items.get(randomNumber);
        BasePage.propertyReader.setValue("item.name",item.getText());
        log.info("Название рандомной вещи - "+BasePage.propertyReader.getProperty("item.name"));
        item.click();
    }
}
