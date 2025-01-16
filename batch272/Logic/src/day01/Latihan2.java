package day01;

public class Latihan2 {
	
	public static void main(String[] args) {
		int a;
		a=5;
		float b=6.7f, c=3.5f;
		double d=3.345;
		boolean e=true;
		short f=2;
		
		String g="String variable";
		long h=23343434;
		Integer i=33442;
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
		System.out.println(g);
		System.out.println(h);
		System.out.println(i);
		
		System.out.println(fstring());
		System.out.println(fstring("Passing parameter/argumen"));
		System.out.println(kelLingkaran(7));
		System.out.println(luasLingkaran(7));
		
		float luasKeliling = kelLingkaran(7) + luasLingkaran(7);
		System.out.println(luasKeliling);
		float luasKeliling2 = kelLingkaran(7) * luasLingkaran(7);
		System.out.println(luasKeliling2);
		
		procKelLingkaran(7);
		procKelLingkaran(5);
		procKelLingkaran(3);
		procKelLingkaran(1);
		
		String input = "februari";
		int out = 0;
		switch(input) {
		case "januari": out=1; break;
		case "februari": out=2; break;
		case "maret": out=3; break;
		case "april": out=4; break;
		}
		
		System.out.println(input + " adalah bulan ke : " + out);
		
		int temp1 = 0;
		int temp2 = 0;
		int temp3 = 1;
		for(int x=1; x<8; x++) {
			temp1+=5; // temp1 = temp1 + 5;
			temp2+=temp1+x;
			temp3*=x;
			System.out.println(temp1 + " - " + temp2 + " - " + temp3);
		}
		System.out.println(temp1);
	}
	
	public static String fstring() {
		return "didalam fungsi...";
	}
	
	public static String fstring(String str) {
		String innerVar = "ABC " + str;
		return innerVar;
	}

	public static float kelLingkaran(int radius) {
		float rumus = 3.14f * (radius+radius);
		return rumus;
	}
	
	public static float luasLingkaran(int radius) {
		return 3.14f * radius*radius;
	}
	
	public static void procKelLingkaran(int radius) {
		float rumus = 3.14f * (radius+radius);
		System.out.println(rumus);
		System.out.println(rumus+luasLingkaran(radius));
	}
}
