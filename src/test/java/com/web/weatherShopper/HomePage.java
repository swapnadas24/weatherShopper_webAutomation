package com.web.weatherShopper;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends LunchPage {
	String homepage_Link = "https://weathershopper.pythonanywhere.com/";
	String sunscreensPage_Link = "https://weathershopper.pythonanywhere.com/sunscreen";
	String checkoutPage_Link = "https://weathershopper.pythonanywhere.com/cart";

	// For 10 degit mobile number
	String randomNumbers = RandomStringUtils.randomNumeric(6);
	String mobile_number = 7939 + randomNumbers;

	public void selectitem() throws InterruptedException {
		// Lunch home page
		driver.get(props.getProperty("home_URL"));
		Assert.assertEquals(driver.getCurrentUrl(), homepage_Link, "Home page is opened");

		// Get the temperature
		driver.findElement(By.xpath(props.getProperty("get_Temperature"))).isDisplayed();

		// Read the instruction and Select "Buy Sunscreens"
		driver.findElement(By.xpath(props.getProperty("sunscrn_instruct"))).getText();
		driver.findElement(By.xpath(props.getProperty("buy_Sunscreen"))).click();
		Thread.sleep(2000);

		// Assert Sunscreen list page has opened
		Assert.assertEquals(driver.getCurrentUrl(), sunscreensPage_Link, "Sunscreen page list is opened");

		// Check 2nd item instruction and Add to cart
		driver.findElement(By.xpath(props.getProperty("2nditem_instruction"))).getText();
		driver.findElement(By.xpath(props.getProperty("buy_2nd_item"))).click();
		Thread.sleep(2000);

		// click the cart
		driver.findElement(By.xpath(props.getProperty("click_goToCart"))).click();
		Assert.assertEquals(driver.getCurrentUrl(), checkoutPage_Link, "Checkout page is opened");

	}

	public void cartpage() throws InterruptedException {

		// verify item has added and click the "Pay with add" tab
		Assert.assertTrue(driver.findElement(By.xpath(props.getProperty("verify_added_item"))).isDisplayed(),
				"Item has added into the cart");

		driver.findElement(By.xpath(props.getProperty("pay_with_card_tab"))).click();
		Thread.sleep(2000);

	}

	public void makepayment() throws InterruptedException {
		// Handle frame,Check the Payment card pop-up should opened then fill deatial
		// and make payment
		driver.switchTo().frame(0);
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath(props.getProperty("payment_card_logo"))).isDisplayed(),
				" Card payment pop-up is showing");

		driver.findElement(By.xpath(props.getProperty("email"))).sendKeys(getemailchars() + "@gmail.com");
		// wait for 20 second and enter OTP manualy
		Thread.sleep(3000);

		driver.findElement(By.xpath(props.getProperty("card_number"))).clear();
		driver.findElement(By.xpath(props.getProperty("card_number"))).sendKeys(props.getProperty("enter_card_number"));
		Thread.sleep(1000);

		driver.findElement(By.xpath(props.getProperty("month"))).clear();
		driver.findElement(By.xpath(props.getProperty("month"))).sendKeys(props.getProperty("enter_month"));
		Thread.sleep(1000);

		driver.findElement(By.xpath(props.getProperty("CVV"))).clear();
		driver.findElement(By.xpath(props.getProperty("CVV"))).sendKeys(props.getProperty("enter_CVV"));
		Thread.sleep(1000);

		driver.findElement(By.xpath(props.getProperty("zip_code"))).clear();
		driver.findElement(By.xpath(props.getProperty("zip_code"))).sendKeys(props.getProperty("enter_zip_code"));
		Thread.sleep(1000);

		driver.findElement(By.xpath(props.getProperty("click_rember_checkbox"))).click();
		Thread.sleep(1000);

		// Enter mobile number
		driver.findElement(By.xpath(props.getProperty("mobile_No"))).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath(props.getProperty("mobile_No")))
				.sendKeys(props.getProperty("plus_icon") + mobile_number);

		// Click pay tab to make payment
		driver.findElement(By.xpath(props.getProperty("payment_tab"))).click();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();

	}

	public void paymentgetSuccess() throws InterruptedException {
		// Assert Pay get success and click the "i" icon to read the instruction
		Assert.assertTrue(driver.findElement(By.xpath(props.getProperty("payment_success"))).isDisplayed(),
				"Payment get success");
		Thread.sleep(3000);
		driver.findElement(By.xpath(props.getProperty("i_payment"))).click();
		driver.findElement(By.xpath(props.getProperty("i_payment"))).getText();
	}

	protected String getemailchars() {
		String emailchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) {

			// length of the random string.
			int index = (int) (rnd.nextFloat() * emailchars.length());
			salt.append(emailchars.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

}
