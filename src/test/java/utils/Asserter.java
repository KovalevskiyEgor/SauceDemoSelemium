package utils;

import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.BasePage;

@Log
public class Asserter extends BasePage {
    public boolean checkIfElementInCart(){
        try {
            driver.findElement(By.xpath("//div[@class=\"cart_item\"]"));
            driver.findElement(By.xpath(String.format("//div[contains(text(),\"%s\")]",propertyReader.getProperty("itemName"))));
            return true;
        }catch (Exception e){
            log.warning(e.getMessage());
            return false;
        }
    }
}
