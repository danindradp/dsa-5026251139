package lw01.prelab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner pembaca = new Scanner(Main.class.getResourceAsStream("jobs.txt"));

        List<PrintJob> daftarJob = new ArrayList<>();

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

        pembaca.close();

        for (PrintJob job : daftarJob) {
            System.out.println(job.summary());
        }
    }
}
