package org.example;

public class VipGuest extends User {
	
	private double discount;

	public VipGuest(String name) {
		super(name);
		this.discount = 0.3;
	}
	
	public VipGuest(String name, Integer balance) {
		super(name, balance);
		this.discount = 30;
	}
	
	public void buy(int price) {
		super.buy(price-(price*this.discount));
	}

}
