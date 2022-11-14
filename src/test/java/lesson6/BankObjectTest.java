package lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class BankObjectTest {
    WebDriver driver;
    XYZBank xyzBank;


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

        xyzBank = new XYZBank(driver);
    }

    @Test
    public void authorizationTest() {
        xyzBank.customerLoginPotter();
        assertEquals("Harry Potter", xyzBank.getName());
    }

    @Test
    public void currencyTest() {
        xyzBank
                .customerLoginPotter()
                .selectAccount(1005);
        assertEquals("Pound", xyzBank.getCurrency());
    }

    @Test
    public void balanceTest() throws InterruptedException {
        xyzBank.customerLoginPotter();
        int balance = xyzBank.getBalance();

        xyzBank
                .inputDeposit("1000")
                .inputWithdrawn("500");

        int newBalance = balance + 500;

        assertEquals(newBalance, xyzBank.getBalance());
    }

    @Test
    public void openAccountTest() {
        xyzBank
                .managerLogin()
                .addCustomer("Got", "Van", "123456");

        driver.switchTo().alert().accept();

        xyzBank
                .openAccountClick()
                .openAccountSelectGotVan()
                .openAccountSelectCurrency("ruppee")
                .openAccountClickProcess();

        String alertMessage = driver.switchTo().alert().getText();
        String AccountNumber = alertMessage.replace("Account created successfully with account Number :", "");
        driver.switchTo().alert().accept();
        xyzBank.checkCustomers();

        assertEquals(AccountNumber, xyzBank.getFind_Got_account_number());
    }

    @Test
    public void deleteAccount() {
        xyzBank
                .managerLogin()
                .addCustomer("Got", "Van", "123456");

        driver.switchTo().alert().accept();

        xyzBank
                .openAccountClick()
                .openAccountSelectGotVan()
                .openAccountSelectCurrency("ruppee")
                .openAccountClickProcess();

        String alertMessage = driver.switchTo().alert().getText();
        String AccountNumber = alertMessage.replace("Account created successfully with account Number :", "");
        driver.switchTo().alert().accept();
        xyzBank.checkCustomers();
        xyzBank.searchCustomer(AccountNumber);
        int numberOfRows = driver.findElements(By.xpath("//tbody/child::tr")).size();
        if (numberOfRows == 1) {
            xyzBank.deleteCustomer();
        }
        assertEquals(0, driver.findElements(By.xpath("//tbody/child::tr")).size());
    }

    @Test
    public void returnHomeTest() {
        xyzBank
                .managerLogin()
                .clickHome();
        assertEquals("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login", driver.getCurrentUrl());
    }

    @AfterEach
    void quit() {
        driver.quit();
    }
}
