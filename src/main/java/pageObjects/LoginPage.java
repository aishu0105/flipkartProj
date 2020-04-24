package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(how = How.XPATH, using = "(//*[@type='text'])[2]")
    private WebElement txtbx_UserName;
    @FindBy(how = How.XPATH, using = "//*[@type='password']")
    private WebElement txtbx_Password;
    @FindBy(how = How.XPATH, using = "(//*[@type='submit'])[2]")
    private WebElement btn_Submit;

    public void enter_UserName(String name) {
        txtbx_UserName.sendKeys(name);
    }

    public void enter_Password(String lastName) {
        txtbx_Password.sendKeys(lastName);
    }

    public void click_Submit() {
        btn_Submit.click();
    }
}
