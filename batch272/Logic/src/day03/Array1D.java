package day03;

import java.util.Arrays;
import java.util.Scanner;

public class Array1D {
	
	public static void readArray(int inarr[]) {
		for(int i=0; i<inarr.length; i++) {
			System.out.print(inarr[i] + ",");
		}
		System.out.println();
	}

	public static void readArray(String inarr[]) {
		for(int i=0; i<inarr.length; i++) {
			System.out.print(inarr[i] + ",");
		}
		System.out.println();
	}

	public static void readArray(char inarr[]) {
		for(int i=0; i<inarr.length; i++) {
			System.out.print(inarr[i] + ",");
		}
		System.out.println();
	}

	public static void main(String args[]) {

		int[] intarr = new int[5];
		int intarr2[] = { 5,4,3,2,1 };
		
		intarr[0] = 7;
		intarr[1] = 4;
		intarr[2] = 2;
		intarr[3] = 9;
		intarr[4] = 0;
		
		System.out.println("Panjang array intarr = " + intarr.length);
		System.out.println("Panjang array intarr2 = " + intarr2.length);
		
		System.out.println(intarr[3]);
		System.out.println(intarr2[4]);
		
		readArray(intarr);
		readArray(intarr2);
		
		int temp=0;
		for(int i=0; i<intarr2.length; i++) {
			temp+=intarr2[i];
		}
		System.out.println(temp);

		for(int i=0; i<intarr.length; i++) {
			System.out.println(intarr[i] + " + " + intarr2[i] + " = " + (intarr[i]+intarr2[i]));
		}
		
		Arrays.sort(intarr);
		
		readArray(intarr);
		
		String strarr[] = {"satu","dua","tiga"};
		char[] chararr = {'A','B','C'};
		float farr[] = {2.1f, 5.3f};
		
		readArray(strarr);
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Masukan panjang array > ");
		int n = scan.nextInt();
		System.out.println("integer : " + n);
		
//		Scanner strscan = new Scanner(System.in);
//		System.out.print("Masukan string > ");
//		String m = strscan.nextLine();
//		System.out.println("integer : " + m);
		
		int arr[] = new int[n];
		Scanner intscan = new Scanner(System.in);
		for(int i=0; i<arr.length; i++) {
			System.out.print("arr["+i+"] > ");
			arr[i] = intscan.nextInt();
		}
		
		readArray(arr);
		
		int A[]; // di scan
		int B[]; // di scan
		int C[]; // menyimpan hasil perkalian A & B
	}
	
	

}
