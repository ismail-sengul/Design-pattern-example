/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany._05180000117_05180000074.Employee;//Gerekli sınıflar ve kütüphaneler import edilmiştir
import com.mycompany._05180000117_05180000074.Director;
import com.mycompany._05180000117_05180000074.Officer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author issen
 */
public class DirectorTest {
    
    public DirectorTest() {
    }   
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    @Test
    public void testDirector(){//Director sınıfının oluşturulup oluşturulmadığı test edilmiştir
        Director direktor = new Director("Ali Veli",500);
        assertNotNull(direktor,"Director classı oluşturulmadı...");//Eğer Director sınıfı oluşturulmazsa test sonucunda ekrana mesaj
    }                                                               //girilir
    
    @Test
    public void testDirectorMaas(){//Direktörün maaşının doğru çıkıp çıkmadığını test eden method
        Director direktor = new Director("Ali",500);
        assertEquals(direktor.getMaas(),500,"Maaş yanlış çıktı...");//Eğer doğru çıkmazsa ekrana hata mesajı yollar
    }
    
    @Test
    public void testDirectorAdSoyad(){//Direktörün ad soyadının doğru yazdırılıp yazdırılmadığını test eden method
        Director direktor = new Director("Ali Veli",400);
        assertEquals(direktor.getAdSoyad(),"Ali Veli","Ad Soyad eşleşmedi...");
    }
    
    @Test
    public void testSadeceDirectorMaaliyet(){//direktörün emrinde çalışan olmadan calisanMaaliyeti methodunun doğru çıkıp çıkmadığını
        Director direktor = new Director("Ali",500);        //test eden method
        assertEquals(direktor.calisanMaaliyeti(),500,"Direktör maaliyeti yanlış hesaplandı...");
    }
    @Test
    public void testEmrindeCalisanlarlaDirectorMaaliyet(){//direktörün emrinde çalışanlar olması halinde calisanMaaliyeti methodunun
        Director direktor = new Director("Ali",500);        //doğru hesaplanıp hesaplanmadığını kontrol eden method
        Employee e1 = new Director("Veli",400);
        Employee e2 = new Officer("Ahmet",300);
        direktor.getEmrindeCalisanListesi().calisanEkle(e1);
        e1.getEmrindeCalisanListesi().calisanEkle(e2);
        assertEquals(direktor.calisanMaaliyeti(),1200,"Direktör maaliyeti yanlış hesaplandı...");
    }
    @Test
    public void testCalisanBul(){//calisanBul methodunun doğru çalışıp çalışmadığını test eden method
        Director direktor = new Director("Ali",800);
        Employee e3 = new Director("Oguz",700);
        Employee e1 = new Officer("İsmail Şengül",400);
        Employee e2 = new Officer("Muhammed Emin Karslı",500);
        Employee e4 = new Director("Veli",600);
        direktor.getEmrindeCalisanListesi().calisanEkle(e3);
        e3.getEmrindeCalisanListesi().calisanEkle(e4);
        e4.getEmrindeCalisanListesi().calisanEkle(e1);
        e4.getEmrindeCalisanListesi().calisanEkle(e2);
        assertEquals(direktor.calisanBul("İsmail").getAdSoyad(),"İsmail Şengül","Çalışanı Bulamadı...");
    }    
}
