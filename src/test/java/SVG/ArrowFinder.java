package SVG;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class ArrowFinder {
    private WebDriver driver;

    public enum ARROWTYPE{
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

    WebElement item;

    String xpath = "//div/img[contains(@src,'base64')]";

    WebElement leftArrow;
    WebElement rightArrow;
    WebElement topArrow;
    WebElement bottomArrow;

    public ArrowFinder(WebDriver driver, WebElement elm)
    {
        this.driver = driver;
        item = elm;
        elm.click();
        List<WebElement> elements = elm.findElements(By.xpath("//ancestor::*[name()='svg']/following-sibling::img"));
        topArrow = elements.get(0);
        bottomArrow = elements.get(1);
        rightArrow = elements.get(2);
        leftArrow = elements.get(3);
    }

    public void dragArrow(ARROWTYPE type, WebElement startElm, WebElement endElm)
    {
        Rectangle startRect = startElm.getRect();
        Rectangle rect = endElm.getRect();
        int offsetX = rect.getX() - rightArrow.getRect().getX();
        int offsetY = -1*(endElm.getRect().getHeight()/4);
        System.out.println(offsetX);
        Actions act= new Actions(driver);
        switch (type)
        {
            case RIGHT:
                act.moveToElement(rightArrow);
                //act.dragAndDrop(rightArrow,elm);
                act.dragAndDropBy(rightArrow, offsetX, offsetY).build().perform();
                break;
        }
    }

    private void waitForElm(WebElement elm)
    {
        Wait wait = new FluentWait(driver).ignoring(StaleElementReferenceException.class).pollingEvery(Duration.ofMillis(500)).withTimeout(Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(elm));
    }


}
