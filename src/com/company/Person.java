package com.company;
public class Person {
    private static Person instance;
    private Person(String kullaniciad, String yetki) {
        if (yetki.equals("KULLANICI")) {
            System.out.println("***** "+ kullaniciad +" Kullanıcısı Sisteme Giriş Yaptı.*****");
            AgArayuzu.getInstance();
        }else{
            System.out.println("*****Kullanıcı Yetkisine Sahip Değilsiniz*****");
        }
    }
    public static synchronized Person getInstance(String kullaniciad, String yetki){
        if(instance == null)
            instance = new Person(kullaniciad, yetki);
        return instance;
    }
}