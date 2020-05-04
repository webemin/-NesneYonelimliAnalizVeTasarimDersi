package com.company;
public class Cihaz {
    public static void main(String[] args) {
        GenelMerkez merkez = new GenelMerkez();
        Publisher p = new Publisher();
        p.attach(merkez);
        Login.getInstance();
    }
}
