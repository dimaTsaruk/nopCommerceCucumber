package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
    public WebDriver driver;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By lnkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(), 'Customers')]");
    By lnkCustomers_menuItem = By.xpath("//a[@href='/Admin/Customer/List']//p");

    By btnAddnew = By.xpath("//*[@class='btn btn-primary']");

    By txtEmail = By.xpath("//input[@id='Email']");
    By txtPassword = By.xpath("//input[@id='Password']");

    By txtCustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");

    By listItemAdmin = By.xpath("//li[contains(text(), 'Administrators')]");
    By listItemRegistered = By.xpath("//li[contains(text(), 'Registered')]");
    By listItemGuests = By.xpath("//li[contains(text(), 'Guests')]");
    By listItemVendors = By.xpath("//li[contains(text(), 'Vendors')]");
    By listItemForumModerator = By.xpath("//li[contains(text(), 'Forum Moderators')]");

    By drpmgrOfVendor = By.xpath("//*[@id='VendorId']");
    By rdMaleGender = By.id("Gender_Male");
    By rdFeMaleGender = By.id("Gender_Female");

    By txtFirstName = By.xpath("//input[@id='FirstName']");
    By txtLastName = By.xpath("//input[@id='LastName']");

    By txtDob = By.xpath("//input[@id='DateOfBirth']");

    By txtCompanyName = By.xpath("//input[@id='Company']");
    By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
    By btnSave = By.xpath("//button[@name='save']");

    //Actions Methods............................................................

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickOnCustomersMenu() {
        driver.findElement(lnkCustomers_menu).click();
    }

    public void clickOnCustomersMenuItem() {
        driver.findElement(lnkCustomers_menuItem).click();
    }

    public void clickOnAddnew() {
        driver.findElement(btnAddnew).click();
    }

    public void setEmail(String email) {
        driver.findElement(txtEmail).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(txtPassword).sendKeys(password);
    }

    public void setCustomerRoles(String role) throws InterruptedException {
        if (!role.equals("Vendors")) {
            driver.findElement(By.xpath(""));
        }
        driver.findElement(txtCustomerRoles).click();
        WebElement listitem;
        Thread.sleep(3000);
        if (role.equals("Administrators")) {
            listitem = driver.findElement(listItemAdmin);
        } else if (role.equals("Guests")) {
            listitem = driver.findElement(listItemGuests);
        } else if (role.equals("Registered")) {
            listitem = driver.findElement(listItemRegistered);
        } else if (role.equals("Vendors")) {
            listitem = driver.findElement(listItemVendors);
        } else {
            listitem = driver.findElement(listItemGuests);
        }
        //listitem.click();
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", listitem);
    }

    public void setManagerOfVendor(String value) {
        Select drp = new Select(driver.findElement(drpmgrOfVendor));
        drp.selectByVisibleText(value);
    }

    public void setGender(String gender) {
        if (gender.equals("Male")) {
            driver.findElement(rdFeMaleGender).click();
        } else if (gender.equals("Female")) {
            driver.findElement(rdFeMaleGender).click();
        } else {
            driver.findElement(rdMaleGender).click(); // Default
        }
    }

    public void setFirstName(String fname) {
        driver.findElement(txtFirstName).sendKeys(fname);
    }

    public void setLastName(String lname) {
        driver.findElement(txtLastName).sendKeys(lname);
    }

    public void setDob(String dob) {
        driver.findElement(txtDob).sendKeys(dob);
    }

    public void setCompanyName(String comname) {
        driver.findElement(txtCompanyName).sendKeys(comname);
    }

    public void setAdminContent(String content) {
        driver.findElement(txtAdminContent).sendKeys(content);
    }

    public void clickOnSave() {
        driver.findElement(btnSave).click();
    }


}














