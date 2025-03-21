/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */

public class Jdbc {
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=TigerSliver;encrypt=true;trustServerCertificate=true;";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "nguyentktk";

    // Nạp driver một lần duy nhất
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Lỗi tải Driver SQL Server", ex);
        }
    }

    /**
     * Kết nối đến cơ sở dữ liệu
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    /**
     * Chuẩn bị câu lệnh SQL (PreparedStatement)
     */
    private static PreparedStatement prepareStatement(Connection conn, String sql, Object... args) throws SQLException {
        PreparedStatement pstmt = sql.trim().startsWith("{") ? conn.prepareCall(sql) : conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    /**
     * Thực hiện INSERT, UPDATE, DELETE (trả về số dòng bị ảnh hưởng)
     */
    public static int update(String sql, Object... args) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = prepareStatement(conn, sql, args)) {
            return stmt.executeUpdate(); // Trả về số dòng bị thay đổi
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thực thi SQL: " + sql, e);
        }
    }

    /**
     * Thực hiện truy vấn SELECT (Trả về ResultSet)
     */
    public static ResultSet query(String sql, Object... args) {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = prepareStatement(conn, sql, args);
            return stmt.executeQuery(); // Người gọi phải đóng ResultSet & Connection
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy vấn SQL: " + sql, e);
        }
    }

    /**
     * Lấy giá trị đơn từ truy vấn SELECT (Ví dụ: COUNT(*), MAX, MIN)
     */
    public static Object value(String sql, Object... args) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = prepareStatement(conn, sql, args);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getObject(1) : null; // Lấy giá trị đầu tiên
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy vấn giá trị SQL: " + sql, e);
        }
    }

}

