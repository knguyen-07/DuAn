/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.dao;

import com.tigersliver.entity.DichVuDaThue;
import com.tigersliver.utils.Jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DichVuDaThueDAO extends HomeStayDAO<DichVuDaThue, String> {

    @Override
    public void insert(DichVuDaThue entity) {
        String sql = "INSERT INTO DichVuDaThue (MaDVDT, MaDP, MaDV, TenDV, DonGia, SoLuong) VALUES (?, ?, ?, ?, ?, ?)";
        Jdbc.update(sql,
                entity.getMaDVDT(),
                entity.getMaDP(),
                entity.getMaDV(),
                entity.getTenDV(),
                entity.getDonGia(),
                entity.getSoLuong()
        );
    }

    @Override
    public void update(DichVuDaThue entity) {
        String sql = "UPDATE DichVuDaThue SET MaDP=?, MaDV=?, TenDV=?, DonGia=?, SoLuong=? WHERE MaDVDT=?";
        Jdbc.update(sql,
                entity.getMaDP(),
                entity.getMaDV(),
                entity.getTenDV(),
                entity.getDonGia(),
                entity.getSoLuong(),
                entity.getMaDVDT()
        );
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM DichVuDaThue WHERE MaDVDT=?";
        Jdbc.update(sql, id);
    }

    @Override
    public DichVuDaThue selectById(String id) {
        String sql = "SELECT * FROM DichVuDaThue WHERE MaDVDT=?";
        List<DichVuDaThue> list = this.selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<DichVuDaThue> selectAll() {
        String sql = "SELECT * FROM DichVuDaThue";
        return this.selectBySql(sql);
    }

    @Override
    protected List<DichVuDaThue> selectBySql(String sql, Object... args) {
        List<DichVuDaThue> list = new ArrayList<>();
        try {
            ResultSet rs = Jdbc.query(sql, args);
            while (rs.next()) {
                DichVuDaThue entity = new DichVuDaThue();
                entity.setMaDVDT(rs.getString("MaDVDT"));
                entity.setMaDP(rs.getString("MaDP"));
                entity.setMaDV(rs.getString("MaDV"));
                entity.setTenDV(rs.getString("TenDV"));
                entity.setDonGia(rs.getDouble("DonGia"));
                entity.setSoLuong(rs.getDouble("SoLuong"));
                list.add(entity);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
