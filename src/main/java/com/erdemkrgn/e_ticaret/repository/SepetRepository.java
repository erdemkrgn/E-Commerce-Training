package com.erdemkrgn.e_ticaret.repository;

import com.erdemkrgn.e_ticaret.model.Sepet;
import com.erdemkrgn.e_ticaret.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SepetRepository extends JpaRepository<Sepet, Long> {
    List<Sepet> findByKullanici(Kullanici kullanici); // **Belirli bir kullanıcının sepetini getir**
}
