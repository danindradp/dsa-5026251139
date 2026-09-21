package lw01.unguided;

import java.util.Scanner;

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
