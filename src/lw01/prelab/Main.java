package lw01.prelab;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Input  : jobs.txt, tiap baris "<TYPE> <ID> <PAGES>"
 *          TYPE = MONO / COLOUR, ID = kode job, PAGES = jumlah halaman
 * Output : satu baris summary() per job, urut sesuai file
 * Steps  :
 * 1. buka jobs.txt lewat getResourceAsStream, bungkus pakai Scanner
 * 2. baca per baris, bikin MonoPrint / ColourPrint sesuai TYPE
 * 3. simpan semua ke List<PrintJob>
 * 4. loop sekali, print summary() tiap job (polymorphism yang milih hitungannya)
 */
public class Main {
    public static void main(String[] args) {
        InputStream aliran = Main.class.getResourceAsStream("jobs.txt");
        if (aliran == null) {
            System.out.println("jobs.txt ga ketemu");
            return;
        }

        List<PrintJob> daftarJob = new ArrayList<>();

        try (Scanner pembaca = new Scanner(aliran)) {
            while (pembaca.hasNext()) {
                String tipe = pembaca.next();
                String idJob = pembaca.next();
                int halaman = pembaca.nextInt();

                if (tipe.equals("MONO")) {
                    daftarJob.add(new MonoPrint(idJob, halaman));
                } else if (tipe.equals("COLOUR")) {
                    daftarJob.add(new ColourPrint(idJob, halaman));
                } else {
                    throw new IllegalArgumentException("tipe ga dikenal: " + tipe);
                }
            }
        }

        // satu loop aja, ga ada instanceof / cast
        for (PrintJob job : daftarJob) {
            System.out.println(job.summary());
        }
    }
}
