package mypackage;

import java.util.Scanner;

public class ZigZagPattern {	
	

	
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        int total = input*input;
        int i=0;
        int j=1;
        int k = 0;
        


    	for (i=1; i<=total; i++) {
    		j++;
    		System.out.print(i+" "); // NORMAL
    		if(i%input==0 && i<total) { // REACHED NUMBER
    			System.out.println(" "); 
    			for (k = i + input; k!=i && k<=total; k--) {
    				System.out.print(k+" ");
    				j++;
    			}
    			System.out.println(" ");
    			i=j-1;
    		}
    	}
    
    
    
        
        sc.close();
    }
}