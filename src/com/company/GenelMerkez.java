package com.company;

public class GenelMerkez implements IObserver {
    @Override
    public void update(String mesaj) {
        System.out.println("Abone1 e gelen mesaj->" + mesaj);
    }
}
