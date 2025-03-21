CREATE DATABASE TigerSliver;
GO

USE TigerSliver;
GO
-- B?ng Nhân Viên
CREATE TABLE NhanVien (
    MaNV NVARCHAR(50) PRIMARY KEY,
    TenNV NVARCHAR(100),
    SoDT VARCHAR(15),
    ChucVu NVARCHAR(50),
    MatKhau NVARCHAR(255),
    Email NVARCHAR(100),
    GioiTinh NVARCHAR(10),
    HinhAnh NVARCHAR(225),
	MaNguoiDung INT UNIQUE,
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung) ON DELETE SET NULL
);

-- B?ng Khách Hàng
CREATE TABLE KhachHang (
    MaKH NVARCHAR(50) PRIMARY KEY,
    TenKH NVARCHAR(100),
    SoDT VARCHAR(15),
    CCCD VARCHAR(20),
    GioiTinh NVARCHAR(10)
);

-- B?ng Lo?i Phòng
CREATE TABLE LoaiPhong (
    MaLoaiPhong NVARCHAR(50) PRIMARY KEY,
    TenLoaiPhong NVARCHAR(50)
);

-- B?ng Danh Sách Phòng
CREATE TABLE DSPhong (
    MaPhong NVARCHAR(50) PRIMARY KEY,
    TenPhong NVARCHAR(50),
    MaLoaiPhong NVARCHAR(50) FOREIGN KEY REFERENCES LoaiPhong(MaLoaiPhong),
    TrangThai NVARCHAR(50)
);

-- B?ng Ki?u Thuê
CREATE TABLE KieuThue (
    MaKieuThue NVARCHAR(50) PRIMARY KEY,
    TenKieuThue NVARCHAR(50),
    DonGia DECIMAL(18,2)
);

-- B?ng ??t Phòng
CREATE TABLE DatPhong (
    MaDP NVARCHAR(50) PRIMARY KEY,
    NgayDen DATETIME DEFAULT GETDATE(),
    NgayDiDuKien DATE,
    MaNV NVARCHAR(50) FOREIGN KEY REFERENCES NhanVien(MaNV),
    MaKH NVARCHAR(50) FOREIGN KEY REFERENCES KhachHang(MaKH),
    MaPhong NVARCHAR(50) FOREIGN KEY REFERENCES DSPhong(MaPhong),
    MaKieuThue NVARCHAR(50) FOREIGN KEY REFERENCES KieuThue(MaKieuThue)
);

-- B?ng D?ch V?
CREATE TABLE DichVu (
    MaDV NVARCHAR(50) PRIMARY KEY,
    TenDV NVARCHAR(100),
    DonGia DECIMAL(18,2)
);

-- B?ng D?ch V? ?ã Thuê
CREATE TABLE DichVuDaThue (
    MaDVT NVARCHAR(50) PRIMARY KEY,
    MaDP NVARCHAR(50) FOREIGN KEY REFERENCES DatPhong(MaDP),
    MaDV NVARCHAR(50) FOREIGN KEY REFERENCES DichVu(MaDV),
    TenDV NVARCHAR(100),
    DonGia DECIMAL(18,2),
	SoLuong FLOAT
);


-- B?ng Hóa ??n Thanh Toán
CREATE TABLE HoaDonThanhToan (
    MaHD NVARCHAR(50) PRIMARY KEY,
    MaDP NVARCHAR(50) FOREIGN KEY REFERENCES DatPhong(MaDP),
    MaNV NVARCHAR(50) FOREIGN KEY REFERENCES NhanVien(MaNV),
	MaKH NVARCHAR(50) FOREIGN KEY REFERENCES KhachHang(MaKH),
    NgaySD DATE,
    NgayTra DATE,
    ThanhTien DECIMAL(18,2),
    NgayLap DATETIME DEFAULT GETDATE()
);

