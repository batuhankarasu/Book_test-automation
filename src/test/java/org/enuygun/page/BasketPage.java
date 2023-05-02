package org.enuygun.page;

import org.apache.log4j.Logger;
import org.enuygun.base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Random;


public class BasketPage extends BasePage {
    @FindBy(id = "button-cart")
    private WebElement addToBasketBtn;
    @FindBy(xpath = "//div[@id=\"sprite-cart-icon\"]")
    private WebElement basketProductNumber;
    @FindBy(xpath = "//*[@id=\"button-cart\"]/span[text()=\"Sepetinizde\"]")
    private WebElement basketCheck;
    @FindBy(id = "cart")
    private WebElement myBasket;
    @FindBy(id = "js-cart")
    private WebElement goToCart;
    @FindBy(xpath = "//*[@class=\"js-cart-update-product\"]/input[@name=\"quantity\"]")
    private WebElement basketIncrementInput;
    @FindBy(xpath = "//*[@title=\"Güncelle\"]")
    private WebElement updateBtn;
    @FindBy(xpath = "//*[@id=\"swal2-title\" and text()=\"Sepetiniz güncelleniyor!\"]")
    private WebElement yourCartUpdating;
    @FindBy(xpath = "//*[@title=\"Kaldır\"]")
    private WebElement basketRemove;
    @FindBy(xpath = "//*[@id=\"cart-items-empty\"]")
    private WebElement basketEmpty;


    final static Logger LOG = Logger.getLogger(BasketPage.class);
    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public void randomProductClick(){
        try {
            List<WebElement> product = driver.findElements(By.xpath("//div[@class=\"product-cr\"]//*[@class=\"name\"]"));
            Random rand = new Random();
            int rand_product = rand.nextInt(product.size());
            findScrollElementCenter(product.get(rand_product));
            wait(2);
            product.get(rand_product).click();
            waitUntilElementVisible(addToBasketBtn);
        }catch (Exception e){
            LOG.error("Random product sepete eklenirken problem oluştu.");
            Assert.fail("Random product sepete eklenirken problem oluştu.");
        }
    }
    public void addToBasketCartValueVerify(){
        try {
            int productNumber = Integer.valueOf(basketProductNumber.getText());
            addToBasketBtn.click();
            wait(3);
            int productNumberAddToBasket = Integer.parseInt(basketProductNumber.getText());
            if (productNumber + 1 == productNumberAddToBasket) {
                LOG.info("Sepete eklenen ürün sayısı doğru bir şekilde eklendi.");
            } else {
                LOG.error("addToBasketCartValueVerify yapılırken bir hata oluştu.");
                Assert.fail("addToBasketCartValueVerify yapılırken bir hata oluştu.");
            }
        }catch (Exception e){
            LOG.error("addToBasketCartValueVerify yapılırken bir hata sorun oluştu.");
            Assert.fail("addToBasketCartValueVerify yapılırken bir hata sorun oluştu.");
        }
    }

    public void addToBaksetUpdateVerify(){
        try {
            myBasket.click();
            waitUntilElementVisible(goToCart);
            goToCart.click();
            wait(3);
            waitUntilElementVisible(basketIncrementInput);
            int arttırmaoncesi=Integer.valueOf(basketIncrementInput.getAttribute("value"));
            basketIncrementInput.clear();
            wait(1);
            basketIncrementInput.sendKeys(String.valueOf(arttırmaoncesi+1));
            wait(1);
            updateBtn.click();
            waitUntilElementVisible(yourCartUpdating);
        }catch (Exception e){
            LOG.error("Update kontrol edilirken problem oluştu.");
            Assert.fail("Update kontrol edilirken problem oluştu.");
        }
    }


    public void basketRemoveVerify(){
        try {
            basketRemove.click();
            waitUntilElementVisible(basketEmpty);
        }catch (Exception e){
            LOG.error("basketRemoveVerify hata oluştu");
            Assert.fail();
        }

    }
}
