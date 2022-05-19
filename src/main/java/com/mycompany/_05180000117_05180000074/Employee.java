
package com.mycompany._05180000117_05180000074;

public abstract class Employee {//Çalışanların bilgilerini tutan soyut sınıf
    private String adSoyad;//ad soyad tutan değişken
    private int maas;// maaş tutan değişken
    private EmployeeCollection emrindeCalisanListesi; // Emrinde çalışanları tutan kendi yazdığımız collection
    
    public Employee(){//parametresiz constructor
        this.maas = 0;
        this.adSoyad = "";
        this.emrindeCalisanListesi = new EmployeeCollection(5); //Eğer liste boyutu verilmezse önemsiz olduğu düşünülerek sadece 5 alanlı liste oluşturulur
    }
    public Employee(String adSoyad , int maas,int listeBoyutu){//ad soyad maaş ve liste boyutu alan constructor
        this.adSoyad = adSoyad;
        this.maas = maas;
        this.emrindeCalisanListesi = new EmployeeCollection(listeBoyutu);
    }
    public Employee(String adSoyad , int maas){//adsoyad ve maaş bilgilerini alan constructor
        this.adSoyad = adSoyad;
        this.maas = maas;
        this.emrindeCalisanListesi = new EmployeeCollection(5);
    }
    public Employee(String adSoyad){//adsoyad bilgilerini alan constructor
        this.maas = 0;
        this.adSoyad = adSoyad;
        this.emrindeCalisanListesi = new EmployeeCollection(5);
    }
    public Employee(int listeBoyutu){//liste boyutunu parametre olarak alan constructor
        this.maas = 0;
        this.adSoyad = "";
        this.emrindeCalisanListesi = new EmployeeCollection(listeBoyutu);
    }
    
    
    public String getAdSoyad() {//ad soyad döndüren get methodu
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {//ad soyad değiştiren set methodu
        this.adSoyad = adSoyad;
    }

    public int getMaas() { //maaş getter ve setter methodları
        return maas;
    }

    public void setMaas(int maas) {
        this.maas = maas;
    }
    public EmployeeCollection getEmrindeCalisanListesi() { //Emrinde çalışan listesi döndüren get methodu
        return emrindeCalisanListesi;
    }

    public void setEmrindeCalisanListesi(EmployeeCollection emrindeCalisanListesi) {
        this.emrindeCalisanListesi = emrindeCalisanListesi;
    }
    //Her bir alt classda farklı yazılacak olan sınıflar abstract olarak tanımlanmıştır.
    public abstract int calisanMaaliyeti();
    public abstract void emrindeCalisanlariListele(int tabSayisi);
    public abstract void emrindeCalisanEkle(Employee calisan);
}
