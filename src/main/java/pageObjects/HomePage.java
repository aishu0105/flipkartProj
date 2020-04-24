package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.CLASS_NAME, using ="LM6RPg")
    private WebElement txtbx_searchBox;

    @FindBy(how = How.XPATH, using ="(//*[@type='submit'])[1]")
    private WebElement lnk_SearchIcon;

    @FindBy(how = How.CLASS_NAME, using ="_2dcihZ")
    private WebElement lnk_cartNo;

    public void enter_ProductName(String productName) {
        txtbx_searchBox.sendKeys(productName);
    }

    public void click_SearchIcon() {
        lnk_SearchIcon.sendKeys(Keys.ENTER);
    }

    public String get_CartNo(){
        String cartNo=lnk_cartNo.getText();
        return cartNo;
    }

    public void click_CartIcon(){
        lnk_cartNo.click();
    }

}
