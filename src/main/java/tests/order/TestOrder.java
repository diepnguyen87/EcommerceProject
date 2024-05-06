package tests.order;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.model.authentication.LoginAccount;
import test_data.model.order.OrderItem;
import test_data.model.order.ShippingAddress;
import test_data.util.DataObjectBuilder;
import test_flow.authentication.LoginFlow;
import test_flow.order.OrderFlow;
import tests.BaseTest;

public class TestOrder extends BaseTest {

    @Test(enabled = false)
    public void cancelOrder() {
        OrderFlow orderFlow = new OrderFlow(getDriver());
        OrderItem[] orderItemList = getOrderItemList();
        orderFlow.orderItems(orderItemList);
        orderFlow.navigateToCartPage();
        orderFlow.verifyItemQualityOnCartIcon();
        orderFlow.verifyItemsInCart(orderItemList);
        orderFlow.cancelOrder();
        orderFlow.verifyEmptyItemQualityOnCartIcon();
        orderFlow.verifyEmptyCart();
    }

    @Test(dataProvider = "getCorrectCredentials")
    public void testOrder(LoginAccount account) {
        LoginFlow loginFlow = new LoginFlow(getDriver());
        loginFlow.navigateToUserPage();
        loginFlow.login(account.getEmail(), account.getPassword());
        loginFlow.navigateToHomePage();

        OrderFlow orderFlow = new OrderFlow(getDriver());
        OrderItem[] orderItemList = getOrderItemList();
        orderFlow.orderItems(orderItemList);
        orderFlow.navigateToCartPage();
        //orderFlow.verifyItemsInCart(orderItemList);
        orderFlow.checkout(getShippingAddress());
        orderFlow.verifyOrderSuccess();
    }

    private OrderItem[] getOrderItemList() {
        String jsonFile = "/src/main/java/test_data/model/order/OrderItemList.json";
        return DataObjectBuilder.buildDataObject(jsonFile, OrderItem[].class);
    }

    @DataProvider
    public LoginAccount[] getCorrectCredentials() {
        String jsonFile = "/src/main/java/test_data/model/authentication/CorrectLoginAccount.json";
        return DataObjectBuilder.buildDataObject(jsonFile, LoginAccount[].class);
    }

    private ShippingAddress getShippingAddress() {
        String jsonFile = "/src/main/java/test_data/model/order/ShippingAddress.json";
        return DataObjectBuilder.buildDataObject(jsonFile, ShippingAddress.class);
    }

}
