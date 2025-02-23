package com.erdemkrgn.e_ticaret.model;

// **JPA (Java Persistence API) kütüphanelerini içe aktarıyoruz**
import jakarta.persistence.*;

@Entity // **Bu sınıfın bir veritabanı tablosunu temsil ettiğini belirtir**
@Table(name = "urunler") // 🔥 Veritabanında "urunler" adlı tabloyu kullan
public class Urun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // **Birincil anahtar (ID) otomatik olarak arttırılacak**
    private Long id;

    @Column(nullable = false, length = 100) // **İsim sütunu zorunlu ve maksimum 100 karakter olabilir**
    private String isim;

    @Column(nullable = false) // **Fiyat sütunu zorunlu olmalı**
    private double fiyat;

    @Column(nullable = false) // **Stok miktarı sütunu zorunlu olmalı**
    private int stok;

    // **Boş Constructor (Hibernate ve JPA için gerekli)**
    public Urun() {}

    // **Tüm alanları içeren Constructor**
    public Urun(String isim, double fiyat, int stok) {
        this.isim = isim;
        this.fiyat = fiyat;
        this.stok = stok;
    }

    // **Getter ve Setter Metotları**
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIsim() { return isim; }
    public void setIsim(String isim) { this.isim = isim; }

    public double getFiyat() { return fiyat; }
    public void setFiyat(double fiyat) { this.fiyat = fiyat; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
}
