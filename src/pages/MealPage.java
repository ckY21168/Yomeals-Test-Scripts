package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage{

	public MealPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
		super(driver, wait, executor);
	}
	
	public WebElement getProductQuantityInput() {
		return this.driver
				.findElement(By.name("product_qty"));
	}
	
	public WebElement getAddCartButton() {
		return this.driver.findElement(By.className("btn--primary"));
	}
	
	public WebElement getFavouriteMealButton() {
		return this.driver.findElement(By.className("favourite"));
	}

	public void addMeal(int quantity) {
		String qty = String.valueOf(quantity);
		this.getProductQuantityInput().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		this.getProductQuantityInput().sendKeys(qty);
		this.getAddCartButton().click();
	}
	
	public void addToFavoriteMeal() {
		getFavouriteMealButton().click();
	}
}
