package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartSummaryPage;
import pages.LocationPopUpPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;

public class MealTest extends BasicTest {

	@Test(priority = 0)
	public void addMealToCart() throws IOException, InterruptedException {
		LocationPopUpPage locationPopUpPage = new LocationPopUpPage(this.driver, this.wait, this.executor);
		NotificationSystemPage notificationSystemPage = new NotificationSystemPage(this.driver, this.wait,
				this.executor);
		MealPage mealPage = new MealPage(this.driver, this.wait, this.executor);

		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");

		locationPopUpPage.closeLocationHeader();
		Thread.sleep(2000);
		mealPage.addMeal(5);

		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("The Following Errors Occurred"));
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Please Select Location"));

		notificationSystemPage.systemMessageDisappear();
		
		locationPopUpPage.openLocationHeader();
		locationPopUpPage.setLocation("City Center - Albany");
		
		Thread.sleep(2000);
		mealPage.addMeal(5);

		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Meal Added To Cart"), 
				"[ERROR] Meal Add To Cart Action Failed");

	}

	@Test(priority = 5)
	public void addMealToFavourite() throws IOException, InterruptedException {
		LocationPopUpPage locationPopUpPage = new LocationPopUpPage(this.driver, this.wait, this.executor);
		LoginPage loginPage = new LoginPage(this.driver, this.wait, this.executor);
		NotificationSystemPage notificationSystemPage = new NotificationSystemPage(this.driver, this.wait,
				this.executor);
		MealPage mealPage = new MealPage(this.driver, this.wait, this.executor);

		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");

		locationPopUpPage.closeLocationHeader();

		mealPage.addToFavoriteMeal();

		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Please login first"));

		notificationSystemPage.systemMessageDisappear();

		this.driver.navigate().to(baseUrl + "guest-user/login-form");

		loginPage.login(email, password);
		notificationSystemPage.systemMessageDisappear();

		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");

		mealPage.addToFavoriteMeal();

		Assert.assertTrue(
				notificationSystemPage.notificationMessage().contains("Product has been added to your favorites"), 
				"[ERROR] Product Add To Favorites Action Failed");

	}

	@Test(priority = 10)
	public void clearCart() throws IOException, InterruptedException {
		CartSummaryPage cartSummaryPage = new CartSummaryPage(this.driver, this.wait, this.executor);
		LocationPopUpPage locationPopUpPage = new LocationPopUpPage(this.driver, this.wait, this.executor);
		NotificationSystemPage notificationSystemPage = new NotificationSystemPage(this.driver, this.wait,
				this.executor);
		MealPage mealPage = new MealPage(this.driver, this.wait, this.executor);
		SoftAssert softAssert = new SoftAssert();

		this.driver.navigate().to(baseUrl + "meals");

		locationPopUpPage.closeLocationHeader();

		locationPopUpPage.openLocationHeader();
		locationPopUpPage.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx").getCanonicalFile();
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			String newTabScript = "window.open();";
			executor.executeScript(newTabScript);

			String mainWindowHandles = driver.getWindowHandle();
			String newMealWindowHandles = "";

			String url = sheet.getRow(i).getCell(0).getStringCellValue();
			double quantityDouble = sheet.getRow(i).getCell(1).getNumericCellValue();

			int quantityInt = (int) quantityDouble;

			ArrayList<String> windowHandlesList = new ArrayList<String>(driver.getWindowHandles());
			windowHandlesList.remove(mainWindowHandles);
			newMealWindowHandles = windowHandlesList.get(0);

			driver.switchTo().window(newMealWindowHandles);
			driver.navigate().to(url);

			mealPage.addMeal(quantityInt);

			Thread.sleep(3000);

			softAssert.assertTrue(notificationSystemPage.notificationMessage().contains("Meal Added To Cart"),
					"[ERROR] Meal Add To Cart Action Failed");

			notificationSystemPage.systemMessageDisappear();
		}
		softAssert.assertAll();
		wb.close();
		fis.close();
		cartSummaryPage.clearAll();
		Assert.assertTrue(
				notificationSystemPage.notificationMessage().contains("All Meals Removed From Cart Successfully"),
				"[ERROR] Meals Removed From Cart Action Failed");
	}

}
