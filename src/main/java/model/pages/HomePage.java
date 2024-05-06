package model.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import model.Direction;
import model.components.product_item.ProductItemComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    private static final By titleSel = AppiumBy.xpath("//android.view.View[@text=\"Home\"]");
    private By searchInputSel = AppiumBy.accessibilityId("searchInput");
    private By categorySectionSel = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"categories\"]/following-sibling::android.view.ViewGroup");
    private By categoryListSel = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"categoryBadge\"]//android.widget.TextView");
    private List<WebElement> productItemList;

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public WebElement searchInput(){
        return driver.findElement(searchInputSel);
    }

    public WebElement categorySection(){
        return driver.findElement(categorySectionSel);
    }
    public List<String> getCategoryList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryListSel));
        return driver.findElements(categoryListSel)
                .stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }

    public List<ProductItemComponent> getProductItemCompList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductItemComponent.selector));
        List<WebElement> productItemList = driver.findElements(ProductItemComponent.selector);
        return productItemList.stream().map(element -> {
            return new ProductItemComponent(driver, element);
        }).collect(Collectors.toList());
    }

    public void swipeOnTop() {
        Rectangle searchInputRect = this.searchInput().getRect();
        int startY = searchInputRect.getY() + searchInputRect.getHeight();
        do {
            try {
                this.categorySection().isDisplayed();
                break;
            } catch (Exception e) {
                this.swipeVerticalByPixel(Direction.UP, startY, 50);
            }
        } while (true);
    }
}
