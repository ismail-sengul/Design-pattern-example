
package com.mycompany._05180000117_05180000074;

public class EmployeeIterator implements Iterator {//Iterator arayüzünü implement eden EmployeeIterator sınıfı
    int simdikiPozisyon = 0;//Listede düzgün gezinebilmek için bir pozisyon tanımlanır ve ilk başta 0 olarak atanır
    EmployeeCollection calisanListesi;//iteratör yapısının çalışacağı listeyi tutar
    
    public EmployeeIterator(EmployeeCollection calisanListesi){//listeyi parametre olarak alan constructor
        this.calisanListesi = calisanListesi;
    }
    @Override
    public Employee sonrakiNesne() {//sonraki nesneyi döndürüp pozisyonu bir arttıran method
      if(this.nesneVarMi()){
          return calisanListesi.getCalisanListesi()[simdikiPozisyon++];
      }
      return null;
    }

    @Override
    public Employee simdikiNesne() {//simdiki pozisyonu döndüren method
        return calisanListesi.getCalisanListesi()[simdikiPozisyon];
    }

    @Override
    public boolean nesneVarMi() {//nesnenin olup olmadığını kontrol eden method
        if(simdikiPozisyon >= calisanListesi.getSize() || 
                calisanListesi.getCalisanListesi()[simdikiPozisyon] == null){//Eğer liste içerisinde bir eleman kalmadıysa
            return false;//false döner
        }
        return true;//Eğer sınıf içerisinde hala eleman varsa true döner
    }
    
}
