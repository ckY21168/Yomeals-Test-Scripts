package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationPopUpPage extends BasicPage {

	private WebDriver driver;
	JavascriptExecutor executor = (JavascriptExecutor) driver;

	public LocationPopUpPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getLocationHeader() {
		return this.driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[1]/div/a"));
	}

	public WebElement getLocationHeaderClose() {
		return this.driver.findElement(By.xpath("//*[@id=\"location-popup\"]/div/div/div/div/a"));
	}

	public WebElement getKeyword() {
		return this.driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return this.driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	public void openLocationHeader() {
		this.getLocationHeader().click();
	}

	public void setLocation(String locationName) {
		this.getKeyword().click();
		String argumentValue = getLocationItem(locationName).getAttribute("data-value");
		String enterLocation = ("arguments[0].value=arguments[1]");
		executor.executeScript(enterLocation, getLocationInput(), argumentValue);
		this.getSubmit();
		executor.executeScript("arguments[0].click();");
	}

	public void closeLocationHeader() {
		this.getLocationHeaderClose().click();
	}

}
