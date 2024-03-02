package org.example;

public class BeautyProcedure {
	
	private String nameOfProcedure;
	private int price;
	private String description;
	
	public BeautyProcedure(String name, int price) {
		this.nameOfProcedure = name;
		this.price = price;
	}
	
	public BeautyProcedure(String name, int price, String description) {
		this.nameOfProcedure = name;
		this.price = price;
		this.description = description;
	}
	
	//getter and setter for name of procedure
	public String getNameOfProcedure() {
		return nameOfProcedure;
	}
	public void setNameOfProcedure(String nameOfProcedure) {
		this.nameOfProcedure = nameOfProcedure;
	}
	
	//getter and setter for price
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	//getter and setter for decription
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void displayDetails() {
        System.out.println("Procedure: " + nameOfProcedure +
                "\nPrice: " + price +
                "\nDescription: " + description);
    }

    public void updateProcedure(String newName,int newPrice, String newDescription) {
        setNameOfProcedure(newName);
        setPrice(newPrice);
        setDescription(newDescription);
        System.out.println("Procedure details updated successfully.");
    }

}
