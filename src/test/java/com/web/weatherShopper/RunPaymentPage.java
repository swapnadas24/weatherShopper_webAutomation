package com.web.weatherShopper;

import org.testng.annotations.Test;

public class RunPaymentPage extends HomePage {

	@Test
	public void additemToPayment() throws InterruptedException {
		this.selectitem();
		this.cartpage();
		this.makepayment();
		Thread.sleep(2000);
		this.paymentgetSuccess();

	}

}