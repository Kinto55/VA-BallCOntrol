import java.util.Scanner;

public class VirtualAddressDecomposer {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Prompt the user for the page size
        System.out.print("Please enter the system page size (a power of 2 between 512 and 16384, inclusive): "); 
        int pageSize = input.nextInt();

        // Check that the page size is valid
        if (pageSize < 512 || pageSize > 16384 || (pageSize & (pageSize - 1)) != 0) {
            System.out.println("Invalid page size.");
            return;
        }

        // Prompt the user for the virtual address
        System.out.print("Please enter the virtual address (between 0 and 4294967295): ");
        long virtualAddress = input.nextLong();

        // Decompose the virtual address into a virtual page number and an offset
        int pageNumber = (int) (virtualAddress / pageSize);
        int offset = (int) (virtualAddress % pageSize);

        // Output the results
        System.out.println("This address is in virtual page: " + pageNumber);
        System.out.println("At offset: " + offset);
    }
}