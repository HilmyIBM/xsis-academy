package bangundatar;

public class Main {

	public static void main(String[] args) {

		Lingkaran ling = new Lingkaran();
		
		ling.sisi = 3.4f;
		
		System.out.println(ling.luas());
		System.out.println(ling.keliling());
		
		Segitiga segitiga = new Segitiga();
		
		segitiga.alas = 5f;
		segitiga.tinggi = 3f;
		segitiga.sisi = 4f;
		System.out.println(segitiga.luas());
		System.out.println(segitiga.keliling());
		
		BangunDatar bd = new BangunDatar();
		System.out.println(bd.sisi);
		System.out.println(bd.tinggi);
		System.out.println(bd.alas);
		
		Segitiga segit = new Segitiga();
		System.out.print("Keliling segitiga : ");
		System.out.println(segit.keliling());
		
	}

}
