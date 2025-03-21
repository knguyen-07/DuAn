/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.dao;

import com.tigersliver.entity.HoaDon;
import com.tigersliver.utils.Jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonDAO extends HomeStayDAO<HoaDon, String> {

    @Override
    public void insert(HoaDon entity) {
        String sql = "INSERT INTO HoaDonThanhToan (MaHD, MaDP, MaNV, NgaySD, NgayTra, ThanhTien, NgayLap) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Jdbc.update(sql,
                entity.getMaHD(),
                entity.getMaDP(),
                entity.getMaNV(),
                new java.sql.Date(entity.getNgaySD().getTime()),
                new java.sql.Date(entity.getNgayTra().getTime()),
                entity.getThanhTien(),
                new java.sql.Timestamp(entity.getNgayLap().getTime())
        );
    }

    @Override
    public void update(HoaDon entity) {
        String sql = "UPDATE HoaDonThanhToan SET MaDP=?, MaNV=?, NgaySD=?, NgayTra=?, ThanhTien=?, NgayLap=? WHERE MaHD=?";
        Jdbc.update(sql,
                entity.getMaDP(),
                entity.getMaNV(),
                new java.sql.Date(entity.getNgaySD().getTime()),
                new java.sql.Date(entity.getNgayTra().getTime()),
                entity.getThanhTien(),
                new java.sql.Timestamp(entity.getNgayLap().getTime()),
                entity.getMaHD()
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM HoaDonThanhToan WHERE MaHD=?";
        Jdbc.update(sql, id);
    }

    @Override
    public HoaDon selectById(String id) {
        String sql = "SELECT * FROM HoaDonThanhToan WHERE MaHD=?";
        List<HoaDon> list = this.selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<HoaDon> selectAll() {
        String sql = "SELECT * FROM HoaDonThanhToan";
        return this.selectBySql(sql);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try (ResultSet rs = Jdbc.query(sql, args)) {
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getString("MaHD"));
                entity.setMaDP(rs.getString("MaDP"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgaySD(rs.getDate("NgaySD"));
                entity.setNgayTra(rs.getDate("NgayTra"));
                entity.setThanhTien(rs.getDouble("ThanhTien"));
                entity.setNgayLap(rs.getTimestamp("NgayLap"));
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy vấn HoaDonThanhToan: " + e.getMessage(), e);
        }
        return list;
    }

    public List<HoaDon> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM HoaDonThanhToan WHERE MaHD LIKE ? OR MaNV LIKE ? OR CONVERT(VARCHAR, NgayLap, 120) LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

}
