package SVG;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DotFinder {
    private WebDriver driver;

    WebElement item;
    List<WebElement> dotElements;

    public DotFinder(WebDriver driver, WebElement elm)
    {
        this.driver = driver;
        item = elm;
        elm.click();
        dotElements = elm.findElements(By.xpath("//parent::*/parent::*/following-sibling::*/*[name()='g']/*[name()='image'][@height='18']"));
    }

    public WebElement getDot(int num)
    {
        return dotElements.get(num);
    }

    public Point getPos(int num)
    {
        getDot(num).click();
        return dotElements.get(num).getLocation();
    }
}
