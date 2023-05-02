package org.enuygun.page;



import org.apache.log4j.Logger;
import org.enuygun.base.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    final static Logger LOG = Logger.getLogger(HomePage.class);
    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void checkHomePageOpen(){
        checkUrlIsOpen("https://www.kitapyurdu.com/");
        checkTitleOpen("Kitapyurdu, Kitapla buluşmanın en kolay yolu");
    }

}