-- B?ng Hóa ??n Chi Ti?t
CREATE TABLE HoaDonChiTiet (
    MaHDCT NVARCHAR(50) PRIMARY KEY,
    MaHD NVARCHAR(50) FOREIGN KEY REFERENCES HoaDonThanhToan(MaHD),
    MaDV NVARCHAR(50) FOREIGN KEY REFERENCES DichVu(MaDV),
    NgayThue DATE,
    PhiThu DECIMAL(18,2)
);

-- Thêm d? li?u vào b?ng Ng??i Dùng
INSERT INTO NguoiDung (HoTen, TenDangNhap, MatKhau, VaiTro)
VALUES
(N'V? Tr?nh Hoàng Anh', 'Ha', '123', N'Admin'),
(N'T? Kim Nguyên', 'Map', '321', N'Admin'),
(N'Ngô Qu?c C??ng', 'L?ng M?t', '111', N'Nhân Viên'),
(N'?ào Nh?t Huy', 'Huy', '333', N'Nhân Viên'),
(N'Ph?m Qu?c Vi?t', 'Viet', '222', N'Nhân Viên'),
(N'Ph?m Huy Hoàng', 'Hoang', '333', N'Nhân Viên');

-- Thêm d? li?u vào b?ng Nhân Viên
INSERT INTO NhanVien (MaNV, TenNV, Email, SoDT, MatKhau, GioiTinh, ChucVu, HinhAnh, MaNguoiDung)
VALUES 
('NV01', N'V? Tr?nh Hoàng Anh', 'hank@example.com', '0987654321', '123', N'Nam', N'Qu?n lí', NULL, '1'),
('NV02', N'T? Kim Nguyên', 'knguyen@example.com', '0978654321', '321', N'Nam', N'Qu?n lí', NULL, '2'),
('NV03', N'Ngô Qu?c C??ng', 'qcuong@example.com', '0968543210', '111', N'Nam', N'Nhân viên', NULL, '3'),
('NV04', N'?ào Nh?t Huy', 'nhuy@example.com', '0957432109', '333', N'N?', N'Nhân viên', NULL, '4'),
('NV05', N'Ph?m Qu?c Vi?t', 'qviet@example.com', '0946321098', '222', N'N?', N'Nhân viên', NULL, '5'),
('NV06', N'Ph?m Huy Hoàng', 'hhoang@example.com', '0945111222', '333', N'N?', N'Nhân viên', NULL, '6');


INSERT INTO DichVu (MaDV, TenDV, DonGia)
VALUES 
    ('DV01', N'D?ch v? d?n phòng', 150000),
    ('DV02', N'D?ch v? gi?t ?i', 50000),
    ('DV03', N'D?ch v? ?n sáng', 100000),
    ('DV04', N'D?ch v? spa', 300000),
    ('DV05', N'D?ch v? ??a ?ón sân bay', 200000),
    ('DV06', N'D?ch v? gi? hành lý', 50000),
    ('DV07', N'D?ch v? g?i xe', 0), -- Mi?n phí
    ('DV08', N'D?ch v? h? b?i', 100000),
    ('DV09', N'D?ch v? ??t vé tham quan', 50000),
    ('DV10', N'D?ch v? phòng h?p', 500000),
	('DV13', N'D?ch v? buffet sáng', 200000),
    ('DV14', N'D?ch v? buffet tr?a', 300000),
    ('DV15', N'D?ch v? buffet t?i', 400000),
	('DV18', N'D?ch v? ??t món ?n t?i phòng', 100000),
	('DV20', N'D?ch v? r??u vang', 500000);



-- Thêm d? li?u cho b?ng LoaiPhong
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong) VALUES 
(N'LP001', N'Phòng Th??ng'),
(N'LP002', N'Phòng VIP');


-- Thêm d? li?u cho b?ng KieuThue
INSERT INTO KieuThue (MaKieuThue, TenKieuThue, DonGia) VALUES 
(N'KT001', N'Gi?', 200),
(N'KT002', N'Ngày', 1000),
(N'KT003', N'Tu?n', 5000);

