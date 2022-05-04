package com.pharmacy.management;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Salesman {
	public String salesmanName;
	public String salesmanPassword;
	Scanner s=new Scanner(System.in);
	//ArrayList<MedicineOrder> ar= new ArrayList<MedicineOrder>();//for storing the orders so far
	
	public Salesman(String salesmanName, String salesmanPassword) {
		super();
		this.salesmanName = salesmanName;
		this.salesmanPassword = salesmanPassword;
	}
	
	public Customer takeOrder(Medicine med){
		System.out.println("enter customer name, available cash, quantity ordered, order date[2015-03-31] format");
		// TODO Auto-generated method stub
		String cusname=s.next();
		double avacash=s.nextDouble();
		int quantityOrdered=s.nextInt();
		Date orderDate = Date.valueOf(s.next());
		Customer customer;
		if(med.quantity >= quantityOrdered && avacash >= med.price*quantityOrdered ) {
			MedicineOrder order=new MedicineOrder(med, quantityOrdered, orderDate);
			customer = new Customer(cusname, avacash, order);
			return customer;
		}
		return null;
		
	}
	
	

}