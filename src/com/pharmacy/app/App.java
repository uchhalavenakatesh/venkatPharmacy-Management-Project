package com.pharmacy.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.pharmacy.management.Admin;
import com.pharmacy.management.Customer;
import com.pharmacy.management.Medicine;
import com.pharmacy.management.Salesman;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/venky";
		String username = "root";
		String password = "root";
		Scanner sc = new Scanner(System.in);
		Statement stmt;
		Connection conn;

		try {
			// Load the driver class in JVM memory
			Class.forName(driver);
			// Establish a (network kind of) connection
			conn = DriverManager.getConnection(url, username, password);

			// Set-up an interface to execute SQL command
			stmt = conn.createStatement();
			System.out.println("enter your choice\n1.admin 2.salesman 3.Customer");

			// Scanner sc=new Scanner(System.in);
			int a = sc.nextInt();
			if (a == 1) {
				System.out.println("enter admin username and password");
				String username1 = sc.next();
				String password1 = sc.next();
				ResultSet set = stmt.executeQuery("select * from admin");
				set.next();
				if (username1.equals(set.getString("username")) && password1.equals(set.getString("password"))) {
					System.out.println("welcome admin");
					System.out.println("1.salesman creation\n" + "2.medicine creation\n" + "3.log out");
					int choice = sc.nextInt();
					Admin admin = new Admin(username1, password1);
					switch (choice) {
					case 1:
						Salesman s1 = admin.SalesmanCreation();
						String sql = "INSERT into salesman(salesmanname,password) VALUES(?,?)";
						PreparedStatement salesStmt = conn.prepareStatement(sql);
						salesStmt.setString(1, s1.salesmanName);
						salesStmt.setString(2, s1.salesmanPassword);
						salesStmt.executeUpdate();
						System.out.println("salesman created succesfully");
						break;
					case 2:
						Medicine m1 = admin.medicineCreation();
						String medSql = "INSERT into medicine VALUES(?,?,?,?)";
						PreparedStatement medStmt = conn.prepareStatement(medSql);
						medStmt.setString(1, m1.medicineName);
						medStmt.setDouble(2, m1.price);
						medStmt.setDouble(3, m1.quantity);
						medStmt.setDate(4, m1.expiryDate);
						medStmt.executeUpdate();
						System.out.println("medicine created succesfully");
						break;
					case 3:
						System.out.println("admin logged out");
						System.exit(0);
						break;
					}

				} else {
					System.out.println("incorrect username and password");
				}
			} else if (a == 2) {

				System.out.println("enter salesman username and password");
				String username1 = sc.next();
				String password1 = sc.next();
				ResultSet set = stmt.executeQuery("select * from salesman where salesmanname='"+username1+"'");
				set.next();
				if (username1.equals(set.getString("salesmanname")) && password1.equals(set.getString("password"))) {
					System.out.println("welcome salesman");
					System.out.println("1.take order\n" + "2.log out");
					int choice = sc.nextInt();
					switch (choice) {
					case 1:
						Salesman s1=new Salesman(username1,password1);
						System.out.println("Ask customer the Medicine Name he wants:");
						String medname = sc.next();
						ResultSet medSet = stmt.executeQuery("select * from medicine where name='"+medname+"'");
						medSet.next();
						Medicine medicine;
						if(medname.equals(medSet.getString("name"))) {
							medicine=new Medicine(medname, medSet.getDouble("price"), medSet.getInt("quantity"), medSet.getDate("expirydate"));
							Customer c1 = s1.takeOrder(medicine);
							if(c1 != null) {
								String orderSql = "INSERT into medicineorders VALUES(?,?,?,?,?)";
								PreparedStatement orderStmt = conn.prepareStatement(orderSql);
								orderStmt.setString(1, c1.customerName);
								orderStmt.setString(2, c1.order.medicine.medicineName);
								orderStmt.setDouble(3, c1.order.medicine.price*c1.order.quantityOrdered);
								orderStmt.setDate(4, c1.order.orderDate);
								orderStmt.setInt(5, c1.order.quantityOrdered);
								orderStmt.executeUpdate();
								System.out.println("Order completed succesfully");
								int updated = stmt.executeUpdate("UPDATE medicine set quantity="+(medicine.quantity - c1.order.quantityOrdered)+" where name='"+medicine.medicineName+"'");
								if(updated > 0) {
									System.out.println("Medicine quantity uppdated successfully!");
								}else {
									System.out.println("Medicine quantity updation failed!");
								}
							}else {
								System.out.println("Medicine quantity or Price is not Satisfied");
							}
						}else {
							System.out.println("Asked Medicine not in Stock");
						}
						break;

					case 2:
						System.out.println("salesman logged out");
						System.exit(0);
						break;
					}
				} else {
					System.out.println("incorrect username and password");
				}
			} else if (a==3) {
				System.out.println("Enter the customer name:");
				String cusName = sc.next();
				ResultSet cusSet = stmt.executeQuery("select * from medicineorders where customername='"+cusName+"'");
				try {
					while(cusSet.next()) {
						System.out.println("Name: "+cusSet.getString(1)+"\nmedname: "+cusSet.getString(2)+"\namount Paid: "+cusSet.getDouble(3)+"\nOrder Date :"+cusSet.getDate(4)+"\nquantity :"+cusSet.getInt(5));
					}
				}	
				catch(Exception e) {
					System.out.println("Customer data doesnot exist");
				}
				
				
			} else {
				System.out.println("enter correct value in between 1 and 2 and 3");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();

	}

}