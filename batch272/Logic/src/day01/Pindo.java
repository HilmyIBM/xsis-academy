package day01;

public class Pindo {
 
	public static float usaha(int k, int x) {
	 return  (1/2f)* k* (x*x);
 }
	public static void usaha1(int k, int x) {
		float a = (1/2f)*k*(x*x);
		System.out.println(a);
	}
	public static void main(String[] args) {
	System.out.println(usaha(4,6));
	usaha1(4,6);
	}

}
