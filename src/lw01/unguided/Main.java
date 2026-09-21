package lw01.unguided;

import java.util.Scanner;

/*
 * Input  : rentals.txt
 *          baris pertama = jumlah data, baris berikutnya "<TYPE> <ID> <DAYS> <UNITS>"
 *          TYPE = LAPTOP / PROJECTOR
 * Output : satu baris summary per rental, urut sesuai file
 * Steps  :
 * 1. buka rentals.txt lewat Scanner + getResourceAsStream
 * 2. baca jumlah data, siapin Rental[] sebesar itu
 * 3. baca tiap baris, bikin LaptopRental / ProjectorRental sesuai TYPE
 *    (units disimpen terpisah di daftarUnit, karena constructor Rental cuma terima id sama days)
 * 4. loop sekali, print summary tiap rental (polymorphism yang milih hitungannya)
 */

public class Main {
    public static void main(String[] args) {
        Scanner pembaca = new Scanner(Main.class.getResourceAsStream("rentals.txt"));

        int jumlahData = pembaca.nextInt();
        Rental[] daftarRental = new Rental[jumlahData];
        int[] daftarUnit = new int[jumlahData];

        for (int i = 0; i < daftarRental.length; i++) {
            String tipe = pembaca.next();
            String idRental = pembaca.next();
            int hari = pembaca.nextInt();
            int unit = pembaca.nextInt();

            if (tipe.equals("LAPTOP")) {
                daftarRental[i] = new LaptopRental(idRental, hari);
            } else if (tipe.equals("PROJECTOR")) {
                daftarRental[i] = new ProjectorRental(idRental, hari);
            } else {
                throw new IllegalArgumentException("tipe ga dikenal: " + tipe);
            }
            daftarUnit[i] = unit;
        }

        pembaca.close();

        for (int i = 0; i < daftarRental.length; i++) {
            System.out.println(daftarRental[i].summary(daftarUnit[i]));
        }
    }
}
