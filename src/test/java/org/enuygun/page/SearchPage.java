package org.enuygun.page;

import org.apache.log4j.Logger;
import org.enuygun.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
    final static Logger LOG = Logger.getLogger(SearchPage.class);
    public SearchPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "search-input")
    private WebElement searchInput;




    public void searchWriteTextAndPressEnter(String text){
        searchInput.sendKeys(text);
        pressEnter(searchInput);
    }

}
