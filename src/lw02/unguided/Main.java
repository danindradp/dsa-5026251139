package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static  void main(String[] args) {
        final int MAX_PINJAM = 2;

        Scanner sc = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        LinkedList<String[]> daftarRequest = new LinkedList<>();
        LinkedList<String[]> daftarBuku = new LinkedList<>();
        LinkedList<String[]> daftarMember = new LinkedList<>();

        daftarBuku.add(new String[] { "Kalkulus", "2"});
        daftarBuku.add(new String[] { "Fisika", "1"});
        daftarBuku.add(new String[] { "Statistika", "2"});

        while (sc.hasNext()) {
            String nama = sc.next();
            String judul = sc.next();
            daftarRequest.add(new String[] { nama, judul });

            boolean sudahAda = false;
            for (int i = 0; i < daftarMember.size(); i++) {
                if (daftarMember.get(i)[0].equals(nama)) {
                    sudahAda = true;
                }
            }
            if (!sudahAda) {
                daftarMember.add(new String[] { nama, "0" });
            }
        }
        sc.close();

        Queue<String[]> antrian = new LinkedList<>();
        for (int i = 0; i < daftarRequest.size(); i++) {
            antrian.add(daftarRequest.get(i));
        }

        LinkedList<String[]> requestSukses = new LinkedList<>();
        Stack<String[]> requestGagal = new Stack<>();

        while (!antrian.isEmpty()) {
            String[] request = antrian.poll();

            int idxBuku = -1;
            for (int i = 0; i < daftarBuku.size(); i++) {
                if (daftarBuku.get(i)[0].equals(request[1])) {
                    idxBuku = i;
                }
            }
            
            int idxMember = -1;
            for (int i = 0; i < daftarMember.size(); i++) {
                if (daftarMember.get(i)[0].equals(request[0])) {
                    idxMember = i;
                }
            }

            boolean stokAda = false;
            if (idxBuku != -1) {
                stokAda = Integer.parseInt(daftarBuku.get(idxBuku)[1]) > 0;
            }
            boolean belumLimit = Integer.parseInt(daftarMember.get(idxMember)[1]) < MAX_PINJAM;

            if (stokAda && belumLimit) {
                int stokBaru = Integer.parseInt(daftarBuku.get(idxBuku)[1]) - 1;
                daftarBuku.get(idxBuku)[1] = String.valueOf(stokBaru);

                int pinjamBaru = Integer.parseInt(daftarMember.get(idxMember)[1]) + 1;
                daftarMember.get(idxMember)[1] = String.valueOf(pinjamBaru);

                requestSukses.add(request);
            } else {
                requestGagal.push(request);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (int i = 0; i < requestSukses.size(); i++) {
            System.out.println(requestSukses.get(i)[0] + " " + requestSukses.get(i)[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Book Stock ===");
        for (int i = 0; i < daftarBuku.size(); i++) {
            System.out.println(daftarBuku.get(i)[0] + " : " + daftarBuku.get(i)[1]);
        }

        System.out.println();
        System.out.println("=== Failed Requests ===");
        while (!requestGagal.isEmpty()) {
            String[] gagal = requestGagal.pop();
            System.out.println(gagal[0] + " " + gagal[1]);
        }
    }
}
