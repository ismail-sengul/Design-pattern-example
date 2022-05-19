
package com.mycompany._05180000117_05180000074;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Deneme {

    public static void main(String[] args) {
        try{
            File dosya = new TxtFile("girdi.txt");//".txt" dosyasını okumak için bir TxtFile nesnesi oluşturulur
            dosya.dosyaOku();            //Oluşturulan nesneden dosya okuma methodu çağırılır
            Director root = dosya.getRoot();//Okuma işlemi bittikten sonra baş direktör(root) nesnesi "root" değişkenine atanır
            
            //İSTENİLEN ÇIKTILAR EKRANA YAZDIRILIR
            System.out.println("Mustafa Türksever'in Maaliyeti: "+root.calisanBul("Mustafa").calisanMaaliyeti());
            System.out.println("-----------------------------------------");
            root.calisanBul("Mustafa").emrindeCalisanlariListele(0);
            System.out.println("-----------------------------------------");
            System.out.println("-->Oğuz Demir'in Maaliyeti: "+root.calisanBul("Oguz").calisanMaaliyeti());
            System.out.println("-----------------------------------------");
            root.calisanBul("Oguz").emrindeCalisanlariListele(0);
            System.out.println("-----------------------------------------");
            
            System.out.println("-->Ahmet Egeli'nin Maaliyeti: "+root.calisanBul("Ahmet").calisanMaaliyeti());
            
        }catch(Exception e){
            System.out.println("Hata: "+e.getMessage());
        }
    }
    
}
