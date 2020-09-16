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
    public void LogInto(){
        driver.get("https://sugaringfactory.com/index.php?route=account/login");
        driver.findElement(By.xpath("//input[@class=\"q1 margen-bottom\" and @name=\"email\"]")).sendKeys("frechnuts@gmail.com");
        driver.findElement(By.xpath("//input[@class=\"q1 margen-bottom\" and @name=\"password\"]")).sendKeys("Qwerty123");
        driver.findElement(By.xpath("//div[@class=\"login-buttons\"]//a[contains(text(), 'Login')]")).click();
        Assert.assertEquals(driver.getTitle(), "My Account");
    }

    @Test (priority = 2)
    public void signInErrorsEmptyFields(){
        driver.get("https://sugaringfactory.com/index.php?route=account/login");
        driver.findElement(By.xpath("//div[@class=\"login-buttons\"]//a[contains(text(), 'Login')]")).click();
        WebElement errorAccount = driver.findElement(By.xpath("//div[@class='warning']"));
        Assert.assertEquals(errorAccount.getText(), "Warning: No match for E-Mail Address and/or Password.");
    }

    @Test (priority = 3)
    public void signInErrorsIncorrectLoginPassword(){
        driver.get("https://sugaringfactory.com/index.php?route=account/login");
        driver.findElement(By.xpath("//input[@class=\"q1 margen-bottom\" and @name=\"email\"]")).sendKeys("fakeID@gmail.com");
        driver.findElement(By.xpath("//input[@class=\"q1 margen-bottom\" and @name=\"password\"]")).sendKeys("fakePassword");
        driver.findElement(By.xpath("//div[@class=\"login-buttons\"]//a[contains(text(), 'Login')]")).click();
        WebElement errorAccount = driver.findElement(By.xpath("//div[@class='warning']"));
        Assert.assertEquals(errorAccount.getText(), "Warning: No match for E-Mail Address and/or Password.");
    }

    @Test (priority = 4)
    public void signInCorrectLoginIncorrectPassword(){
        driver.get("https://sugaringfactory.com/index.php?route=account/login");
        driver.findElement(By.xpath("//input[@class=\"q1 margen-bottom\" and @name=\"email\"]")).sendKeys("frechnuts@gmail.com");
        driver.findElement(By.xpath("//input[@class=\"q1 margen-bottom\" and @name=\"password\"]")).sendKeys("fakePassword");
        driver.findElement(By.xpath("//div[@class=\"login-buttons\"]//a[contains(text(), 'Login')]")).click();
        WebElement errorAccount = driver.findElement(By.xpath("//div[@class='warning']"));
        Assert.assertEquals(errorAccount.getText(), "Warning: No match for E-Mail Address and/or Password.");
    }

    @Test (priority = 5)
    public void signInCorrectPasswordIncorrectLogin(){
        driver.get("https://sugaringfactory.com/index.php?route=account/login");
        driver.findElement(By.xpath("//input[@class=\"q1 margen-bottom\" and @name=\"email\"]")).sendKeys("fakeID@gmail.com");
        driver.findElement(By.xpath("//input[@class=\"q1 margen-bottom\" and @name=\"password\"]")).sendKeys("Qwerty123");
        driver.findElement(By.xpath("//div[@class=\"login-buttons\"]//a[contains(text(), 'Login')]")).click();
        WebElement errorAccount = driver.findElement(By.xpath("//div[@class='warning']"));
        Assert.assertEquals(errorAccount.getText(), "Warning: No match for E-Mail Address and/or Password.");
    }

    @AfterMethod
    public void TearDown(){
        driver.quit();
    }
}