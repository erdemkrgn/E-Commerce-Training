package com.erdemkrgn.e_ticaret.service;

import com.erdemkrgn.e_ticaret.model.Urun;
import com.erdemkrgn.e_ticaret.repository.UrunRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrunService {

    private final UrunRepository urunRepository;

    public UrunService(UrunRepository urunRepository) {
        this.urunRepository = urunRepository;
    }

    public List<Urun> tumUrunleriGetir() {
        return urunRepository.findAll();
    }

    public Optional<Urun> urunBul(Long id) {
        return urunRepository.findById(id);
    }

    public Urun urunEkle(Urun urun) {
        return urunRepository.save(urun);
    }

    public Urun urunGuncelle(Long id, Urun guncellenenUrun) {
        return urunRepository.findById(id)
                .map(urun -> {
                    urun.setIsim(guncellenenUrun.getIsim());
                    urun.setFiyat(guncellenenUrun.getFiyat());
                    urun.setStok(guncellenenUrun.getStok());
                    return urunRepository.save(urun);
                })
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı!"));
    }

    public void urunSil(Long id) {
        urunRepository.deleteById(id);
    }
}
