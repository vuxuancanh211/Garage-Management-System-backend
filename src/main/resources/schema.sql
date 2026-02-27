DROP TABLE khach_hang;
DROP TABLE nhan_vien;

CREATE TABLE IF NOT EXISTS khach_hang (
    "maKH" VARCHAR(10) PRIMARY KEY,
    "hoTen" VARCHAR(100),
    "sdt" VARCHAR(10),
    "email" VARCHAR(100),
    "diaChi" TEXT,
    "matKhau" VARCHAR(255),
    "role" VARCHAR(50) DEFAULT 'ROLE_CUSTOMER'
    );

CREATE TABLE IF NOT EXISTS nhan_vien (
    "maNV" VARCHAR(10) PRIMARY KEY,
    "hoTen" VARCHAR(100),
    "vaiTro" VARCHAR(50),
    "sdt" VARCHAR(10),
    "email" VARCHAR(100),
    "matKhau" VARCHAR(255)
    );