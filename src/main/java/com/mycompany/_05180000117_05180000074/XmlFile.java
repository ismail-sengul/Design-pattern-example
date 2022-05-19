
package com.mycompany._05180000117_05180000074;

public class XmlFile implements OtherFiles {//OtherFiles arayüzünü implement eden adapter desene örnek olarak oluşturduğumuz sınıf
    @Override
    public void xmlOku() {//amacımız xml den okumak olmadığı için sadece adapterin çalıştığını göstermek için ekrana mesaj
        System.out.println("Xml dosyası girildi...");   //yazdıran method
    }

    @Override
    public Director xmlGetRoot() {//yine kullanmayacağımız sadece örnek olsun diye oluşturduğumuz adapter tasarımında getRoot
        return new Director();      //methodunun çalışmasında kullanılan method
    }
    
}
