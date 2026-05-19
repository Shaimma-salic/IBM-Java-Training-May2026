package mypackage;
import java.util.Scanner;

public class loops {
	
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		
		int n = 0;
		int i = 0;
		int j = 0;
		boolean restart = false;
	
		// DO WHILE LOOP
		do {
		
			n = 0;  
			
			while (n>20 || n<1){
			System.out.print("Enter a number(1-20): ");
			n=scanner.nextInt();
				if (n>20 || n<1)
					System.out.println("Invalid. Please try again!");	
			}
		// NESTED LOOP/ FOR LOOP
			for(i=0; i<n; i++) {
				for (j=1; j<=i+1; j++) {
					System.out.print(j + " ");
				}
				System.out.println();
			}
			
			 // Restart
	        System.out.print("Do you want to try again? (true/false): ");
	        restart = scanner.nextBoolean();
	        
		} while(restart);
		
		}

}
