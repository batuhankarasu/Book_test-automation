package org.enuygun.base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver;



    @Before
    public void start(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.kitapyurdu.com/");
        driver.manage().window().maximize();
    }

    @After
    public void stop() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    public WebDriver getDriver(){
        return driver;
    }

}
