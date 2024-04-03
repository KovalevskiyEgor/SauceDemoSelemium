package test;

import org.testng.Assert;
import org.testng.annotations.*;
import page.*;
import utils.Asserter;

public class SaucedemoTest extends BaseTest{
    @Test
    @Parameters({"wrongPassword","wrongLogin"})
    public void test(String wrongPassword, String wrongLogin){
        Asserter asserter = new Asserter();

        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.checkAnotherCredentials(wrongLogin, wrongPassword)); //проверка входа с неправильным логином и паролем
        loginPage.login();

        ProductsPage productsPage = new ProductsPage();
        productsPage.goToItemPage();

        ItemPage itemPage = new ItemPage();
        itemPage.addToCard();
        Assert.assertTrue(itemPage.checkIfItemAdded()); //проверка что в корзине только 1 элемент (цифра на иконке корзины) и что кнопка нажалась
        itemPage.goToCard();

        CartPage cartPage = new CartPage();
        Assert.assertTrue(asserter.checkIfElementInCart()); //проверка на наличие вещи в корзине
        cartPage.goToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.insertFirstname(BasePage.propertyReader.getProperty("firstName"));
        checkoutPage.insertLastname(BasePage.propertyReader.getProperty("lastName"));
        checkoutPage.insertPostalCode(BasePage.propertyReader.getProperty("postalCode"));
        checkoutPage.continuee();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
        Assert.assertTrue(asserter.checkIfElementInCart()); //проверка на наличие вещи в корзине и что это именно тот элемент
        Assert.assertTrue(checkoutOverviewPage.checkIfPriceCorrect()); //проверка что цена правильно посчитана
        checkoutOverviewPage.finish();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();
        Assert.assertTrue(checkoutCompletePage.checkIfBasketIsEmpty()); //проверка что корзина пуста
        Assert.assertTrue(checkoutCompletePage.checkIfOrderCompleted()); //проверка текста после оформления
        checkoutCompletePage.goBackToProducts();
    }
}
