/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.entity;

/**
 *
 * @author Admin
 */
public class DichVu {

    private String maDV;
    private String tenDV;
    private double donGia;

    // Getter và Setter
    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
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
        return this.tenDV; // Hiển thị tên dịch vụ trong JComboBox
    }

}
