/*
 * This automation test case is prepared by Tarika Tanushree.
 * This test class contains scenarios related to Search functionality in Salesforce.
 */
package test.automation;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import java.io.IOException;
import java.util.Properties;

public class SearchFunctionalityTest {
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
    String searchField = "";
    String profileIcon = "";
    String logoutLink = "";
    String searchText = "";
    String searchResult = "";
    String searchResultMany = "";
    String searchResultMany1 = "";
    String searchResultMany2 = "";
    String searchResultMany3 = "";
    String searchResult0 = "";
    String searchResult0Web = "";
    String searchFieldXpath = "";

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
        searchField = properties.getProperty("searchField");
        profileIcon = properties.getProperty("profileIcon");
        logoutLink = properties.getProperty("logoutLink");
        searchText = properties.getProperty("searchText");
        searchResult = properties.getProperty("searchResult");
        searchResultMany = properties.getProperty("searchResultMany");
        searchResultMany1 = properties.getProperty("searchResultMany1");
        searchResultMany2 = properties.getProperty("searchResultMany2");
        searchResultMany3 = properties.getProperty("searchResultMany3");
        searchResult0 = properties.getProperty("searchResult0");
        searchResult0Web = properties.getProperty("searchResult0Web");
        searchFieldXpath = properties.getProperty("searchFieldXpath");
    }

    /* This is search with valid text which will return only 1 result:-
     * 1. Login to SF
     * 2. On successful login, search for search field
     * 3. Send the search text
     * 4. Test passes if only one desired result is returned
     * 5. Logout of the page
     */
    @Test
    public void searchValidTextOneResultTest() throws Exception {
        try {
            driver.get(url);
            Thread.sleep(1000);
            driver.findElement(By.id(loginId)).sendKeys(username);
            driver.findElement(By.id(passwordId)).sendKeys(password);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(submitSuccess)).sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            driver.findElement(By.xpath(searchFieldXpath)).sendKeys(searchText, Keys.ENTER);
            Thread.sleep(4000);
            boolean oneResultFound = driver.findElement(By.xpath(searchResult)).isDisplayed();
            Assert.assertTrue(oneResultFound, "Actual result not found");
            Thread.sleep(2000);
            driver.findElement(By.xpath(profileIcon)).click();
            Thread.sleep(4000);
            driver.findElement(By.xpath(logoutLink)).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/searchValidTextOneResultTest.png"));
            e.printStackTrace();
            Assert.fail("searchValidTextOneResultTest failed");
        }
    }

    /* This is search with valid text which will return multiple results:-
     * 1. Login to SF
     * 2. On successful login, search for search field
     * 3. Send the search text
     * 4. Test passes if all the expected search results are returned
     * 5. Logout of the page
     */
    @Test
    public void searchValidTextManyResultsTest() throws Exception {
        try {
            driver.get(url);
            Thread.sleep(1000);
            driver.findElement(By.id(loginId)).sendKeys(username);
            driver.findElement(By.id(passwordId)).sendKeys(password);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(submitSuccess)).sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            driver.findElement(By.xpath(searchFieldXpath)).sendKeys(searchResultMany, Keys.ENTER);
            Thread.sleep(4000);
            boolean foundResults = (driver.findElement(By.xpath(searchResultMany1)).isDisplayed() && driver.findElement(By.xpath(searchResultMany2)).isDisplayed() && driver.findElement(By.xpath(searchResultMany3)).isDisplayed());
            Assert.assertTrue(foundResults, "Search results not matching");
            Thread.sleep(2000);
            driver.findElement(By.xpath(profileIcon)).click();
            Thread.sleep(4000);
            driver.findElement(By.xpath(logoutLink)).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/searchValidTextManyResultsTest.png"));
            e.printStackTrace();
            Assert.fail("searchValidTextManyResultsTest failed");
        }
    }

    /* This is search with invalid text which will not return any result:-
     * 1. Login to SF
     * 2. On successful login, search for search field
     * 3. Send the search text
     * 4. Test passes if only if no results are returned
     * 5. Logout of the page
     */
    @Test
    public void searchInValidTextNoResultTest() throws Exception {
        try {
            driver.get(url);
            Thread.sleep(1000);
            driver.findElement(By.id(loginId)).sendKeys(username);
            driver.findElement(By.id(passwordId)).sendKeys(password);
            driver.findElement(By.id(submit)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(submitSuccess)).sendKeys(Keys.ENTER);
            Thread.sleep(1000);
            driver.findElement(By.xpath(searchFieldXpath)).sendKeys(searchResult0, Keys.ENTER);
            Thread.sleep(2000);
            boolean noResult = driver.findElement(By.xpath(searchResult0Web)).isDisplayed();
            Assert.assertTrue(noResult, "Test Failed for invalid search text");
            Thread.sleep(2000);
            driver.findElement(By.xpath(profileIcon)).click();
            Thread.sleep(4000);
            driver.findElement(By.xpath(logoutLink)).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("/Users/tarikatanushree/Desktop/SFAutomationProject/src/FailedScenarioScreenshot/searchInValidTextNoResultTest.png"));
            e.printStackTrace();
            Assert.fail("searchInValidTextNoResultTest failed");
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
