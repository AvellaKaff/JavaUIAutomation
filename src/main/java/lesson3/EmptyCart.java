package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class EmptyCart {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().setSize(new Dimension(1000, 824));
        driver.get("http://automationpractice.com/index.php");

        driver.findElement(By.xpath("//*[@title=\"View my shopping cart\"]")).click();

        driver.findElement(By.xpath("//*[@class=\"alert alert-warning\"]")).getText().equals("Your shopping cart is empty.");

        Thread.sleep(5000);
        driver.quit();
    }
}
