package org.example;

public class Booking {
	
	private static int nextId = 1;
	private int id;
	private String procedureName;
	private String date;
	private String time;

	public Booking(String procedureName, String date, String time) {
		this.id = nextId++;
		this.procedureName = procedureName;
		this.date = date;
		this.time = time;
	}

	public Booking(){

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
