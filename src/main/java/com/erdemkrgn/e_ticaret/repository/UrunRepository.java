package com.erdemkrgn.e_ticaret.repository;

import com.erdemkrgn.e_ticaret.model.Urun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Spring'in bu sınıfı bir Repository olarak tanımasını sağlar
public interface UrunRepository extends JpaRepository<Urun, Long> {
}
