package lw06.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/lw06/unguided/treenumbers.txt"));

        BinarySearchTree bst = new BinarySearchTree();

        String[] values = sc.nextLine().trim().split("\\s+");
        for (String v : values) {
            bst.insert(Integer.parseInt(v));
        }

        int target = Integer.parseInt(sc.nextLine().trim());
        sc.close();

        System.out.print("Preorder : ");
        bst.preOrder();
        System.out.print("Inorder : ");
        bst.inOrder();
        System.out.print("Postorder : ");
        bst.postOrder();
        System.out.println("Search " + target + " : " + bst.search(target));
        System.out.println("Minimum : " + bst.findMin());
        System.out.println("Maximum : " + bst.findMax());
    }
}
