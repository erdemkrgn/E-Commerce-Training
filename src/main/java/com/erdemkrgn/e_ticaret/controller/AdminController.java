package com.erdemkrgn.e_ticaret.controller;

// **Spring Boot’un HTTP yanıtlarını yönetmesi için gerekli kütüphaneler ekleniyor**
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // **Bu sınıf bir REST API Controller'dır**
@RequestMapping("/admin") // **Tüm istekler "/admin" altında çalışacaktır**
public class AdminController {

    // **Admin paneline erişmek için GET isteği**
    @GetMapping("/panel")
    public ResponseEntity<String> adminPanel() {
        return ResponseEntity.ok("Admin Paneline Hoşgeldiniz!"); // **Yanıt olarak bir mesaj döndürülüyor**
    }
}
