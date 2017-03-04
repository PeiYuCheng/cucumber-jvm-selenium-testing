package step_definitions;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.sun.tools.classfile.ConstantPool;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by peiyu on 3/2/17.
 */
public class ShoppingCart {
    String productPage = "https://www.amazon.ca/dp/B01KPKRH4G/ref=s9_acss_bw_cts_x_T3_w?pf_rd_m=A1IM4EOPHS76S7&pf_rd_s=merchandised-search-3&pf_rd_r=4H2H3EDP3XQA7205WHSA&pf_rd_t=101&pf_rd_p=5ae33d1d-76d8-45c6-aa32-7f56e37d6bcb&pf_rd_i=10293415011&th=1";
    String todayDealPage = "https://www.amazon.ca/gp/goldbox";
    String cartItemBefore;

    public WebDriver driver;
    public Select select;

    public ShoppingCart()
    {
        driver = Hooks.driver;
    }

    @Given("^user is on the product’s description page$")
    public void userIsOnTheProductSDescriptionPage() throws Throwable {
        driver.get(productPage);
        cartItemBefore = driver.findElement(By.id("nav-cart-count")).getText();
    }

    @When("^user selects valid options for product characteristics$")
    public void userSelectsValidOptionsForProductCharacteristics() throws Throwable {
        //get the "select size" element, which is to be disappeared
        final WebElement selectSizeMessage = driver.findElement(By.id("partialStateBuybox")).findElement(By.tagName("strong"));

        //find the dropdown list and select the first choice
        select = new Select(driver.findElement(By.name("dropdown_selected_size_name")));
        select.selectByIndex(1);

        //wait for the "select size" message to disappear
        new FluentWait<WebDriver>(driver)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.MILLISECONDS)
                .until(new Predicate<WebDriver>() {
                    public boolean apply(WebDriver d) {
                        try {
                            return !selectSizeMessage.isDisplayed();
                        } catch (StaleElementReferenceException elementHasDisappeared) {
                            return true;
                        }
                    }
                });
    }

    @And("^user adds the product to cart$")
    public void userAddsTheProductToCart() throws Throwable {
        System.out.println("adding product to cart");
        driver.findElement(By.id("add-to-cart-button")).click();
    }

    @Then("^the product is added to the shopping cart$")
    public void theProductIsAddedToTheShoppingCart() throws Throwable {
        WebElement addedToCart = driver.findElement(By.id("huc-v2-order-row-container"));
        Assert.assertEquals(true, addedToCart.isDisplayed());
    }

    @When("^user selects invalid options for product characteristics$")
    public void userSelectsInvalidOptionsForProductCharacteristics() throws Throwable {
        //do nothing
    }

    @Then("^the product is not added to the shopping cart$")
    public void theProductIsNotAddedToTheShoppingCart() throws Throwable {
        //the number of items in cart should remain the same
        String cartItemAfter = driver.findElement(By.id("nav-cart-count")).getText();
        Assert.assertEquals(cartItemAfter, cartItemBefore);
    }

    @Given("^user is on the Today’s Deals page$")
    public void userIsOnTheTodaySDealsPage() throws Throwable {
        driver.get(todayDealPage);
    }

    @When("^user selects “Add to Cart”$")
    public void userSelectsAddToCart() throws Throwable {
        //find the target "add to cart" button
        WebElement addToCart = driver.findElement(By.id("100_dealView_1")).findElement(By.tagName("button"));
        addToCart.click();
    }

    @Then("^the corresponding product is added to the shopping cart$")
    public void theCorrespondingProductIsAddedToTheShoppingCart() throws Throwable {
        //wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("a-icon-alert")));
        //find the confirmation element
        WebElement addedToCart = driver.findElement(By.id("100_dealView_1")).findElement(By.className("a-icon-alert"));
        Assert.assertEquals(true, addedToCart.isDisplayed());
    }

    @Given("^user has already selected the same product$")
    public void userHasAlreadySelectedTheSameProduct() throws Throwable {
        userIsOnTheProductSDescriptionPage();
        userSelectsValidOptionsForProductCharacteristics();
        userAddsTheProductToCart();
    }

    @Given("^user is on the same product’s description page$")
    public void userIsOnTheSameProductSDescriptionPage() throws Throwable {
        userIsOnTheProductSDescriptionPage();
    }

    @When("^user select the corresponding product characteristics$")
    public void userSelectTheCorrespondingProductCharacteristics() throws Throwable {
        userSelectsValidOptionsForProductCharacteristics();
    }
}
