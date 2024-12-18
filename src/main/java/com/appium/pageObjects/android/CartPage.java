package com.appium.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.appium.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{

AndroidDriver driver;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms;
	
	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptButton;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement preceed;
	
	@AndroidFindBy(id = "android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement cartPageTitle;
	
	
	public List<WebElement> getProductList() {
		
		return productList;
	}
	
	public double getProductsSum() {
		waitForElementAttributeToBe(cartPageTitle, "text", "Cart");
		
		int count = productList.size();
		double totalSum=0;
		for(int i=0; i< count; i++) {
			
			String amountString = productList.get(i).getText();
			Double price = getFormatedAmount(amountString);
			totalSum = totalSum + price;
		}
		return totalSum;
		
	}
	
	public Double getTotalAmountDisplayed() {
		return getFormatedAmount(totalAmount.getText());
	}
	
	public void acceptTermscondition() {
		waitForElement(terms, 20, driver);
		longPressAction(terms);
		acceptButton.click();
		
	}
	
	
	
	

}
