package com.erdemkrgn.e_ticaret.controller;

// **Model ve Servis katmanlarını içe aktarıyoruz**
import com.erdemkrgn.e_ticaret.model.Urun;
import com.erdemkrgn.e_ticaret.service.UrunService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// **Bu sınıf bir REST API Controller'dır**
@RestController
@RequestMapping("/api/urunler") // **Tüm istekler "/api/urunler" altında çalışacaktır**
public class UrunController {

    private final UrunService urunService; // **Service katmanını kullanarak işlemleri yöneteceğiz**

    // **Bağımlılık enjeksiyonu ile UrunService'i ekliyoruz**
    public UrunController(UrunService urunService) {
        this.urunService = urunService;
    }

    // **Tüm ürünleri listeleme endpoint'i**
    @GetMapping
    public ResponseEntity<List<Urun>> tumUrunleriGetir() {
        return ResponseEntity.ok(urunService.tumUrunleriGetir());
    }

    // **Belirli bir ürünü ID ile bulma endpoint'i**
    @GetMapping("/{id}")
    public ResponseEntity<Urun> urunBul(@PathVariable Long id) {
        Optional<Urun> urun = urunService.urunBul(id);
        return urun.map(ResponseEntity::ok) // **Eğer ürün varsa, onu döndür**
                .orElseGet(() -> ResponseEntity.notFound().build()); // **Eğer ürün yoksa 404 döndür**
    }

    // **Yeni bir ürün ekleme endpoint'i**
    @PostMapping
    public ResponseEntity<Urun> urunEkle(@RequestBody Urun urun) {
        return ResponseEntity.ok(urunService.urunEkle(urun));
    }

    // **Var olan bir ürünü güncelleme endpoint'i**
    @PutMapping("/{id}")
    public ResponseEntity<Urun> urunGuncelle(@PathVariable Long id, @RequestBody Urun urun) {
        return ResponseEntity.ok(urunService.urunGuncelle(id, urun));
    }

    // **Var olan bir ürünü silme endpoint'i**
    @DeleteMapping("/{id}")
    public ResponseEntity<String> urunSil(@PathVariable Long id) {
        urunService.urunSil(id);
        return ResponseEntity.ok("Ürün başarıyla silindi!");
    }
}
