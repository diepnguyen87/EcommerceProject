package test_flow.order;

import io.appium.java_client.AppiumDriver;
import model.components.cart.CartComponent;
import model.components.cart.CartItemComponent;
import model.components.checkout.CheckoutComponent;
import model.components.checkout.ConfirmOrderComponent;
import model.components.checkout.PaymentComponent;
import model.components.checkout.ShippingComponent;
import model.components.product_item.ProductItemComponent;
import model.pages.CartPage;
import model.pages.HomePage;
import org.testng.Assert;
import test_data.model.order.OrderItem;
import test_data.model.order.ShippingAddress;
import test_flow.BaseFlow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFlow extends BaseFlow {

    private OrderItem[] orderItemList = null;
    private Map<String, Integer> map = null;
    private CartPage cartPage;

    public OrderFlow(AppiumDriver driver) {
        super(driver);
        cartPage = new CartPage(driver);
    }

//    public void orderItems(OrderItem[] orderItemList) {
//        this.orderItemList = orderItemList;
//
//        HomePage homePage = new HomePage(driver);
//        int deviceHeight = driver.manage().window().getSize().getHeight();
//
//        Rectangle bottomNavigateRect = homePage.getNavigationComp().getRect();
//        int startYRatio_Down = (bottomNavigateRect.getY() * 100 / deviceHeight) - 2;
//
//        int endYRatio_Down = startYRatio_Down - 25;
//
//        Rectangle searchInputRect = homePage.searchInput().getRect();
//        int startYRatio_UP = ((searchInputRect.getY() + searchInputRect.getHeight()) * 100 / deviceHeight) + 5;
//
//        int endYRatio_UP = startYRatio_UP + 25;
//        List<ProductItemComponent> productItemCompList = new HomePage(driver).getProductItemCompList();
//
//        for (int orderIndex = 0; orderIndex < orderItemList.length; orderIndex++) {
//            OrderItem orderItem = orderItemList[orderIndex];
//            for (int productIndex = 0; productIndex < productItemCompList.size(); productIndex++) {
//                ProductItemComponent productItemComp = productItemCompList.get(productIndex);
//                try {
//                    productItemComp.addToCartButton().isDisplayed();
//                } catch (Exception e) {
//                    homePage.swipeVertical(Direction.DOWN,0, startYRatio_Down, 0, endYRatio_Down);
//                }
//                if (orderItem.getName().equalsIgnoreCase(productItemComp.getProductName())) {
//                    for (int orderItemQuality = 0; orderItemQuality < orderItem.getQuality(); orderItemQuality++) {
//                        productItemComp.addToCartButton().click();
//                    }
//                    break;
//                }
//            }
//            do {
//                try {
//                    homePage.categorySection().isDisplayed();
//                    break;
//                } catch (Exception e) {
//                    homePage.swipeVertical(Direction.UP, 0, startYRatio_UP, 0, endYRatio_UP);
//                }
//            } while (true);
//        }
//    }

    public void orderItems(OrderItem[] orderItemList) {
        this.orderItemList = orderItemList;

        HomePage homePage = new HomePage(driver);
        List<ProductItemComponent> productItemCompList = homePage.getProductItemCompList();

        for (int orderIndex = 0; orderIndex < orderItemList.length; orderIndex++) {
            OrderItem orderItem = orderItemList[orderIndex];
            for (int productIndex = 0; productIndex < productItemCompList.size(); productIndex++) {
                ProductItemComponent productItemComp = productItemCompList.get(productIndex);
                if (orderItem.getName().equalsIgnoreCase(productItemComp.getProductName())) {
                    for (int orderItemQuality = 0; orderItemQuality < orderItem.getQuality(); orderItemQuality++) {
                        productItemComp.addToCartButton().click();
                    }
                    break;
                }
            }
        }
        homePage.swipeOnTop();
    }


    public void verifyItemQualityOnCartIcon() {
        if (map == null) {
            getExpectedPriceAndQuality();
        }
        int expectedOrderQuality = map.get("TotalQuality");
        int actualOrderQuality = Integer.parseInt(new CartPage(driver).getNavigationComp().getOrderQualityOnCartIcon(expectedOrderQuality));
        Assert.assertEquals(actualOrderQuality, expectedOrderQuality);
    }

    public void verifyItemsInCart(OrderItem[] orderItemList) {
        CartComponent cartComp = cartPage.getCartComponent();
        verifyCartTitle(cartComp);
        verifyCartItemList(cartComp, orderItemList);
        verifyTotalPrice(cartComp, orderItemList);
    }

    private void verifyCartTitle(CartComponent cartComp) {
        Assert.assertEquals(cartComp.getCardHeader(), "Cart");
    }

    private void verifyCartItemList(CartComponent cartComp, OrderItem[] orderItemList) {
        List<CartItemComponent> cartItemComponentList = cartComp.getCartItemComponentList();

        int itemIndex = 0;
        for (int orderIndex = 0; orderIndex < orderItemList.length; orderIndex++) {
            OrderItem currentOrderItem = orderItemList[orderIndex];
            int i = 0;

            while (i < currentOrderItem.getQuality()) {
                CartItemComponent currentCartItem = cartItemComponentList.get(itemIndex);
                Assert.assertEquals(currentCartItem.getProductName(), currentOrderItem.getName());
                Assert.assertEquals(currentCartItem.getProductPrice(), currentOrderItem.getPrice());
                ++i;
                itemIndex += 1;
            }
        }
    }

    private void verifyTotalPrice(CartComponent cartComp, OrderItem[] orderItemList) {
        List<CartItemComponent> cartItemComponentList = cartComp.getCartItemComponentList();
        int actualTotalPrice = 0;
        for (CartItemComponent cartItemComponent : cartItemComponentList) {
            actualTotalPrice += cartItemComponent.getProductPrice();
        }
        if (map == null) {
            getExpectedPriceAndQuality();
        }
        Assert.assertEquals(actualTotalPrice, map.get("TotalPrice"));
    }

    private Map<String, Integer> getExpectedPriceAndQuality() {
        map = new HashMap<>();
        int totalPrice = 0;
        int totalQuality = 0;
        if (this.orderItemList == null) {
            throw new Error("Please call order first using the method orderItems(OrderItem[] orderItemList)");
        }

        for (OrderItem orderItem : this.orderItemList) {
            int currentQuality = orderItem.getQuality();
            totalQuality += currentQuality;
            totalPrice += orderItem.getPrice() * currentQuality;
        }
        map.put("TotalQuality", totalQuality);
        map.put("TotalPrice", totalPrice);

        return map;
    }

    public void cancelOrder() {
        CartComponent cartComp = cartPage.getCartComponent();
        cartComp.clickOnClearBtn();
    }

    public void verifyEmptyItemQualityOnCartIcon() {
        Assert.assertTrue(new HomePage(driver).getNavigationComp().isOrderQualityUnDisplayedOnCartIcon(3));
    }

    public void verifyEmptyCart() {
        Assert.assertEquals(cartPage.getCartComponent().getEmptyCartMessage(), "Add products to your cart to get started");
    }

    public void checkout(ShippingAddress shippingAddress) {
        CartComponent cartComp = cartPage.getCartComponent();
        cartComp.clickOnCheckoutBtn();
        CheckoutComponent checkoutComp = cartPage.getCheckoutComponent();

        ShippingComponent shippingComp = checkoutComp.getShippingComponent();
        PaymentComponent paymentComp = checkoutComp.getPaymentComponent();
        ConfirmOrderComponent confirmOrderComp = checkoutComp.getConfirmOrderComponent();
        confirmShipping(shippingComp, shippingAddress);
        confirmPayment(paymentComp);
        confirmOrder(confirmOrderComp, shippingAddress);
    }

    private void confirmShipping(ShippingComponent shippingComp, ShippingAddress shippingAddress) {
        shippingComp.phoneNum().sendKeys(shippingAddress.getPhoneNum());
        shippingComp.address1().sendKeys(shippingAddress.getAddress1());
        shippingComp.address2().sendKeys(shippingAddress.getAddress2());
        shippingComp.city().sendKeys(shippingAddress.getCity());
        shippingComp.zipCode().sendKeys(shippingAddress.getZipCode());
        shippingComp.selectCountry(shippingAddress.getCountry());
        shippingComp.clickOnConfirmShippingBtn();
    }

    private void confirmPayment(PaymentComponent paymentComp) {
        paymentComp.selectCashOnDelivery();
        paymentComp.clickOnConfirmButton();
    }

    private void confirmOrder(ConfirmOrderComponent confirmOrderComp, ShippingAddress expectedShippingAdd) {
        String actualHeader = confirmOrderComp.getHeader();
        String shippingToSectionHeader = confirmOrderComp.getShippingToSectionHeader();
        String actualConfirmShippingAdd1 = confirmOrderComp.getConfirmShippingAddress1();
        String actualConfirmShippingAdd2 = confirmOrderComp.getConfirmShippingAddress2();
        String actualConfirmCity = confirmOrderComp.getConfirmCity();
        String actualConfirmZipCode = confirmOrderComp.getConfirmZipCode();
        String actualConfirmCountry = confirmOrderComp.getConfirmCountry();
        String actualItemSectionHeader = confirmOrderComp.getItemSectionHeader();

        Assert.assertEquals(actualHeader, "Confirm Order");
        Assert.assertEquals(shippingToSectionHeader, "Shipping to:");
        Assert.assertEquals(actualConfirmShippingAdd1, "Address: " + expectedShippingAdd.getAddress1());
        Assert.assertEquals(actualConfirmShippingAdd2, "Address2: " + expectedShippingAdd.getAddress2());
        Assert.assertEquals(actualConfirmCity, "City: " + expectedShippingAdd.getCity());
        Assert.assertEquals(actualConfirmZipCode, "Zip Code: " + expectedShippingAdd.getZipCode());
        Assert.assertEquals(actualConfirmCountry, "Country: " + expectedShippingAdd.getCountry());
        Assert.assertEquals(actualItemSectionHeader, "Items:");

        confirmOrderComp.clickOnPlaceOrderButton();
    }


    public void verifyOrderSuccess() {
        Assert.assertEquals(cartPage.getPopupNotificationComp().getMessageInfo1(), "Order Completed");
        Assert.assertEquals(cartPage.getCartComponent().getEmptyCartMessage(), "Add products to your cart to get started");
    }
}
