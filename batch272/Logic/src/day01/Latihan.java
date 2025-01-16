package day01;

public class Latihan {
	
	public static void method1() {
		System.out.println("method1");
	}
	
	public void method2() {
		System.out.println("method2");
	}
	
	public void method3( ) {
		System.out.println("method3");
		method2();
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome");
		method1();
		
		Latihan lat = new Latihan(); //instansiasi --- instance
		
		lat.method2();
		lat.method3();
	}

}
