package day01;

import java.util.Scanner;

public class SplitString {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		String split1[] = input.split("#");
		String temp[] = new String[4];
		String out[][] = new String[5][5];
		for(int i=0; i<split1.length; i++) {
			System.out.println(split1[i]);
			out[i] = split1[i].split("/");
		}
		
		for(int i=0; i<out.length; i++) {
			System.out.println(">>>" + out[i][0]);
		}
	}
	

}
