package testNG_09282019;

import Reusable_Classes.Reusable_Library;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class testNG_Assertions {

    //declare the driver to be re-used on all annotations methods
    WebDriver driver = null;

    @BeforeSuite
    public void openBrowser() throws IOException {
        // Navigate command to open the URL
        driver = Reusable_Library.navigate(driver,"https://www.google.com");
    } //end of before suite

    @Test
    public void googleSearch() {
        //verify google title matches using hard assert  --> Assert and HardAssert is the same
        Assert.assertEquals(driver.getTitle(),"Google");

        //using soft assert to verify the title (it should not match)
        //SoftAssert softAssert = new SoftAssert();
        //softAssert.assertEquals(driver.getTitle(),"google");

        //enter a keyword on Google Search field
        Reusable_Library.userInput(driver,"//*[@name='q']","cars","Search Field");

        //When using soft assert, you have to catch exception
        //softAssert.assertEquals();


    } //end of test annotation

    @AfterSuite
    public void close() {
        //quite the driver
        //driver.quit();


    } //end of after suite


} //end of class
