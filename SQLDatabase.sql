CREATE DATABASE TigerSliver;
GO

USE TigerSliver;
GO
-- B?ng Nh�n Vi�n
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

-- B?ng Kh�ch H�ng
CREATE TABLE KhachHang (
    MaKH NVARCHAR(50) PRIMARY KEY,
    TenKH NVARCHAR(100),
    SoDT VARCHAR(15),
    CCCD VARCHAR(20),
    GioiTinh NVARCHAR(10)
);

-- B?ng Lo?i Ph�ng
CREATE TABLE LoaiPhong (
    MaLoaiPhong NVARCHAR(50) PRIMARY KEY,
    TenLoaiPhong NVARCHAR(50)
);

-- B?ng Danh S�ch Ph�ng
CREATE TABLE DSPhong (
    MaPhong NVARCHAR(50) PRIMARY KEY,
    TenPhong NVARCHAR(50),
    MaLoaiPhong NVARCHAR(50) FOREIGN KEY REFERENCES LoaiPhong(MaLoaiPhong),
    TrangThai NVARCHAR(50)
);

-- B?ng Ki?u Thu�
CREATE TABLE KieuThue (
    MaKieuThue NVARCHAR(50) PRIMARY KEY,
    TenKieuThue NVARCHAR(50),
    DonGia DECIMAL(18,2)
);

-- B?ng ??t Ph�ng
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

-- B?ng D?ch V? ?� Thu�
CREATE TABLE DichVuDaThue (
    MaDVT NVARCHAR(50) PRIMARY KEY,
    MaDP NVARCHAR(50) FOREIGN KEY REFERENCES DatPhong(MaDP),
    MaDV NVARCHAR(50) FOREIGN KEY REFERENCES DichVu(MaDV),
    TenDV NVARCHAR(100),
    DonGia DECIMAL(18,2),
	SoLuong FLOAT
);


-- B?ng H�a ??n Thanh To�n
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

-- B?ng H�a ??n Chi Ti?t
CREATE TABLE HoaDonChiTiet (
    MaHDCT NVARCHAR(50) PRIMARY KEY,
    MaHD NVARCHAR(50) FOREIGN KEY REFERENCES HoaDonThanhToan(MaHD),
    MaDV NVARCHAR(50) FOREIGN KEY REFERENCES DichVu(MaDV),
    NgayThue DATE,
    PhiThu DECIMAL(18,2)
);

-- Th�m d? li?u v�o b?ng Ng??i D�ng
INSERT INTO NguoiDung (HoTen, TenDangNhap, MatKhau, VaiTro)
VALUES
(N'V? Tr?nh Ho�ng Anh', 'Ha', '123', N'Admin'),
(N'T? Kim Nguy�n', 'Map', '321', N'Admin'),
(N'Ng� Qu?c C??ng', 'L?ng M?t', '111', N'Nh�n Vi�n'),
(N'?�o Nh?t Huy', 'Huy', '333', N'Nh�n Vi�n'),
(N'Ph?m Qu?c Vi?t', 'Viet', '222', N'Nh�n Vi�n'),
(N'Ph?m Huy Ho�ng', 'Hoang', '333', N'Nh�n Vi�n');

-- Th�m d? li?u v�o b?ng Nh�n Vi�n
INSERT INTO NhanVien (MaNV, TenNV, Email, SoDT, MatKhau, GioiTinh, ChucVu, HinhAnh, MaNguoiDung)
VALUES 
('NV01', N'V? Tr?nh Ho�ng Anh', 'hank@example.com', '0987654321', '123', N'Nam', N'Qu?n l�', NULL, '1'),
('NV02', N'T? Kim Nguy�n', 'knguyen@example.com', '0978654321', '321', N'Nam', N'Qu?n l�', NULL, '2'),
('NV03', N'Ng� Qu?c C??ng', 'qcuong@example.com', '0968543210', '111', N'Nam', N'Nh�n vi�n', NULL, '3'),
('NV04', N'?�o Nh?t Huy', 'nhuy@example.com', '0957432109', '333', N'N?', N'Nh�n vi�n', NULL, '4'),
('NV05', N'Ph?m Qu?c Vi?t', 'qviet@example.com', '0946321098', '222', N'N?', N'Nh�n vi�n', NULL, '5'),
('NV06', N'Ph?m Huy Ho�ng', 'hhoang@example.com', '0945111222', '333', N'N?', N'Nh�n vi�n', NULL, '6');


INSERT INTO DichVu (MaDV, TenDV, DonGia)
VALUES 
    ('DV01', N'D?ch v? d?n ph�ng', 150000),
    ('DV02', N'D?ch v? gi?t ?i', 50000),
    ('DV03', N'D?ch v? ?n s�ng', 100000),
    ('DV04', N'D?ch v? spa', 300000),
    ('DV05', N'D?ch v? ??a ?�n s�n bay', 200000),
    ('DV06', N'D?ch v? gi? h�nh l�', 50000),
    ('DV07', N'D?ch v? g?i xe', 0), -- Mi?n ph�
    ('DV08', N'D?ch v? h? b?i', 100000),
    ('DV09', N'D?ch v? ??t v� tham quan', 50000),
    ('DV10', N'D?ch v? ph�ng h?p', 500000),
	('DV13', N'D?ch v? buffet s�ng', 200000),
    ('DV14', N'D?ch v? buffet tr?a', 300000),
    ('DV15', N'D?ch v? buffet t?i', 400000),
	('DV18', N'D?ch v? ??t m�n ?n t?i ph�ng', 100000),
	('DV20', N'D?ch v? r??u vang', 500000);



-- Th�m d? li?u cho b?ng LoaiPhong
INSERT INTO LoaiPhong (MaLoaiPhong, TenLoaiPhong) VALUES 
(N'LP001', N'Ph�ng Th??ng'),
(N'LP002', N'Ph�ng VIP');


-- Th�m d? li?u cho b?ng KieuThue
INSERT INTO KieuThue (MaKieuThue, TenKieuThue, DonGia) VALUES 
(N'KT001', N'Gi?', 200),
(N'KT002', N'Ng�y', 1000),
(N'KT003', N'Tu?n', 5000);

INSERT INTO DSPhong (MaPhong, TenPhong, MaLoaiPhong, TrangThai)
VALUES 
    (N'P101', N'Ph�ng 101', N'LP001', N'Tr?ng'),
    (N'P102', N'Ph�ng 102', N'LP001', N'?� ??t'),
    (N'P103', N'Ph�ng 103', N'LP001', N'?� ??t'),
    (N'P104', N'Ph�ng 104', N'LP002', N'Tr?ng'),
    (N'P105', N'Ph�ng 105', N'LP002', N'B?o tr�'),
    (N'P106', N'Ph�ng 106', N'LP001', N'Tr?ng'),
	(N'P107', N'Ph�ng 107', N'LP001', N'Tr?ng'),
	(N'P108', N'Ph�ng 108', N'LP002', N'?� ??t');


INSERT INTO KhachHang (MaKH, TenKH, SoDT, CCCD, GioiTinh) 
VALUES 
('KH001', N'Nguy?n Anh Tu?n', '0912345678', '123456789012', N'Nam'),
('KH002', N'Tr?n Th? Tuy?t Nhi', '0987654321', '987654321098', N'N?'),
('KH003', N'Ng� Huy Ho�ng', '0981527364', '916283528364', N'Nam');


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
