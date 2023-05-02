package org.enuygun.scenarios;

import org.apache.log4j.Logger;
import org.enuygun.base.BaseTest;
import org.enuygun.page.BasketPage;
import org.enuygun.page.HomePage;
import org.enuygun.page.SearchPage;

public class Test extends BaseTest {

    final static Logger LOG = Logger.getLogger(Test.class);


    @org.junit.Test
    public void test(){
        new HomePage(driver).checkHomePageOpen();
        String aranacakKelime=new HomePage(driver).readExel(0,0);
        new SearchPage(driver).searchWriteTextAndPressEnter(aranacakKelime);
        new BasketPage(driver).randomProductClick();
        new BasketPage(driver).addToBasketCartValueVerify();
        new BasketPage(driver).addToBaksetUpdateVerify();
        new BasketPage(driver).basketRemoveVerify();
    }
}
