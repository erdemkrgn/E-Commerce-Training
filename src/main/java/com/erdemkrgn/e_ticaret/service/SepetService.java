package com.erdemkrgn.e_ticaret.service;

import com.erdemkrgn.e_ticaret.model.Kullanici;
import com.erdemkrgn.e_ticaret.model.Sepet;
import com.erdemkrgn.e_ticaret.model.Urun;
import com.erdemkrgn.e_ticaret.repository.SepetRepository;
import com.erdemkrgn.e_ticaret.repository.UrunRepository;
import com.erdemkrgn.e_ticaret.repository.KullaniciRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SepetService {

    private final SepetRepository sepetRepository;
    private final UrunRepository urunRepository;
    private final KullaniciRepository kullaniciRepository;

    public SepetService(SepetRepository sepetRepository, UrunRepository urunRepository, KullaniciRepository kullaniciRepository) {
        this.sepetRepository = sepetRepository;
        this.urunRepository = urunRepository;
        this.kullaniciRepository = kullaniciRepository;
    }

    // **Kullanıcının sepetini getir**
    public List<Sepet> kullanicininSepetiniGetir(Long kullaniciId) {
        Optional<Kullanici> kullanici = kullaniciRepository.findById(kullaniciId);
        return kullanici.map(sepetRepository::findByKullanici).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
    }

    // **Sepete ürün ekle**
    public Sepet sepeteUrunEkle(Long kullaniciId, Long urunId, int adet) {
        Kullanici kullanici = kullaniciRepository.findById(kullaniciId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
        Urun urun = urunRepository.findById(urunId)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı!"));

        Sepet yeniSepet = new Sepet(kullanici, urun, adet);
        return sepetRepository.save(yeniSepet);
    }

    // **Sepetten ürün çıkar**
    public void sepettenUrunCikar(Long sepetId) {
        sepetRepository.deleteById(sepetId);
    }
}
