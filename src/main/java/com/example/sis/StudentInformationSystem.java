package com.example.sis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentInformationSystem {

    private static final String DB_URL = "jdbc:sqlite:students.db";

    private StudentInformationSystem() {
        // Utility class: prevent object creation.
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Students (
                StudentID INTEGER PRIMARY KEY AUTOINCREMENT,
                RollNumber TEXT NOT NULL UNIQUE,
                StudentName TEXT NOT NULL,
                Email TEXT NOT NULL UNIQUE,
                Department TEXT NOT NULL,
                Marks REAL NOT NULL
                    CHECK (Marks >= 0 AND Marks <= 100)
            )
            """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
            System.out.println("Students table created successfully!");

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Failed to initialize the Students table.", e
            );
        }
    }

    public static void main(String[] args) {
        createTable();
    }
}