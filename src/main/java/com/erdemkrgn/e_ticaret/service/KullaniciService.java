package com.erdemkrgn.e_ticaret.service;

import com.erdemkrgn.e_ticaret.model.Kullanici;
import com.erdemkrgn.e_ticaret.repository.KullaniciRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KullaniciService {

    private final KullaniciRepository kullaniciRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public KullaniciService(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Kullanici kayitOl(Kullanici kullanici) {
        kullanici.setSifre(passwordEncoder.encode(kullanici.getSifre())); // Şifreyi BCrypt ile şifrele
        return kullaniciRepository.save(kullanici);
    }

    public Optional<Kullanici> girisYap(String kullaniciAdi, String sifre) {
        Optional<Kullanici> kullanici = kullaniciRepository.findByKullaniciAdi(kullaniciAdi);

        if (kullanici.isPresent() && passwordEncoder.matches(sifre, kullanici.get().getSifre())) {
            return kullanici; // Şifre doğruysa, kullanıcıyı döndür
        }
        return Optional.empty(); // Hatalı giriş
    }
}
