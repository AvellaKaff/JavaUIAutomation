package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AddToCart {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().setSize(new Dimension(1000, 824));
        driver.get("http://automationpractice.com/index.php");

        driver.findElement(By.xpath("//span[text()=\"Add to cart\"]")).click();
        driver.findElement(By.xpath("//span[contains(text(), \"Proceed to checkout\")]")).click();

        driver.findElement(By.xpath("//*[@id=\"order-detail-content\"]"));

        Thread.sleep(5000);
        driver.quit();
    }
}
