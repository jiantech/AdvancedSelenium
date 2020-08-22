package SVG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
        WebElement ele = driver.findElement(By.xpath("//body[1]/div[8]/*[local-name()='svg'][1]/*[name()='g'][1]/*[name()='g'][2]/*[name()='g'][1]/*[name()='rect'][1]"));
        System.out.println(ele.getLocation());

        act.moveToElement(ele).perform();

        WebElement roundedRect = driver.findElement(By.xpath("//body[1]/div[8]/*[local-name()='svg'][1]/*[name()='g'][1]/*[name()='g'][2]/*[name()='g'][2]/*[name()='rect'][1]"));
        ArrowFinder arrowFinder = new ArrowFinder(driver, ele);

        //DotFinder dotFinder = new DotFinder(driver, roundedRect);
        arrowFinder.dragArrow(ArrowFinder.ARROWTYPE.RIGHT, ele, roundedRect);

        /*ele.click();
        WebElement arrow = driver.findElement(By.xpath("//body[1]/div[8]/*[local-name()='svg'][1]/*[name()='g'][1]/*[name()='g'][3]/*[name()='g'][6]/*[name()='image']"));

        System.out.println(arrow.getLocation());

        act.dragAndDropBy(arrow, 30, 175).build().perform();

        //act.dragAndDrop(arrow, element2).build().perform();
         */

        //driver.close();
    }

}