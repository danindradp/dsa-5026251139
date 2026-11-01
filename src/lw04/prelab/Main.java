import java.util.Scanner;

public class Main {

    static void insertionSort(int[] data, int jumlahPass) {
        for (int i = 1; i <= jumlahPass; i++) {
            int kunci = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] < kunci) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = kunci;
        }
    }

    static int cari(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int n = sc.nextInt();
        int[] cabang = new int[n];
        for (int i = 0; i < cabang.length; i++) {
            cabang[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] target = new int[m];
        for (int i = 0; i < target.length; i++) {
            target[i] = sc.nextInt();
        }

        insertionSort(cabang, 3);
        for (int i = 0; i < cabang.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(cabang[i]);
        }
        System.out.println();

        for (int i = 0; i < target.length; i++) {
            int idx = cari(cabang, target[i]);
            if (idx != -1) {
                System.out.println(target[i] + ": FOUND at index " + idx);
            } else {
                System.out.println(target[i] + ": NOT FOUND");
            }
        }
    }
}
