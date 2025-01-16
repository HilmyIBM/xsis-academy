package day01;

public class Theo {
	
	public static void lbola(int r) {
		float luas = 4 * 3.14f * (float) r * (float) r;
		System.out.println(luas);
	}
	
	public static float lbola2(int r) {
		float luas = 4 * 3.14f * (float) r * (float) r;
		return luas;
	}
	
	public static void main(String[] args) {
		lbola(7);
		System.out.println(lbola2(7));

	}

}
