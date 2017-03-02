package step_definitions;

import com.google.common.base.Function;
import com.sun.tools.classfile.ConstantPool;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * Created by peiyu on 3/2/17.
 */
public class ShoppingCart {
    String productPage = "https://www.amazon.ca/dp/B01KPKRH4G/ref=s9_acss_bw_cts_x_T3_w?pf_rd_m=A1IM4EOPHS76S7&pf_rd_s=merchandised-search-3&pf_rd_r=4H2H3EDP3XQA7205WHSA&pf_rd_t=101&pf_rd_p=5ae33d1d-76d8-45c6-aa32-7f56e37d6bcb&pf_rd_i=10293415011&th=1";
    public WebDriver driver;
    public Select select;

//    //    public List<HashMap<String,String>> datamap = DataHelper.data();
//    public static List<HashMap<String,String>> datamap = null;


    public ShoppingCart()
    {
        driver = Hooks.driver;

//        datamap = new ArrayList<HashMap<String,String>>();
//        HashMap<String,String> sampleData = new HashMap<String,String>();
//        sampleData.put("username","abc@xyz.com");
//        sampleData.put("password","Test@123");
//        System.out.println("Current data" +sampleData);
//        datamap.add(sampleData);
    }

    @Given("^user is on the product’s description page$")
    public void userIsOnTheProductSDescriptionPage() throws Throwable {
        driver.get(productPage);
    }

    @When("^user selects valid options for product characteristics$")
    public void userSelectsValidOptionsForProductCharacteristics() throws Throwable {
        select = new Select(driver.findElement(By.name("dropdown_selected_size_name")));
        select.selectByIndex(1);
    }

    @And("^user adds the product to cart$")
    public void userAddsTheProductToCart() throws Throwable {
        while(select.getFirstSelectedOption().equals("Select")) {
            //wait until a size is selected
        }
        driver.findElement(By.name("submit.add-to-cart")).click();
    }

    @Then("^the product is added to the shopping cart$")
    public void theProductIsAddedToTheShoppingCart() throws Throwable {
        while(driver.getCurrentUrl()==productPage){
            //wait till page changes
        }
        WebElement addedToCart = driver.findElement(By.id("huc-v2-order-row-container"));

        Assert.assertEquals(true, addedToCart.isDisplayed());
    }

    @When("^user selects invalid options for product characteristics$")
    public void userSelectsInvalidOptionsForProductCharacteristics() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the product is not added to the shopping cart$")
    public void theProductIsNotAddedToTheShoppingCart() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^user is on the Today’s Deals page$")
    public void userIsOnTheTodaySDealsPage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^user selects “Add to Cart”$")
    public void userSelectsAddToCart() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the corresponding product is added to the shopping cart$")
    public void theCorrespondingProductIsAddedToTheShoppingCart() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
