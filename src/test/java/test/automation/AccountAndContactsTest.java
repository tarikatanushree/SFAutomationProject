/*
 * This automation test case is prepared by Tarika Tanushree.
 * This test class contains scenarios related to adding of accounts and contacts functionality in Salesforce.
 */
package test.automation;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AccountAndContactsTest {
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
    String profileIcon = "";
    String logoutLink = "";
    String accountsURL = "";
    String accountsFilter = "";
    String accountNew = "";
    String newAccountForm = "";
    String accountName = "";
    String accountNameId = "";
    String descriptionText = "";
    String descriptionXpath = "";
    String formSaveXpath = "";
    String accountCreationSuccessXpath = "";
    String newContactButtonXpath = "";
    String firstNameXpath = "";
    String lastNameXpath = "";
    String emailXpath = "";
    String firstNameContact = "";
    String lastNameContact = "";
    String emailContact = "";
    String contactSaveXpath = "";
    String contactSaveSuccessXpath = "";
    String contactFormXpath = "";
    String searchFieldXpath = "";
    String createdAccountSearchXpath = "";
    boolean accountCreateSuccess = true;
    String accountName2="";

    /*This is setup method which is invoked before every test.
     *It has the data mapping from the properties file and webdriver details.
     */
    @BeforeTest
    public void setUp() throws IOException {
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
        profileIcon = properties.getProperty("profileIcon");
        logoutLink = properties.getProperty("logoutLink");
        accountsURL = properties.getProperty("accountsURL");
        accountsFilter = properties.getProperty("accountsFilter");
        accountNew = properties.getProperty("accountNew");
        newAccountForm = properties.getProperty("newAccountForm");
        accountName = properties.getProperty("accountName");
        accountNameId = properties.getProperty("accountNameId");
        descriptionText = properties.getProperty("descriptionText");
        descriptionXpath = properties.getProperty("descriptionXpath");
        formSaveXpath = properties.getProperty("formSaveXpath");
        accountCreationSuccessXpath = properties.getProperty("accountCreationSuccessXpath");
        newContactButtonXpath = properties.getProperty("newContactButtonXpath");
        firstNameXpath = properties.getProperty("firstNameXpath");
        lastNameXpath = properties.getProperty("lastNameXpath");
        emailXpath = properties.getProperty("emailXpath");
        firstNameContact = properties.getProperty("firstNameContact");
        lastNameContact = properties.getProperty("lastNameContact");
        emailContact = properties.getProperty("emailContact");
        contactSaveXpath = properties.getProperty("contactSaveXpath");
        contactSaveSuccessXpath = properties.getProperty("contactSaveSuccessXpath");
        contactFormXpath = properties.getProperty("contactFormXpath");
        searchFieldXpath = properties.getProperty("searchFieldXpath");
        createdAccountSearchXpath = properties.getProperty("createdAccountSearchXpath");
        accountName2=properties.getProperty("accountName2");

    }

    /* This is a test to verify if new account form is getting displayed, it runs first as per the priority provided:-
     * 1. Login to SF
     * 2. On successful login, go to account filter
     * 3. Click on New
     * 4. Test passes if only when new account form is displayed
     * 5. Logout of the page
     */
    @Test(priority = 0)
    public void newAccountFormTest() throws Exception {
        try {
            driver.get(accountsURL);
            Thread.sleep(1000);
            driver.findElement(By.id(loginId)).sendKeys(username);
            driver.findElement(By.id(passwordId)).sendKeys(password);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(accountsFilter)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(accountNew)).click();
            Thread.sleep(4000);
            boolean pageFound = driver.findElement(By.xpath(newAccountForm)).isDisplayed();
            Assert.assertTrue(pageFound, "New account form did not load");
            Thread.sleep(2000);
            driver.findElement(By.xpath(profileIcon)).click();
            Thread.sleep(4000);
            driver.findElement(By.xpath(logoutLink)).click();
            Thread.sleep(4000);
        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/newAccountFormTest.png"));
            e.printStackTrace();
            Assert.fail("newAccountFormTest failed");
        }
    }

    /* This is a test to verify new account form's field validation:-
     * 1. Login to SF
     * 2. On successful login, go to account filter
     * 3. Click on New
     * 4. click on Save without providing account name
     * 5. Test passes when form is still displayed
     * 6. Logout of the page
     */
    @Test(priority = 1)
    public void newAccountCreationWithoutMandatoryFieldTest() throws Exception {
        try {
            driver.get(accountsURL);
            Thread.sleep(1000);
            driver.findElement(By.id(loginId)).sendKeys(username);
            driver.findElement(By.id(passwordId)).sendKeys(password);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(accountsFilter)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(accountNew)).click();
            Thread.sleep(4000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(formSaveXpath)));
            driver.findElement(By.xpath(formSaveXpath)).click();
            Thread.sleep(2000);
            boolean validationFailed = driver.findElement(By.xpath(newAccountForm)).isDisplayed();
            Assert.assertTrue(validationFailed, "Some other error occurred instead of validation failure");
            Thread.sleep(2000);
            driver.findElement(By.xpath(profileIcon)).click();
            Thread.sleep(4000);
            driver.findElement(By.xpath(logoutLink)).click();
            Thread.sleep(4000);
        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/newAccountCreationWithoutMandatoryFieldTest.png"));
            e.printStackTrace();
            Assert.fail("newAccountCreationWithoutMandatoryFieldTest failed");
        }
    }

    /* This is a test to verify successful new account creation:-
     * 1. Login to SF
     * 2. On successful login, go to account filter
     * 3. Click on New
     * 4. Provide account name, description
     * 5. Click on Save
     * 6. Test passes when newly created account's page is displayed
     * 7. Logout of the page
     */
    @Test(priority = 2)
    public void newAccountCreationTest() throws Exception {
        try {
            driver.get(accountsURL);
            Thread.sleep(1000);
            driver.findElement(By.id(loginId)).sendKeys(username);
            driver.findElement(By.id(passwordId)).sendKeys(password);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(accountsFilter)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(accountNew)).click();
            Thread.sleep(4000);
            driver.findElement(By.xpath(accountNameId)).sendKeys(accountName);
            driver.findElement(By.xpath(descriptionXpath)).sendKeys(descriptionText);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(formSaveXpath)));
            driver.findElement(By.xpath(formSaveXpath)).click();
            Thread.sleep(4000);
            accountCreateSuccess = driver.findElement(By.xpath(accountCreationSuccessXpath)).isDisplayed();
            Thread.sleep(2000);
            Assert.assertTrue(accountCreateSuccess, "New account failed to create");
            Thread.sleep(2000);
            driver.findElement(By.xpath(profileIcon)).click();
            Thread.sleep(4000);
            driver.findElement(By.xpath(logoutLink)).click();
            Thread.sleep(4000);
        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/newAccountCreationTest.png"));
            e.printStackTrace();
            Assert.fail("newAccountCreationTest failed");
        }
    }

    /* This is a test to verify successful creation of new contact:-
     * This test will only execute if account is successfully created in the previous tests
     * 1. Login to SF
     * 2. On successful login, go to new created account
     * 3. Click on New Contact
     * 4. Enter firstname, lastname and email
     * 5. Click on save
     * 6. Test passes when newly created contact page is displayed
     * 7. Logout of the page
     */
    @Test
    public void newContactCreationTest() throws Exception {
        try {
            if (!accountCreateSuccess) {
                throw new SkipException("Skipping newContactCreationTest as account creation failed");
            } else {
                driver.get(accountsURL);
                Thread.sleep(1000);
                driver.findElement(By.id(loginId)).sendKeys(username);
                driver.findElement(By.id(passwordId)).sendKeys(password);
                driver.findElement(By.id(submit)).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath(submitSuccess)).sendKeys(Keys.ENTER);
                Thread.sleep(1000);
                driver.findElement(By.xpath(searchFieldXpath)).sendKeys(accountName, Keys.ENTER);
                Thread.sleep(4000);
                driver.findElement(By.xpath(newContactButtonXpath)).click();
                Thread.sleep(4000);
                driver.findElement(By.xpath(firstNameXpath)).sendKeys(firstNameContact);
                driver.findElement(By.xpath(lastNameXpath)).sendKeys(lastNameContact);
                driver.findElement(By.xpath(emailXpath)).sendKeys(emailContact);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactSaveXpath)));
                driver.findElement(By.xpath(contactSaveXpath)).click();
                Thread.sleep(4000);
                boolean contactCreateSuccess = driver.findElement(By.xpath(contactSaveSuccessXpath)).isDisplayed();
                Assert.assertTrue(contactCreateSuccess, "New contact failed to create");
                Thread.sleep(2000);
                driver.findElement(By.xpath(profileIcon)).click();
                Thread.sleep(4000);
                driver.findElement(By.xpath(logoutLink)).click();
                Thread.sleep(4000);
            }
        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/newContactCreationTest.png"));
            e.printStackTrace();
            Assert.fail("newContactCreationTest failed");

        }
    }

    /* This is a test to verify new contacts form field validation:-
     * 1. Login to SF
     * 2. On successful login, create a new created account
     * 3. Click on New Contact
     * 4. Click on Save without entering mandatory field last name
     * 5. Test passes when new contact form is still displayed
     * 6. Logout of the page
     */
    @Test
    public void newContactCreationWithoutMandatoryFieldTest() throws Exception {
        try {
            driver.get(accountsURL);
            Thread.sleep(1000);
            driver.findElement(By.id(loginId)).sendKeys(username);
            driver.findElement(By.id(passwordId)).sendKeys(password);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(accountsFilter)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(accountNew)).click();
            Thread.sleep(4000);
            driver.findElement(By.xpath(accountNameId)).sendKeys(accountName2);
            driver.findElement(By.xpath(descriptionXpath)).sendKeys(descriptionText);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(formSaveXpath)));
            driver.findElement(By.xpath(formSaveXpath)).click();
            Thread.sleep(4000);
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newContactButtonXpath)));
            driver.findElement(By.xpath(newContactButtonXpath)).click();
            Thread.sleep(4000);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contactSaveXpath)));
            driver.findElement(By.xpath(contactSaveXpath)).click();
            Thread.sleep(4000);
            boolean contactValidationFailed = driver.findElement(By.xpath(contactSaveXpath)).isDisplayed();
            Assert.assertTrue(contactValidationFailed, "Some other error occurred instead of validation failure");
            Thread.sleep(2000);
            driver.findElement(By.xpath(profileIcon)).click();
            Thread.sleep(4000);
            driver.findElement(By.xpath(logoutLink)).click();
            Thread.sleep(4000);
        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/newContactCreationWithoutMandatoryFieldTest.png"));
            e.printStackTrace();
            Assert.fail("newContactCreationWithoutMandatoryFieldTest failed");
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