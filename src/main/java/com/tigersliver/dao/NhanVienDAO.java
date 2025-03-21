/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.dao;

import com.tigersliver.entity.NhanVien;
import com.tigersliver.utils.Jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienDAO extends HomeStayDAO<NhanVien, String> {

    @Override
    public void insert(NhanVien entity) {
        String sql = "INSERT INTO NhanVien (MaNV, TenNV, Email, SoDT, MatKhau, GioiTinh, ChucVu, HinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            int result = Jdbc.update(sql,
                    entity.getMaNV(),
                    entity.getHoTen(),
                    entity.getEmail(),
                    entity.getSoDienThoai(),
                    entity.getMatKhau(),
                    entity.getGioiTinh(),
                    entity.getChucVu(),
                    entity.getAnh()
            );
            if (result > 0) {
                System.out.println("Thêm nhân viên thành công: " + entity.getMaNV());
            } else {
                System.err.println("Lỗi: Không thể thêm nhân viên.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm nhân viên: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(NhanVien entity) {
        System.out.println("Đang cập nhật nhân viên: " + entity.getMaNV());
        System.out.println("Thông tin cập nhật: " + entity);

        String sql = "UPDATE NhanVien SET TenNV=?, Email=?, SoDT=?, MatKhau=?, GioiTinh=?, ChucVu=?, HinhAnh=? WHERE MaNV=?";
        try {
            int result = Jdbc.update(sql,
                    entity.getHoTen(),
                    entity.getEmail(),
                    entity.getSoDienThoai(),
                    entity.getMatKhau(),
                    entity.getGioiTinh(),
                    entity.getChucVu(),
                    entity.getAnh(),
                    entity.getMaNV()
            );
            if (result > 0) {
                System.out.println("Cập nhật thành công nhân viên: " + entity.getMaNV());
            } else {
                System.err.println("Lỗi: Không tìm thấy nhân viên để cập nhật.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật nhân viên: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        try {
            int result = Jdbc.update(sql, id);
            if (result > 0) {
                System.out.println("Xóa thành công nhân viên có mã: " + id);
            } else {
                System.err.println("Lỗi: Không tìm thấy nhân viên để xóa.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa nhân viên: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public NhanVien selectById(String id) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = this.selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<NhanVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return this.selectBySql(sql);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try (ResultSet rs = Jdbc.query(sql, args)) {
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setHoTen(rs.getString("TenNV"));
                entity.setEmail(rs.getString("Email"));
                entity.setSoDienThoai(rs.getString("SoDT"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setGioiTinh(rs.getString("GioiTinh"));
                entity.setChucVu(rs.getString("ChucVu"));
                entity.setAnh(rs.getString("HinhAnh"));
                list.add(entity);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}
