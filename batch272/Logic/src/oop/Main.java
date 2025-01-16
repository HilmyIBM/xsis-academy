package oop;

public class Main {

	public static void main(String[] args) {
		
		Encapsulation obj = new Encapsulation();
		
		obj.name = "Bruce";
		obj.getName();
		System.out.println(obj.name);
		
		obj.setAge(20);
		System.out.println(obj.getAge());
		
		Encapsulation pindo = new Encapsulation();
		
		pindo.name = "Pindo";
		pindo.getName();
		
		Kucing kucing = new Kucing();
		kucing.kucing();
		
		Ayam ayam = new Ayam();
		ayam.ayam();
		
		Ras ras = new Ras();
		ras.kucing();
		ras.ras();
		
	}

}
