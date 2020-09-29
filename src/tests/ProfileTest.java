package tests;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import pages.AuthenticationPage;
import pages.LocationPopUpPage;
import pages.LoginPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public class ProfileTest extends BasicTest{
	
	@Test(priority = 0)
	public void editProfile() throws IOException, InterruptedException {
		LocationPopUpPage locationPopUpPage = new LocationPopUpPage(this.driver, this.wait, this.executor);
		LoginPage loginPage = new LoginPage(this.driver, this.wait, this.executor);
		NotificationSystemPage notificationSystemPage = new NotificationSystemPage(this.driver, this.wait, this.executor);
		ProfilePage profilePage = new ProfilePage(this.driver, this.wait, this.executor);
		AuthenticationPage authenticationPage = new AuthenticationPage(this.driver, this.wait, this.executor);
		
		this.driver.navigate().to(baseUrl + "guest-user/login-form");
			
		locationPopUpPage.closeLocationHeader();
		
		loginPage.login(email, password);
		
		this.driver.navigate().to(baseUrl + "member/profile/");
		
		Assert.assertTrue(notificationSystemPage.notificationMessage().equalsIgnoreCase("Login Successfull"));
			
		profilePage.profileUpdate("Mladen", "Cekic", "Naissus", "0658430036" , 18000, "United States", "California", "Los Angeles");
		
		Assert.assertTrue(notificationSystemPage.notificationMessage().equalsIgnoreCase("Setup Successfull"));
		
		authenticationPage.logout();
		
		Assert.assertTrue(notificationSystemPage.notificationMessage().equalsIgnoreCase("Logout Successfull"));
	}
	

}
