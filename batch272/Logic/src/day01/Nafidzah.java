package day01;

public class Nafidzah {
	
	public static void tabung(float r, float t) {
		float luas=3.14f*r*r*t;
		System.out.println(luas);
		
	}
	
	public static float tabung2(float r, float t) {
		float luas=3.14f*r*r*t;
		return luas;
		
	}

	public static void main(String[] args) {
		
		System.out.println(tabung2(4f,8f));
		tabung(4f,8f);

	}

}
