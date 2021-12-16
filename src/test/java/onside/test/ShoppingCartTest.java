package onside.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartTest extends BaseTest  {

    @Test
    public void addProductToShoppingCart() {
        getDriver().get("https://www.saucedemo.com/");
        getElement(By.name("user-name")).sendKeys("standard_user");
        getElement(By.name("password")).sendKeys("secret_sauce");
        getElement(By.id("login-button")).click();

        Assert.assertTrue("Shopping cart link is not present", getElement(By.className("shopping_cart_link")).isDisplayed());
        List<WebElement> products = getDriver().findElements(By.className("inventory_item"));
        WebElement product = products.stream().findFirst().orElse(null);
        String expectedProductName = product.findElement(By.className("inventory_item_name")).getText();
        WebElement addToCartButton = product.findElement(By.xpath("//button[text()='Add to cart']"));
        addToCartButton.click();

        getElement(By.className("shopping_cart_link")).click();
        List<WebElement> cartItems = getDriver().findElements(By.className("cart_item"));
        WebElement cartItem = cartItems.stream().findFirst().orElse(null);
        String productName = cartItem.findElement(By.className("inventory_item_name")).getText();
        Assert.assertEquals(expectedProductName, productName);
    }
}
