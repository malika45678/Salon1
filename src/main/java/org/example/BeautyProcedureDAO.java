package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BeautyProcedureDAO {

    public void addProcedure(BeautyProcedure procedure) {
        String sql = "INSERT INTO procedures (nameOfProcedure, price, description) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, procedure.getNameOfProcedure());
            stmt.setInt(2, procedure.getPrice());
            stmt.setString(3, procedure.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read a Procedure by name
    public BeautyProcedure getProcedure(String name) {
        String sql = "SELECT * FROM procedures WHERE nameOfProcedure = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new BeautyProcedure(
                        rs.getString("nameOfProcedure"),
                        rs.getInt("price"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update a Procedure
    public void updateProcedure(BeautyProcedure procedure) {
        String sql = "UPDATE procedures SET price = ?, description = ? WHERE nameOfProcedure = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, procedure.getPrice());
            stmt.setString(2, procedure.getDescription());
            stmt.setString(3, procedure.getNameOfProcedure());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a Procedure
    public void deleteProcedure(String name) {
        String sql = "DELETE FROM procedures WHERE nameOfProcedure = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List all Procedures
    public ArrayList<BeautyProcedure> getAllProcedures() {
        ArrayList<BeautyProcedure> procedures = new ArrayList<>();
        String sql = "SELECT * FROM procedures";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                procedures.add(new BeautyProcedure(
                        rs.getString("nameOfProcedure"),
                        rs.getInt("price"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return procedures;
    }

}
