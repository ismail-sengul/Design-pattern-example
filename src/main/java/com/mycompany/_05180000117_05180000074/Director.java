package com.mycompany._05180000117_05180000074;

public class Director extends Employee {//Director classı Employee classını extend eder
    
    public Director(){//Parametre almayan constructor
        super();
    }
    public Director(String adSoyad,int maas,int listeBoyutu){//Parametre olarak ad soyad, maaş ve emrinde çalışanların listesinin
        super(adSoyad,maas,listeBoyutu);                    // liste boyutunu alan constructor
    }
    public Director(String adSoyad,int maas){//Parametre olarak ad soyad ve maaş alan constructor
        super(adSoyad,maas);
    }
    public Director(int listeBoyutu){//Parametre olarak sadece liste boyutunu alan constructor
        super(listeBoyutu);
    }
    public Director(String adSoyad){//Parametre olarak sadece ad soyad alan constructor
        super(adSoyad);
    }

    @Override
    public int calisanMaaliyeti() {//calisan maaliyetini hesaplayan method(Employee den override edilir)
        int maaliyet = 0;//döndüreceğimiz maaliyeti tutan değişken oluşturulur
        maaliyet += this.getMaas();//direktör maaşı maaliyet değişkenine eklenir
        Iterator hiyerarsiDongusu = this.getEmrindeCalisanListesi().iteratorOlustur();//Hiyerarşik yapıyı gezip çalışanlarının maaşını döndürülecek maaliyete katmak için Iterator nesnesi çağırılır
        while(hiyerarsiDongusu.nesneVarMi()){//hiyerarşik yapıdaki çalışanlar kontrol edilir
            Employee calisan = hiyerarsiDongusu.sonrakiNesne();
            maaliyet+= calisan.calisanMaaliyeti();//her bir çalışanın maaliyeti döndürülecek değişkene eklenir
        }
        return maaliyet;//maaliyet döndürülür
    }
    
    @Override
    public void emrindeCalisanEkle(Employee calisan) {//emrinde çalışan ekleyen method
        this.getEmrindeCalisanListesi().calisanEkle(calisan);//emrinde çalışan collectionuna bir eleman eklenir
    }
    
    public Employee calisanBul(String ad){ //Bir çalışanı hiyerarşik yapıda bulabilmek için kullanılan method(Parametre olarak kişi adını alır)
        String [] adSoyadAyriTutulanListe = this.getAdSoyad().split(" ");//Ad soyadı ayırıp bir dizi içerisine aldık
        if(adSoyadAyriTutulanListe[0].equalsIgnoreCase(ad)){//Root nesneden başlattığımız düşünülürse eğer istenen çalışan 
            return this;                                    //root ise root nesnesini döndürür
        }
        Iterator calisanBulmaDongusu = this.getEmrindeCalisanListesi().iteratorOlustur();//Hiyerarşik yapıda gezmek için oluşturulan iteratör
        while(calisanBulmaDongusu.nesneVarMi()){//emrinde çalışan listesinde nesne olup olmadığı kontrol edilir
            Employee calisan = calisanBulmaDongusu.sonrakiNesne();//Emrinde çalışan listesindeki elemanlar kontrol edilir
            adSoyadAyriTutulanListe = calisan.getAdSoyad().split(" ");//Ad soyad diziye alınır
            if(adSoyadAyriTutulanListe[0].equalsIgnoreCase(ad)){//Eğer çalışan bulunursa döndürülür
                return calisan;
            }
            else if(calisan instanceof Director){//Eğer çalışan bir direktör ise hiyerarşik yapıyı tam gezebilmek için bir recursive olarak direktörün emrinde çalışanlarda kontrol edilir
                Director direktor = (Director) calisan;//Directorde bulunan calisanBul methodu için bulunan calisan director sınıfına downcast edilir
                Employee e = direktor.calisanBul(ad);//recursive fonksiyon çağırılır
                if(e != null){//Eğer eleman bulunursa döndürülür
                    return e;
                }
            }
        }
        return null;//Eğer öyle bir eleman bulunamaz ise null döndürülür
    }
    
    @Override
    public void emrindeCalisanlariListele(int tabSayisi) {//Emrinde çalışanları listeleyen method. Düzgün bir şekilde çıktı vermesi için int tipinde tab sayısı parametre olarak alınır
        String tab = "";//Düzgün çıktı verebilmek için atılacak tablar bir stringde kontrol edilir
        int tabKontrol = 0;//tab değişkenine tab atabilmek için oluşturulan kontrol değişkeni
        while(tabKontrol < tabSayisi){//tab sayisi kadar tab stringi değişkenin içine atılır
            tab += "\t";
            tabKontrol++;//her tab atmada kontrol değişkeni bir artar
        }
        System.out.println(tab+"D("+this.getAdSoyad()+","+this.getMaas()+")");//Direktörün bilgisi o andaki tab ile beraber yazdırılır.
        tabSayisi++;//Direktörün altındaki elemanların düzgün bir şekilde çıkması için tab sayısı arttırılır ve
        tab += "\t";//tab stringine bir tab daha eklenir
        Iterator hiyerarsiDongusu = this.getEmrindeCalisanListesi().iteratorOlustur();//Hiyerarşik yapıda dönmek için iteratör kullanılır
        while(hiyerarsiDongusu.nesneVarMi()){//listedeki nesneler kontrol edilir
            Employee calisan = hiyerarsiDongusu.sonrakiNesne();//çalışanlar bir değişkene atanır
            if(calisan instanceof Director){//Eğer listede bir director görülürse onunda altında çalışanları yazdırmak için recursive olarak method çağırılır
                calisan.emrindeCalisanlariListele(tabSayisi);
            }else{//Eğer memursa direk bilgiler yazdırılır
                System.out.println(tab+"M("+calisan.getAdSoyad()+","+calisan.getMaas()+")");
            }
        }
        tabSayisi--;//Her bir direktörün altındakiler yazıldıktan sonra bir üst direktörün diğer 
    }               //emrinde çalışanların düzgün bir şekilde çıkabilmesi için tabsayısı bir azaltılır
    
    @Override
    public String toString(){//Direktör bilgilerini yazdıran toString methodu
        return "Direktörün Adı Soyadı: "+this.getAdSoyad()+"  Direktörün Maaşı: "+this.getMaas();
    }

    

    
    
}
