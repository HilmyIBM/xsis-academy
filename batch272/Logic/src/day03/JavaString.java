package day03;

public class JavaString {

	public static void main(String[] args) {

		char chararr[] = {'X','s','i','s'};
		
		Array1D arrobj = new Array1D();
		
		arrobj.readArray(chararr);
		
		String str1 = new String(chararr);
		System.out.println(str1);
		
		String str2 = "Xsis Academy";
		System.out.println(str2);
		
		char chararr2[] = str2.toCharArray();
		
		arrobj.readArray(chararr2);
		
		System.out.println(str2.charAt(3));
		
		for(int i=0; i<str2.length(); i++) {
			System.out.print(str2.charAt(i));
		}
		System.out.println();
		
		System.out.println(str2.substring(3));
		System.out.println(str2.substring(3,8));
		
		String str3 = "Xsis AcaDemy xis";
		System.out.println(str2 + " " + str3);
		
		System.out.println(str2.concat(str3));
		
		if(str2.equals(str3)) {
			System.out.println("Sama");
		} else {
			System.out.println("Tidak sama");
		}
		
		System.out.println(str2.equalsIgnoreCase(str3));
		
		System.out.println(str3.replace("is", "Langsat"));
		
		String[] strsplit = str3.split(" ");
		arrobj.readArray(strsplit);
		
		System.out.println(str3.toUpperCase());
		System.out.println(str3.toLowerCase());
		
		String alamat = "  Langsat 3 nomor 5 ";
		String strtrim = alamat.trim();
		System.out.println(alamat + " length:" + alamat.length());
		System.out.println(strtrim + " length:" + strtrim.length());
		
		/*
		SOAL-SOAL LOGIC STRING
		======================

		1.============================================
		makeOutWord("<<>>", "Yay") → "<<Yay>>"
		makeOutWord("<<>>", "WooHoo") → "<<WooHoo>>"
		makeOutWord("[[]]", "word") → "[[word]]"

		2.============================================
		doubleChar("The") → "TThhee"
		doubleChar("AAbb") → "AAAAbbbb"
		doubleChar("Hi-There") → "HHii--TThheerree"

		3.============================================
		countHi("abc hi ho") → 1
		countHi("ABChi hi") → 2
		countHi("hihi") → 2

		4.============================================
		mixString("abc", "xyz") → "axbycz"
		mixString("Hi", "There") → "HTihere"
		mixString("xxxx", "There") → "xTxhxexre"

		5.============================================
		repeatSeparator("Word", "X", 3) → "WordXWordXWord"
		repeatSeparator("This", "And", 2) → "ThisAndThis"
		repeatSeparator("This", "And", 1) → "This"

		BONUS.========================================
		ANAGRAMS
		========
		INPUT : SILENT & LISTEN
		INPUT : FUNERAL & REAL FUN
		INPUT : FORTY FIVE & OVER FIFTY
		INPUT : ELEVEN PLUS TWO & TWELVE PLUS ONE*/		
	}

}
