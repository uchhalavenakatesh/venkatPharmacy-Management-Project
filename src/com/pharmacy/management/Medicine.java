package com.pharmacy.management;
import java.sql.Date;

public class Medicine {

	public double price;
	public int quantity;
	public String medicineName;
	public Date expiryDate;
	public Medicine(String medicineName, double price, int quantity,Date expiryDate) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.medicineName = medicineName;
		this.expiryDate = expiryDate;
	}


}