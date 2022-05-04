package com.pharmacy.management;

import java.sql.Date;
import java.util.Scanner;

public class Admin {
	private String adminName;
	private String adminPassword;
	Scanner sc=new Scanner(System.in);
	
	public Admin(String adminName, String adminPassword) {
		super();
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	
	}
	public Salesman SalesmanCreation() {
		
		System.out.println("enter salesman name and password to add");
		String salesmanName=sc.next();
		String salesmanPassword=sc.next();
		return new Salesman(salesmanName, salesmanPassword);
		
	}
	public Medicine medicineCreation() {
		// TODO Auto-generated method stub
		System.out.println("enter medicine name,price,quantity,expiry date[2015-03-31] format");
		String name=sc.next();
		double price=sc.nextDouble();
		int quantity=sc.nextInt();
		Date expiryDate= Date.valueOf(sc.next());
		return new Medicine(name, price, quantity, expiryDate);
	}
	

}