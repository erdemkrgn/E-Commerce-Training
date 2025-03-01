package com.erdemkrgn.e_ticaret.controller;

import com.erdemkrgn.e_ticaret.model.Siparis;
import com.erdemkrgn.e_ticaret.model.SiparisDurumu;
import com.erdemkrgn.e_ticaret.service.SiparisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/siparis")
public class SiparisController {

    private final SiparisService siparisService;

    public SiparisController(SiparisService siparisService) {
        this.siparisService = siparisService;
    }

    @GetMapping("/{kullaniciId}")
    public ResponseEntity<List<Siparis>> kullanicininSiparisleriniGetir(@PathVariable Long kullaniciId) {
        return ResponseEntity.ok(siparisService.kullanicininSiparisleriniGetir(kullaniciId));
    }

    @PostMapping
    public ResponseEntity<Siparis> siparisOlustur(@RequestParam Long kullaniciId) {
        return ResponseEntity.ok(siparisService.siparisOlustur(kullaniciId));
    }

    @PutMapping("/{siparisId}")
    public ResponseEntity<Siparis> siparisDurumuGuncelle(@PathVariable Long siparisId, @RequestParam SiparisDurumu yeniDurum) {
        return ResponseEntity.ok(siparisService.siparisDurumuGuncelle(siparisId, yeniDurum));
    }
}
