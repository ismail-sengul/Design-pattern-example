package com.mycompany._05180000117_05180000074;

import java.io.BufferedReader;//DOSYA OKUMAK İÇİN GEREKLİ OLAN KÜTÜPHANELER İMPORT EDİLMİŞTİR
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TxtFile implements File {//File dosyasını implement eden txt dosyası okuyan sınıf
    private int listeBoyutu;//txt dosyasındaki bütün satırların sayısını içinde tutacağımız değişken
    private final EmployeeFactory calisanOlusturucu;//çalışan sınıflarını oluşturacağımız factory nesnesi
    private Director root;//baş direktör nesnesini tutan değişken
    private String dosyaAdi;//dosya adını tutan değişken
    
    public TxtFile(String dosyaAdi){//dosya adını parametre olarak alan constructor
        String [] dosyaAdiVeUzantisi = dosyaAdi.split("\\.");//dosya adı ve uzantısı split ile ayrılıp bir dizi içine alındı
        if(dosyaAdiVeUzantisi[dosyaAdiVeUzantisi.length-1].equalsIgnoreCase("txt")){//eğer dosya txt uzantılı ise
            this.dosyaAdi = dosyaAdi;//girilen dosya adı sınıf içindeki dosyaAdi değişkenine atandı
        }else{//Eğer uzantı txt değilse ekrana bir mesaj yazdırılır
            System.out.println("txt Dosyası girilmedi...");
        }
        calisanOlusturucu = new EmployeeFactory();//EmployeeFactory oluşturuldu
        root = new Director();//root oluşturuldu
        listeBoyutu = 0;//liste boyutu 0 olarak ayarlandı
    }
    public void direktoruBulunmayanlariEslestir(EmployeeCollection calisanListe //Verilen txt dosyasında hiyerarşik yapının karışık verilmesi öngörülerek kodun patlamamasını sağlamak için
            ,String[] calisanlarinDirektorleri,int listeBoyutu){ //direktörü bulunamayan çalışanların listesini direktörlerin dizisini ve liste boyutunu parametre olarak alan method
        
        Iterator direktoruBulunmayanDongusu = calisanListe.iteratorOlustur();//direktörü bulunamayan çalışanlar listesi içinde gezinmek için oluşturulan iteratör
        EmployeeCollection calisanListeRec = new EmployeeCollection(listeBoyutu);//bu method içerisindede çalışanı bulunamayan olacağı için recursive bir şekilde işlemlerin yapılmasını
        String[] calisanlarinDirektorleriRec = new String[listeBoyutu]; //sağlamak amacıyla tekrar direktörü bulunmayan çalışanları ve direktörleri içinde tutan liste ve dizi tanımladık
        int direktorlerSayisi = 0;//parametrede girilen direktör dizisi içerisinde gezinmek için oluşturulan integer değişken
        int direktorlerSayisiRec = 0;//bu işlemlerdede bulunamayan çalışanların direktörlerinin dizisi için oluşturulan değişken
        while(direktoruBulunmayanDongusu.nesneVarMi()){//direktörü bulunamayan listesi kontrol edilir
            Employee calisan = direktoruBulunmayanDongusu.sonrakiNesne();//sıradaki çalışan bir değişkene atanır
            if(root.calisanBul(calisanlarinDirektorleri[direktorlerSayisi]) == null){//Eğer çalışanın direktörü bulunamaz ise
                calisanlarinDirektorleriRec[direktorlerSayisiRec++] = calisanlarinDirektorleri[direktorlerSayisi];
                calisanListeRec.calisanEkle(calisan);//recursive dönecek olan liste ve diziye çalışan ve direktör nesneleri atılır
            }else{//Eğer çalışanın direktörü bulunduysa direktörün emrinde çalışanlara eklenir
                root.calisanBul(calisanlarinDirektorleri[direktorlerSayisi]).emrindeCalisanEkle(calisan);
            }
            direktorlerSayisi++;//direktör sayısı tutan değişken düzgün bir şekilde çalışanların eklenmesi için her bir liste geziniminde 1 artar
        }
        if(direktorlerSayisiRec != 0){//Eğer recursive dönecek olan liste içerisinde eleman varsa işlemler recursive olarak tekrarlar
            direktoruBulunmayanlariEslestir(calisanListeRec,calisanlarinDirektorleriRec,direktorlerSayisiRec);
        }
    }
    
    @Override
    public void dosyaOku(){//txt dosyasının okuma işlemini yapan method
        String [] dosyaAdiVeUzantisi = dosyaAdi.split("\\.");//dosya adı ve uzantısı ayrılır
        if(dosyaAdiVeUzantisi[dosyaAdiVeUzantisi.length-1].equalsIgnoreCase("txt")){//eğer dosya txt ise okuma işlemleri başlar
            try{//dosya adı bulunamamasına karşın önlem almak için try catch oluşturulur
                FileInputStream fStream = new FileInputStream(dosyaAdi);//Dosya okuma işlemi için gerekli olan kütüphaneler ve
                FileInputStream fStream2 = new FileInputStream(dosyaAdi);//nesneleri oluşturulur.
                DataInputStream dStream = new DataInputStream(fStream);
                DataInputStream dStream2 = new DataInputStream(fStream2);
                BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream));
                BufferedReader bReader2 = new BufferedReader(new InputStreamReader(dStream2));

                String satir = bReader.readLine();  //txt ilk satırı bir değişkene atanır
                while(satir != null){//eğer satır boş değilse ilk olarak txt satır sayısını liste boyutu değişkenine atma döngüsü başlatılır
                    String [] bilgiler = satir.split(",");//satırdaki bilgiler bir dizide tutulur
                    if(bilgiler.length != 4){//eğer satırda istenilen bilgiler düzgün formda değilse yeni satır okunur ve işlemler
                        satir = bReader.readLine();//atlanır
                        continue;
                    }
                    else if(bilgiler[3].equalsIgnoreCase("Root")){//txt içerisinde baş direktör(Root) bulunduğunda
                        Employee e = calisanOlusturucu.calisanOlustur(bilgiler[0]);//root nesnesi oluşturulur
                        Director direktor = (Director) e;
                        direktor.setAdSoyad(bilgiler[1]);//root nesnesinin bilgileri düzgün bir biçimde ayarlanır
                        direktor.setMaas(Integer.parseInt(bilgiler[2]));
                        root = direktor;//ve en sonunda sınıf içerisinde tanımladığımız root değişkenine nesne atılır
                    }
                    listeBoyutu++;//her bir satır için listeBoyutu değişkeni bir arttırılır
                    satir = bReader.readLine();//işlemler sonunde bir sonraki satır okunup döngü devam eder
                }
                bReader.close();//ilk okuma işlemimiz bittiği için 1. BufferedReader nesnesi kapatılır
                
                satir = bReader2.readLine();//Hiyerarşik olarak çalışan nesnelerini oluşturma işlemi için 2. BufferedReader nesnesi
                                            //okuma ilk satırdan başlatır
                EmployeeCollection direktoruBulunmayanCalisanlar = new EmployeeCollection(listeBoyutu);//direktörü bulunamayan calışanlar ve direktörleri için bir liste ve sınıf
                String [] calisanlarinDirektorleri = new String[listeBoyutu];//tanımlanır.
                int direktorlerSayisi = 0;//çalışanların bulunamayan direktörleri için bir dizi sayacı oluşturulur

                while(satir != null){//txt dosyasındaki verilerin tamamını okuma işlemi için bir while döngüsü başlatılır
                    String [] bilgiler = satir.split(",");//bilgiler bir dizi içerisine düzgün bir şekilde split edilir
                    if(bilgiler.length != 4 || bilgiler[3].equalsIgnoreCase("Root")){//eğer istenilen bilgiler tam olarak verilmediyse veya önceden oluşturduğumuz root
                        satir = bReader2.readLine();            //çalışanının bilgilerini içeren satıra gelindiyse dosyadan bir sonraki satır okunup işlemler atlanır
                        continue;
                    }
                    else{//Eğer bilgiler doğru verilmişse ve root çalışan bilgisi girilmemiş ise işlemler yapılır
                        if(bilgiler[0].equalsIgnoreCase("D")){//eğer dosyadan ilk okunan değer "D" olarak gelirse bir Director oluşturulup işlemler yapılır
                            Employee e = calisanOlusturucu.calisanOlustur(bilgiler[0]);//factory sayesinde nesne oluşturuldu
                            Director direktor = (Director) e;//nesne Director sınıfına downcast edildi
                            direktor.setAdSoyad(bilgiler[1]);//Ad soyad ve maaş bilgileri nesne içerisinde ayarlandı
                            direktor.setMaas(Integer.parseInt(bilgiler[2]));
                            if(root.calisanBul(bilgiler[3]) == null){//Eğer çalışanın direktörü bulunamadıysa direktörü ve çalışan nesnesi gerekli dizi ve listeye eklendi
                                direktoruBulunmayanCalisanlar.calisanEkle(direktor);
                                calisanlarinDirektorleri[direktorlerSayisi++] = bilgiler[3];
                            }else{//Eğer bulunduysa çalışan düzgün bir şekilde direktörünün emrinde çalışan listesine eklendi
                                root.calisanBul(bilgiler[3]).emrindeCalisanEkle(direktor);
                            }
                        }else if(bilgiler[0].equalsIgnoreCase("M")){//Eğer çalışan bir memur ise
                            Employee e = calisanOlusturucu.calisanOlustur(bilgiler[0]);//çalışan nesnesi oluşturulup Officer sınıfına
                            Officer memur = (Officer) e;                //downcast edilir
                            memur.setAdSoyad(bilgiler[1]);//ad soyad ve maaş ayarlanır
                            memur.setMaas(Integer.parseInt(bilgiler[2]));
                            if(root.calisanBul(bilgiler[3]) == null){//Eğer çalışanın direktörü bulunamaz ise çalışan ve direktör önceden 
                                direktoruBulunmayanCalisanlar.calisanEkle(memur);//tanımladığımız çalışan listesi ve direktör dizisine eklenir
                                calisanlarinDirektorleri[direktorlerSayisi++] = bilgiler[3];
                            }else{//Eğer çalışanın direktörü bulunursa emrinde çalışan listesine eklenir
                                root.calisanBul(bilgiler[3]).emrindeCalisanEkle(memur);
                            }
                        }
                    }
                    satir = bReader2.readLine();//Döngü bir sonraki dosya satırından devam eder
                }
                direktoruBulunmayanlariEslestir(direktoruBulunmayanCalisanlar,calisanlarinDirektorleri,listeBoyutu);//Direktörleri bulunamayan çalışanların direktörlerini bulabilmek için üstte oluşturduğumuz recursive çalışan method çağırılır
                bReader2.close();//BufferedReader kapatılır
            }catch(Exception e){//Eğer işlemde bir hata çıkarsa mesaj konsola yazdırılır
                System.out.println("Hata: "+e.getMessage());
            }
        }else{//Eğer dosya txt değilse işlem yapılmadan konsola gerekli mesaj verilir
            System.out.println("Girilen dosya uzantısı .txt değil...");
        } 
    }
    
    @Override
    public Director getRoot(){////Root çalışanı döndüren method
        return root;
    }
    
}
