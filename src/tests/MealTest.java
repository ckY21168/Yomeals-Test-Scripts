package tests;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.LocationPopUpPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class MealTest extends BasicTest {

	@Test(priority = 0)
	public void addMealToCart() throws IOException, InterruptedException {
		LocationPopUpPage locationPopUpPage = new LocationPopUpPage(this.driver, this.wait, this.executor);
		LoginPage loginPage = new LoginPage(this.driver, this.wait, this.executor);
		NotificationSystemPage notificationSystemPage = new NotificationSystemPage(this.driver, this.wait,
				this.executor);
		ProfilePage profilePage = new ProfilePage(this.driver, this.wait, this.executor);
		AuthenticationPage authenticationPage = new AuthenticationPage(this.driver, this.wait, this.executor);
		MealPage mealPage = new MealPage(this.driver, this.wait, this.executor);

		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");

		locationPopUpPage.closeLocationHeader();

		mealPage.addMeal(5);

		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("The Following Errors Occurred"));
		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Please Select Location"));

		notificationSystemPage.systemMessageDisappear();

		locationPopUpPage.setLocation("City Center - Albany");

		mealPage.addMeal(5);

		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Meal Added To Cart"));

	}
}