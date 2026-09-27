import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BankTransactionProcessor {

    public static void main(String[] args) {
        // 1. LinkedList untuk menyimpan semua transaksi dari file
        LinkedList<String[]> transactions = new LinkedList<>();

        // 2. LinkedList lain untuk menyimpan data customer (nama, saldo)
        LinkedList<String[]> customers = new LinkedList<>();

        // --- Membaca file transactions.txt ---
        try (Scanner fileScanner = new Scanner(new File("transactions.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+"); // {nama, tipe, jumlah}
                transactions.add(parts);

                String name = parts[0];
                boolean sudahAda = false;
                for (String[] c : customers) {
                    if (c[0].equals(name)) {
                        sudahAda = true;
                        break;
                    }
                }
                if (!sudahAda) {
                    // Customer baru, saldo awal 0
                    customers.add(new String[]{name, "0"});
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt tidak ditemukan.");
            return;
        }

        // 3. Pindahkan semua transaksi ke Queue (FIFO)
        Queue<String[]> queue = new LinkedList<>();
        queue.addAll(transactions);

        // 4. Stack untuk menyimpan transaksi WITHDRAW yang gagal
        Stack<String[]> failedTransactions = new Stack<>();

        // --- Proses transaksi dalam urutan FIFO ---
        while (!queue.isEmpty()) {
            String[] trx = queue.poll(); // ambil transaksi terdepan
            String name = trx[0];
            String type = trx[1];
            int amount = Integer.parseInt(trx[2]);

            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    int balance = Integer.parseInt(c[1]);

                    if (type.equalsIgnoreCase("DEPOSIT")) {
                        balance += amount;
                        c[1] = String.valueOf(balance);
                    } else if (type.equalsIgnoreCase("WITHDRAW")) {
                        if (amount > balance) {
                            // Saldo tidak cukup -> gagal, simpan ke stack
                            failedTransactions.push(trx);
                        } else {
                            balance -= amount;
                            c[1] = String.valueOf(balance);
                        }
                    }
                    break;
                }
            }
        }

        // 5. Tampilkan hasil akhir
        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] trx = failedTransactions.pop(); // LIFO
            System.out.println(trx[0] + " " + trx[1] + " " + trx[2]);
        }
    }
}
