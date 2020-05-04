package com.company;
import java.util.Scanner;
public class AgArayuzu
{
    private static AgArayuzu instance;
    private AgArayuzu() {
        SicaklikAlgilayici s = new SicaklikAlgilayici();
        Eyleyici e = new Eyleyici();
        Scanner scann = new Scanner(System.in);
        int secim;
        while (true) {
            System.out.println("*****Arayuz*****");
            System.out.println("1- Sıcaklık Görüntüleme\n2- Soğutucuyu Aç\n3- Soğutucuyu Kapat\n4- Çıkış");
            System.out.print("Seçiminiz: ");
            secim = scann.nextInt();
            if (secim == 1)
                s.sicaklikAlgilayici();
            else if (secim == 2)
                e.isOpen(true);
            else if (secim == 3)
                e.isOpen(false);
            else if (secim == 4)
                Login.getInstance();
            else
                System.out.println("Geçerli Bir Giriş Yapmanız Gerekmektedir!");
        }
    }
    public static synchronized AgArayuzu getInstance(){
        if(instance == null)
            instance = new AgArayuzu();
        return instance;
    }
}
