package com.erdemkrgn.e_ticaret.security;

// **Gerekli kütüphaneleri içe aktarıyoruz**
import com.erdemkrgn.e_ticaret.service.KullaniciService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component // **Spring tarafından otomatik yönetilen bir bileşen olarak işaretlenir**
public class JwtRequestFilter extends OncePerRequestFilter {

    private final KullaniciService kullaniciService;
    private final JwtUtil jwtUtil;

    // **Bağımlılık enjeksiyonu**
    public JwtRequestFilter(KullaniciService kullaniciService, JwtUtil jwtUtil) {
        this.kullaniciService = kullaniciService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization"); // **Header'dan JWT token'ı al**

        String kullaniciAdi = null;
        String jwt = null;

        // **JWT token'ın geçerli olup olmadığını kontrol et**
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // "Bearer " kısmını kaldır
            kullaniciAdi = jwtUtil.extractUsername(jwt); // **Token içindeki kullanıcı adını al**
        }

        // **Eğer token geçerli ve kullanıcı doğrulanmamışsa işlemi devam ettir**
        if (kullaniciAdi != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(jwt, kullaniciAdi)) {
                String role = jwtUtil.extractRole(jwt); // **Token içindeki rol bilgisini al**

                UserDetails userDetails = User.withUsername(kullaniciAdi)
                        .password("") // **Şifreye ihtiyacımız yok çünkü JWT doğrulama yapıyoruz**
                        .authorities(role) // **Kullanıcının yetkisini ekliyoruz**
                        .build();

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // **Kullanıcıyı Spring Security Context'ine ekleyerek yetkilendirme işlemini tamamla**
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // **Sonraki filtreye geçiş yap**
        chain.doFilter(request, response);
    }
}
