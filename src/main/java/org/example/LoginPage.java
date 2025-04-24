package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "header img.ubs-header-sing-in-img.ubs-header-sing-in-img-greencity")
    private WebElement signInButton;
    @FindBy(css = "#mat-mdc-dialog-0 app-sign-in h1")
    private WebElement welcomeText;
    @FindBy(css = "h2:nth-child(2)")
    private WebElement signInDetailsText;
    @FindBy(css = "label:nth-child(1)")
    private WebElement emailLabel;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(xpath = "//*[@id=\"email-err-msg\"]/app-error/div")
    private WebElement errorEmail;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(css = "#pass-err-msg")
    private WebElement errorPassword;
    @FindBy(css = ".greenStyle")
    private WebElement signInSubmitButton;
    @FindBy(css = ".alert-general-error")
    private WebElement NonActivatedAcc;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openLoginForm() {
        clickSafely(signInButton);
        wait.until(ExpectedConditions.visibilityOf(welcomeText));
    }

    public String getWelcomeText() {
        return welcomeText.getText();
    }

    public String getSignInDetailsText() {
        return signInDetailsText.getText();
    }

    public void fillEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void fillPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void submit() {
        clickSafely(signInSubmitButton);
    }

    public String getEmailError() {
        return errorEmail.getText();
    }

    public String getPasswordError() {
        return errorPassword.getText();
    }

    public String getNonActivatedAccError() {
        return NonActivatedAcc.getText();
    }

    public String getEmailInputValue() {
        return emailInput.getAttribute("value");
    }

    public String getPasswordInputValue() {
        return passwordInput.getAttribute("value");
    }

    //Для спрощення кліку по елементах та уникнення ситуацій, коли клік не вдається через перехоплення іншими елементами, або елемент ще не готовий до кліку
    private void clickSafely(WebElement element) {
        try {
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            clickableElement.click();
        } catch (Exception e) {
            System.out.println("Unable to click on the element: " + e.getMessage());
        }
    }
}
