package lw04.unguided;

import java.util.Scanner;
 
public class Main {
 
    static void sortCards(int[] kartu, int passes) {
        for (int i = 0; i < passes; i++) {
            int idxMin = i;
            for (int j = i + 1; j < kartu.length; j++) {
                if (kartu[j] < kartu[idxMin]) {
                    idxMin = j;
                }
            }
            int temp = kartu[i];
            kartu[i] = kartu[idxMin];
            kartu[idxMin] = temp;
        }
    }
 
    static int findCard(int[] kartu, int target) {
        for (int i = 0; i < kartu.length; i++) {
            if (kartu[i] == target) {
                return i;
            }
        }
        return -1;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards.txt"));
 
        int n = sc.nextInt();
        int[] kartu = new int[n];
        for (int i = 0; i < kartu.length; i++) {
            kartu[i] = sc.nextInt();
        }
 
        int m = sc.nextInt();
        int[] tanya = new int[m];
        for (int i = 0; i < tanya.length; i++) {
            tanya[i] = sc.nextInt();
        }
 
        sortCards(kartu, 2);
 
        for (int i = 0; i < kartu.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(kartu[i]);
        }
        System.out.println();

        for (int i = 0; i < tanya.length; i++) {
            int hasil = findCard(kartu, tanya[i]);
            if (hasil == -1) {
                System.out.println(tanya[i] + ": NOT FOUND");
            } else {
                System.out.println(tanya[i] + ": FOUND at index " + hasil);
            }
        }
    }
}