package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage{

	public SearchResultPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
		super(driver, wait, executor);
	}

	public List<WebElement> searchResults() {
		return this.driver.findElements(By.className("product-name"));
	}

	public List<String> mealNames() {
		List<String> mealsFound = new ArrayList<>();
		for (int i = 0; i < searchResults().size(); i++) {
			mealsFound.add(searchResults().get(i).getText());
		}
		return mealsFound;
	}

	public int mealsCount() {
		return this.searchResults().size();
	}

}
