# 🚀 MotoRent - Ứng dụng thuê xe máy

MotoRent là hệ thống thuê xe máy dành cho người dùng phổ thông, gồm 2 phần chính:
- MotoRent/ - Backend Spring Boot (MVC + REST API)
- MotoRentMobile/ - Ứng dụng Android viết bằng Java (kiến trúc MVVM)

## 🛠 Yêu cầu hệ thống
- Java 21
- Android Studio Hedgehog trở lên
- MySQL Server 8.x
- Gradle, Maven
- Git

## ⚙️ Cài đặt Backend (MotoRent - Spring Boot)

1. Cấu hình MySQL
Tạo database với tên bất kỳ và import file sql kèm theo, ví dụ: motorent_db

`CREATE DATABASE motorent_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;`

2. Cập nhật thông tin kết nối trong file `application.properties`

```
spring.datasource.url=jdbc:mysql://localhost:3306/motorent_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
payment.vnPay.returnUrl=http://192.168.1.13:8080/api/payment/vnpay/return
```
Lưu ý: 
- Đảm bảo MySQL server đang chạy và bạn có tài khoản kết nối hợp lệ.
- Bạn cần cập nhật dãy IPv4 theo máy tính chạy server , hãy nhớ dãy IP này cũng dùng để cài đặt Android App.

Cách tìm IP:
- Trên Windows: dùng lệnh ipconfig
- Trên macOS/Linux: dùng lệnh ifconfig
- Nếu dùng Android Emulator thì thay localhost bằng 10.0.2.2

Cả máy tính chạy backend và điện thoại (nếu test thật) phải chung mạng WiFi.
3. Chạy ứng dụng
Mở project MotoRent bằng IntelliJ hoặc Eclipse, chạy file com.motorrent.MotoRentApplication.
Mặc định server sẽ chạy tại http://localhost:8080

## 📱 Cài đặt Android App (MotoRentMobile)

1. Mở bằng Android Studio
- Mở thư mục MotoRentMobile bằng Android Studio
- Sync Gradle nếu được yêu cầu

2. Cấu hình IP Backend

Ứng dụng gọi API đến Spring Boot backend, bạn cần cập nhật IP trong file chứa URL API (ở đây là `data/api/APIClient.java`):

`public static final String BASE_URL = "http://192.168.1.10:8080/api/";`


3. Cho phép kết nối HTTP từ Android:
File `res/xml/network_security_config.xml` đã có sẵn, bạn chỉ cần sửa lại  bằng IP thực tế của máy đang chạy backend: 

```
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">192.168.1.4</domain>
    </domain-config>
</network-security-config>
```

4. Build và chạy app
- Chọn thiết bị hoặc emulator trong Android Studio
- Bấm nút Run ▶️ để cài và chạy ứng dụng

## 🔐 Tài khoản mẫu để test

| Role    | Email              | Mật khẩu |
|---------|--------------------|----------|
| Quản lý | admin@example.com  | admin123 |
|Nhân viên| staff@example.com  | staff123 |
| Khách   |customer@example.com|customer123|

Bạn cũng có thể tự đăng ký tài khoản mới trong app.

## 📤 Triển khai thật
Khi triển khai lên VPS/server:
- Thay đổi database thật trong application.properties
- Cập nhật BASE_URL trong Android app trỏ đến IP/domain thật


## 📧 Liên hệ / Đóng góp
Nếu bạn muốn đóng góp, vui lòng tạo pull request hoặc issue.
Liên hệ: tadzltv22082004@gmail.com

© 2025 MotoRent. All rights reserved.
