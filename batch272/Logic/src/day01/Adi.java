package day01;

public class Adi {

	public static void main(String[] args) {
		
    System.out.println( PerGetaran (25f,17f));
		PerGetaran1(25f,17f);
		
	}

	public static float PerGetaran(float t, float n) {
		return t/n;
	}
	
	public static void PerGetaran1(float t, float n) {
		float rumus = t/n;
		System.out.println(rumus);
	}
	
}
