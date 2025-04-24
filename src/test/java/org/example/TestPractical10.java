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

    @ParameterizedTest
    @CsvSource({
            "valid@email.com, 12345, errorPassword, Invalid password",
            "invalidemail.com, ValidPassword123, errorEmail, Invalid email",
            "nonactivated@greencity.com, ValidPassword123, nonActivatedAcc, Account doesn't exist"
    })
    public void signInNegativeScenarios (String email, String password, String errorType, String expectedMessage) {
        loginPage.openLoginForm();
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
        loginPage.submit();
        switch (errorType) {
            case "errorPassword":
                assertThat(loginPage.getPasswordError(), is(expectedMessage));
                break;
            case "errorEmail":
                assertThat(loginPage.getEmailError(), is(expectedMessage));
                break;
            case "nonActivatedAcc":
                assertThat(loginPage.getNonActivatedAccError(), is(expectedMessage));
                break;
        }
    }

    @ParameterizedTest
    @CsvSource({
            "'', 'This field is required'", // Порожнє поле
            "'invalidemail.com', 'Invalid email format'", // Відсутність @ та .
                "'@invalid.com', 'Invalid email format'", // Відсутність username
            "'invalid@com', 'Invalid email format'", // Відсутність домену
            "'invalid@.com', 'Invalid email format'", // Відсутність локальної частини перед .
            "'invalid@domain.', 'Invalid email format'" // Невірний домен
    })
    public void testEmailValidation(String email, String expectedMessage) {
        loginPage.openLoginForm();
        loginPage.fillEmail(email);
        loginPage.submit();

        assertThat(loginPage.getEmailError(), is(expectedMessage)); // Перевірка повідомлення про помилку
    }

    @ParameterizedTest
    @CsvSource({
            "short, false, Password must be at least 8 characters long",
            "verylongpasswordwithmorethan20characters, false, Password must not exceed 20 characters",
            "password, false, Password must contain at least one uppercase letter",
            "PASSWORD123, false, Password must contain at least one lowercase letter",
            "password123, false, Password must contain at least one special character",
            "password!@, false, Password must contain at least one digit",
            "validPass123!, true, Password is valid"
    })
    public void testPasswordValidate (String password, boolean isValid, String expectedMessage) {
        loginPage.openLoginForm();
        loginPage.fillEmail("valid@email.com"); // Тут використовуємо валідний емейл
        loginPage.fillPassword(password);
        loginPage.submit();

        if (isValid) {
            Assertions.assertTrue(loginPage.getPasswordError().isEmpty(), "Password should be valid, but error appeared.");
        } else {
            Assertions.assertEquals(expectedMessage, loginPage.getPasswordError(), "Password validation message does not match.");
        }
    }

        public void signInNotValid(String message) {
        loginPage.openLoginForm();
        loginPage.fillEmail("artem.a.bagdasaryan@gmail.com"); // without @
        loginPage.fillPassword("Archi246!");
        assertThat(loginPage.getEmailError(), is(message));
    }
}