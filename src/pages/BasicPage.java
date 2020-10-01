package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasicPage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor executor;

	public BasicPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
		this.driver = driver;
		this.wait = wait;
		this.executor = executor;
	}
}
