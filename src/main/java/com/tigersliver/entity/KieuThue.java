/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.entity;

/**
 *
 * @author Admin
 */
public class KieuThue {

    private String maKieuThue, tenKieuThue;
    private double donGia;

    public String getMaKieuThue() {
        return maKieuThue;
    }

    public void setMaKieuThue(String maKieuThue) {
        this.maKieuThue = maKieuThue;
    }

    public String getTenKieuThue() {
        return tenKieuThue;
    }

    public void setTenKieuThue(String tenKieuThue) {
        this.tenKieuThue = tenKieuThue;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    // Ghi đè toString()
    @Override
    public String toString() {
        return this.tenKieuThue; // Hiển thị tên dịch vụ trong JComboBox
    }
}