INSERT INTO DSPhong (MaPhong, TenPhong, MaLoaiPhong, TrangThai)
VALUES 
    (N'P101', N'Phòng 101', N'LP001', N'Tr?ng'),
    (N'P102', N'Phòng 102', N'LP001', N'?ã ??t'),
    (N'P103', N'Phòng 103', N'LP001', N'?ã ??t'),
    (N'P104', N'Phòng 104', N'LP002', N'Tr?ng'),
    (N'P105', N'Phòng 105', N'LP002', N'B?o trì'),
    (N'P106', N'Phòng 106', N'LP001', N'Tr?ng'),
	(N'P107', N'Phòng 107', N'LP001', N'Tr?ng'),
	(N'P108', N'Phòng 108', N'LP002', N'?ã ??t');


INSERT INTO KhachHang (MaKH, TenKH, SoDT, CCCD, GioiTinh) 
VALUES 
('KH001', N'Nguy?n Anh Tu?n', '0912345678', '123456789012', N'Nam'),
('KH002', N'Tr?n Th? Tuy?t Nhi', '0987654321', '987654321098', N'N?'),
('KH003', N'Ngô Huy Hoàng', '0981527364', '916283528364', N'Nam');


INSERT INTO DatPhong (MaDP, NgayDiDuKien, MaNV, MaKH, MaPhong, MaKieuThue)
VALUES 
('DP001', '2025-03-25', 'NV03', 'KH001', 'P108', 'KT002'),
('DP002', '2025-03-25', 'NV04', 'KH002', 'P108', 'KT002'),
('DP003', '2025-03-27', 'NV05', 'KH003', 'P102', 'KT003');


INSERT INTO HoaDonThanhToan (MaHD, MaDP, MaNV, MaKH, NgaySD, NgayTra, ThanhTien)
VALUES 
('HD001', 'DP001', 'NV03', 'KH001', '2025-03-20', '2025-03-23', 3000),
('HD002', 'DP002', 'NV04', 'KH002', '2025-03-21', '2025-03-25', 5000),
('HD003', 'DP003', 'NV05', 'KH003', '2025-03-20', '2025-03-27', 5000);
GO


CREATE PROCEDURE GetDoanhThuPhong
    @ThangNam NVARCHAR(7) -- ??nh d?ng 'YYYY-MM'
AS
BEGIN
    SELECT 
        ROW_NUMBER() OVER (ORDER BY P.MaPhong) AS STT,
        P.MaPhong,
        COALESCE(SUM(HDC.PhiThu), 0) AS PhuThu,
        SUM(HD.ThanhTien) AS ThanhTien
    FROM HoaDonThanhToan HD
    INNER JOIN DatPhong DP ON HD.MaDP = DP.MaDP
    INNER JOIN DSPhong P ON DP.MaPhong = P.MaPhong
    LEFT JOIN HoaDonChiTiet HDC ON HD.MaHD = HDC.MaHD
    WHERE FORMAT(HD.NgayLap, 'yyyy-MM') = @ThangNam
    GROUP BY P.MaPhong;
END;
GO


CREATE PROCEDURE GetDoanhThuDichVu
    @ThangNam NVARCHAR(7) -- 'YYYY-MM'
AS
BEGIN
    SELECT 
        ROW_NUMBER() OVER (ORDER BY DV.MaDV) AS STT,
        DV.MaDV,
        DV.TenDV,
        SUM(DVT.SoLuong) AS SoLuong, 
        DV.DonGia,
        SUM(DVT.SoLuong * DV.DonGia) AS ThanhTien
    FROM DichVuDaThue DVT
    INNER JOIN DichVu DV ON DVT.MaDV = DV.MaDV
    INNER JOIN DatPhong DP ON DVT.MaDP = DP.MaDP
    INNER JOIN HoaDonThanhToan HD ON DP.MaDP = HD.MaDP
    WHERE FORMAT(HD.NgayLap, 'yyyy-MM') = @ThangNam
    GROUP BY DV.MaDV, DV.TenDV, DV.DonGia;
END;
GO
