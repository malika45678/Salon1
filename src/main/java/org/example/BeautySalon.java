package org.example;

import java.util.ArrayList;

public class BeautySalon {
	
	private ArrayList<User> users;
	private ArrayList<BeautyProcedure> procedures;
	private ArrayList<Booking> bookingHistory;
	
	public BeautySalon() {
        this.procedures = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookingHistory = new ArrayList<>();
    }
	
	public void addProcedure(BeautyProcedure procedure) {
        procedures.add(procedure);
    }

    public void addUser(User user) {
        users.add(user);
    }
    
    public void bookProcedure(User user, BeautyProcedure procedure, String date, String time) {
        Integer procedurePrice = procedure.getPrice();
        if (user.getBalance() >= procedurePrice) {
            user.takeProcedure(procedure);
            user.setBalance(user.getBalance() - procedurePrice);

            Booking booking = new Booking(procedure.getNameOfProcedure(), date, time);
            bookingHistory.add(booking);

            System.out.println("Booking successful! Procedure booked for " + user.getName() +
                    " on " + date + " at " + time);
        } else {
            System.out.println("Booking failed. Insufficient balance.");
        }
    }
    
    public void cancelBooking(User user, Booking booking) {
        user.getListOfBeatyProcedures().remove(booking.getProcedureName());
        bookingHistory.remove(booking);
        System.out.println("Booking canceled for " + user.getName() +
                " (Procedure: " + booking.getProcedureName() +
                ", Date: " + booking.getDate() +
                ", Time: " + booking.getTime() + ")");
    }
    
    public void showBookingHistory() {
        System.out.println("Booking History:");
        for (Booking booking : bookingHistory) {
            System.out.println("Booking ID: " + booking.getId() +
                    ", User: " + getUserByProcedure(booking.getProcedureName()) +
                    ", Procedure: " + booking.getProcedureName() +
                    ", Date: " + booking.getDate() +
                    ", Time: " + booking.getTime());
        }
    }
    
    private String getUserByProcedure(String procedureName) {
        for (User user : users) {
            if (user.getListOfBeatyProcedures().contains(procedureName)) {
                return user.getName();
            }
        }
        return "Unknown User";
    }
    
    public void showProcedures() {
        System.out.println("Available Beauty Procedures:");
        for (BeautyProcedure procedure : procedures) {
            System.out.println("Name: " + procedure.getNameOfProcedure() +
                    ", Price: " + procedure.getPrice() +
                    ", Description: " + procedure.getDescription());
        }
    }
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public ArrayList<BeautyProcedure> getProcedures() {
		return procedures;
	}
	
	public void setProcedures(ArrayList<BeautyProcedure> procedures) {
		this.procedures = procedures;
	}
	
	public ArrayList<Booking> getBookingHistory() {
		return bookingHistory;
	}
	
	public void setBookingHistory(ArrayList<Booking> bookingHistory) {
		this.bookingHistory = bookingHistory;
	}

}
