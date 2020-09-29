package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationPage extends BasicPage{

	public AuthenticationPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
		super(driver, wait, executor);
	}
	
	public WebElement logoutDropDown() {
		return this.driver.findElement(By.className("after-arrow"));
	}
	public WebElement logoutButton() {
		return this.driver.findElement(By.id("header"));
	}
	public void logout() throws InterruptedException {
		this.logoutDropDown().click();
		this.logoutButton().click();
	}
}

