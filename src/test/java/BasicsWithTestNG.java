import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class BasicsWithTestNG extends Base {
    public WebDriver driver;

//    public void waitForElement(WebElement element){
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebElement elementToWait =
//                wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
//    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://demoqa.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('fixedban').style.display='none';");
    }

    @Test(priority = 2)
    public void element_textbox(){
        WebElement elementText = driver.findElement(By.xpath("//h5[text()='Elements']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementText);
        elementText.click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//h5[text()='Elements']/../preceding-sibling::div[@class='avatar mx-auto white']")
//        ));

//        element.click();
        driver.findElement(By.xpath("//span[text()='Text Box']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.id("userName")).sendKeys("Sravani");
       driver.findElement(By.id("userEmail")).sendKeys("userEmail@gmail.com");
       driver.findElement(By.xpath("//textarea[@placeholder='Current Address']")).sendKeys("7 - 17 is my address");
       driver.findElement(By.id("submit")).submit();
    }
}