package lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import lesson7.MyUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Story("XYZBank тесты")
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
    @DisplayName("Авторизация пользователя")
    @Description("Проверка авторизации пользователя, под аккаунтом Harry Potter")
    @Link("https://docs.google.com/document/d/15-pGz4UVlO4FE99Uql1pL_Sc_wbQBEHIEV9Z8WTiKWQ/edit")
    @Issue("https://docs.google.com/document/d/1WFRQnJBgLWbj_DJMztfYoXOLxzjYk6Hrb9QqXxeUGAY/edit")
    @Severity(SeverityLevel.BLOCKER)
    public void authorizationTest() {
        xyzBank.customerLoginPotter();
        assertEquals("Harry Potter", xyzBank.getName());
    }

    @Test
    @DisplayName("Валюта аккаунта")
    @Description("Проверка валюты аккаунта 1005")
    @Severity(SeverityLevel.NORMAL)
    public void currencyTest() {
        try {

            xyzBank
                    .customerLoginPotter()
                    .selectAccount(1005);
            assertEquals("Pound", xyzBank.getCurrency());
        } catch (Exception e) {
            File file = MyUtils.makeScreenshot(driver, "currencyTest" + System.currentTimeMillis() + ".png");
        }
    }

    @Test
    @DisplayName("Манипуляции с балансом")
    @Description("Прибавляем к балансу 1000, убавляем 500.")
    @Severity(SeverityLevel.CRITICAL)
    public void balanceTest() throws InterruptedException {
        try {
            xyzBank.customerLoginPotter();
            int balance = xyzBank.getBalance();

            xyzBank
                    .inputDeposit("1000")
                    .inputWithdrawn("500");

            int newBalance = balance + 500;

            assertEquals(newBalance, xyzBank.getBalance());
        } catch (Exception e) {
            File file = MyUtils.makeScreenshot(driver, "balanceTest" + System.currentTimeMillis() + ".png");
        }
    }

    @Test
    @DisplayName("Открытие аккаунта")
    @Description("Создаем новый аккаунт клиента, открываем ему аккаунт, проверяем номер аккаунта в списке клиентов.")
    @Severity(SeverityLevel.CRITICAL)
    public void openAccountTest() {
        try {
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
        } catch (Exception e) {
            File file = MyUtils.makeScreenshot(driver, "openAccountTest" + System.currentTimeMillis() + ".png");
        }
    }

    @Test
    @DisplayName("Удаление клиента")
    @Description("Создаем новый аккаунт клиента, открываем ему аккаунт, по номеру аккаунта находим клиента в списке и удаляем.")
    @Severity(SeverityLevel.NORMAL)
    public void deleteAccount() {
        try {
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
        } catch (Exception e) {
            File file = MyUtils.makeScreenshot(driver, "deleteAccount" + System.currentTimeMillis() + ".png");
        }
    }

    @Test
    @DisplayName("Проверка кнопки \"Home\"")
    @Description("Заходим как менеджер и возврашаемся на главную по кнопке \"Home\"")
    @Severity(SeverityLevel.MINOR)
    public void returnHomeTest() {
        try {
            xyzBank
                    .managerLogin()
                    .clickHome();
            assertEquals("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login", driver.getCurrentUrl());
        } catch (Exception e) {
            File file = MyUtils.makeScreenshot(driver, "returnHomeTest" + System.currentTimeMillis() + ".png");
        }
    }

    @AfterEach
    void quit() {
        driver.quit();
    }
}
