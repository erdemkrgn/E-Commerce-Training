package com.erdemkrgn.e_ticaret.config;

// **Güvenlik bileşenlerini ve JWT filtrelerini içe aktarıyoruz**
import com.erdemkrgn.e_ticaret.security.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // **Bu sınıfın bir yapılandırma (configuration) sınıfı olduğunu belirtir**
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter; // **JWT doğrulama filtresini kullanacağız**

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // **CSRF korumasını devre dışı bırakıyoruz çünkü JWT kullanıyoruz**
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // **Stateful oturum yerine JWT kullanıyoruz**
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/login").permitAll() // 🔓 Kayıt ve giriş serbest
                        .requestMatchers(HttpMethod.GET, "/api/urunler").hasAnyAuthority("USER", "ADMIN") // 🔐 USER ve ADMIN görüntüleyebilir
                        .requestMatchers(HttpMethod.POST, "/api/urunler").hasAuthority("ADMIN") // 🔐 Sadece ADMIN ekleyebilir
                        .requestMatchers(HttpMethod.PUT, "/api/urunler/**").hasAuthority("ADMIN") // 🔐 Sadece ADMIN güncelleyebilir
                        .requestMatchers(HttpMethod.DELETE, "/api/urunler/**").hasAuthority("ADMIN") // 🔐 Sadece ADMIN silebilir
                        .requestMatchers("/admin/**").hasAuthority("ADMIN") // 🔐 Sadece ADMIN erişebilir
                        .anyRequest().authenticated() // **Diğer tüm istekler için kimlik doğrulaması gerekli**
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // **JWT doğrulama filtresini Spring Security zincirine ekliyoruz**

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
