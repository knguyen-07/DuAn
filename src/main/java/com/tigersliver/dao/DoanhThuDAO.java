/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.dao;

import com.tigersliver.utils.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DoanhThuDAO {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Lấy doanh thu phòng theo tháng (YYYY-MM)
    public List<Object[]> getDoanhThuPhong(String thangNam) {
        String sql = "{CALL GetDoanhThuPhong(?)}";
        String[] cols = {"STT", "MaPhong", "PhuThu", "ThanhTien"};
        return this.getListOfArray(sql, cols, thangNam);
    }

    // Lấy doanh thu dịch vụ theo tháng (YYYY-MM)
    public List<Object[]> getDoanhThuDichVu(String thangNam) {
        String sql = "{CALL GetDoanhThuDichVu(?)}";
        String[] cols = {"STT", "MaDV", "TenDV", "SoLuong", "DonGia", "ThanhTien"};
        return this.getListOfArray(sql, cols, thangNam);
    }

    public List<String> getThangNam() {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT CONCAT(MONTH(ngay), '/', YEAR(ngay)) AS ThangNam FROM HoaDon ORDER BY YEAR(ngay), MONTH(ngay)";
            ResultSet rs = Jdbc.query(sql);
            while (rs.next()) {
                list.add(rs.getString("ThangNam"));
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
