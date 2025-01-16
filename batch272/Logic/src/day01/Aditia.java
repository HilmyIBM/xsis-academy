package day01;

public class Aditia {
	//fungsi
	public static float GerakVertikal (float v0) {
		float rumus = v0 * v0 / 2 * 9.8f;
		return rumus;
	}
	
	// procedure
	public static void GerakVertikal2 (float v0) {
		float rumus = v0 * v0 / 2 * 9.8f;
		System.out.println(rumus);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(GerakVertikal(3f));
		
		GerakVertikal2(3);

	}

}
