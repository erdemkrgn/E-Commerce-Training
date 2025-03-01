package com.erdemkrgn.e_ticaret.service;

import com.erdemkrgn.e_ticaret.model.Kullanici;
import com.erdemkrgn.e_ticaret.model.Siparis;
import com.erdemkrgn.e_ticaret.model.SiparisDurumu;
import com.erdemkrgn.e_ticaret.repository.SiparisRepository;
import com.erdemkrgn.e_ticaret.repository.KullaniciRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SiparisService {

    private final SiparisRepository siparisRepository;
    private final KullaniciRepository kullaniciRepository;

    public SiparisService(SiparisRepository siparisRepository, KullaniciRepository kullaniciRepository) {
        this.siparisRepository = siparisRepository;
        this.kullaniciRepository = kullaniciRepository;
    }

    // **Kullanıcının tüm siparişlerini getir**
    public List<Siparis> kullanicininSiparisleriniGetir(Long kullaniciId) {
        Optional<Kullanici> kullanici = kullaniciRepository.findById(kullaniciId);
        return kullanici.map(siparisRepository::findByKullanici).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
    }

    // **Yeni sipariş oluştur**
    public Siparis siparisOlustur(Long kullaniciId) {
        Kullanici kullanici = kullaniciRepository.findById(kullaniciId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));

        Siparis yeniSiparis = new Siparis(kullanici, new Date(), SiparisDurumu.BEKLEMEDE);
        return siparisRepository.save(yeniSiparis);
    }

    // **Sipariş durumunu güncelle**
    public Siparis siparisDurumuGuncelle(Long siparisId, SiparisDurumu yeniDurum) {
        return siparisRepository.findById(siparisId)
                .map(siparis -> {
                    siparis.setDurum(yeniDurum);
                    return siparisRepository.save(siparis);
                })
                .orElseThrow(() -> new RuntimeException("Sipariş bulunamadı!"));
    }
}
