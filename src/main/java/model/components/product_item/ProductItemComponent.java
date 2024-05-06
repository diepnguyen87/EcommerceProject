package model.components.product_item;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.Direction;
import model.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ProductItemComponent extends Component {

    public static final By selector = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"productItem\"]");
    private By productImageSel = AppiumBy.xpath("//android.widget.ImageView");
    private By productNameSel = AppiumBy.accessibilityId("productName");
    private By productPriceSel = AppiumBy.accessibilityId("productPrice");
    private By addToCartBtnSel = AppiumBy.accessibilityId("addToCartBtn");

    public ProductItemComponent(AppiumDriver driver, WebElement component){
        super(driver, component);
    }

    public String getProductName(){
        while (true){
            try {
                return this.component.findElement(productNameSel).getText();
            } catch (NoSuchElementException e) {
                swipeVerticalByPercentage(Direction.DOWN, 85, 25);
            }
        }
    }

    public String getProductPrice(){
        while (true){
            try {
                return this.component.findElement(productPriceSel).getText();
            } catch (NoSuchElementException e) {
                swipeVerticalByPercentage(Direction.DOWN, 85, 25);
            }
        }
    }

    public WebElement addToCartButton(){
        while (true){
            try {
                return this.component.findElement(addToCartBtnSel);
            } catch (NoSuchElementException e) {
                swipeVerticalByPercentage(Direction.DOWN, 85, 25);
            }
        }
    }
}
