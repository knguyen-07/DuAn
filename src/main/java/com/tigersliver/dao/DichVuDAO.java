/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.dao;

import com.tigersliver.entity.DichVu;
import com.tigersliver.utils.Jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DichVuDAO extends HomeStayDAO<DichVu, String> {

    @Override
    public void insert(DichVu entity) {
        String sql = "INSERT INTO DichVu (MaDV, TenDV, DonGia) VALUES (?, ?, ?)";
        Jdbc.update(sql,
                entity.getMaDV(),
                entity.getTenDV(),
                entity.getDonGia()
        );
    }

    @Override
    public void update(DichVu entity) {
        String sql = "UPDATE DichVu SET TenDV=?, DonGia=? WHERE MaDV=?";
        Jdbc.update(sql,
                entity.getTenDV(),
                entity.getDonGia(),
                entity.getMaDV()
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM DichVu WHERE MaDV=?";
        Jdbc.update(sql, id);
    }

    @Override
    public DichVu selectById(String id) {
        String sql = "SELECT * FROM DichVu WHERE MaDV=?";
        List<DichVu> list = this.selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<DichVu> selectAll() {
        String sql = "SELECT * FROM DichVu";
        return this.selectBySql(sql);
    }

    @Override
    protected List<DichVu> selectBySql(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                DichVu entity = new DichVu();
                entity.setMaDV(rs.getString("MaDV"));
                entity.setTenDV(rs.getString("TenDV"));
                entity.setDonGia(rs.getDouble("DonGia"));
                list.add(entity);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void insertDatDichVu(String maKH, String maDV) {
        String sql = "INSERT INTO DatDichVu (MaKH, MaDV) VALUES (?, ?)";
        Jdbc.update(sql, maKH, maDV);
    }
}
