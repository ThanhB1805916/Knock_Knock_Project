-- Tạo cở sở dữ liệu cho phần mềm nhắn tin
DROP DATABASE Knock_Knock_Project; 

CREATE DATABASE Knock_Knock_Project;
USE Knock_Knock_Project;

-- Tạo các bảng (5 bảng)

-- Bảng người dùng
CREATE TABLE Person
(
    id INT AUTO_INCREMENT, -- ID tự tăng
    `username` VARCHAR(20) NOT NULL UNIQUE, -- Tài khoản 
    `password` VARCHAR(128) NOT NULL, -- Mật khẩu lưu hash
    `name` NVARCHAR(128) NOT NULL, -- Họ tên
    gender BOOL NOT NULL DEFAULT FALSE, -- Giới tính 1 là nam 0 là nữ
    phonenumber CHAR(10) UNIQUE NOT NULL, -- Số điện thoại
    dateofbirth DATE, -- Ngày sinh
    avatar VARCHAR(128), -- Lưu đường dẫn ảnh đại diện người dùng
    
    -- Khóa chính
    CONSTRAINT Person_PK PRIMARY KEY (id)
);

-- Bảng phòng
CREATE TABLE Room
(
	id INT AUTO_INCREMENT, -- ID phòng tự tăng
    `name` NVARCHAR(20) NOT NULL, -- Tên phòng
    datecreate DATETIME NOT NULL DEFAULT NOW(), -- Ngày tạo phòng
    avatar VARCHAR(128), -- Lưu đường dẫn ảnh đại diện phòng
    
    -- Khóa chính
    CONSTRAINT Room_PK PRIMARY KEY(id)
);

-- Bảng bạn bè
CREATE TABLE Friend
(
	id_person INT NOT NULL,
	id_friend INT NOT NULL,
    isFriend BOOL NOT NULL DEFAULT 0, -- 0 Đang chờ kết bạn
	adddate DATETIME NOT NULL DEFAULT NOW(), -- Ngày thêm bạn
    
    -- Khóa ngoại tham chiếu bảng Person
     CONSTRAINT Friend_FK1_Person FOREIGN KEY(id_person) REFERENCES Person(id) ON DELETE CASCADE,
     CONSTRAINT Friend_FK2_Person FOREIGN KEY(id_friend) REFERENCES Person(id),
     
     -- Khóa chính
     CONSTRAINT Friens_PK PRIMARY KEY(id_person, id_friend)
);

-- Bảng danh sách người dùng trong phòng
CREATE TABLE Person_Room
(
	id_room INT, -- ID phòng
    id_person INT, -- ID người dùng
    intime DATETIME NOT NULL DEFAULT NOW(), -- Thời gian vào phòng mặc định lấy giờ hệ thống
	outtime DATETIME NOT NULL DEFAULT NOW(), -- Thời gian ra khỏi phòng  mặc định lấy giờ hệ thống 
    -- Khóa ngoại
    -- Bảng Room
    CONSTRAINT Person_Room_FK_Room FOREIGN KEY(id_room) REFERENCES Room(id) ON DELETE CASCADE,
    -- Bảng Person
	CONSTRAINT Person_Room_FK_Person FOREIGN KEY(id_person) REFERENCES Person(id) ON DELETE CASCADE,
    
    -- Khóa chính
    CONSTRAINT Person_Room_PK PRIMARY KEY(id_room, id_person)
);

-- Bảng lưu tin nhắn của phòng đó từ người dùng
CREATE TABLE Message
(
	id INT AUTO_INCREMENT, -- ID
	id_room INT NOT NULL, -- ID phòng
    id_person INT NOT NULL, -- ID người dùng
	messagecontent NVARCHAR(128) NOT NULL, -- Nội dung tin nhắn hoặc đường dẫn file
    isFile BOOL NOT NULL DEFAULT FALSE, -- Kiểm tra file mặc định là tin nhắn
    sendtime DATETIME NOT NULL DEFAULT NOW(), -- Thời gian gửi tin nhắn mặc định lấy giờ hệ thống

	-- Khóa ngoại
	-- Bảng Room
    CONSTRAINT Message_FK_Room FOREIGN KEY(id_room) REFERENCES Room(id) ON DELETE CASCADE,
    -- Bảng Person
	CONSTRAINT Message_FK_Person FOREIGN KEY(id_person) REFERENCES Person(id) ON DELETE CASCADE,
    
    -- Khóa chính
    CONSTRAINT Message_PK PRIMARY KEY (id)
);

