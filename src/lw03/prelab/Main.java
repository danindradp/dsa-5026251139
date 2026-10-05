import java.util.*;

public class Main {

    public static void main(String[] args) {
        soal1();
        soal2();
        soal3();
    }

    static void soal1() {
        Scanner baca = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new ArrayList<>();

        while (baca.hasNextLine()) {
            String baris = baca.nextLine().trim();
            if (baris.isEmpty()) continue;

            if (baris.startsWith("ADD")) {
                String[] bagian = baris.split(" ", 2);
                playlist.add(bagian[1]);
            } else if (baris.startsWith("INSERT")) {
                String[] bagian = baris.split(" ", 3);
                int indeks = Integer.parseInt(bagian[1]);
                playlist.add(indeks, bagian[2]);
            } else if (baris.startsWith("REMOVE")) {
                String[] bagian = baris.split(" ", 2);
                playlist.remove(bagian[1]);
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
        System.out.println();
    }

    static void soal2() {
        Scanner baca = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> peserta = new LinkedHashSet<>();
        int duplikat = 0;

        while (baca.hasNextLine()) {
            String nama = baca.nextLine().trim();
            if (nama.isEmpty()) continue;

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

        while (baca.hasNextLine()) {
            String baris = baca.nextLine().trim();
            if (baris.isEmpty()) continue;

            String[] bagian = baris.split(" ");
            String tipe = bagian[0];
            String produk = bagian[1];
            int jumlah = Integer.parseInt(bagian[2]);

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
