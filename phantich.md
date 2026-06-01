# PHẦN 1 - PHÂN TÍCH LOGIC

## 1. Sự khác biệt giữa CSRF trong Web truyền thống và REST API

### Web truyền thống (Session-Based)

Trong ứng dụng web truyền thống, sau khi người dùng đăng nhập, trình duyệt sẽ lưu Session ID trong Cookie và tự động gửi Cookie này trong mọi request tiếp theo.

Điều này tạo ra nguy cơ tấn công CSRF (Cross-Site Request Forgery). Kẻ tấn công có thể lừa người dùng truy cập một trang web độc hại, từ đó gửi các request đến hệ thống với Cookie hợp lệ của người dùng mà không cần biết mật khẩu.

Để ngăn chặn điều này, Spring Security sử dụng CSRF Token. Mỗi request thay đổi dữ liệu như POST, PUT, DELETE phải gửi kèm token hợp lệ.

---

### REST API (Stateless / Token-Based)

Trong REST API hiện đại, client thường sử dụng JWT Token hoặc Access Token lưu ở phía ứng dụng di động.

Token được gửi thủ công trong Header:

```http
Authorization: Bearer <token>
```

Trình duyệt không tự động gửi token này như Cookie.

Do đó nguy cơ CSRF gần như không tồn tại vì website độc hại không thể tự động đính kèm Authorization Header của người dùng.

Vì vậy nhiều hệ thống REST API lựa chọn vô hiệu hóa CSRF để đơn giản hóa việc giao tiếp giữa client và server.

---

## 2. Tại sao không nên vô hiệu hóa CSRF một cách mù quáng?

Nếu ứng dụng vẫn sử dụng Session và Cookie truyền thống nhưng CSRF bị tắt hoàn toàn thì hệ thống sẽ dễ bị tấn công CSRF.

Ví dụ:

* Người dùng đăng nhập vào hệ thống ngân hàng.
* Cookie phiên vẫn còn hiệu lực.
* Người dùng truy cập một website độc hại.
* Website này tự động gửi yêu cầu chuyển tiền đến hệ thống ngân hàng.
* Trình duyệt sẽ tự động gửi Cookie đăng nhập.
* Server tin rằng yêu cầu đến từ người dùng hợp lệ và thực hiện giao dịch.

Điều này có thể dẫn đến mất dữ liệu, thay đổi thông tin trái phép hoặc thực hiện các giao dịch ngoài ý muốn.

Vì vậy:

* Ứng dụng Web truyền thống nên bật CSRF.
* REST API sử dụng JWT hoặc Token-Based có thể vô hiệu hóa CSRF một cách an toàn.