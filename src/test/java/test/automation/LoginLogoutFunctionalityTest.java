/*
 * This automation test case is prepared by Tarika Tanushree.
 * This test class contains scenarios related to Login and Logout functionality in Salesforce.
 */
package test.automation;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class LoginLogoutFunctionalityTest {
    ChromeDriver driver;
    Properties properties;
    FileInputStream inputStream;
    String url = "";
    String loginId = "";
    String username = "";
    String passwordId = "";
    String password = "";
    String submit = "";
    String submitSuccess = "";
    String invalidNonExistingEmail = "";
    String invalidPwd = "";
    String profileIcon = "";
    String logoutLink = "";

    /*This is setup method which is invoked before every test.
     *It has the data mapping from the properties file and webdriver details.
     */
    @BeforeTest
    public void setUp() throws Exception {
        ChromeOptions o = new ChromeOptions();
        o.addArguments("--disable-notifications");
        driver = new ChromeDriver(o);
        properties = new Properties();
        inputStream = new FileInputStream("/Users/tarikatanushree/Desktop/SFAutomationProject/src/test/resources/properties/testdata.properties");
        properties.load(inputStream);
        url = properties.getProperty("url");
        loginId = properties.getProperty("loginId");
        username = properties.getProperty("username");
        passwordId = properties.getProperty("passwordId");
        password = properties.getProperty("password");
        submit = properties.getProperty("submit");
        submitSuccess = properties.getProperty("submitSuccess");
        invalidNonExistingEmail = properties.getProperty("invalidNonExistingEmail");
        invalidPwd = properties.getProperty("invalidPwd");
        profileIcon = properties.getProperty("profileIcon");
        logoutLink = properties.getProperty("logoutLink");
    }

    /* This is username validation test:-
     * 1. SF Login page is displayed
     * 2. Password is entered but no Username
     * 3. Submit Button is clicked
     * 4. Test passes if login page is displayed again on click of submit in 3rd step
     */
    @Test
    public void loginFieldValidationTest() throws Exception {
        try {
            driver.get(url);
            Thread.sleep(1000);
            driver.findElement(By.id(passwordId)).sendKeys(password);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(4000);
            boolean found = driver.findElement(By.id(loginId)).isDisplayed();
            Assert.assertTrue(found, "some other error 500/503/404 etc.. would have occurred");
            Thread.sleep(2000);

        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/loginFieldValidationTest.png"));
            e.printStackTrace();
            Assert.fail("loginFieldValidationTest failed");
        }
    }

    /* This is password validation test:-
     * 1. SF Login page is displayed
     * 2. Username is entered but no Password
     * 3. Submit Button is clicked
     * 4. Test passes if login page is displayed again on click of submit in 3rd step
     */
    @Test
    public void passwordFieldValidationTest() throws Exception {
        try {
            driver.get(url);
            Thread.sleep(1000);
            driver.findElement(By.id(loginId)).sendKeys(username);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(4000);
            boolean found = driver.findElement(By.id(loginId)).isDisplayed();
            Assert.assertTrue(found);
            Thread.sleep(2000);

        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/passwordFieldValidationTest.png"));
            e.printStackTrace();
            Assert.fail("passwordFieldValidationTest failed");
        }
    }

    /* This is both username and password validation test:-
     * 1. SF Login page is displayed
     * 2. No Username and Password are entered
     * 3. Submit Button is clicked
     * 4. Test passes if login page is displayed again on click of submit in 3rd step
     */
    @Test
    public void loginPasswordFieldValidationTest() throws Exception {
        try {
            driver.get(url);
            Thread.sleep(1000);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(4000);
            boolean found = driver.findElement(By.id(loginId)).isDisplayed();
            Assert.assertTrue(found);
            Thread.sleep(2000);
        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/loginPasswordFieldValidationTest.png"));
            e.printStackTrace();
            Assert.fail("loginPasswordFieldValidationTest failed");
        }
    }

    /* This is invalid username test:-
     * 1. SF Login page is displayed
     * 2. Invalid Username is entered
     * 3. Submit Button is clicked
     * 4. Test passes if login page is displayed again on click of submit in 3rd step
     */
    @Test
    public void invalidNonExistingUsernameTest() throws Exception {
        try {
            driver.get(url);
            Thread.sleep(1000);
            driver.findElement(By.id(loginId)).sendKeys(invalidNonExistingEmail);
            driver.findElement(By.id(passwordId)).sendKeys(password);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(4000);
            boolean found = driver.findElement(By.id(loginId)).isDisplayed();
            Assert.assertTrue(found);
            Thread.sleep(2000);

        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/invalidNonExistingUsernameTest.png"));
            e.printStackTrace();
            Assert.fail("invalidNonExistingUsernameTest failed");
        }
    }

    /* This is password validation test:-
     * 1. SF Login page is displayed
     * 2. Valid username but invalid Password is entered
     * 3. Submit Button is clicked
     * 4. Test passes if login page is displayed again on click of submit in 3rd step
     */
    @Test
    public void invalidPasswordTest() throws Exception {
        try {
            driver.get(url);
            Thread.sleep(1000);
            driver.findElement(By.id(loginId)).sendKeys(username);
            driver.findElement(By.id(passwordId)).sendKeys(invalidPwd);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(4000);
            boolean found = driver.findElement(By.id(loginId)).isDisplayed();
            Assert.assertTrue(found);
            Thread.sleep(2000);

        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/invalidPasswordTest.png"));
            e.printStackTrace();
            Assert.fail("invalidPasswordTest failed");
        }
    }

    /* This is successful login and then logout test:-
     * 1. SF Login page is displayed
     * 2. Valid username and Password are entered
     * 3. Submit Button is clicked
     * 4. First Test passes if search text box is displayed which is displayed upon successful login only
     * 5. If login is successful then process with logout
     * 6. Second test passes on successful logout, login page is displayed back
     */
    @Test
    public void successfulLoginThenLogoutTest() throws Exception {
        try {
            driver.get(url);
            Thread.sleep(1000);
            driver.findElement(By.id(loginId)).sendKeys(username);
            driver.findElement(By.id(passwordId)).sendKeys(password);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(4000);
            boolean successfulLogin = driver.findElement(By.xpath(submitSuccess)).isDisplayed();
            Assert.assertTrue(successfulLogin, "Login failed");
            Thread.sleep(1000);
            if (successfulLogin) {
                driver.findElement(By.xpath(profileIcon)).click();
                Thread.sleep(4000);
                driver.findElement(By.xpath(logoutLink)).click();
                Thread.sleep(4000);
                boolean successfulLogout = driver.findElement(By.id(submit)).isDisplayed();
                Assert.assertTrue(successfulLogout, "Logout failed");
            }
            Thread.sleep(4000);

        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/successfulLoginThenLogoutTest.png"));
            e.printStackTrace();
            Assert.fail("successfulLoginThenLogoutTest failed");
        }
    }

    /* This is tear down method which executes after every test.
     * Driver is closed on every test case execution
     */
    @AfterTest
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.close();
    }
}
