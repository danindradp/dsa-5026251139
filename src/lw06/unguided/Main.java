package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));
        BinarySearchTree pohon = new BinarySearchTree();

        String[] angka = read.nextLine().split(" ");
        for (int i = 0; i < angka.length; i++) {
            pohon.insert(Integer.parseInt(angka[i]));
        }

        int target = read.nextInt();            
        System.out.print("Preorder : ");
        pohon.preOrder();
        System.out.print("Inorder : ");
        pohon.inOrder();
        System.out.print("Postorder : ");
        pohon.postOrder();

        System.out.println("Search " + target + " : " + pohon.search(target));
        System.out.println("Minimum : " + pohon.findMin());
        System.out.println("Maximum : " + pohon.findMax());

        read.close();
    }
}
