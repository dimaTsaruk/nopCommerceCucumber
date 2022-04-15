package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.util.List;

public class SearchCustomerPage {
    public WebDriver driver;
    WaitHelper waitHelper;

    public SearchCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    @FindBy(how = How.ID, using = "SearchEmail")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(how = How.ID, using = "SearchFirstName")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(how = How.ID, using = "SearchLastName")
    @CacheLookup
    WebElement txtLastName;

    @FindBy(how = How.ID, using = "search-customers")
    @CacheLookup
    WebElement btnSearch;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
    WebElement table;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
    List<WebElement> tableRows;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr//td")
    List<WebElement> tableColumn;


    public void setEmail(String email) {
        waitHelper.WaitForElement(txtEmail, 30);
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setFirstName(String fname) {
        waitHelper.WaitForElement(txtFirstName, 30);
        txtFirstName.clear();
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        waitHelper.WaitForElement(txtLastName, 30);
        txtLastName.clear();
        txtLastName.sendKeys(lname);
    }

    public void clickSearch() {
        btnSearch.click();
        waitHelper.WaitForElement(btnSearch, 30);
    }

    public int getNoOfRows() {
        return (tableRows.size());
    }

    public int getNoOfColumns() {
        return (tableColumn.size());
    }

    public boolean searchCustomerByEmail(String email) {
        boolean flag = false;
        for (int i = 1; i <= getNoOfRows(); i++) {
            String emailid = table.findElement(
                    By.xpath("//table[@id='customers-grid']//tbody" +
                            "/tr[" + i + "]/td[2]")).getText();
            System.out.println(emailid);

            if (emailid.equals("victoria_victoria@nopCommerce.com")) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean searchCustomerByName(String Name) {
        boolean flag = false;
        for (int i = 1; i <= getNoOfRows(); i++) {
            String name = table.findElement(
                    By.xpath("//table[@id='customers-grid']//tbody" +
                            "/tr[" + i + "]/td[3]")).getText();
            String names[] = name.split(" ");
            if (names[0].equals("James") && names[1].equals("Pan")) {
                flag = true;
            }
        }
        return true;
    }

}


