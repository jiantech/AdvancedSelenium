package SVG;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class TestAppDiagram {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Tutorial\\Workout\\AdvancedSelenium\\src\\test\\resources\\drivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://app.diagrams.net/");
        driver.findElement(By.xpath("//span[contains(text(),'Decide later')]")).click();

        WebElement drag = driver.findElement(By.xpath("//div[@class='geSidebarContainer']//div//a[1]//*[local-name()='svg']//*[name()='g']//*[name()='g']//*[name()='g' and contains(@transform,'translate(')]//*[name()='rect' and contains(@x,'1.44')]"));

        Actions act= new Actions(driver);
        act.dragAndDropBy(drag, 300, 175).build().perform();

        WebElement element2 = driver.findElement(By.xpath("//div[@class='geSidebarContainer']//div//a[2]//*[local-name()='svg']//*[name()='g']//*[name()='g']//*[name()='g' and contains(@transform,'translate(')]//*[name()='rect' and contains(@x,'1.44')]"));

        act= new Actions(driver);
        act.dragAndDropBy(element2, 450, 175).build().perform();

        driver.findElement(By.xpath("//div[@class='geDiagramContainer geDiagramBackdrop']//*[local-name()='svg']")).click();
        WebElement rectangle = driver.findElement(By.xpath("//body[1]/div[8]/*[local-name()='svg'][1]/*[name()='g'][1]/*[name()='g'][2]/*[name()='g'][1]/*[name()='rect'][1]"));
        //act.moveToElement(rectangle).perform();

        driver.findElement(By.xpath("//div[@class='geDiagramContainer geDiagramBackdrop']//*[local-name()='svg']")).click();
        WebElement roundedRect = driver.findElement(By.xpath("//body[1]/div[8]/*[local-name()='svg'][1]/*[name()='g'][1]/*[name()='g'][2]/*[name()='g'][2]/*[name()='rect'][1]"));
        act.moveToElement(roundedRect).perform();
        XFinder xFinderRR = new XFinder(driver, roundedRect);

        act.moveToElement(rectangle).perform();
        XFinder xFinderRectangle = new XFinder(driver, rectangle);
        Wait wait = new FluentWait(driver).ignoring(StaleElementReferenceException.class).pollingEvery(Duration.ofMillis(500)).withTimeout(Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(xFinderRectangle.getX(1)));
        int x = xFinderRR.getRect(7).getX() - xFinderRectangle.getRect(15).getX();
        act.dragAndDropBy(xFinderRectangle.getX(15), x, 0).perform();
        driver.findElement(By.xpath("//div[@class='geDiagramContainer geDiagramBackdrop']//*[local-name()='svg']")).click();









        //ArrowFinder arrowFinder = new ArrowFinder(driver, ele);

        //DotFinder dotFinder = new DotFinder(driver, roundedRect);
        //arrowFinder.dragArrow(ArrowFinder.ARROWTYPE.RIGHT, ele, roundedRect);

        /*ele.click();
        WebElement arrow = driver.findElement(By.xpath("//body[1]/div[8]/*[local-name()='svg'][1]/*[name()='g'][1]/*[name()='g'][3]/*[name()='g'][6]/*[name()='image']"));

        System.out.println(arrow.getLocation());

        act.dragAndDropBy(arrow, 30, 175).build().perform();

        //act.dragAndDrop(arrow, element2).build().perform();
         */

        //driver.close();
    }

}