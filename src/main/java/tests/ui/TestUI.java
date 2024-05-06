package tests.ui;

import org.testng.annotations.Test;
import test_data.model.product_item.ProductItem;
import test_data.util.DataObjectBuilder;
import test_flow.ui.UIFlow;
import tests.BaseTest;

public class TestUI extends BaseTest {

    @Test
    public void testUI() {
        UIFlow uiFlow = new UIFlow(getDriver());
        uiFlow.navigateToHomePage();
        uiFlow.verifyCategoryListNotEmpty();
        uiFlow.verifyProductItemListNotEmpty();

        uiFlow.verifyProductItemCorrect(getProductItemList());
    }

    public ProductItem[] getProductItemList() {
        String jsonFile = "/src/main/java/test_data/model/product_item/ProductItemList.json";
        return DataObjectBuilder.buildDataObject(jsonFile, ProductItem[].class);
    }
}
