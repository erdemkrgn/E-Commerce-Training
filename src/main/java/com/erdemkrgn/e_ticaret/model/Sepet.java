package com.erdemkrgn.e_ticaret.model;

// **JPA (Java Persistence API) kütüphanelerini içe aktarıyoruz**
import jakarta.persistence.*;

@Entity
@Table(name = "sepet")
public class Sepet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // **Otomatik Artan ID**
    private Long id;

    @ManyToOne // **Bir kullanıcı birden fazla sepete sahip olabilir**
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

    @ManyToOne // **Bir üründen sepette birden fazla olabilir**
    @JoinColumn(name = "urun_id", nullable = false)
    private Urun urun;

    @Column(nullable = false) // **Ürün adedi**
    private int adet;

    // **Boş Constructor (Hibernate ve JPA için gerekli)**
    public Sepet() {}

    // **Tüm alanları içeren Constructor**
    public Sepet(Kullanici kullanici, Urun urun, int adet) {
        this.kullanici = kullanici;
        this.urun = urun;
        this.adet = adet;
    }

    // **Getter ve Setter Metotları**
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Kullanici getKullanici() { return kullanici; }
    public void setKullanici(Kullanici kullanici) { this.kullanici = kullanici; }

    public Urun getUrun() { return urun; }
    public void setUrun(Urun urun) { this.urun = urun; }

    public int getAdet() { return adet; }
    public void setAdet(int adet) { this.adet = adet; }
}
