package day01;

public class Dahlan {
	
	public static void main(String[] args) {
		System.out.println(hitung(15f,25f));
		hitung2(15f, 25f);
	}
	
	public static float hitung(float n, float t) {
		return n/t;
	}
	
	public static void hitung2(float n, float t) {
		float rumus= n/t;
		System.out.println(rumus);
	}
}
