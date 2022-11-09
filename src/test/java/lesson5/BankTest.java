package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {
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
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject");

        driver.findElement(By.xpath("//button[text()=\"Customer Login\"]")).click();
        driver.findElement(By.xpath("//option[text()=\"Harry Potter\"]")).click();
        driver.findElement(By.xpath("//button[text()=\"Login\"]")).click();
    }

    @Test
    public void authorizationTest() {

        assertEquals("Harry Potter", driver.findElement(By.xpath("//span[@class=\"fontBig ng-binding\"]")).getText());
    }

    @Test
    public void balanceTest() throws InterruptedException {
                int balance = Integer.parseInt(driver.findElement(By.xpath("//strong[@class=\"ng-binding\"][2]")).getText());
        driver.findElement(By.xpath("//button[@ng-click=\"deposit()\"]")).click();
        driver.findElement(By.xpath("//input")).sendKeys("1000");
        driver.findElement(By.xpath("//button[text()=\"Deposit\"]")).click();
        driver.findElement(By.xpath("//button[@ng-click=\"withdrawl()\"]")).click();
        driver.findElement(By.xpath("//input")).sendKeys("500");
        driver.findElement(By.xpath("//button[text()=\"Withdraw\"]")).click();

        int newBalance = balance + 500;

        assertEquals(newBalance, Integer.parseInt(driver.findElement(By.xpath("//strong[@class=\"ng-binding\"][2]")).getText()));
    }

    @AfterEach
    void quit() {
        driver.quit();
    }
}
