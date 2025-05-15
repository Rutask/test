import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://webdriveruniversity.com/Actions/index.html");
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDragAndDrop() {
        Actions actions = new Actions(driver);

        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));

        actions.dragAndDrop(drag, drop).build().perform();

        // Tikrinti ar pasikeitė tekstas po "drag and drop"
        String dropText = drop.getText();
        assertEquals("Dropped!", dropText);

        // Tikrina fono spalvą po "drag and drop"
        WebElement doubleClick = driver.findElement(By.cssSelector("div#droppable > p"));
        String clickBackgroundColor = doubleClick.getCssValue("background-color");
        assertEquals("rgba(244, 89, 80, 1)", clickBackgroundColor);
    }

    @Test
    public void testDoubleClick() {
        Actions actions = new Actions(driver);

        WebElement doubleClick = driver.findElement(By.cssSelector("div.div-double-click h2"));

        actions.doubleClick(doubleClick).build().perform();

        // Tikrina fono spalvą po "drag and drop"
        WebElement doubleClickColor = driver.findElement(By.cssSelector("div.double"));
        String doubleClickBackgroundColor = doubleClickColor.getCssValue("background-color");
        assertEquals("rgba(147, 203, 90, 1)", doubleClickBackgroundColor);
    }

    @Test
    public void testHover() {
        Actions actions = new Actions(driver);

        WebElement hover = driver.findElement(By.cssSelector("div:nth-of-type(1) .dropbtn"));

        actions.moveToElement(hover).build().perform();

        // Tikrina ar užvedus pelę išsiskleidęs meniu tekstas sutampa
        WebElement hoverLink = driver.findElement(By.cssSelector("div.dropdown-content a.list-alert"));
        String hoverText = hoverLink.getText();
        assertEquals("Link 1", hoverText);
    }

    @Test
    public void tesClickandHold() {
        Actions actions = new Actions(driver);

        WebElement clickAndHold = driver.findElement(By.cssSelector("#click-box"));

        actions.clickAndHold(clickAndHold).build().perform();

        // Tikrina ar laikant nuspaudus pelę - tekstas atitinka
        WebElement holdText = driver.findElement(By.cssSelector("div#click-box"));
        String holdingText = holdText.getText();
        assertEquals("Well done! keep holding that click now.....", holdingText);

        // Tikrina ar laikant nuspaudus pelę - spalva atitinka
        WebElement holdColor = driver.findElement(By.cssSelector("div#click-box"));
        String holdingColor = holdColor.getCssValue("background-color");
        assertEquals("rgba(0, 255, 0, 1)", holdingColor);
    }



    }
