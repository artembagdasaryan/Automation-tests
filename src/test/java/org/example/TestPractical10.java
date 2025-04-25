package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPractical10 {
    private static final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private static WebDriver driver;
    private LoginPage loginPage;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.get("https://www.greencity.cx.ua/#/greenCity");
        driver.manage().window().setSize(new Dimension(1264, 798));
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("@AfterAll executed");
    }

    @BeforeEach
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void verifyTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(webDriver -> webDriver.getTitle().contains("GreenCity"));

        Assertions.assertEquals("GreenCity", driver.getTitle());
    }

    @ParameterizedTest
    @CsvSource({
            "artem.a.bagdsaryan@gmail.com, Archi246!"
    })


    public void signIn(String email, String password) {
        loginPage.openLoginForm();
        assertThat(loginPage.getWelcomeText(), is("Welcome back!"));
        assertThat(loginPage.getSignInDetailsText(), is("Please enter your details to sign in."));
        loginPage.fillEmail(email);
        assertThat(loginPage.getEmailInputValue(), is(email));
        loginPage.fillPassword(password);
        assertThat(loginPage.getPasswordInputValue(), is(password));
        loginPage.submit();
    }
    @Test
    @DisplayName("Sign in with invalid email should show error")
    public void notValidEmail() {
        loginPage.openLoginForm();
        loginPage.fillEmail("invalid@com");
        loginPage.fillPassword("Qwerty1!");
        loginPage.submit();
        String ExpectedError = "Email must be valid";
        assertThat(loginPage.getEmailError(), is(ExpectedError));
    }
    @Test
    @DisplayName("Sign in with empty email should show error")
    public void emptyEmail() {
        loginPage.openLoginForm();
        loginPage.fillEmail("");
        loginPage.fillPassword("Qwerty1!");
        loginPage.submit();
        String ExpectedError = "Email is required.";
        assertThat(loginPage.getEmailError(), is(ExpectedError));
    }
    @Test
    @DisplayName("Sign in with invalid password should show error")
    public void notValidPassword() {
        loginPage.openLoginForm();
        loginPage.fillEmail("invalid@ar.com");
        loginPage.fillPassword("123");
        loginPage.submit();
        String ExpectedError = "Password have from 8 to 20 characters long without spaces and contain at least one uppercase letter (A-Z), one lowercase letter (a-z), a digit (0-9), and a special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)\"";
        assertThat(loginPage.getPasswordError(), is(ExpectedError));
    }
    @Test
    @DisplayName("Sign in with empty password should show error")
    public void emptyPassword() {
        loginPage.openLoginForm();
        loginPage.fillEmail("qwerty@qwerty.com");
        loginPage.fillPassword("");
        loginPage.submit();
        String ExpectedError = "This field is required";
        assertThat(loginPage.getPasswordError(), is(ExpectedError));
    }
    @Test
    @DisplayName("Sign in with invalid password should show error")
    public void signWithValidEmailPassword() {
        loginPage.openLoginForm();
        loginPage.fillEmail("invalid@ar.com");
        loginPage.fillPassword("Qwerty1!");
        loginPage.submit();
        String ExpectedError = "Bad email or password";
        assertThat(loginPage.getNonActivatedAccError(), is(ExpectedError));
    }
}