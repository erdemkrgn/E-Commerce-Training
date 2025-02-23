package com.erdemkrgn.e_ticaret.controller;

import com.erdemkrgn.e_ticaret.model.Kullanici;
import com.erdemkrgn.e_ticaret.security.JwtUtil;
import com.erdemkrgn.e_ticaret.service.KullaniciService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class KullaniciController {

    private final KullaniciService kullaniciService;
    private final JwtUtil jwtUtil;

    public KullaniciController(KullaniciService kullaniciService, JwtUtil jwtUtil) {
        this.kullaniciService = kullaniciService;
        this.jwtUtil = jwtUtil;
    }

    // **Kullanıcı Kayıt Olma (Register)**
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Kullanici kullanici) {
        try {
            Kullanici savedUser = kullaniciService.kayitOl(kullanici);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kullanıcı kaydı başarısız: " + e.getMessage());
        }
    }

    // **Kullanıcı Girişi (Login) ve Token Oluşturma**
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Kullanici loginRequest) {
        Optional<Kullanici> kullanici = kullaniciService.girisYap(loginRequest.getKullaniciAdi(), loginRequest.getSifre());

        if (kullanici.isPresent()) {
            String token = jwtUtil.generateToken(kullanici.get()); // ✅ Kullanici nesnesini gönderiyoruz
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Geçersiz kullanıcı adı veya şifre");
        }
    }
}
