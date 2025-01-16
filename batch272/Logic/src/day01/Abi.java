package day01;

public class Abi {
	public static float Kecepatan(float v1, float t1) {
		float rumus = v1 * t1 / t1;
		return rumus;
	}
	
	public static void Kecepatan1(float v1, float t1) {
		float rumus = v1 * t1 / t1;
		System.out.println(rumus);
	}
	
	public static void main(String[] args) {
	
		System.out.println(Kecepatan(20f, 5f));
		Kecepatan1(20f, 5f);
}
}
