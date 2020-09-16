package TestingUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestingUIExample{
    WebDriver driver;

    @BeforeMethod
    public void settingUp(){
        System.setProperty("webdriver.chrome.driver", "c:\\1\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test (priority = 1)
    public void signInErrors(){
        driver.get("https://app.usertesting.com/users/sign_in");
        driver.findElement(By.name("commit")).click();
        WebElement errorAccount = driver.findElement(By.xpath("//span[@class=\"form__hint l-flex mr-auto\"]"));
        Assert.assertEquals(errorAccount.getText(), "Please enter a valid email address");
        WebElement errorPassword = driver.findElement(By.xpath("//span[@class=\"form__hint form__hint--error l-flex mr-auto\"]"));
        Assert.assertEquals(errorPassword.getText(), "Please enter a valid password");
    }

    @Test
    public void signIn2(){

    }

    @AfterMethod
    public void TearDown(){
        driver.quit();
    }
}