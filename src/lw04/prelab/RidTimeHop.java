package lw04.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RidTimeHop {

    public static void main(String[] args) throws FileNotFoundException {
        //Baca input dari branches.txt
        Scanner sc = new Scanner(new File("src/lw04/prelab/branches.txt"));

        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        //Urutkan dengan Insertion Sort, tepat 3 pass (descending)
        insertionSort(a, 3);

        //Tampilkan array setelah 3 pass
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(a[i]);
        }
        System.out.println();

        //Cari setiap target dengan Linear Search
        for (int i = 0; i < m; i++) {
            int idx = linearSearch(a, targets[i]);
            if (idx != -1) {
                System.out.println(targets[i] + ": FOUND at index " + idx);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }

    // Insertion sort descending, berhenti setelah 'passes' pass
    static void insertionSort(int[] a, int passes) {
        for (int i = 1; i <= passes; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] < key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // Linear search: kembalikan index, atau -1 jika tidak ketemu
    static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }
}