package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.DatabaseUtil.getConnection;


public class BookingDAO {
    public void addBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings (procedureName, date, time) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, booking.getProcedureName());
            stmt.setString(2, booking.getDate());
            stmt.setString(3, booking.getTime());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        booking.setId(rs.getInt(1));
                    }
                }
            }
        }
    }

    public Booking getBooking(int id) throws SQLException {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Booking booking = new Booking();
                    booking.setId(rs.getInt("id"));
                    booking.setProcedureName(rs.getString("procedureName"));
                    booking.setDate(rs.getString("date"));
                    booking.setTime(rs.getString("time"));
                    return booking;
                }
            }
        }
        return null;
    }

    public void updateBooking(Booking booking) throws SQLException {
        String sql = "UPDATE bookings SET procedureName = ?, date = ?, time = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, booking.getProcedureName());
            stmt.setString(2, booking.getDate());
            stmt.setString(3, booking.getTime());
            stmt.setInt(4, booking.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteBooking(int id) throws SQLException {
        String sql = "DELETE FROM bookings WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setProcedureName(rs.getString("procedureName"));
                booking.setDate(rs.getString("date"));
                booking.setTime(rs.getString("time"));
                bookings.add(booking);
            }
        }
        return bookings;
    }

}