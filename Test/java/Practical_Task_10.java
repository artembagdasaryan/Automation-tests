import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class Practical_Task_10 {
    private static WebDriver driver;
    private LoginPage loginPage;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.greencity.cx.ua/#/greenCity");
        driver.manage().window().setSize(new Dimension(1264, 798));
    }

    @BeforeEach
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void verifyTitle() {
        Assertions.assertEquals("GreenCity — Build Eco-Friendly Habits Today", driver.getTitle());
    }

    @ParameterizedTest
    @CsvSource({
            "samplestest@greencity.com, weyt3$Guew^",
            "anotheruser@greencity.com, anotherpassword"
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

    @ParameterizedTest
    @CsvSource({
            "Please check if the email is written correctly"
    })
    public void signInNotValid(String message) {
        loginPage.openLoginForm();
        loginPage.fillEmail("samplestesgreencity.com"); // without @
        loginPage.fillPassword("uT346^^^erw");
        assertThat(loginPage.getEmailError(), is(message));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
