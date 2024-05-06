package test_flow.ui;

import io.appium.java_client.AppiumDriver;
import model.components.product_item.ProductItemComponent;
import model.pages.HomePage;
import org.openqa.selenium.Rectangle;
import org.testng.Assert;
import test_data.model.product_item.ProductItem;
import test_flow.BaseFlow;

import java.util.List;

public class UIFlow extends BaseFlow {

    public UIFlow(AppiumDriver driver) {
        super(driver);
    }

    public void verifyCategoryListNotEmpty() {
        Assert.assertTrue(new HomePage(driver).getCategoryList().size() > 0);
    }

    public void verifyProductItemListNotEmpty() {
        Assert.assertTrue(new HomePage(driver).getProductItemCompList().size() > 0);
    }

    public void verifyProductItemCorrect(ProductItem[] expectedProductItemList) {
        HomePage homePage = new HomePage(driver);
        int deviceHeight = driver.manage().window().getSize().getHeight();

        Rectangle bottomNavigateRect = homePage.getNavigationComp().getRect();
        int startYRatio = (bottomNavigateRect.getY() * 100 / deviceHeight) - 2;

        int endYRatio = startYRatio - 25;

        List<ProductItemComponent> actualPoductItemList = homePage.getProductItemCompList();
        Assert.assertEquals(actualPoductItemList.size(), expectedProductItemList.length, "[ERROR] Actual and expected product item list are not the same size");

        for (int index = 0; index < expectedProductItemList.length; index++) {
            ProductItemComponent currentActualProductItem = actualPoductItemList.get(index);
            ProductItem currentExpectedProductItem = expectedProductItemList[index];
            Assert.assertEquals(currentActualProductItem.getProductName(), currentExpectedProductItem.getName());
            Assert.assertEquals(currentActualProductItem.getProductPrice(), currentExpectedProductItem.getPrice());
            Assert.assertTrue(currentActualProductItem.addToCartButton().isDisplayed());
        }
    }
}
