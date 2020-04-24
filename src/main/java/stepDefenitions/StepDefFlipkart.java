package stepDefenitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StepDefFlipkart {
    WebDriver driver;
    int noOfProducts;
    String exptProdDesc;
    String exptPrice;



    @Given("^Launch \"([^\"]*)\" browser$")
    public void launchBrowser(String browser) throws Exception {
        // if user input is firefox, the firefox browser will be launched
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
            String windowHandle = driver.getWindowHandle();
        //if user input is chrome, chrome broswer willl be launched
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            String windowHandle = driver.getWindowHandle();
        // if user input is ie, ie browser will be launched
        } else if (browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "src\\main\\resources\\drivers\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            String windowHandle = driver.getWindowHandle();
        } else {
            throw new Exception("Browser is not correct");
        }
        //wait for the browser to launch
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @When("^I launch the Flipkart Application$")
    public void LaunchApplication() throws Exception {
        //launch the flipkart application
        FileInputStream fis = new FileInputStream("src\\main\\resources\\testData\\testdata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(1);
        Cell cell = row.getCell(0);
        driver.get(String.valueOf(cell));
        driver.manage().window().maximize();

    }


    @And("^Login with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void login(String userName, String password) throws Exception {
        //Enter the credentials in login page
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enter_UserName(userName);
        loginPage.enter_Password(password);
        loginPage.click_Submit();

    }



    @And("^I search for \"([^\"]*)\" in the product listing page$")
    public void searchProduct(String productName) throws Exception {
        HomePage homePage=new HomePage(driver);
        //wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
        //Enter the product name in search tab
        homePage.enter_ProductName(productName);
        homePage.click_SearchIcon();

    }
    //get the number of products listed in the page.
    @And("^I get the number of products listed$")
    public void noOfProducts() {
        //get the count of products in grid view
        ProductListingPage listingPage=new ProductListingPage(driver);
        Boolean listView=listingPage.listing_ListView();
        if(listView){
            noOfProducts=listingPage.allProducts_LstView();
        }
        else
        {
            noOfProducts=listingPage.allProducts_GridView();
        }


    }

    @And("^I Add one random item to the shopping cart with \"([^\"]*)\"$")
    public void addRandomItemToCart(String zipcode) {
        //generate a random number
        Random random = new Random();
        int randomInteger = random.nextInt(noOfProducts);
        while (randomInteger==0){
            randomInteger = random.nextInt(noOfProducts);
        }
        //click on any random product
        ProductListingPage listingPage=new ProductListingPage(driver);
        Boolean listView=listingPage.listing_ListView();
        if(listView){
            exptProdDesc =driver.findElement(By.xpath("(//*[@class='_2cLu-l'])[" + randomInteger + "]")).getText();
            exptPrice=driver.findElement(By.xpath("(//*[contains(@class,'_1vC4OE')])[" + randomInteger + "]")).getText();
            driver.findElement(By.xpath("(//*[@class='_2cLu-l'])[" + randomInteger + "]")).click();
        }
        else
        {
            exptProdDesc =driver.findElement(By.xpath("(//*[@class='_3wU53n'])[" + randomInteger + "]")).getText();
            exptPrice=driver.findElement(By.xpath("(//*[contains(@class,'_2rQ-NK')])[" + randomInteger + "]")).getText();
            driver.findElement(By.xpath("(//*[@class='_3wU53n'])[" + randomInteger + "]")).click();
        }
        //Shifting the control to the new tab opened
        ArrayList tabs = new ArrayList (driver.getWindowHandles());
        driver.switchTo().window(String.valueOf(tabs.get(1)));
        //clear the value in the zipcode field and enter the value sent by the user
        ProductDescriptionPage productDescriptionPage=new ProductDescriptionPage(driver);
        productDescriptionPage.clear_Pincode();
        productDescriptionPage.set_Pincode(zipcode);
        productDescriptionPage.submit_Pincode();


    }

    //click on add to basket button in the description page and navigate to cart page
    @And("^I checkout$")
    public void iCheckout() {
        CartPage cartPage=new CartPage(driver);
        cartPage.click_AddToCart();
        cartPage.click_ShowBasket();
    }

    //Validate if the product description in teh cart page matches the one in listing page
    @Then("^Validate the Name in checkout page$")
    public void validateName() {
        CheckoutPage CheckoutPage=new CheckoutPage(driver);
        String actProdDesc =CheckoutPage.get_ProductName();
        Assert.assertEquals(exptProdDesc,actProdDesc);
    }

    //Validate if the product price in teh cart page matches the one in listing page
    @Then("^Validate the Price in checkout page$")
    public void validatePrice() {
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        String actPrice =checkoutPage.get_ProductPrice();
        Assert.assertEquals(exptPrice,actPrice);
    }

    //close the broswer after validation
    @Then("^I close the browser$")
    public void iCloseTheBrowser() {
        driver.quit();
    }

}

