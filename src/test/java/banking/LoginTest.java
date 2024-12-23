package banking;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        performLogin(username, password);
    }

    @Test(priority = 1 , dataProvider = "loginData")
    public void invalidLogin(String username)
    {
        performLogin(username, "password");
    }

    private void performLogin(String username, String password) {
//        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("input.button"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        if(driver.findElement(By.className("error")).isDisplayed()){
            String errorMessage = driver.findElement(By.className("error")).getText();
            System.out.println("errorMessage --> "+errorMessage);
            Assert.assertEquals(errorMessage,
                "The username and password could not be verified.");
        } else if (driver.findElement
                (By.xpath("//h1[contains(text(), 'Accounts Overview')]")).
                    isDisplayed()) {
                String accountsView = driver.findElement
                        (By.xpath("//div[@id='showOverview']/h1"))
                        .getText();
                System.out.println("accountsView --> "+accountsView);
                Assert.assertEquals(accountsView,
                        "Accounts Overview");
            
        }
//        WebElement welcomeMessage = wait.until
//                (ExpectedConditions.visibilityOfElementLocated(By.className("error")));
//        Assert.assertTrue(welcomeMessage.isDisplayed(),
//                "The customer information provided could not be found.");
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
//                {"user1", "password1"}
//                ,
                {"USER 2", "Password1"},
//                {"user3", "password3"},
//                {"user4", "password4"},
//                {"user5", "password5"}
        };

    }

    @AfterMethod public void tearDown() {
        if (driver != null) {
            driver.quit(); }
    }
}