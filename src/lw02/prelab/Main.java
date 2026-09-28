package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        LinkedList<String[]> daftarTransaksi = new LinkedList<>();
        LinkedList<String[]> daftarNasabah = new LinkedList<>();

        while (sc.hasNext()) {
            String nama = sc.next();
            String tipe = sc.next();
            String jumlah = sc.next();
            daftarTransaksi.add(new String[] { nama, tipe, jumlah });

            boolean sudahAda = false;
            for (int i = 0; i < daftarNasabah.size(); i++) {
                if (daftarNasabah.get(i)[0].equals(nama)) {
                    sudahAda = true;
                }
            }
            if (!sudahAda) {
                daftarNasabah.add(new String[] { nama, "0" });
            }
        }
        sc.close();

        Queue<String[]> antrian = new LinkedList<>();
        for (int i = 0; i < daftarTransaksi.size(); i++) {
            antrian.add(daftarTransaksi.get(i));
        }

        Stack<String[]> transaksiGagal = new Stack<>();

        while (!antrian.isEmpty()) {
            String[] transaksi = antrian.poll();
            int jumlah = Integer.parseInt(transaksi[2]);

            int idxNasabah = -1;
            for (int i = 0; i < daftarNasabah.size(); i++) {
                if (daftarNasabah.get(i)[0].equals(transaksi[0])) {
                    idxNasabah = i;
                }
            }

            int saldo = Integer.parseInt(daftarNasabah.get(idxNasabah)[1]);

            if (transaksi[1].equals("DEPOSIT")) {
                daftarNasabah.get(idxNasabah)[1] = String.valueOf(saldo + jumlah);
            } else {
                if (jumlah > saldo) {
                    transaksiGagal.push(transaksi);
                } else {
                    daftarNasabah.get(idxNasabah)[1] = String.valueOf(saldo - jumlah);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (int i = 0; i < daftarNasabah.size(); i++) {
            System.out.println(daftarNasabah.get(i)[0] + " : " + daftarNasabah.get(i)[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!transaksiGagal.isEmpty()) {
            String[] gagal = transaksiGagal.pop();
            System.out.println(gagal[0] + " " + gagal[1] + " " + gagal[2]);
        }
    }
}
