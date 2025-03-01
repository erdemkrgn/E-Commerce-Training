package com.erdemkrgn.e_ticaret.repository;

import com.erdemkrgn.e_ticaret.model.Siparis;
import com.erdemkrgn.e_ticaret.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiparisRepository extends JpaRepository<Siparis, Long> {
    List<Siparis> findByKullanici(Kullanici kullanici); // **Belirli bir kullanıcının siparişlerini getir**
}
