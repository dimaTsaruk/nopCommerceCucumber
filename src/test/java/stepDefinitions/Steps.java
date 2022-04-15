package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
import utilities.Driver;


public class Steps extends BaseClass {

    @Before
    public void setUp() {
        driver = Driver.getDriver();
    }


    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {
        lp = new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        // logger.info("*****Opening URL*****");
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) throws InterruptedException {
        //  logger.info("*****Providing Logging Details*****");
        lp.setUserName(email);
        lp.setPassword(password);

    }

    @When("Click on Login")
    public void click_on_login() throws InterruptedException {
        // logger.info("*****Starting Logging Proccess *****");
        lp.clickLogin();
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) {
        if (driver.getPageSource().contains("Login was unsuccessful.")) {
            driver.close();
            //logger.info("*****Login passed*****");
            Assert.fail();
        } else {
            //  logger.info("*****Login failed*****");
            Assert.assertEquals(title, driver.getTitle());
        }
    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() throws InterruptedException {
        //  logger.info("*****Click on logout link*****");
        lp.clickLogout();
        Thread.sleep(3000);
    }

    @Then("close browser")
    public void close_browser() {
        //  logger.info("*****Closing browser*****");
        Driver.closeDriver();
    }

    //Customers feature step definitions....................................
    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        addCust = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
    }

    @When("User click on customers Menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        // Thread.sleep(3000);
        addCust.clickOnCustomersMenu();
    }

    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() throws InterruptedException {
        //Thread.sleep(3000);
        addCust.clickOnCustomersMenuItem();
    }

    @When("click on Add new button")
    public void click_on_add_new_button() throws InterruptedException {
        addCust.clickOnAddnew();
        Thread.sleep(2000);
    }

    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration",
                addCust.getPageTitle());
    }

    @When("User enter customer info")
    public void userEnterCustomerInfo() throws InterruptedException {
//        logger.info("*****Adding new Customer*****");
//        logger.info("*****Providing Customer details*****");
        String email = randomString() + "@gmail.com";
        addCust.setEmail(email);

        addCust.setPassword("test123");
        //Registered - default
        // The customer cannot be in both 'Guest' and 'Registered' customer roles
        // And the customer to 'Guests' or 'Registered' customer role
        //addCust.setCustomerRoles("Guest");
        Thread.sleep(3000);

        // addCust.setManagerOfVendor("Vendor 2");
        addCust.setGender("Male");
        addCust.setFirstName("Dima");
        addCust.setLastName("Tsaruk");
        addCust.setDob("02/05/1994");
        addCust.setCompanyName("busyQA");
        addCust.setAdminContent("This is for testing ........");
    }

    @When("click on Save button")
    public void click_on_save_button() throws InterruptedException {
        // logger.info("*****Saving Customer data*****");
        addCust.clickOnSave();
        Thread.sleep(2000);
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String msg) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully."));
    }

    // Steps for searching a customer using Email ID ..................
    @When("Enter customer EMail")
    public void enter_customer_e_mail() {
        // logger.info("*****Searching a customer by using EmailID*****");
        searchCust = new SearchCustomerPage(driver);
        searchCust.setEmail("victoria_victoria@nopCommerce.com");
    }

    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCust.clickSearch();
        Thread.sleep(2000);
    }

    @Then("User should found Email in the Search table")
    public void user_should_found_email_in_the_search_table() {
        boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
        Assert.assertTrue(status);
    }

    //Steps for Searching user by First and Last Name ...................................................
    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        // logger.info("*****Searching user by First and Last Name*****");
        searchCust = new SearchCustomerPage(driver);
        searchCust.setFirstName("James");
    }

    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        searchCust.setLastName("Pan");
    }

    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {
        boolean state = searchCust.searchCustomerByName("James Pan");
        Assert.assertTrue(state);
    }


}
