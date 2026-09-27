package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        LinkedList<String[]> daftarTransaksi = new LinkedList<>();
        while (sc.hasNextLine()) {
            String baris = sc.nextLine().trim();
            if (baris.isEmpty()) continue; 
            String[] bagian = baris.split("\\s+");
            daftarTransaksi.add(bagian);
        }

        LinkedList<String[]> daftarNasabah = new LinkedList<>();
        for (String[] t : daftarTransaksi) {
            String nama = t[0];
            boolean sudahAda = false;
            for (String[] n : daftarNasabah) {
                if (n[0].equals(nama)) {
                    sudahAda = true;
                    break;
                }
            }
            if (!sudahAda) {
                daftarNasabah.add(new String[]{nama, "0"});
            }
        }

        Queue<String[]> antreanTransaksi = new LinkedList<>(daftarTransaksi);
        Stack<String[]> transaksiGagal = new Stack<>();

        while (!antreanTransaksi.isEmpty()) {
            String[] t = antreanTransaksi.poll();
            String nama = t[0];
            String tipe = t[1];
            int jumlah = Integer.parseInt(t[2]);

            String[] data = null;
            for (String[] n : daftarNasabah) {
                if (n[0].equals(nama)) {
                    data = n;
                    break;
                }
            }

            int saldo = Integer.parseInt(data[1]);

            if (tipe.equals("DEPOSIT")) {
                saldo += jumlah;
                data[1] = String.valueOf(saldo);
            } else {
                if (jumlah > saldo) {
                    transaksiGagal.push(t); 
                } else {
                    saldo -= jumlah;
                    data[1] = String.valueOf(saldo);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] n : daftarNasabah) {
            System.out.println(n[0] + " : " + n[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!transaksiGagal.isEmpty()) {
            String[] t = transaksiGagal.pop();
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }
    }
}
