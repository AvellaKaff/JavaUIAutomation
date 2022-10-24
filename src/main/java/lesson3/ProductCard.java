package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ProductCard {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().setSize(new Dimension(1000, 824));
        driver.get("http://automationpractice.com/index.php");

        driver.findElement(By.xpath("//i[@class=\"icon-eye-open\"]")).click();

        try {
            driver.findElement(By.xpath("//*[contains(@class, 'fancybox-wrap')]"));
        }catch (NoSuchElementException elementException){
            System.out.println("Ошибка. Окно не открылось.");
        }

        Thread.sleep(5000);
        driver.quit();
    }
}
