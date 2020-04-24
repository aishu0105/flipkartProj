package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductDescriptionPage {
    public ProductDescriptionPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.ID, using ="pincodeInputId")
    private WebElement txtbx_pincode;

    @FindBy(how = How.CLASS_NAME, using ="_2aK_gu")
    private WebElement lnk_pincodeclick;

    public void clear_Pincode(){
        txtbx_pincode.clear();
    }
    public void set_Pincode(String zipcode){
        txtbx_pincode.sendKeys(zipcode);
    }
    public void submit_Pincode(){
        lnk_pincodeclick.click();
    }
}
