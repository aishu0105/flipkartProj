package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductListingPage {
    public ProductListingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.CLASS_NAME, using ="_2cLu-l")
    private WebElement lnk_ProdlstView;

    @FindBy(how = How.CLASS_NAME, using ="_2cLu-l")
    private List<WebElement> lnk_AllProdlstView;

    @FindBy(how = How.CLASS_NAME, using ="_3wU53n")
    private WebElement lnk_ProdGridView;

    @FindBy(how = How.CLASS_NAME, using ="_3wU53n")
    private List<WebElement> lnk_AllProdGridView;

    @FindBy(xpath = "<dynlstview>")
    private WebElement parentElement;

    public Boolean listing_ListView(){
        Boolean lstview=lnk_ProdlstView.isDisplayed();
        return lstview;
    }

    public int allProducts_LstView(){
        int noOfProd=lnk_AllProdlstView.size();
        return noOfProd;
    }

    public String getDynamicElementLst(int randomNumber){
        String prodDesc=parentElement.findElement(By.xpath("(//*[@class='_2cLu-l'])[" + randomNumber + "]")).getText();
        return prodDesc;
    }

    public Boolean listing_GridView(){
        Boolean gridView=lnk_ProdGridView.isDisplayed();
        return gridView;
    }

    public int allProducts_GridView(){
        int noOfProd=lnk_AllProdGridView.size();
        return noOfProd;
    }
}
