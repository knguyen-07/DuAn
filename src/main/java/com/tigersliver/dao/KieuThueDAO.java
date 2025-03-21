/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.dao;

import com.tigersliver.entity.KieuThue;
import com.tigersliver.utils.Jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KieuThueDAO extends HomeStayDAO<KieuThue, String> {
    @Override
    public void insert(KieuThue entity) {
        String sql = "INSERT INTO KieuThue (MaKieuThue, TenKieuThue, DonGia) VALUES (?, ?, ?)";
        Jdbc.update(sql,
                entity.getMaKieuThue(),
                entity.getTenKieuThue(),
                entity.getDonGia()
        );
    }

    @Override
    public void update(KieuThue entity) {
        String sql = "UPDATE KieuThue SET TenKieuThue=?, DonGia=? WHERE MaKieuThue=?";
        Jdbc.update(sql,
                entity.getTenKieuThue(),
                entity.getDonGia(),
                entity.getMaKieuThue()
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM KieuThue WHERE MaKieuThue=?";
        Jdbc.update(sql, id);
    }

    @Override
    public KieuThue selectById(String id) {
        String sql = "SELECT * FROM KieuThue WHERE MaKieuThue=?";
        List<KieuThue> list = this.selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<KieuThue> selectAll() {
        String sql = "SELECT * FROM KieuThue";
        return this.selectBySql(sql);
    }

    @Override
    protected List<KieuThue> selectBySql(String sql, Object... args) {
        List<KieuThue> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                KieuThue entity = new KieuThue();
                entity.setMaKieuThue(rs.getString("MaKieuThue"));
                entity.setTenKieuThue(rs.getString("TenKieuThue"));
                entity.setDonGia(rs.getDouble("DonGia"));
                list.add(entity);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}