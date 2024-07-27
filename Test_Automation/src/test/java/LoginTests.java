import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestInitialization {

    //Check if the username and password fields are on the main screen of the application
    @Test(priority = 1)
    public void verifyLoginFieldsExistence(){
        WebElement usernameField=  driver.findElement(By.id("user-name"));
        WebElement passwordField= driver.findElement(By.id("password"));
        WebElement loginButton= driver.findElement(By.id("login-button"));

        Assert.assertTrue(usernameField.isDisplayed());
        Assert.assertTrue(passwordField.isDisplayed());
        Assert.assertTrue(loginButton.isDisplayed());

    }
    //Check if the given valid credentials work
    @Test (priority = 2)
    public void verifyLoginWithValidCredentials(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement swagLabsAppLogo=driver.findElement(By.className("app_logo"));
        Assert.assertTrue(swagLabsAppLogo.isDisplayed());

    }
    //Check if the given wrong credentials work
    @Test (priority = 3)
    public void validateLoginWithInValidCredentials(){
        driver.findElement(By.id("user-name")).sendKeys("invalid_username");
        driver.findElement(By.id("password")).sendKeys("invalid_password");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector("div[class=\"error-message-container error\"]"));
        Assert.assertTrue((errorMessage.getText().contains("Epic sadface: Username and password do not match any user in this service")));
    }
    //Check for empty credentials
    @Test (priority = 4)
    public void validateLoginWithEmptyCredentials(){
        driver.findElement(By.id("login-button")).click();
        WebElement UsernameErrorMessage = driver.findElement(By.cssSelector("div[class=\"error-message-container error\"]"));
        Assert.assertTrue((UsernameErrorMessage.getText().contains("Epic sadface: Username is required")));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        WebElement passwordErrorMessage = driver.findElement(By.cssSelector("div[class=\"error-message-container error\"]"));
        Assert.assertTrue((passwordErrorMessage.getText().contains("Epic sadface: Password is required")));
    }
}
