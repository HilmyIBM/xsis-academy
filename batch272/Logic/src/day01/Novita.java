package day01;

public class Novita {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(period(12f));
		period2(12f);
	}
	
	public static float period(float t) {
		float rumus= 1/t;
		float frekuensi=1/rumus;
		return frekuensi;
		
	}
	
	public static void period2(float t) {
		float rumus= 1/t;
		float frekuensi=1/rumus;
		System.out.println(frekuensi);
		

}
}
