/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.utils;

import com.tigersliver.entity.NhanVien;

/**
 *
 * @author Admin
 */
public class Auth {

    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
    public static NhanVien user = null;

    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void clear() {
        Auth.user = null;
    }
    
    /**
     * Kiểm tra xem đã đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return Auth.user != null;
    }

    /**
     * Kiểm tra xem có phải là Admin hay không
     */
    public static boolean isAdmin() {
        return isLogin() && "Quản lí".equalsIgnoreCase(user.getChucVu());
    }

    /**
     * Kiểm tra xem có phải là Nhân Viên hay không
     */
    public static boolean isNhanVien() {
        return isLogin() && "Nhân Viên".equalsIgnoreCase(user.getChucVu());
    }

    /**
     * Kiểm tra xem có phải là khách (Guest) hay không
     */
    public static boolean isGuest() {
        return !isLogin();
    }

    /**
     * Kiểm tra quyền truy cập vào chức năng dành cho người chưa đăng nhập
     */
    public static boolean isGuestAccess() {
        return isGuest();
    }

    /**
     * Kiểm tra quyền truy cập vào chức năng dành cho Nhân Viên
     */
    public static boolean isEmployeeAccess() {
        return isNhanVien() || isAdmin();
    }

    /**
     * Kiểm tra quyền truy cập vào chức năng dành cho Admin
     */
    public static boolean isAdminAccess() {
        return isAdmin();
    }

    /**
     * Đăng nhập với một đối tượng NguoiDung
     */
    public static void login(NhanVien nhanVien) {
        Auth.user = nhanVien;
    }

    /**
     * Đăng xuất, xóa thông tin người dùng
     */
    public static void logout() {
        Auth.user = null;
    }

    /**
     * Lấy thông tin người dùng hiện tại
     */
    public static NhanVien getUser() {
        return Auth.user;
    }

    /**
     * Thiết lập người dùng hiện tại (chỉ nên dùng trong trường hợp đặc biệt)
     */
    public static void setUser(NhanVien nhanVien) {
        Auth.user = nhanVien;
    }
}
