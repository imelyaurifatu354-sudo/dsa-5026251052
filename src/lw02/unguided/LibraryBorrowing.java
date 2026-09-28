import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class LibraryBorrowing {

    public static void main(String[] args) {
        final int MAX_BORROW = 2;

        LinkedList<String[]> requests = new LinkedList<>();

        LinkedList<String[]> books = new LinkedList<>();
        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        LinkedList<String[]> members = new LinkedList<>();

        LinkedList<String[]> successful = new LinkedList<>();

        try (Scanner fileScanner = new Scanner(new File("borrowing.txt"))) {
            while (fileScanner.hasNext()) {
                String name = fileScanner.next();
                String title = fileScanner.next();
                requests.add(new String[]{name, title});

                boolean sudahAda = false;
                for (String[] m : members) {
                    if (m[0].equals(name)) {
                        sudahAda = true;
                        break;
                    }
                }
                if (!sudahAda) {
                    members.add(new String[]{name, "0"});
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File borrowing.txt tidak ditemukan.");
            return;
        }

        Queue<String[]> queue = new LinkedList<>();
        for (String[] r : requests) {
            queue.add(r);
        }

        Stack<String[]> failed = new Stack<>();

        while (!queue.isEmpty()) {
            String[] req = queue.poll();

            String[] book = null;
            for (String[] b : books) {
                if (b[0].equals(req[1])) {
                    book = b;
                    break;
                }
            }
            String[] member = null;
            for (String[] m : members) {
                if (m[0].equals(req[0])) {
                    member = m;
                    break;
                }
            }

            boolean success = false;
            if (book != null && member != null) {
                int stock = Integer.parseInt(book[1]);
                int borrowed = Integer.parseInt(member[1]);


                if (stock > 0 && borrowed < MAX_BORROW) {
                    book[1] = String.valueOf(stock - 1);
                    member[1] = String.valueOf(borrowed + 1);
                    success = true;
                }
            }

            if (success) {
                successful.add(req);
            } else {
                failed.push(req);
            }
        }
        // Outputnya nanti 
        System.out.println("=== Successfully Processed Requests ===");
        for (String[] r : successful) {
            System.out.println(r[0] + " " + r[1]);
        }
        
        System.out.println();
        System.out.println("=== Remaining Book Stock ===");
        for (String[] b : books) {
            System.out.println(b[0] + " : " + b[1]);
        }
        
        System.out.println();
        System.out.println("=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] r = failed.pop(); // LIFO: gagal terbaru dulu
            System.out.println(r[0] + " " + r[1]);
        }
    }
}
