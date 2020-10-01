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
	
	public WebElement getLogoutDropDown() {
		return this.driver.findElement(By.className("after-arrow"));
	}
	public WebElement getLogoutButton() {
		return this.driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/div[2]/ul/li/div/ul/li[2]/a"));
	}
	public void logout() throws InterruptedException {
		this.getLogoutDropDown().click();
		Thread.sleep(3000);
		this.getLogoutButton().click();
	}
}

