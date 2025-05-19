package org.example;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class GetCookies {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.greencity.cx.ua/#/greenCity");

        // Чекаємо, поки користувач не авторизується (якщо є Cloudflare, даємо час пройти його)
        try {
            Thread.sleep(60000); // 60 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Отримуємо куки
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("Name: " + cookie.getName() + ", Value: " + cookie.getValue());
        }

        // Закриваємо браузер
        driver.quit();
    }
}