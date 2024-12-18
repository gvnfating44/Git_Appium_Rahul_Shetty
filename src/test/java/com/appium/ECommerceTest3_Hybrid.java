package com.appium;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import com.appium.pageObjects.android.CartPage;
import com.appium.pageObjects.android.ProductcalaloguePage;
import com.appium.testUtils.AndroidBaseTest;

public class ECommerceTest3_Hybrid extends AndroidBaseTest{
	
	@Test(dataProvider = "getData", groups = {"smoke"})
	public void fillFormTest(HashMap<String, String> input) throws InterruptedException {		//fillFormTest(String name, String gender, String country)
		
//		FormPage formpage = new FormPage(driver);
		formpage.setNameField(input.get("name"));		
		formpage.setGender(input.get("gender"));
		formpage.setCountrySelection(input.get("country"));
		ProductcalaloguePage productCatalogue = formpage.submitForm();
		
//		ProductcalaloguePage productCatalogue = new ProductcalaloguePage(driver);
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(1);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		double totalSum = cartPage.getProductsSum();
		double displayFormatedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormatedSum);
		cartPage.acceptTermscondition();
		Thread.sleep(4000);	
		
	}
	
//	@BeforeMethod(alwaysRun = true)
//	public void preSetup() throws InterruptedException {
//		// adb shell dumpsys window | grep -E 'mCurrentFocus'
//		
//		// Screen to Home page
//		formpage.setActivity();
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//com//appium//testData//eCommerce.json");
		return new Object [][] {
//			{"Govind", "female", "Argentina"},
//			{"Rahul", "male", "Argentina"}
			
			{data.get(0)},
			{data.get(1)}
		};
	}
	

}
