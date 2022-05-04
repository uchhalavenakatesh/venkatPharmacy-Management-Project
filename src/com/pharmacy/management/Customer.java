package com.pharmacy.management;


public class Customer {
	public String customerName;
	public double availablecash;
	public MedicineOrder order;
	
	public Customer(String customerName, double availablecash, MedicineOrder order) {
		super();
		this.customerName = customerName;
		this.availablecash = availablecash;
		this.order = order;
	}
}