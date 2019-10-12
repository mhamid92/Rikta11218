package Yahoo_Page_Object;

import Reusable_Classes.Abstract_Class;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Yahoo_Base_Class extends Abstract_Class {

    public Yahoo_Base_Class(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
    }

    //// Object for yahoo home page
    public static Yahoo_Homepage yahoo_homepage() {
        Yahoo_Homepage yahoo_homepage = new Yahoo_Homepage(driver);
        return yahoo_homepage;
    }

    //// Object for yahoo home page
    public static Yahoo_SearchResultPage yahoo_searchResultPage() {
        Yahoo_SearchResultPage yahoo_searchResultPage = new Yahoo_SearchResultPage(driver);
        return yahoo_searchResultPage;
    }

   /* //// Object for yahoo home page
    public static Login_Page login_page() {
        Login_Page login_page = new Login_Page(driver);
        return login_page;
    }*/

    public static Yahoo_Registration_Page yahoo_registration_page() {
        Yahoo_Registration_Page yahoo_registration_page = new Yahoo_Registration_Page(driver);
        return yahoo_registration_page;
    }


} //end of class
