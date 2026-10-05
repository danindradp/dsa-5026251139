package lw03.prelab;
 
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
 
    public static void main(String[] args) {
        soal1();
        soal2();
        soal3();
    }
 
    static void soal1() {
        Scanner baca = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new ArrayList<>();
 
        while (baca.hasNext()) {
            String perintah = baca.next();

            if (perintah.equals("ADD")) {
                playlist.add(baca.nextLine());
            } else if (perintah.equals("INSERT")) {
                int indeks = baca.nextInt();
                playlist.add(indeks, baca.nextLine());
            } else if (perintah.equals("REMOVE")) {
                playlist.remove(baca.nextLine());
            }
        }
 
        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ":" + playlist.get(i));
        }
        System.out.println();
    }
 
    static void soal2() {
        Scanner baca = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> peserta = new LinkedHashSet<>();
        int duplikat = 0;
 
        while (baca.hasNext()) {
            String nama = baca.next();
 
            if (!peserta.add(nama)) {
                duplikat++;
            }
        }
 
        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + peserta.size());
        int nomor = 1;
        for (String nama : peserta) {
            System.out.println(nomor + ". " + nama);
            nomor++;
        }
        System.out.println("Duplicate registrations: " + duplikat);
        System.out.println();
    }
 
    static void soal3() {
        Scanner baca = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> stok = new LinkedHashMap<>();
        int gagalJual = 0;
 
        while (baca.hasNext()) {
            String tipe = baca.next();
            String produk = baca.next();
            int jumlah = baca.nextInt();
 
            if (tipe.equals("ADD")) {
                if (stok.containsKey(produk)) {
                    stok.put(produk, stok.get(produk) + jumlah);
                } else {
                    stok.put(produk, jumlah);
                }
            } else if (tipe.equals("SELL")) {
                if (stok.containsKey(produk) && stok.get(produk) >= jumlah) {
                    stok.put(produk, stok.get(produk) - jumlah);
                } else {
                    gagalJual++;
                }
            }
        }
 
        System.out.println("===== Problem 3 =====");
        for (String produk : stok.keySet()) {
            System.out.println(produk + ": " + stok.get(produk));
        }
        System.out.println("Failed sales: " + gagalJual);
    }
}
