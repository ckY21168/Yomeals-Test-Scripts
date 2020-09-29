package pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor executor) {
		super(driver, wait, executor);
	}

	public WebElement getFirstNameInput() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastNameInput() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddressInput() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhoneNumberInput() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCodeInput() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public void countrySelect(String country) {
		WebElement getCountry = this.driver.findElement(By.name("user_country_id"));
		Select select = new Select(getCountry);
		select.selectByVisibleText(country);
	}

	public void stateSelect(String state) {
		WebElement getState = this.driver.findElement(By.name("user_state_id"));
		Select select = new Select(getState);
		select.selectByVisibleText(state);
	}

	public void citySelect(String city) {
		WebElement getCity = this.driver.findElement(By.name("user_city"));
		Select select = new Select(getCity);
		select.selectByVisibleText(city);
	}

	public WebElement getPhotoInput() {
		return this.driver.findElement(By.className("upload"));
	}
	
	public WebElement getImgRemove() {
		return this.driver.findElement(By.className("remove"));
	}
	
	public WebElement getImgUpload() {
		return this.driver.findElement(By.xpath("//input[@type='file']"));
	}
	
	public void photoUpload() throws IOException {
		String scriptStart = "arguments[0].click()";
		executor.executeScript(scriptStart, this.getPhotoInput());
		String imgPath = new File("images/ProfilePicture.jpg").getCanonicalPath();
		this.getImgUpload().sendKeys(imgPath);
	}
		
	public WebElement getSaveButton() {
		return this.driver.findElement(By.name("btn_submit"));
	}

	public void profileUpdate(String firstName, String lastName, String address, String phoneNumber, int zipCode, 
							  String country, String state, String city) throws InterruptedException {
		
		this.getFirstNameInput().clear();
		this.getLastNameInput().clear();
		this.getAddressInput().clear();
		this.getPhoneNumberInput().clear();
		this.getZipCodeInput().clear();
		
		this.getFirstNameInput().sendKeys(firstName);
		this.getLastNameInput().sendKeys(lastName);
		this.getAddressInput().sendKeys(address);
		this.getPhoneNumberInput().sendKeys(phoneNumber);
		String zipString = String.valueOf(zipCode);
		this.getZipCodeInput().sendKeys(zipString);
		
		this.countrySelect(country);
		Thread.sleep(3000);
		this.stateSelect(state);
		Thread.sleep(3000);
		this.citySelect(city);
		
		this.getSaveButton().click();
	}
	
}
