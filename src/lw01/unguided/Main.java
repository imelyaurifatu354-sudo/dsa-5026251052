import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("rentals.txt"));

        int n = fileScanner.nextInt(); 

        
        Rental[] rentals = new Rental[n];
        int[] units = new int[n];
        
        for(int i = 0; i < n; i++) {
            String type = fileScanner.next();
            String id = fileScanner.next();
            int days = fileScanner.nextInt();
            units[i] = fileScanner.nextInt();

            if (type.equals("LAPTOP")) {
                rentals[i] = new LaptopRental(id, days);
            } else {
                rentals[i] = new ProjectorRental(id, days);
            }
        }


        fileScanner.close();
        for(int i = 0; i < n; i++) {
            int total = rentals[i].calculateCharge(units[i]);
            System.out.println(rentals[i].getId() + " | " + rentals[i].label() + " | " + total);
        }
    }
}
