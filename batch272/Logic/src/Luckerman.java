
public class Luckerman {
	
	public static float segitiga(float a, float tinggi) {
		float rumus = 0.5f * (a * tinggi);
		return rumus;
	}
	public static void segitiga2(float a, float tinggi) {
		float rumus = 0.5f * (a * tinggi);
		System.out.println(rumus);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(segitiga(5f, 7f));
		segitiga2(5f,7f);

	}

}
