
package com.mycompany._05180000117_05180000074;

public class Officer extends Employee{//Employee abstract sınıfını implement eden Officer sınıfı

    public Officer(String adSoyad, int maas) { //ad soyad ve maaş bilgilerini parametre olarak alan constructor method
        super(adSoyad, maas);
    }
    public Officer() { //parametre almayan constructor
        super();
    }
    public Officer(String adSoyad){//sadece ad soyad bilgisini parametre olarak alan constructor
        super(adSoyad);
    }
    @Override
    public int calisanMaaliyeti() {//memur çalışan maaliyeti methodu sadece kendi maaşını çevirecek şekilde
        return this.getMaas();      //oluşturuldu.
    }
    
    @Override
    public String toString(){//Memur bilgilerini gösteren toString methodu
        return "Memurun Adı: "+this.getAdSoyad()+"  Memurun Maaşı: "+this.getMaas();
    }

    @Override
    public void emrindeCalisanlariListele(int tabSayisi) {
        //Memurun altında çalışan herhangi bir çalışan olmayacağından bu kısım boş bırakılması gerekir.
    }

    @Override
    public void emrindeCalisanEkle(Employee calisan) {
        //Memurun altında çalışan olmayacağından bu method boş bırakılması gerekir.
    }
    
    

    
    
}
