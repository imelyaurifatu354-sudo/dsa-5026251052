import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        
        Map<String, Integer> enrollment = new HashMap<>();
        List<String> courseOrder = new ArrayList<>();
        List<String> checkResults = new ArrayList<>();
        int rejected = 0;

        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        while (sc.hasNext()) {
            String operation = sc.next();
            String code = sc.next();

            if (operation.equals("CHECK")) {
                if (enrollment.containsKey(code)) {
                    checkResults.add(code + ": " + enrollment.get(code) + " students");
                } else {
                    checkResults.add(code + ": Not found");
                }
            } else {
                int count = sc.nextInt();

                if (count <= 0) {
                    rejected++; // jumlah tidak valid
                } else if (operation.equals("REGISTER")) {
                    if (enrollment.containsKey(code)) {
                        enrollment.put(code, enrollment.get(code) + count);
                    } else {
                        enrollment.put(code, count);
                        courseOrder.add(code);
                    }
                } else if (operation.equals("WITHDRAW")) {
                    if (enrollment.containsKey(code) && enrollment.get(code) >= count) {
                        enrollment.put(code, enrollment.get(code) - count);
                    } else {
                        rejected++; 
                }
            }
        }
        sc.close();

        System.out.println("===== Enrollment Checks =====");
        for (int i = 0; i < checkResults.size(); i++) {
            System.out.println(checkResults.get(i));
        }

        System.out.println("===== Final Enrollment =====");
        for (int i = 0; i < courseOrder.size(); i++) {
            String code = courseOrder.get(i);
            System.out.println(code + ": " + enrollment.get(code) + " students");
        }
        System.out.println("Rejected operations: " + rejected);
    }
}
