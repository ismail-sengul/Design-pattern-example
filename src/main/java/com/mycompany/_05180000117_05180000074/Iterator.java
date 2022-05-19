
package com.mycompany._05180000117_05180000074;

public interface Iterator {//Collectionlarda oluşturacağımız Iterator arayüzü
    Employee sonrakiNesne();//liste içindeki sonraki nesneyi döndüren method tanımlandı
    Employee simdikiNesne();//simdiki nesneyi döndüren method
    boolean nesneVarMi();//liste içinde nesne olup olmadığını kontrol eden method
}
