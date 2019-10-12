package Action_Items;

import Reusable_Classes.Reusable_Library;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class Action_WeightWatchers_TestNG {

    WebDriver driver; //declare driver variable outside so it can be reused on all annotation methods

    String zipCode = "11218";

    @BeforeSuite
    public void openBrowser() throws IOException {
        // define the driver here
        driver = Reusable_Library.navigate(driver, "https://www.weightwatchers.com/us/");

    } //to open the browser

    @Test (priority = 1)
    public void clickonStudio() throws InterruptedException {

//timeout to capture the page title
        Thread.sleep(3000);
//verifying the page title
        String title1 = driver.getTitle();
        if (title1.equals("Weight Loss Program, Recipes & Help | Weight Watchers")) {
            System.out.println("Title matches");
        } else {
            System.out.println("Title doesn't match " + title1);
        }

        //click on 'Find a Studio' button
        Thread.sleep(4000);
        Reusable_Library.click(driver, "//*[@href='/us/find-a-meeting']", "Find a Studio");
    } //end of Test 1

    @Test (priority = 2)
    public void clickOnStudio() throws InterruptedException {


//timeout to capture the page title 2
        Thread.sleep(3000);
//verifying the page title 2
        String title2 = driver.getTitle();
        if (title2.equals("Find a Studio & Meeting Near You |")) {
            System.out.println("Title matches");
        } else {
            System.out.println("Title doesn't match " + title2);
        }

//enter zip code
        Thread.sleep(4000);
        Reusable_Library.userInput(driver, "//*[@type='text']", zipCode, "Enter Location");
//click on Search button
        Thread.sleep(2000);
        Reusable_Library.click(driver, "//*[@class='btn spice-translated']", "Search Button");

//capture the first result for meeting location
        String meetLocation = Reusable_Library.captureTextByIndex(driver, "//*[@class='location__name']", 0, "Meeting Location");
        System.out.println("The first location is " + meetLocation);

//click on first element using index
        Reusable_Library.clickByIndex(driver, "//*[@class='location__name']", 0, "Location Name Link");

//capture the current day operation hour
        String opHour = null;
//if the operation hour is not present for a zip code then store the variable inside try catch
        try {
            Thread.sleep(2800);
            opHour = driver.findElements(By.xpath("//*[contains(@class,'currentday')]")).get(0).getText();
            System.out.println("The hours for today are " + opHour);
        } catch (Exception e) {
            System.out.println("Current Operation Hour is not available for this Zip Code");
        } //end of try catch



    } //actual test cases

    @AfterSuite
    public void closeBrowser() {
        driver.close();

    } //to close the browser

} //end of class
