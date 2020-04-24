package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.XPATH, using ="(//*[@class='_3vlFMF']/a)[last()]")
    private WebElement txt_Name;

    @FindBy(how = How.XPATH, using ="(//*[@class='pMSy0p XU9vZa'])[last()]")
    private WebElement txt_Price;

    public String get_ProductName(){
        String productDesc=txt_Name.getText();
        return productDesc;
    }
    public String get_ProductPrice(){
        String price=txt_Price.getText();
        return price;
    }

}
