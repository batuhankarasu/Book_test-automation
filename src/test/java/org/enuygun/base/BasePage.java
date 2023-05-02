package org.enuygun.base;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public WebDriver driver;
    private static final String FILE_NAME = "data.xlsx";
    private long timeOutInSeconds=30L;
    final static Logger LOG = Logger.getLogger(BasePage.class);


    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkUrlIsOpen(String url) { //fix asssertequal
        try {
            if (url.equals(driver.getCurrentUrl())) {
                LOG.info("Sayfanın Url'si doğrudur");
            } else {
                LOG.error("Sayfanın Url'si doğru değildir.Sayfa açılamadı.");
                Assert.fail();
            }
        }catch (Exception e){
            LOG.error("Sayfanın Url'si kontrol edilirken problem oluştu.");
            Assert.fail();
        }

    }
    public void waitUntilElementVisible(WebElement elementFindBy) {
        try {
            FluentWait<WebDriver> wait = (new WebDriverWait(driver, Duration.ofSeconds(this.timeOutInSeconds))).pollingEvery(Duration.ofSeconds(5L)).withTimeout(Duration.ofSeconds(this.timeOutInSeconds)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(elementFindBy));
            LOG.info(elementFindBy.getTagName()+" ekranda görüldü.");
        } catch (Throwable var3) {

            LOG.error("Element: " + elementFindBy + " WebElement gorunur degil !!");
            Assert.fail("Element: " + elementFindBy + " WebElement gorunur degil !!");
        }
    }

    public void checkTitleOpen(String title) {
        if(title.equals(driver.getTitle())) {
            LOG.info("Sayfanın başlığı doğrudur.");
        }else {
            LOG.info("Sayfanın başlığı doğru değildir.");
            Assert.fail();
        }

    }
    public void pressEnter(WebElement webElement) {
        webElement.sendKeys(Keys.ENTER);
    }



    public String readExel(int cellIndex,int rowIndex) {
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            XSSFWorkbook workbook=new XSSFWorkbook(excelFile);
            XSSFSheet sheet=workbook.getSheetAt(0);
            XSSFRow row=sheet.getRow(rowIndex);
            XSSFCell cell =row.getCell(cellIndex);
            String getValue=cell.getStringCellValue();
            return getValue;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "İşlem gerçekleşmedi";
        } catch (IOException e) {
            e.printStackTrace();
            return "İşlem gerçekleşmedi";
        }
    }

    public void findScrollElementCenter(WebElement webElement) {
        try {
            LOG.debug(webElement + " scroll ediliyor..");
            String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
            ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, webElement);
            LOG.debug(webElement + " scroll ediliyor..");
        }catch (Exception e){
            LOG.error(webElement+"scroll edilirken problem oluştu..." );
            Assert.fail(webElement + " scroll edilirken problem oluştu...");
        }
    }
    public void wait(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void checkElementVisible(WebElement webElement){
        LOG.info(webElement+"element görünürlüğüne bakar");
        if (webElement.isDisplayed()) {
            LOG.info(webElement+"Element görünür");
        }else {
            LOG.info(webElement+"Element gözükmüyor");
            Assert.fail();
        }
    }
}
