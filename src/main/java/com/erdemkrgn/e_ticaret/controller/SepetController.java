package com.erdemkrgn.e_ticaret.controller;

import com.erdemkrgn.e_ticaret.dto.SepetRequest;
import com.erdemkrgn.e_ticaret.model.Sepet;
import com.erdemkrgn.e_ticaret.service.SepetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sepet")
public class SepetController {

    private final SepetService sepetService;

    public SepetController(SepetService sepetService) {
        this.sepetService = sepetService;
    }

    // **Kullanıcının sepetini getir**
    @GetMapping("/{kullaniciId}")
    public ResponseEntity<List<Sepet>> kullanicininSepetiniGetir(@PathVariable Long kullaniciId) {
        return ResponseEntity.ok(sepetService.kullanicininSepetiniGetir(kullaniciId));
    }

    // **Sepete ürün ekle**
    @PostMapping
    public ResponseEntity<Sepet> sepeteUrunEkle(@RequestBody SepetRequest request) {
        return ResponseEntity.ok(sepetService.sepeteUrunEkle(request.getKullaniciId(), request.getUrunId(), request.getAdet()));
    }


    // **Sepetten ürün çıkar**
    @DeleteMapping("/{sepetId}")
    public ResponseEntity<String> sepettenUrunCikar(@PathVariable Long sepetId) {
        sepetService.sepettenUrunCikar(sepetId);
        return ResponseEntity.ok("Ürün sepetten çıkarıldı!");
    }
}
