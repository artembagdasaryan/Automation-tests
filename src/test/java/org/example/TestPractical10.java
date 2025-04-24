package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
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
    public void signInNotValid(String message) {
        loginPage.openLoginForm();
        loginPage.fillEmail("artem.a.bagdasaryan@gmail.com"); // without @
        loginPage.fillPassword("Archi246!");
        assertThat(loginPage.getEmailError(), is(message));
    }
}