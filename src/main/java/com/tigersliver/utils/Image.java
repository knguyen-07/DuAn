/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tigersliver.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class Image {
    public static java.awt.Image getAppIcon() {
        String path = "D:\\DuAn\\HomeStay\\src\\main\\java\\com\\tigersliver\\icon\\logoGoc.png";
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalStateException("File not found: " + path);
        }
        return new ImageIcon(path).getImage();
    }
    public static void save(File file) {
        try {
            File dir = new File("logos");
            if (!dir.exists()) {
                dir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
            }
            Files.copy(file.toPath(), new File(dir, file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc file từ thư mục "logos" hoặc trả về icon mặc định
    public static ImageIcon read(String fileName) {
        File path = new File("logos", fileName);
        if (!path.exists()) {
            return new ImageIcon(getAppIcon()); // Trả về icon mặc định nếu không tìm thấy hình
        }
        return new ImageIcon(path.getAbsolutePath());
    }
}
