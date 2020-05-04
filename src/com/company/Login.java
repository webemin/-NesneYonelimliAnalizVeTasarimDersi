package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Login {
    private static int yanlisGiris;
    private static String kullaniciadi;
    private static String parola;
    private static boolean tekrar;

    private static Login instance;

    private Login() {
        try {   /***** Bağlantı kurulumu *****/
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sogutucu",
                    "postgres", "sa");
            if (conn == null)
                System.out.println("Bağlantı girişimi başarısız!");

            Scanner scanner = new Scanner(System.in);
            Statement stmt = conn.createStatement();
            ResultSet rs;
            String sql;
            if(tekrar)
            {
                System.out.print("Parola: ");
                parola = scanner.next();
            }
            else {
                System.out.print("Kullanıcı Adı: ");
                kullaniciadi = scanner.next();
                sql = "SELECT COUNT(*) FROM Kullanicilar WHERE KullaniciAdi = '"+kullaniciadi+"'";
                rs = stmt.executeQuery(sql);
                rs.next();
                if(rs.getInt("count") != 1){
                    System.out.println("Kullanıcı Bulunamadı");
                    getInstance();
                }
                System.out.print("Parola: ");
                parola = scanner.next();
            }
            sql = "SELECT COUNT(*) FROM Kullanicilar WHERE KullaniciAdi='" + kullaniciadi + "' AND Parola='" + parola + "'";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (rs.getInt("count") == 1)  //kullanıcı adı şifre girilen değerler gibi olursa
                {
                    System.out.println("Doğrulama BAŞARILI!");
                    stmt.close();
                    conn.close();
                    tekrar = false;

                    Person.getInstance(kullaniciadi, "KULLANICI");//Giriş yapan kişiye KULLANICI yetkisi atandı.
                } else {
                    System.out.println("Doğrulama BAŞARISIZ! Şifreyi Tekrar Giriniz!");
                        tekrar = true;
                        if(++yanlisGiris < 3)
                             getInstance();
                        else {
                            String yeniParola;
                            String yeniParolaDogrula;
                            System.out.print("*****Üç Yanlış Giriş Hakkınız Vardı.*****\n*****Yetkili Çağırınız ve Parolanızı Değiştiriniz.*****" +
                                    "\n*****Yetkiliyi Çağırmak için 1 Tuşlayınız..*****\nSeçim: ");
                            if (scanner.nextInt() == 1){
                                System.out.println("*****Yetkili İle İletişime Geçildi*****\nYeni Parolanız: ");
                                yeniParola = scanner.next();
                                System.out.println("Yeni Parola Tekrar: ");
                                yeniParolaDogrula = scanner.next();

                                if (yeniParola.equals(yeniParolaDogrula)) {
                                    System.out.println("*****Parolanız Değiştiriliyor.*****");
                                    sql = "UPDATE kullanicilar SET parola = '"+ yeniParola +"' where kullaniciadi = '"+kullaniciadi+"'";
                                    stmt.executeUpdate(sql);
                                    System.out.println("*****Parolanız değiştirildi. Tekrar giriş yapınız.*****");
                                    yanlisGiris = 0;
                                    getInstance();
                                }else{
                                    System.out.println("*****Parolalar Eşleşmedi..*****");
                                    yanlisGiris = 0;
                                    getInstance();
                                }
                            } else {
                                sql = "DELETE from kullanicilar where kullaniciadi = '" + kullaniciadi + "'";
                                stmt.executeUpdate(sql);
                                System.out.println("*****Yetkili Çağırmadınız. Hesabınız Silindi.***** \n*****Tekrar Üye Olma İçin Müşteri Hizmetleriyle İletişime Geçiniz.*****");
                                tekrar = false;
                                yanlisGiris = 0;
                                getInstance();
                            }
                        }
                }
            }
            conn.close();
            stmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized Login getInstance(){
        if(instance == null)
            instance = new Login();
        return instance;
    }
}
