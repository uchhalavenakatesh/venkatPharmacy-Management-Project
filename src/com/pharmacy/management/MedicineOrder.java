package com.pharmacy.management;
import java.sql.Date;

public class MedicineOrder {
	public Medicine medicine;
	public Date orderDate;
	public int quantityOrdered;
	public MedicineOrder(Medicine medicine, int quantityOrdered, Date orderDate) {
		super();
		this.medicine = medicine;
		this.quantityOrdered = quantityOrdered;
		this.orderDate = orderDate;
	}
	
}