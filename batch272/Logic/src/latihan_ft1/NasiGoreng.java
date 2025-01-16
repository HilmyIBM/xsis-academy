package latihan_ft1;

import java.util.Scanner;

public class NasiGoreng {

	public static void main(String[] args) {
		int laki2dewasa = 0; // 1
		int perempuanDewasa = 0; // 2
		int remaja = 0; // 3
		int anak2 = 0; // 4
		int balita = 0; // 5
		
		String out = "";
		int pil = 0;
		int jum = 0;
		int total = 0;
		while(pil != 6) {
			Scanner pilihan = new Scanner(System.in);
			System.out.println("1. laki2 dewasa");
			System.out.println("2. perempuan dewasa");
			System.out.println("3. remaja");
			System.out.println("4. anak2");
			System.out.println("5. balita");
			System.out.println("6. selesai");
			System.out.print("Silakan pilih : ");
			pil = pilihan.nextInt();
			
			if(pil==6) {
				System.out.println("SELESAI");
			} else {
				Scanner jumlah = new Scanner(System.in);
				System.out.print("Jumlah : ");
				jum = jumlah.nextInt();
//				System.out.println();
				
				switch(pil) {
					case 1 : out = "Laki2 Dewasa"; total += jum; break;
					case 2 : out = "Perempuan Dewasa"; total += jum; break;
					case 3 : out = "Remaja"; total += jum; break;
					case 4 : out = "Anak2"; total += jum; break;
					case 5 : out = "Balita"; total += jum; break;
					case 6 : out = "Selesai"; total += jum; break;
					default : break;
				}
				System.out.println(out + " " + jum + " Porsi");
				System.out.println();
			}
		}
		System.out.println("Total : " + total + " Porsi");
	}

}
