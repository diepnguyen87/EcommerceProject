package model.components.checkout;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PaymentComponent extends Component {

    public static final By selector = AppiumBy.xpath("//android.view.View[@text=\"Checkout\"]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/following-sibling::android.view.ViewGroup");
    private By headerSel = AppiumBy.accessibilityId("paymentTabHeader");
    private By cashOnDeliverySel = AppiumBy.accessibilityId("Cash on Delivery");
    private By bankTransferSel = AppiumBy.accessibilityId("Bank Transfer");
    private By cardPaymentSel = AppiumBy.accessibilityId("Card Payment");
    private By confirmBtnSel = AppiumBy.accessibilityId("confirmBtn");

    public PaymentComponent(AppiumDriver driver, WebElement component) {
        super(driver, component);
    }

    public String getHeader() {
        return this.component.findElement(headerSel).getText();
    }

    public void selectCashOnDelivery() {
        this.component.findElement(cashOnDeliverySel).click();
    }

    public void selectBankTransfer() {
        this.component.findElement(bankTransferSel).click();
    }

    public void selectCardPayment() {
        this.component.findElement(cardPaymentSel).click();
    }

    public void clickOnConfirmButton() {
        this.component.findElement(confirmBtnSel).click();
    }
}
