package com.company;

import java.awt.desktop.SystemSleepEvent;
import java.util.Random;

public class SicaklikAlgilayici {
    ISubject publisher;
    int sicaklik;

    public void sicaklikAlgilayici(){

        Random r = new Random();
        sicaklik = r.nextInt(20)+20;
        //this.publisher = publisher;
        //if (sicaklik > 36)
        //   publisher.notify("Ortam SSıcaklığı İstenilen Değerden Çok Yüksek!");
        System.out.println("Ortamın Sıcaklığı "+ sicaklik + " C");
    }
}
