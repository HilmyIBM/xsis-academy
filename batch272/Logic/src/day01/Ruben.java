package day01;

public class Ruben {

	public static float gerakLurus(float v0, float t, float a) {
		float rumus=v0+t+(0.5f*a*t*t);
		return rumus;
	}
	
	public static void gerakLurus1(float v0, float t, float a) {
		float rumus=v0+t+(0.5f*a*t*t);
		System.out.println(rumus);
	}
	public static void main(String[] args) {
		System.out.println(gerakLurus(20f,5f,2f));
		gerakLurus1(20f,5f,2f);

	}

}
