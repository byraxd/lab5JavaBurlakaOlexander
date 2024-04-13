package org.example;

import java.sql.*;

public class StudentDatabaseProgram {
    private static final String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "byra";
    private static final String PASSWORD = "1111";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            int birthMonth = 3;

            String sql = "SELECT * FROM students WHERE MONTH(birth_date) = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, birthMonth);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String lastName = resultSet.getString("last_name");
                        String firstName = resultSet.getString("first_name");
                        String middleName = resultSet.getString("middle_name");
                        Date birthDate = resultSet.getDate("birth_date");
                        String studentId = resultSet.getString("student_id");

                        System.out.println("ID: " + id);
                        System.out.println("Last Name: " + lastName);
                        System.out.println("First Name: " + firstName);
                        System.out.println("Middle Name: " + middleName);
                        System.out.println("Birth Date: " + birthDate);
                        System.out.println("Student ID: " + studentId);
                        System.out.println("--------------------");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}