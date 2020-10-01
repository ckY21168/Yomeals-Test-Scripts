package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LocationPopUpPage;
import pages.SearchResultPage;

public class SearchTest extends BasicTest {

	@Test(priority = 0)
	public void searchResultsTest() throws InterruptedException, IOException {
		SearchResultPage searchResultPage = new SearchResultPage(this.driver, this.wait, this.executor);
		LocationPopUpPage locationPopUpPage = new LocationPopUpPage(this.driver, this.wait, this.executor);

		this.driver.navigate().to(this.baseUrl + "meals/");
		locationPopUpPage.closeLocationHeader();
		Thread.sleep(3000);
		locationPopUpPage.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx").getCanonicalFile();
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheet("Meal Search Results");

		for (int i = 1; i <= sheet1.getLastRowNum(); i++) {
			String newTabScript = "window.open();";
			executor.executeScript(newTabScript);
			String mainWindowHandles = driver.getWindowHandle();
			String newMealWindowHandles = "";

			String location = sheet1.getRow(i).getCell(0).getStringCellValue();
			String url = sheet1.getRow(i).getCell(1).getStringCellValue();
			double numberOfResults = sheet1.getRow(i).getCell(2).getNumericCellValue();
			int numberOfResultsInt = (int) numberOfResults;

			ArrayList<String> windowHandlesList = new ArrayList<String>(driver.getWindowHandles());
			windowHandlesList.remove(mainWindowHandles);
			newMealWindowHandles = windowHandlesList.get(0);

			driver.switchTo().window(newMealWindowHandles);
			driver.navigate().to(url);

			Thread.sleep(3000);

			locationPopUpPage.setLocation(location);

			Thread.sleep(3000);

			Assert.assertEquals(searchResultPage.mealsCount(), numberOfResultsInt, "[ERROR] Meals Number Not Equal");

			for (int j = 3; j < 3 + numberOfResultsInt; j++) {
				softAssert.assertTrue(searchResultPage.mealNames().get(j - 3)
						.contains(sheet1.getRow(i).getCell(j).getStringCellValue()), "[ERROR] Meals Does Not Present");
			}
		}

		softAssert.assertAll();
		wb.close();
		fis.close();
	}

}
