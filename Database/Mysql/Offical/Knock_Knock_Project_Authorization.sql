USE Knock_Knock_Project;

-- Tạo vai trò người dùng bình thường
-- CREATE ROLE Role_StdUser;

-- Cấp quyền cho vai trò
-- Quyền xem tất cả bảng

-- Tạo người dùng bình thường
CREATE USER 'stdUser'@'localhost' IDENTIFIED BY 'std1234';

-- Cấp role cho người dùng
-- GRANT Role_StdUser TO stdUser;
-- DROP user 'stdUser'@'localhost';
-- Hiện quyền
SHOW GRANTS FOR 'stdUser'@'localhost';
-- Thực thi procedure cố định
-- GRANT EXECUTE ON PROCEDURE spUsers_GetUsers TO stdUser;
-- GRANT EXECUTE TO Role_StdUser;
-- Thực thi tất cả procedure 
GRANT EXECUTE ON Knock_Knock_Project.* TO 'stdUser'@'localhost';

