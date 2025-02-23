package com.erdemkrgn.e_ticaret.security;

import com.erdemkrgn.e_ticaret.model.Kullanici;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Güçlü bir JWT imzalama anahtarı
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // Token 10 saat geçerli olacak

    // **JWT Token Oluşturma**
    public String generateToken(Kullanici kullanici) {
        return Jwts.builder()
                .setSubject(kullanici.getKullaniciAdi()) // Kullanıcı adını JWT içine ekler
                .claim("role", kullanici.getRole().name()) // Kullanıcının rolünü JWT içine ekler
                .setIssuedAt(new Date()) // Token’in oluşturulma zamanı
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Token geçerlilik süresi
                .signWith(SECRET_KEY) // Belirlenen SECRET_KEY ile imzala
                .compact();
    }

    // **JWT Token’dan Kullanıcı Adını Çıkartma**
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // **JWT Token’dan "role" (Yetki) Bilgisini Çıkartma**
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    // **Genel Amaçlı Claim Okuma Metodu**
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // **Token Doğrulama**
    public boolean validateToken(String token, String kullaniciAdi) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(kullaniciAdi) && !isTokenExpired(token));
    }

    // **Token Süresi Dolmuş mu Kontrol Et**
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    // **Token'daki Tüm Claim'leri Çıkart**
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Belirlenen SECRET_KEY ile doğrula
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
