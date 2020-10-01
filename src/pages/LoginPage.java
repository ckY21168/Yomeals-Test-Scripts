package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
		super(driver, wait, executor);
	}

	public WebElement getEmailInput() {
		return this.driver.findElement(By.name("username"));
	}

	public WebElement getPasswordInput() {
		return this.driver.findElement(By.name("password"));
	}

	public WebElement getLoginButton() {
		return this.driver.findElement(By.name("btn_submit"));
	}

	public void login(String email, String password) {
		this.getEmailInput().clear();
		this.getEmailInput().sendKeys(email);
		this.getPasswordInput().clear();
		this.getPasswordInput().sendKeys(password);
		this.getLoginButton().click();
	}

}
