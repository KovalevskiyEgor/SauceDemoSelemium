package pages;

import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
@Log
public class LoginPage extends BasePage{
    @FindBy(xpath = "//input[@placeholder=\"Username\"]")
    private WebElement loginField;

    @FindBy(xpath = "//input[@placeholder=\"Password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    private WebElement loginButton;

    public LoginPage() {
        PageFactory.initElements(driver,this);
    }
    @Step("логинимся")
    public void login(){
        passwordField.clear();
        loginField.clear();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementValue(loginField, ""));
        wait.until(ExpectedConditions.textToBePresentInElementValue(passwordField, ""));

        String password = driver.findElement(By.className("login_password")).getText().split(":")[1].trim();
        String login = driver.findElement(By.xpath("//div[@class='login_credentials']")).getText().split(":")[1].split("\n")[1];

        loginField.sendKeys(login);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }
    public boolean checkAnotherCredentials(String login, String password){
        try {
            loginField.sendKeys(login);
            passwordField.sendKeys(password);
            loginButton.click();
            driver.findElement(By.xpath("//h3"));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}