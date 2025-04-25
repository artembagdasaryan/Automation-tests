package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPractical11 {
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

    @ParameterizedTest
    @CsvSource({
            "valid@email.com, 12345, errorPassword, Password have from 8 to 20 characters long without spaces and contain at least one uppercase letter (A-Z), one lowercase letter (a-z), a digit (0-9), and a special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
            "invalidemail.com, ValidPassword123, errorEmail, Please check that your e-mail address is indicated correctly",
            "nonactivated@greencity.com, ValidPassword123, nonActivatedAcc, Bad email or password"
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
    @CsvFileSource(resources = "/negative_login_scenarios.csv", numLinesToSkip = 1)
    public void signInNegativeScenariosViaFile (String email, String password, String errorType, String expectedMessage) {
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
            "'', 'Email is required.'", // Порожнє поле
            "'invalidemail.com', 'Please check that your e-mail address is indicated correctly'", // Відсутність @ та .
                "'@invalid.com', 'Please check that your e-mail address is indicated correctly'", // Відсутність username
            "'invalid@com', 'Please check that your e-mail address is indicated correctly'", // Відсутність домену
            "'invalid@.com', 'Please check that your e-mail address is indicated correctly'", // Відсутність локальної частини перед .
            "'invalid@domain.', 'Please check that your e-mail address is indicated correctly'" // Невірний домен
    })
    public void testEmailValidation(String email, String expectedMessage) {
        loginPage.openLoginForm();
        loginPage.fillEmail(email);
        loginPage.submit();

        assertThat(loginPage.getEmailError(), is(expectedMessage)); // Перевірка повідомлення про помилку
    }

    @ParameterizedTest
    @CsvSource({
            "short, false, Password have from 8 to 20 characters long without spaces and contain at least one uppercase letter (A-Z), one lowercase letter (a-z), a digit (0-9), and a special character (~`!@#$%^&*()+=_-{}[]|:;”’?/<>,.)",
            "verylongpasswordwithmorethan20characters, false, Password must be less than 20 characters long without spaces.",
            "'', false, This field is required",
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
}