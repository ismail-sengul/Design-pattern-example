package com.mycompany._05180000117_05180000074;

public class EmployeeCollection implements Collection {//Collection arayüzünü implement eden çalışan listesi tutan sınıf
    private int size = 0;//Çalışan listesinin içindeki en son girilen nesnenin indexini tutan değişken. Bu sayede listedeki eleman sayısınıda tutar
    private int listeBoyutu;//listeyi düzgün oluşturmak için tanımlanan liste boyutu tutan değişken
    private Employee [] calisanListesi;//bir çalışan dizisi
    
    public EmployeeCollection(int listeBoyutu){//liste boyutunu parametre olarak alan constructor
        this.listeBoyutu = listeBoyutu;
        calisanListesi = new Employee[listeBoyutu];
    } 
    
    public void calisanEkle(Employee calisan){//dizi içerisine çalışan ekleme işlemi yapan method
        if(listeBoyutu <= size){//Eğer size değişkeni liste boyutundan büyük yada eşitse liste dolu diye ekrana yazdırılır
            System.out.println("Liste dolu...");
        }else{//Eğer liste dolmamışsa calışan listeye eklenir
            calisanListesi[size++] = calisan;
        }
    }
    
    public Employee calisanGetir(int index){//belirli indexteki çalışanı getiren method.
        return calisanListesi[index];
    }
    
    @Override
    public Iterator iteratorOlustur() {//Collection sınıfından override edilen iteratör oluşturan method
       return new EmployeeIterator(this);//Iterator olarak EmployeeIterator oluşturup şu anki nesneyi parametre olarak atar
    }
    
    public int getSize(){//liste içindekilerin sayısını döndüren method
        return size;
    }
    public Employee[] getCalisanListesi() {//calışan listesinin get ve set methodları
        return calisanListesi;
    }

    public void setCalisanListesi(Employee[] calisanListesi) {
        this.calisanListesi = calisanListesi;
    }     
}
