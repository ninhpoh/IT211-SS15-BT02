package com.bt02.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/auth/register")
    public String register() {
        return "Đăng ký thành công";
    }

    @PostMapping("/auth/login")
    public String login() {
        return "Đăng nhập thành công";
    }

    @GetMapping("/customers")
    public String customers() {
        return "Danh sách khách hàng";
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return "Xóa khách hàng có mã: " + id;
    }
}