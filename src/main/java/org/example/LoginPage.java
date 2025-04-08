package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(css = "app-header:nth-child(1) .ubs-header-sing-in-img")
    private WebElement signInButton;
    @FindBy(css = ".ng-star-inserted > h1")
    private WebElement welcomeText;
    @FindBy(css = "h2:nth-child(2)")
    private WebElement signInDetailsText;
    @FindBy(css = "label:nth-child(1)")
    private WebElement emailLabel;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(css = ".ubsStyle")
    private WebElement signInSubmitButton;
    @FindBy(xpath = "//*[@id=\"email-err-msg\"]/app-error/div")
    private WebElement errorEmail;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void openLoginForm() {
        signInButton.click();
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
        signInSubmitButton.click();
    }

    public String getEmailError() {
        return errorEmail.getText();
    }

    public String getEmailInputValue() {
        return emailInput.getAttribute("value");
    }

    public String getPasswordInputValue() {
        return passwordInput.getAttribute("value");
    }
}