public class Day01 {
    public static void main(String []args) {
        float jari = 28.0F;
        float sisi = 5.0F;
        float angka = 10.0F;
        float pembagi = 4.0F;
        float puntung = 100.0F;
        float harga = 500.0F;
        float nilai = 85.0F;


        //no 1
        System.out.println("Keliling lingkaran dengan jari-jari " + jari + ": " + kelilingLingkaran(jari));

        System.out.println("Luas lingkaran dengan jari-jari " + jari + ": " + luasLingkaran(jari));

        //no 2
        System.out.println("Keliling Persegi dengan sisi " + sisi + ": " + kelilingPersegi(sisi));

        System.out.println("Luas Persegi dengan sisi " + sisi + ": " + luasPersegi(sisi));

        //no 3
        System.out.println(perhitunganMod(angka, pembagi));

        //no 4
        System.out.println(batangTerkumpul(puntung));

        System.out.println(keuntungan(puntung, harga));

        System.out.println(sisaPuntung(puntung));
        
        //no 5
        System.out.println(grade(nilai));

        //no 6
        System.out.println(ganjilgenap(angka));

    }

    public static float kelilingLingkaran(float jari){
        float ans = 22.0F / 7 * 2 * jari;
        return ans;
    }

    public static float luasLingkaran(float jari){
        float ans = 22.0F / 7 * jari * jari;
        return ans;
    }

    public static float kelilingPersegi (float sisi) {
        float ans = 4.0F * sisi;
        return ans;
    }

    public static float luasPersegi (float sisi) {
        float ans = sisi * sisi;
        return ans;
    }

    public static String perhitunganMod (float angka, float pembagi){
        float ans = angka%pembagi;
        if (ans == 0.0F) {
            return "angka(" + angka + ")%(" + pembagi + ") adalah 0";
        }else {
            return "angka(" + angka + ")%(" + pembagi + ") bukan 0 melainkan " + ans;
        }
    }

    public static Float batangTerkumpul (float puntung) {
        Float ans = (puntung - puntung%8) / 8;
        return ans;
    }

    public static Float keuntungan (float puntung, float harga){
        float ans = (puntung - puntung%8) / 8 * harga;
        return ans;
    }

    public static float sisaPuntung (float puntung){
        float ans = puntung%8;
        return ans;
    }

    public static String grade (float nilai){
        if (nilai >= 80.0F){
            return "A";
        } else if (nilai <80.0F && nilai >= 60.0F){
            return "B";
        } else {
            return "C";
        }
    }

    public static String ganjilgenap (float angka){
        if (angka%2 == 0){
            return "Genap";
        } else {
            return "Ganjil";
        }
    }
}
