package Action_Items;

import Reusable_Classes.Reusable_Library;
import jxl.write.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Yahoo_TestNG {

    //Declare driver variable
    WebDriver driver = null;

    @BeforeSuite
    public void openBrowser () throws IOException {
        //1. Navigate to Yahoo web page
        driver = Reusable_Library.navigate(driver,"https://www.yahoo.com/");

    } //end of @BeforeSuite

    @Test
    public void fullTest() throws InterruptedException {

        //2. Assert that we are on the correct page by checking the title = 'Yahoo'
        Assert.assertEquals(driver.getTitle(),"Yahoo");

        //3. Display the count of options on the left side panel
        try{
            Thread.sleep(3000);
            List<WebElement> tabsCount = driver.findElements(By.xpath("//*[contains(@class,'Mstart(21px)')]"));
            System.out.println("tab count is " + tabsCount.size());
        } catch (Exception e) {
            System.out.println("Unable to count tabs because " + e);
        }

        //4. Enter 'Nutrition' on the search bar on the top
        Reusable_Library.userInput(driver,"//*[@name='p']","Nutrition","Search Bar");

        //5. Click on 'Search' Button
        Reusable_Library.click(driver,"//*[@id='uh-search-button']","clickButton");

        //6. Scroll down to the page
        Thread.sleep(2000);
        JavascriptExecutor jseDown = (JavascriptExecutor) driver;
        jseDown.executeScript("scroll(0,5000)");

        //7. Display the search result number
        Thread.sleep(3000);
        try {
        String searchResult = driver.findElement(By.xpath("//span[contains(text(), 'results')]")).getText();
        System.out.println("The search result is " + searchResult);
        } catch (Exception e) {
            System.out.println("Unable to print search result number because " + e);
        } //end of try catch

//        try {
//            String searchResult = driver.findElement(By.xpath("//*[@id ='yui_3_10_0_1_1569716057677_312']")).getText();
//            String[] arrayResult = searchResult.split(" ");
//            System.out.println("My search result number is "  +  arrayResult[0]);
//        } catch (Exception error){
//            System.out.println("Unable to print search result number because " + error);
//        } //end of try and catch

        //8. Scroll up and click on image link
          Thread.sleep(3000);
          JavascriptExecutor jseUp = (JavascriptExecutor) driver;
          jseUp.executeScript("scroll(0,-50000)");

          Thread.sleep(2000);
          Reusable_Library.click(driver, "//*[@class ='p-3u mr-3u fl-l']","Images Tab");

        //9. Display the count of all images appearing on the screen

        //10. On top, right click on sign in button
        Reusable_Library.click(driver, "//*[@id = 'yucs-profile_text']","signInButton");

        //11. Verify the Boolean state of checkbox, if it is selected as default or not
        WebElement checkBox = driver.findElement(By.xpath("//*[@type = 'checkbox']"));
        System.out.println("Is element selected? " + checkBox.isSelected());

        //12. Enter invalid user name
        Reusable_Library.userInput(driver,"//*[@id = 'login-username']","mhamid1234567","Invalid User Name");

        //13. Click on 'Next' button
        Thread.sleep(2000);
        try{
        driver.findElement(By.xpath("//input[(@value='Next')]")).submit();
        } catch (Exception e){
            System.out.println("Unable to click on Next button because " + e);
        } //end of try catch

        //Reusable_Library.click(driver,"//input[@id = 'login-signin']","Next Button");

        //14. Capture the error message
        Thread.sleep(5000);

        try {
        String errorMessage = driver.findElement(By.xpath("//p[@id = 'username-error']")).getText();
        System.out.println("The error message is " + errorMessage);
        } catch (Exception e) {
            System.out.println("Unable to capture error message because " + e);
        } //end of try catch


        Thread.sleep(2000);
    } //end of test

        @AfterSuite
        public void closeBrowser() throws InterruptedException {
            //driver.quit();

        } //end of After suite

} //end of Action item
