package tests;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.LocationPopUpPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest {

	@Test(priority = 0)
	public void editProfile() throws IOException, InterruptedException {
		LocationPopUpPage locationPopUpPage = new LocationPopUpPage(this.driver, this.wait, this.executor);
		LoginPage loginPage = new LoginPage(this.driver, this.wait, this.executor);
		NotificationSystemPage notificationSystemPage = new NotificationSystemPage(this.driver, this.wait,
				this.executor);
		ProfilePage profilePage = new ProfilePage(this.driver, this.wait, this.executor);
		AuthenticationPage authenticationPage = new AuthenticationPage(this.driver, this.wait, this.executor);

		this.driver.navigate().to(baseUrl + "guest-user/login-form");

		locationPopUpPage.closeLocationHeader();

		loginPage.login(email, password);

		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Login Successful"),
				"[ERROR] Login Action Failed");

		this.driver.navigate().to(baseUrl + "member/profile/");

		profilePage.profileUpdate("Mladen", "Cekic", "Naissus", "0658430036", 18000, "United States", "California",
				"Los Angeles");

		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Setup Successful"),
				"[ERROR] Profile Update Action Failed"); 
		
		authenticationPage.logout();

		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Logout Successful"),
				"[ERROR] Logout Action Failed");
	}

	@Test(priority = 5)
	public void profileImageChange() throws IOException, InterruptedException {
		LocationPopUpPage locationPopUpPage = new LocationPopUpPage(this.driver, this.wait, this.executor);
		LoginPage loginPage = new LoginPage(this.driver, this.wait, this.executor);
		NotificationSystemPage notificationSystemPage = new NotificationSystemPage(this.driver, this.wait,
				this.executor);
		ProfilePage profilePage = new ProfilePage(this.driver, this.wait, this.executor);
		AuthenticationPage authenticationPage = new AuthenticationPage(this.driver, this.wait, this.executor);

		this.driver.navigate().to(baseUrl + "guest-user/login-form");

		locationPopUpPage.closeLocationHeader();

		loginPage.login(email, password);

		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Login Successful"),
				"[ERROR] Login Action Failed");

		this.driver.navigate().to(baseUrl + "member/profile/");

		profilePage.photoUpload();

		Assert.assertTrue(
				notificationSystemPage.notificationMessage().contains("Profile Image Uploaded Successfully"),
				"[ERROR] Profile Image Upload Action Failed");

		notificationSystemPage.systemMessageDisappear();

		profilePage.imageRemove();

		Assert.assertTrue(
				notificationSystemPage.notificationMessage().contains("Profile Image Deleted Successfully"),
				"[ERROR] Profile Image Remove Action Failed");

		notificationSystemPage.systemMessageDisappear();

		authenticationPage.logout();

		Assert.assertTrue(notificationSystemPage.notificationMessage().contains("Logout Successful"),
				"[ERROR] Logout Action Failed");
	}

}
