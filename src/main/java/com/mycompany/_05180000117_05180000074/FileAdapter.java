package com.mycompany._05180000117_05180000074;


public class FileAdapter implements File {//Ana sınıfın kullandığı File arayüzünü implement eden Adapter sınıfı

    private OtherFiles digerDosyalar;//Adapte edilecek dosyayı değişken olarak atadık
    private String dosyaAdi;//dosya adını değişken olarak aldık
    
    public FileAdapter(String dosyaAdi){//dosya adını değişken olarak alan constructor
        this.dosyaAdi = dosyaAdi;//dosya adı tanımlandı
        String [] dosyaAdiVeUzantisi = dosyaAdi.split("\\."); // dosya adının uzantısı ayrıldı
        if(dosyaAdiVeUzantisi[dosyaAdiVeUzantisi.length-1].equalsIgnoreCase("xml")){//Eğer uzantı xml ise xml nesnesi oluşturuldu
            digerDosyalar = new XmlFile();
        }
    }
    @Override
    public void dosyaOku() {//dosyaOku işleminde gelen dosya adına göre yapılacak okuma işlemi bu methodda yapılmıştır
        String [] dosyaAdiVeUzantisi = dosyaAdi.split("\\.");//dosya uzantısı ve adı bir diziye alındı
        if(dosyaAdiVeUzantisi[dosyaAdiVeUzantisi.length-1].equalsIgnoreCase("xml")){//eğer uzantı xml ise
            digerDosyalar.xmlOku();//xml dosyasının okuma methodu olan xmlOku çalıştırıldı
        }
    }

    @Override
    public Director getRoot() {//root döndürme işlemini gelen dosya adına göre yapan method
        String [] dosyaAdiVeUzantisi = dosyaAdi.split("\\.");
        if(dosyaAdiVeUzantisi[dosyaAdiVeUzantisi.length-1].equalsIgnoreCase("xml")){//eğer dosya adı xml ise
            return digerDosyalar.xmlGetRoot();//xml sınıfındaki root döndürme methodu çağırıldı
        }
        return null;//eğer dosya uzantısını farklı bulursa null döndürür
    }
    
    public OtherFiles getDigerDosyalar() {//digerDosyalar değişkeninin get ve set methodları
        return digerDosyalar;
    }

    public void setDigerDosyalar(OtherFiles digerDosyalar) {
        this.digerDosyalar = digerDosyalar;
    }
    
}
