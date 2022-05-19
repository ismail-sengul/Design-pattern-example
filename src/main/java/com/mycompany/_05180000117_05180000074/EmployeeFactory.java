package com.mycompany._05180000117_05180000074;

public class EmployeeFactory {
    
    public Employee calisanOlustur(String calisanTipi){
        if(calisanTipi == null){
            return null;
        }
        if(calisanTipi.equalsIgnoreCase("D")){
            return new Director();
        }else if(calisanTipi.equalsIgnoreCase("M")){
            return new Officer();
        }
        return null;
    }
    
}
