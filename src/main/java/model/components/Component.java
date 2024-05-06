package model.components;

import io.appium.java_client.AppiumDriver;
import model.Direction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

import static java.time.Duration.ofMillis;

public class Component {

    protected AppiumDriver driver;
    protected WebElement component;
    protected WebDriverWait wait;
    protected Actions action;

    public Component(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15L));
        action = new Actions(driver);
    }

    public Component(AppiumDriver driver, WebElement component) {
        this.driver = driver;
        this.component = component;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15L));
        action = new Actions(driver);
    }

    public Rectangle getRect() {
        return this.component.getRect();
    }

    public void swipeVertical(int startXRatio, int startYRatio, int endXRatio, int endYRatio) {
        Dimension dimension = driver.manage().window().getSize();
        int deviceHeight = dimension.getHeight();
        int deviceWidth = dimension.getWidth();

        int startX = startXRatio * (deviceWidth / 100);
        int endX = endXRatio * (deviceWidth / 100);
        int startY = startYRatio * (deviceHeight / 100);
        int endY = endYRatio * (deviceHeight / 100);

        PointerInput swipeAction = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipeSequence = new Sequence(swipeAction, 1);
        swipeSequence.addAction(swipeAction.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), startX, startY));
        swipeSequence.addAction(swipeAction.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipeSequence.addAction(new Pause(swipeAction, Duration.ofMillis(200)));
        swipeSequence.addAction(swipeAction.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));
        swipeSequence.addAction(swipeAction.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipeSequence));
    }

    public void swipeVerticalByPercentage(Direction dir, int startYRatio, int swipePercent) {
        Dimension dimension = driver.manage().window().getSize();
        int deviceHeight = dimension.getHeight();
        int deviceWidth = dimension.getWidth();

        int startX = (50 * deviceWidth) / 100;
        int endX = (50 * deviceWidth) / 100;
        int startY = (startYRatio * deviceHeight) / 100;
        int endY = 0;

        switch (dir) {
            case DOWN -> {
                endY = startY - swipePercent * (deviceHeight / 100);
            }
            case UP -> {
                endY = startY + (swipePercent * deviceHeight) / 100;
            }
        }


        PointerInput swipeAction = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipeSequence = new Sequence(swipeAction, 1);
        swipeSequence.addAction(swipeAction.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), startX, startY));
        swipeSequence.addAction(swipeAction.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipeSequence.addAction(new Pause(swipeAction, Duration.ofMillis(200)));
        swipeSequence.addAction(swipeAction.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));
        swipeSequence.addAction(swipeAction.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipeSequence));
    }

    public void swipeVerticalByPixel(Direction dir, int startY, int swipePercent) {
        Dimension dimension = driver.manage().window().getSize();
        int deviceHeight = dimension.getHeight();
        int deviceWidth = dimension.getWidth();
        int startX = deviceWidth / 2;
        int endX = startX;

        int percentHeight = deviceHeight / 100;
        int endY = 0;
        switch (dir) {
            case DOWN -> {
                startY = startY - percentHeight * 10;
                endY = startY - (percentHeight * swipePercent);
            }
            case UP -> {
                startY = startY + percentHeight * 10;
                endY = startY + (percentHeight * swipePercent);
            }
        }

        PointerInput swipeAction = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipeSequence = new Sequence(swipeAction, 1);
        swipeSequence.addAction(swipeAction.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), startX, startY));
        swipeSequence.addAction(swipeAction.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipeSequence.addAction(new Pause(swipeAction, Duration.ofMillis(200)));
        swipeSequence.addAction(swipeAction.createPointerMove(ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));
        swipeSequence.addAction(swipeAction.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipeSequence));
    }
}
