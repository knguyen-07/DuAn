/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.dao;

import com.tigersliver.entity.KhachHang;
import com.tigersliver.utils.Jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachHangDAO extends HomeStayDAO<KhachHang, String> {

    @Override
    public void insert(KhachHang entity) {
        String sql = "INSERT INTO KhachHang (MaKH, TenKH, SoDT, CCCD, GioiTinh) VALUES (?, ?, ?, ?, ?)";
        Jdbc.update(sql,
                entity.getMaKH(),
                entity.getHoTen(),
                entity.getSoDienThoai(),
                entity.getSoCCCD(),
                entity.getGioiTinh()
        );
    }

    @Override
    public void update(KhachHang entity) {
        String sql = "UPDATE KhachHang SET TenKH=?, SoDT=?, CCCD=?, GioiTinh=? WHERE MaKH=?";
        Jdbc.update(sql,
                entity.getHoTen(),
                entity.getSoDienThoai(),
                entity.getSoCCCD(),
                entity.getGioiTinh(),
                entity.getMaKH()
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM KhachHang WHERE MaKH=?";
        Jdbc.update(sql, id);
    }

    @Override
    public KhachHang selectById(String id) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH=?";
        List<KhachHang> list = this.selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<KhachHang> selectAll() {
        String sql = "SELECT * FROM KhachHang";
        return this.selectBySql(sql);
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try (ResultSet rs = Jdbc.query(sql, args)) {
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString("MaKH"));
                entity.setHoTen(rs.getString("TenKH"));
                entity.setSoDienThoai(rs.getString("SoDT"));
                entity.setSoCCCD(rs.getString("CCCD"));
                entity.setGioiTinh(rs.getString("GioiTinh"));
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy vấn KhachHang: " + e.getMessage(), e);
        }
        return list;
    }

    public List<KhachHang> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KhachHang WHERE MaKH LIKE ? OR SoDT LIKE ? OR TenKH LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    }

    public List<KhachHang> selectNotInCourse(int makh, String keyword) {
        String sql = "SELECT * FROM KhachHang "
                + "WHERE (TenKH LIKE ? OR SoDT LIKE ?) AND "
                + "MaPhong NOT IN (SELECT MaPhong FROM DSPhong WHERE MaPhong=?)";// Sửa MaKH thành MaPhong
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", makh);
    }

    public KhachHang findBySDT(String sdt) {
        String sql = "SELECT * FROM DatPhong WHERE MaKH IN (SELECT MaKH FROM KhachHang WHERE SoDT = ?)";
        List<KhachHang> list = this.selectBySql(sql, sdt);
        return list.isEmpty() ? null : list.get(0);
    }
}
