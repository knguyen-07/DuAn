/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.dao;

/**
 *
 * @author Admin
 */
import com.tigersliver.entity.Phong;
import com.tigersliver.utils.Jdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongDAO extends HomeStayDAO<Phong, String> {

    @Override
    public void insert(Phong entity) {
        String sql = "INSERT INTO DSPhong (MaPhong, TenPhong, MaLoaiPhong, TrangThai) VALUES (?, ?, ?, ?)";
        Jdbc.update(sql,
                entity.getMaPhong(),
                entity.getTenPhong(),
                entity.getLoaiPhong(),
                entity.getTrangThai()
        );
    }

    @Override
    public void update(Phong entity) {
        String sql = "UPDATE DSPhong SET TenPhong=?, MaLoaiPhong=?, TrangThai=? WHERE MaPhong=?";
        Jdbc.update(sql,
                entity.getTenPhong(),
                entity.getLoaiPhong(),
                entity.getTrangThai(),
                entity.getMaPhong()
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM DSPhong WHERE MaPhong=?";
        Jdbc.update(sql, id);
    }

    @Override
    public Phong selectById(String id) {
        String sql = "SELECT * FROM DSPhong WHERE MaPhong=?";
        List<Phong> list = this.selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Phong> selectAll() {
        String sql = "SELECT * FROM DSPhong";
        return this.selectBySql(sql);
    }

    @Override
    protected List<Phong> selectBySql(String sql, Object... args) {
        List<Phong> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                Phong entity = new Phong();
                entity.setMaPhong(rs.getString("MaPhong"));
                entity.setTenPhong(rs.getString("TenPhong"));
                entity.setLoaiPhong(rs.getString("MaLoaiPhong"));
                entity.setTrangThai(rs.getString("TrangThai"));
                list.add(entity);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // Lấy danh sách phòng kèm loại phòng
    public List<Phong> getAllRoomsWithLoaiPhong() {
        List<Phong> rooms = new ArrayList<>();
        String query = "SELECT p.MaPhong, p.TenPhong, l.TenLoaiPhong, p.TrangThai "
                + "FROM DSPhong p JOIN LoaiPhong l ON p.MaLoaiPhong = l.MaLoaiPhong";

        try (Connection conn = Jdbc.getConnection(); PreparedStatement statement = conn.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Tạo đối tượng Phong bằng constructor mặc định
                Phong room = new Phong();

                // Gán giá trị cho từng thuộc tính bằng setter
                room.setMaPhong(resultSet.getString("MaPhong"));
                room.setTenPhong(resultSet.getString("TenPhong"));
                room.setLoaiPhong(resultSet.getString("TenLoaiPhong"));
                room.setTrangThai(resultSet.getString("TrangThai"));

                // Thêm đối tượng vào danh sách
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

}
