package com.erdemkrgn.e_ticaret.repository;

// **Spring Data JPA kütüphaneleri içe aktarılıyor**
import com.erdemkrgn.e_ticaret.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // **Bu sınıfın bir veritabanı repository (depolama) sınıfı olduğunu belirtiyoruz**
public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {

    // **Kullanıcı adına göre kullanıcıyı bulmak için özel bir sorgu oluşturuyoruz**
    @Query("SELECT k FROM Kullanici k WHERE k.kullaniciAdi = :kullaniciAdi")
    Optional<Kullanici> findByKullaniciAdi(@Param("kullaniciAdi") String kullaniciAdi);
}
