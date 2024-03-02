package org.example;

import java.util.ArrayList;

public class User {

	//fields of User
	private static int nextId = 1;

	private int userId = 0;
	private String name;
	private double balance;
	private ArrayList<String> listOfBeatyProcedures = new ArrayList<>();


	public User(String name) {
		this.userId = nextId++;
		this.name = name;
		this.balance = 0;
	}
	
	public User(String name, Integer balance) {
		this.userId = nextId++;
		this.name = name;
		this.balance = balance;
	}

	public User() {

	}

	//getter and setter for Name of User
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//getter and setter for balance of User
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void buy(double d) {
		this.balance -= d;
	}

	//getter and setter for BeautyProcedures
	public ArrayList<String> getListOfBeatyProcedures() {
		return listOfBeatyProcedures;
	}

	public void setListOfBeatyProcedures(ArrayList<String> listOfBeatyProcedures) {
		this.listOfBeatyProcedures = listOfBeatyProcedures;
	}
	
	public void takeProcedure(BeautyProcedure BeautyProcedure) {
		if(this.balance - BeautyProcedure.getPrice() > 0){
			this.listOfBeatyProcedures.add(BeautyProcedure.getNameOfProcedure());
			buy(BeautyProcedure.getPrice());
		}
		else System.out.println("Not enogth money on your account");
	}
	
	public void topUpBalance(Integer amount) {
        balance += amount;
        System.out.println("Balance topped up. New balance: " + balance);
    }
	
	public void cancelProcedure(String procedureName) {
        if (listOfBeatyProcedures.contains(procedureName)) {
        	listOfBeatyProcedures.remove(procedureName);
            System.out.println("Procedure canceled: " + procedureName);
        } else {
            System.out.println("Procedure not found in taken procedures.");
        }
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
