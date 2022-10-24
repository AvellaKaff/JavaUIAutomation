package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SendMessege {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().setSize(new Dimension(1000,824));
        driver.get("http://automationpractice.com/index.php?controller=contact");

        driver.findElement(By.xpath("//option[2]")).click();
        driver.findElement(By.id("email")).sendKeys("aaa@gmail.com");
        driver.findElement(By.id("message")).sendKeys("test");
        driver.findElement(By.id("submitMessage")).click();


        driver.findElement(By.xpath("//p[@class=\"alert alert-success\"]")).getText().equals("Your message has been successfully sent to our team.");


        Thread.sleep(5000);
        driver.quit();
    }
}
