package lw03.unguided;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner baca = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        Map<String, Integer> enrollment = new HashMap<>();
        List<String> urutanMatkul = new ArrayList<>();
        List<String> hasilCheck = new ArrayList<>();
        int ditolak = 0;

        while (baca.hasNext()) {
            String operasi = baca.next();
            String kode = baca.next();

            if (operasi.equals("CHECK")) {
                if (enrollment.containsKey(kode)) {
                    hasilCheck.add(kode + ": " + enrollment.get(kode) + " students");
                } else {
                    hasilCheck.add(kode + ": Not found");
                }
            } else {
                int jumlah = baca.nextInt();

                if (jumlah <= 0) {
                    ditolak++;
                } else if (operasi.equals("REGISTER")) {
                    if (enrollment.containsKey(kode)) {
                        enrollment.put(kode, enrollment.get(kode) + jumlah);
                    } else {
                        enrollment.put(kode, jumlah);
                        urutanMatkul.add(kode);
                    }
                } else if (operasi.equals("WITHDRAW")) {
                    if (enrollment.containsKey(kode) && enrollment.get(kode) >= jumlah) {
                        enrollment.put(kode, enrollment.get(kode) - jumlah);
                    } else {
                        ditolak++;
                    }
                }
            }
        }

        System.out.println("===== Enrollment Checks =====");
        for (int i = 0; i < hasilCheck.size(); i++) {
            System.out.println(hasilCheck.get(i));
        }

        System.out.println("===== Final Enrollment =====");
        for (int i = 0; i < urutanMatkul.size(); i++) {
            String kode = urutanMatkul.get(i);
            System.out.println(kode + ": " + enrollment.get(kode) + " students");
        }

        System.out.println("Rejected operations: " + ditolak);
    }
}