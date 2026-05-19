package mypackage;

public class DayOne {

    int blackJack(int a, int b) {
        if (a > 21 && b > 21) {
            return 0;
        } else if (a > 21) {
            return b;
        } else if (b > 21) {
            return a;
        } else {
            return Math.max(a, b);
        }
    }
    
    
    public static void main(String[] args) {
    	DayOne dayOne = new DayOne();
        System.out.println(dayOne.blackJack(1, 22)); 
        System.out.println(dayOne.blackJack(21, 22));
        System.out.println(dayOne.blackJack(22, 22));
        System.out.println(dayOne.blackJack(2, 10));
    }
}