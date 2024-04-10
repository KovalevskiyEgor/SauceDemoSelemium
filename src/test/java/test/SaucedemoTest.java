package test;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.Asserter;
public class SaucedemoTest extends BaseTest{
    @Test
    @Parameters({"wrongPassword","wrongLogin"})
    public void test(String wrongPassword, String wrongLogin){
        Asserter asserter = new Asserter();
        SoftAssert softAssert = new SoftAssert();

        LoginPage loginPage = new LoginPage();
        softAssert.assertTrue(loginPage.checkAnotherCredentials(wrongLogin, wrongPassword),"проверка входа с неправильным логином и паролем");
        loginPage.login();

        ProductsPage productsPage = new ProductsPage();
        productsPage.goToItemPage();

        ItemPage itemPage = new ItemPage();
        itemPage.addToCard();
        softAssert.assertTrue(itemPage.checkIfItemAdded(),"проверка что в корзине только 1 элемент (цифра на иконке корзины)");
        softAssert.assertTrue(itemPage.checkIfButtonDisplayed(),"проверка что кнопка добавления этого же товара пропала");
        itemPage.goToCard();

        CartPage cartPage = new CartPage();
        Assert.assertTrue(asserter.checkIfElementInCart()); //проверка на наличие вещи в корзине
        cartPage.goToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.insertFirstname(BasePage.propertyReader.getProperty("person.firstname"));
        checkoutPage.insertLastname(BasePage.propertyReader.getProperty("person.lastname"));
        checkoutPage.insertPostalCode(BasePage.propertyReader.getProperty("person.postalcode"));
        checkoutPage.continuee();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
        Assert.assertTrue(asserter.checkIfElementInCart()); //проверка на наличие вещи в корзине и что это именно тот элемент
        softAssert.assertTrue(checkoutOverviewPage.checkIfPriceCorrect(),"проверка что цена правильно посчитана");
        checkoutOverviewPage.finish();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();
        softAssert.assertTrue(checkoutCompletePage.checkIfBasketIsEmpty(),"проверка что корзина пуста");
        softAssert.assertTrue(checkoutCompletePage.checkIfOrderCompleted(),"проверка текста после оформления");
        checkoutCompletePage.goBackToProducts();

        softAssert.assertAll();
    }
}