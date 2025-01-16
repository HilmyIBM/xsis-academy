package day01;

public class Fahri {
	
	public static float belahketupat(float d1, float d2) {
		float rumus = 0.5f * d1 *d2;
		return rumus;
	}
	
	public static void belahketupat2(float d1, float d2) {
		float rumus = 0.5f * d1 * d2;
		System.out.println(rumus);
	}
	

	public static void main(String[] args) {
		System.out.println(belahketupat(22f,33f));
		belahketupat2(22f,33f);

	}

}
