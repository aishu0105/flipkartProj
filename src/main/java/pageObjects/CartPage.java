package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
        public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CLASS_NAME, using ="_3oJBMI")
    private WebElement btnAddToBasket;
    @FindBy(how = How.XPATH, using ="(//*[@class='gdUKd9'])[2]")
    private WebElement btnRemoveBasket;


    public void click_AddToCart(){
        btnAddToBasket.click();
    }

    public void click_ShowBasket(){
        btnAddToBasket.click();
    }

    public void click_removeBasket(){
        btnRemoveBasket.click();
    }

}
