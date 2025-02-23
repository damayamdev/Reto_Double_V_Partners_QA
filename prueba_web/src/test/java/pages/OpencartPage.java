package pages;

import java.util.Stack;

public class OpencartPage extends BasePage{

    private static final String ITEM_MYACCOUNT = "//span[contains(text(),'My Account')]";
    private static final String ITEM_REGISTER = "//a[contains(text(),'Register')]";
    private static final String ITEM_LOGIN = "//a[contains(text(),'Login')]";

    private static final String TXT_FIRSTNAME = "//input[@id='input-firstname']";
    private static final String TXT_LASTNAME = "//input[@id='input-lastname']";
    private static final String TXT_EMAIL = "//input[@id='input-email']";
    private static final String TXT_TELEPHONE = "//input[@id='input-telephone']";
    private static final String TXT_PASSWORD = "//input[@id='input-password']";
    private static final String TXT_CONFIRM = "//input[@id='input-confirm']";
    private static final String CHK_YES = "(//input[@name='newsletter'])[1]";
    private static final String BTN_CONTINUAR = "//input[@value='Continue']";
    private static final String BTN_LOGIN = "//input[@value='Login']";
    private static final String CHECKBOX_AGREE = "//input[@name='agree']";

    private String firsResult (String text){
        return "//p[contains(text(),'"+text+"')]";
    };

    private static final String LBL_MY_ACCOUNT = "//h2[contains(text(),'My Account')]";
    private static final String ITEM_LAPTOPS_NOTEBOOKS = "(//a[contains(text(),'Laptops & Notebooks')])[1]";
    private static final String ITEM_LAPTOPS_NOTEBOOKS_ALL = "//a[contains(text(),'Show All Laptops & Notebooks')]";

    private String addCart (String text){
        return "//a[contains(text(),'"+text+"')]//parent::h4//parent::div[@class='caption']//following-sibling::div//child::button[1]";
    };

    private static final String BTN_CARD = "//a[@title=\"Shopping Cart\"]";
    private static final String TXT_SEARCH = "//input[@name=\"search\"]";
    private static final String BTN_SEARCH = "//div[@id='search']//child::button";
    private static final String BTN_DELETE_PRODUCT = "(//td[contains(text(),'Product 18')]//following-sibling::td//child::button)[2]";

    private static final String BTN_CHECKOUT = "//a[contains(text(),'Checkout')]";


    private static final String TXT_PAYMENT_FIRSTNAME = "//input[@id='input-payment-firstname']";
    private static final String TXT_PAYMENT_LASTNAME = "//input[@id='input-payment-lastname']";
    private static final String TXT_PAYMENT_COMPANY = "//input[@id='input-payment-company']";
    private static final String TXT_PAYMENT_ADDRESS_ONE = "//input[@id='input-payment-address-1']";
    private static final String TXT_PAYMENT_ADDRESS_TWO = "//input[@id='input-payment-address-2']";
    private static final String TXT_PAYMENT_CITY = "//input[@id='input-payment-city']";
    private static final String TXT_PAYMENT_POSTCODE = "//input[@id='input-payment-postcode']";

    private static final String SELECT_COUNTRY = "//select[@id='input-payment-country']";
    private static final String SELECT_ZONE = "//select[@id='input-payment-zone']";
    private static final String BTN_COTINUE = "(//input[@id='button-payment-address'])";
    private static final String BTN_COTINUE_V2 = "//input[@id='button-shipping-address']";
    private static final String BTN_COTINUE_V3 = "//input[@id='button-shipping-method']";
    private static final String BTN_COTINUE_V4 = "//input[@id='button-payment-method']";
    private static final String TXT_COMENTS = "//textarea";
    private static final String BTN_CONFIRM = "//input[@id='button-confirm']";
    private static final String LBL_MESSAGE = "//h1[contains(text(),'Your order has been placed!')]";
    private static final String ADDRESS = "//input[@name=\"payment_address\"]";


    public OpencartPage() {
        super(driver);
    }

    public void navigateTo(){
        navigateTo("https://opencart.abstracta.us/");
    }

    public void navegateRegisterPage(){
        clickElement(ITEM_MYACCOUNT);
        clickElement(ITEM_REGISTER);
    }

    public void navegateLoginPage(){
        clickElement(ITEM_MYACCOUNT);
        clickElement(ITEM_LOGIN);
    }

    public void fillRegisterForm(String firstName, String lastName, String email, String telephone, String password, String confirmPassword){
        write(TXT_FIRSTNAME, firstName);
        write(TXT_LASTNAME, lastName);
        write(TXT_EMAIL, email);
        write(TXT_TELEPHONE, telephone);
        write(TXT_PASSWORD, password);
        write(TXT_CONFIRM, confirmPassword);
        clickElement(CHK_YES);
        clickElement(CHECKBOX_AGREE);
        clickElement(BTN_CONTINUAR);
    }

    public void fillLoginForm(String email, String password){
        write(TXT_EMAIL, email);
        write(TXT_PASSWORD, password);
        clickElement(BTN_LOGIN);
    }

    public String firstResult(String test){
        return textFromElement(firsResult(test));
    }

    public String orderResult(){
        return textFromElement(LBL_MESSAGE);
    }

    public String myAccountTitle(){
        return textFromElement(LBL_MY_ACCOUNT);
    }

    public void navigateToLaptopsNotebookPage(String item){
        clickElement(ITEM_LAPTOPS_NOTEBOOKS);
        clickElement(ITEM_LAPTOPS_NOTEBOOKS_ALL);
        clickElement(addCart(item));
    }

    public void navigateToTabletPage(String item){
        write(TXT_SEARCH, item);
        clickElement(BTN_SEARCH);
        clickElement(addCart(item));
        clickElement(BTN_CARD);
    }

    public void deleteProduct(){
        clickElement(BTN_DELETE_PRODUCT);
        try {
            Thread.sleep((long)10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void endCart(){
        scrollToElement(BTN_CHECKOUT);
        clickElement(BTN_CHECKOUT);
    }


    public void billingDetails(){
        timedScroll(15000);
        if (elementIsVisible(TXT_PAYMENT_FIRSTNAME)){
            write(TXT_PAYMENT_FIRSTNAME, "Juan");
            write(TXT_PAYMENT_LASTNAME, "Perez");
            write(TXT_PAYMENT_COMPANY, "Abstracta");
            write(TXT_PAYMENT_ADDRESS_ONE, "Calle 123");
            write(TXT_PAYMENT_ADDRESS_TWO, "Piso 1");
            write(TXT_PAYMENT_CITY, "Medellin");
            write(TXT_PAYMENT_POSTCODE, "28000");
            scrollToElement(BTN_COTINUE);
            selectFromDropdownText(SELECT_COUNTRY, "Colombia");
            timedScroll(1000);
            selectFromDropdownByValue(SELECT_ZONE, "721");

        }
        clickElement(BTN_COTINUE);
        timedScroll(10000);
        clickElement(BTN_COTINUE_V2);
        timedScroll(10000);
        write(TXT_COMENTS, "Comentario de prueba");
        scrollToElement(BTN_COTINUE_V3);
        clickElement(BTN_COTINUE_V3);
        timedScroll(10000);
        clickElement(CHECKBOX_AGREE);
        scrollToElement(BTN_COTINUE_V4);
        clickElement(BTN_COTINUE_V4);
        scrollToElement(BTN_CONFIRM);
        clickElement(BTN_CONFIRM);
    }

    public void quit(){
        closeBrowser();
    }


}
