package FIS_AssessmentUIAPI;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ebayTest {
    public static void main(String args[]){
        e2eautomatebuy_addToCart();

    }
    public static void e2eautomatebuy_addToCart(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.get("https://www.ebay.com/");

        // Search for 'book'
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='gh-ac']"));
        searchBox.sendKeys("book");
        searchBox.submit();

        // Click on the first book in the list
        WebElement firstBook = driver.findElement(By.xpath("//img[contains(@alt,'Before RedNote, Original Little Red Book: QUOTATIONS FROM CHAIRMAN MAO TSE-TUNG')]"));
        firstBook.click();

        // Get all the window handles
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Switch to the new tab
        driver.switchTo().window(tabs.get(1));

        // Click on 'Add to cart'
        WebElement addToCartButton = driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]"));
        addToCartButton.click();

        // Wait for the cart to update
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Verify the cart has been updated
        WebElement cartCount = driver.findElement(By.xpath("//span[contains(@aria-label,'Your shopping cart contains 1 items')]"));
        String itemCount = cartCount.getText();

        if (Integer.parseInt(itemCount) > 0) {
            System.out.println("Cart updated successfully! and the item count is:"+itemCount);
        } else {
            System.out.println("Cart update failed.");
        }

        // Close the browser
        driver.quit();
    }
}
