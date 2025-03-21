/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.dao;

import com.tigersliver.entity.DatPhong;
import com.tigersliver.utils.Jdbc;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DatPhongDAO extends HomeStayDAO<DatPhong, String> {

    @Override
    public void insert(DatPhong entity) {
        String sql = "INSERT INTO DatPhong (MaDP, NgayDi, MaNV, MaKH, MaPhong, MaKieuThue) VALUES (?, ?, ?, ?, ?, ?)";
        Jdbc.update(sql,
                entity.getMaDP(),
                entity.getNgayDi(),
                entity.getMaNV(),
                entity.getMaKH(),
                entity.getMaPhong(),
                entity.getMaKieuThue()
        );
    }

    @Override
    public void update(DatPhong entity) {
        String sql = "UPDATE DatPhong SET NgayDen=?, NgayDi=?, MaNV=?, MaKH=?, MaPhong=?, MaKieuThue=? WHERE MaDP=?";
        Jdbc.update(sql,
                entity.getNgayDen(),
                entity.getNgayDi(),
                entity.getMaNV(),
                entity.getMaKH(),
                entity.getMaPhong(),
                entity.getMaKieuThue(),
                entity.getMaDP()
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM DatPhong WHERE MaDP=?";
        Jdbc.update(sql, id);
    }

    @Override
    public DatPhong selectById(String id) {
        String sql = "SELECT * FROM DatPhong WHERE MaDP=?";
        List<DatPhong> list = this.selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<DatPhong> selectAll() {
        String sql = "SELECT * FROM DatPhong";
        return this.selectBySql(sql);
    }

    @Override
    protected List<DatPhong> selectBySql(String sql, Object... args) {
        List<DatPhong> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                DatPhong entity = new DatPhong();
                entity.setMaDP(rs.getString("MaDP"));
                entity.setNgayDen(rs.getDate("NgayDen")); // SQL tự động điền giá trị này
                entity.setNgayDi(rs.getDate("NgayDi"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMaKH(rs.getString("MaKH"));
                entity.setMaPhong(rs.getString("MaPhong"));
                entity.setMaKieuThue(rs.getString("MaKieuThue"));
                list.add(entity);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
