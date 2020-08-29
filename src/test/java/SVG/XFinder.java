package SVG;

import org.openqa.selenium.*;
import java.util.List;
import java.util.stream.Collectors;

public class XFinder {
    private WebDriver driver;

    List<Rectangle> rects;
    List<WebElement> elements;

    public XFinder(WebDriver driver, WebElement element)
    {
        this.driver = driver;
        setPoints(element);
    }

    private void setPoints(WebElement element) {
        elements = element.findElements(By.xpath("parent::*/parent::*/following-sibling::*/*"));
        rects = elements.stream().map(p->p.getRect()).collect(Collectors.toList());
    }

    public WebElement getX(int i)
    {
        return elements.get(i);
    }

    public Rectangle getRect(int i)
    {
        return rects.get(i);
    }
}
