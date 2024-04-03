package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends BasePage{
    @FindBy(xpath = "//div[contains(@class, \"inventory_item_name\")]")
    private WebElement item;

    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }

    public void goToItemPage(){
        item.click();
    }


}
