package com.erdemkrgn.e_ticaret.model;

// **JPA (Java Persistence API) kütüphaneleri içe aktarılıyor**
import jakarta.persistence.*;

@Entity // **Bu sınıfın bir veritabanı tablosunu temsil ettiğini belirtir**
@Table(name = "kullanicilar") // 🔥 Veritabanında "kullanicilar" adlı tabloyu kullan
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // **Birincil anahtar (ID) otomatik arttırılacak**
    private Long id;

    @Column(name = "kullanici_adi") // 🔥 Veritabanındaki sütun adını doğru eşleştir
    private String kullaniciAdi;

    private String sifre; // **Şifre saklanacak alan (BCrypt ile şifrelenmiş halde olmalıdır)**

    @Enumerated(EnumType.STRING) // **Role bilgisini Enum olarak saklayacak**
    private Role role;

    // **Boş Constructor (Hibernate için gerekli)**
    public Kullanici() {}

    // **Tüm alanları içeren Constructor**
    public Kullanici(String kullaniciAdi, String sifre, Role role) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.role = role;
    }

    // **Getter ve Setter Metotları**
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKullaniciAdi() { return kullaniciAdi; }
    public void setKullaniciAdi(String kullaniciAdi) { this.kullaniciAdi = kullaniciAdi; }

    public String getSifre() { return sifre; }
    public void setSifre(String sifre) { this.sifre = sifre; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
