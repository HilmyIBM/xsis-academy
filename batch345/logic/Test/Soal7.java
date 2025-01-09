package Test;

import java.util.Scanner;

public class Soal7 {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String kalimat = input.nextLine();
        soal7(kalimat);
        input.close();
    }

    public static void soal7(String kalimat){
        String regex = "[,'\"@/&]";
        String result = kalimat.replaceAll(regex, "");
        String[] cekSama = result.split(" ");
        for (int i = 0; i < cekSama.length; i++) {
            for (int j = 0; j < cekSama.length -1 - i; j++) {
                if (cekSama[j].equals(cekSama[j + 1])) { 
                    cekSama[j] = "";
                }
            }
            if (cekSama[i] == "") {
                continue;
            }else System.out.print(cekSama[i] + " ");
        }
    }
}
//  karakter dan kata yang termasuk sampah(perlu di cleansing): koma(,), kutip satu('), kutip dua("), anotasi(@), kata yang sama, garis miring(/), dan(&)
// - tiap suku kata hanya dipisah dengan 1 spasi