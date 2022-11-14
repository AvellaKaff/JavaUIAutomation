package lesson6;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class XYZBank {
    private WebDriver driver;

    @FindBy(xpath = "//button[@class=\"btn home\"]")
    private WebElement button_home;
    @FindBy(xpath = "//button[@class=\"btn logout\"]")
    private WebElement button_logout;

    @FindBy(xpath = "//button[text()=\"Customer Login\"]")
    private WebElement button_customer_login;
    @FindBy(xpath = "//button[text()=\"Login\"]")
    private WebElement button_Login;

    @FindBy(xpath = "//button[@ng-click=\"transactions()\"]")
    private WebElement menu_button_transactions;
    @FindBy(xpath = "//button[@ng-click=\"deposit()\"]")
    private WebElement menu_button_deposit;
    @FindBy(xpath = "//button[@ng-click=\"withdrawl()\"]")
    private WebElement menu_button_withdrawl;
    @FindBy(xpath = "//button[text()=\"Deposit\"]")
    private WebElement button_input_deposit;
    @FindBy(xpath = "//button[text()=\"Withdraw\"]")
    private WebElement button_input_withdraw;

    @FindBy(xpath = "//button[@ng-click=\"manager()\"]")
    private WebElement button_manager_login;

    @FindBy(xpath = "//button[@ng-click=\"addCust()\"]")
    private WebElement menu_button_add_customer;
    @FindBy(xpath = "//input[@placeholder=\"First Name\"]")
    private WebElement input_first_name;
    @FindBy(xpath = "//input[@placeholder=\"Last Name\"]")
    private WebElement input_last_name;
    @FindBy(xpath = "//input[@placeholder=\"Post Code\"]")
    private WebElement input_post_code;
    @FindBy(xpath = "//button[text()=\"Add Customer\"]")
    private WebElement button_add_customer;

    @FindBy(xpath = "//button[@ng-click=\"openAccount()\"]")
    private WebElement menu_button_open_account;
    @FindBy(xpath = "//option[text()=\"Got Van\"]")
    private WebElement select_customer_Got_Van;
    @FindBy(xpath = "//option[text()=\"Dollar\"]")
    private WebElement select_currency_Dollar;
    @FindBy(xpath = "//option[text()=\"Pound\"]")
    private WebElement select_currency_Pound;
    @FindBy(xpath = "//option[text()=\"Rupee\"]")
    private WebElement select_currency_Rupee;
    @FindBy(xpath = "//button[text()=\"Process\"]")
    private WebElement button_Process;


    @FindBy(xpath = "//button[@ng-click=\"showCust()\"]")
    private WebElement menu_button_Customers;
    @FindBy(xpath = "//td[text()=\"Got\"]/following-sibling::td/span")
    private WebElement find_Got_account_number;
    @FindBy(xpath = "//input[@placeholder=\"Search Customer\"]")
    private WebElement input_search_customer;
    @FindBy(xpath = "//button[text()=\"Delete\"]")
    private WebElement delete_customer;

    @FindBy(xpath = "//label[contains(text(), 'Deposited')]/following-sibling::input")
    private WebElement input_deposit;
    @FindBy(xpath = "//label[contains(text(), 'Withdrawn')]/following-sibling::input")
    private WebElement input_withdrawn;

    @FindBy(xpath = "//option[text()=\"Harry Potter\"]")
    private WebElement select_Harry_Potter;

    @FindBy(xpath = "//option[@label=\"1004\"]")
    private WebElement select_account_1004;
    @FindBy(xpath = "//option[@label=\"1005\"]")
    private WebElement select_account_1005;
    @FindBy(xpath = "//option[@label=\"1006\"]")
    private WebElement select_account_1006;

    @FindBy(xpath = "//span[@class=\"fontBig ng-binding\"]")
    private WebElement name;
    @FindBy(xpath = "//strong[@class=\"ng-binding\"][1]")
    private WebElement Account_Number;
    @FindBy(xpath = "//strong[@class=\"ng-binding\"][2]")
    private WebElement balanceString;
    @FindBy(xpath = "//strong[@class=\"ng-binding\"][3]")
    private WebElement Currency;

    public String getName() {
        return name.getText();
    }

    public String getBalanceString() {
        return balanceString.getText();
    }

    public String getAccount_Number() {
        return Account_Number.getText();
    }

    public String getCurrency() {
        return Currency.getText();
    }

    public String getFind_Got_account_number() {
        return find_Got_account_number.getText();
    }

    public WebElement getInput_search_customer() {
        return input_search_customer;
    }

    public WebElement getButton_manager_login() {
        return button_manager_login;
    }

    public XYZBank(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public XYZBank clickHome() {
        button_home.click();
        return this;
    }

    public XYZBank clickLogout() {
        button_logout.click();
        return this;
    }

    public XYZBank customerLoginPotter() {
        button_customer_login.click();
        select_Harry_Potter.click();
        button_Login.click();
        return this;
    }

    public XYZBank selectAccount(int acc_1004_1005_1006) {
        switch (acc_1004_1005_1006) {
            case (1004):
                select_account_1004.click();
                break;
            case (1005):
                select_account_1005.click();
                break;
            case (1006):
                select_account_1006.click();
                break;
        }
        return this;
    }


    public int getBalance() {
        int balance = Integer.parseInt(getBalanceString());
        return balance;
    }

    public XYZBank inputDeposit(String amount) {
        menu_button_deposit.click();
        input_deposit.sendKeys(amount);
        button_input_deposit.click();
        return this;
    }

    public XYZBank inputWithdrawn(String amount) {
        menu_button_withdrawl.click();
        input_withdrawn.sendKeys(amount);
        button_input_withdraw.click();
        return this;
    }

    public XYZBank managerLogin() {
        button_manager_login.click();
        return this;
    }

    public XYZBank addCustomer(String FirstName, String LastName, String PostCode) {
        menu_button_add_customer.click();
        input_first_name.sendKeys(FirstName);
        input_last_name.sendKeys(LastName);
        input_post_code.sendKeys(PostCode);
        button_add_customer.click();
        return this;
    }

    public XYZBank openAccountClick() {
        menu_button_open_account.click();
        return this;
    }

    public XYZBank openAccountSelectGotVan() {
        select_customer_Got_Van.click();
        return this;
    }

    public XYZBank openAccountSelectCurrency(String currency_dollar_pound_ruppee) {
        switch (currency_dollar_pound_ruppee) {
            case ("dollar"):
                select_currency_Dollar.click();
                break;
            case ("pound"):
                select_currency_Pound.click();
                break;
            case ("ruppee"):
                select_currency_Rupee.click();
                break;
        }
        return this;
    }

    public XYZBank openAccountClickProcess() {
        button_Process.click();
        return this;
    }

    public XYZBank checkCustomers() {
        menu_button_Customers.click();
        return this;
    }

    public XYZBank searchCustomer(String search) {
        input_search_customer.click();
        input_search_customer.sendKeys(search);
        return this;
    }
    public XYZBank deleteCustomer(){
        delete_customer.click();
        return this;
    }
}


