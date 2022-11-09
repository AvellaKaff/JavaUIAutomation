package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SendMessegeTest {
    WebDriver driver;


    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void newDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().setSize(new Dimension(1000, 824));
        driver.get("http://automationpractice.com/index.php?controller=contact");
    }

        @Test
                void sendMessegeTest(){

        driver.findElement(By.xpath("//option[2]")).click();
        driver.findElement(By.id("email")).sendKeys("aaa@gmail.com");
        driver.findElement(By.id("message")).sendKeys("test");
        driver.findElement(By.id("submitMessage")).click();


        assertEquals("Your message has been successfully sent to our team.", driver.findElement(By.xpath("//p[@class=\"alert alert-success\"]")).getText());

    }

    @AfterEach
    void quit() {
        driver.quit();
    }
}
