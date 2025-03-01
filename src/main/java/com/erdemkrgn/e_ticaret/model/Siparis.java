package com.erdemkrgn.e_ticaret.model;

// **JPA (Java Persistence API) kütüphanelerini içe aktarıyoruz**
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "siparisler")
public class Siparis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // **Otomatik Artan ID**
    private Long id;

    @ManyToOne // **Bir kullanıcı birden fazla sipariş verebilir**
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

    @Column(nullable = false)
    private Date siparisTarihi;

    @Enumerated(EnumType.STRING) // **Sipariş durumunu Enum olarak saklayacağız**
    private SiparisDurumu durum;

    // **Boş Constructor (Hibernate ve JPA için gerekli)**
    public Siparis() {}

    // **Tüm alanları içeren Constructor**
    public Siparis(Kullanici kullanici, Date siparisTarihi, SiparisDurumu durum) {
        this.kullanici = kullanici;
        this.siparisTarihi = siparisTarihi;
        this.durum = durum;
    }

    // **Getter ve Setter Metotları**
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Kullanici getKullanici() { return kullanici; }
    public void setKullanici(Kullanici kullanici) { this.kullanici = kullanici; }

    public Date getSiparisTarihi() { return siparisTarihi; }
    public void setSiparisTarihi(Date siparisTarihi) { this.siparisTarihi = siparisTarihi; }

    public SiparisDurumu getDurum() { return durum; }
    public void setDurum(SiparisDurumu durum) { this.durum = durum; }
}
