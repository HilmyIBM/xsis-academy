package day01;

public class Kondisional {
	public static void main(String[] args) {
		int number = 51;
		
		if(number == 1) {
			System.out.println("Single");
		} else if(number > 1 && number <= 5) {
			System.out.println("Lower 5");
		} else if (number > 5 && number <= 50) {
			System.out.println("5 to 50");
		} else if(number > 50) {
			System.out.println("Big Number...");
		} else {
			System.out.println("Other number");
		}
		
		String out ="";
		switch(number) {
			case 1 : out = "Single"; break;
			case 2 : 
			case 3 : 
			case 4 : 
			case 5 : out = "Lower 5"; break;
			default : out = "Other Number"; break;
		}
		
		System.out.println(out);
		
		int x = 8 % 7;
		System.out.println(x);
		
	}
}
