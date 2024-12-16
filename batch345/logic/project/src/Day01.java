public class Day01 {

    public static void HitungKelilingDanLuasLingkaran(float r) {
        float k = (2 * (22F/7F) * r);
        float l = ((22F/7F) * r * r);

        System.out.println("Keliling Lingkaran adalah " + k);
        System.out.println("Luas Lingkaran adalah " + l);
    }

    public static void HitungLuasDanKelilingPersegi(float s) {
        float l = s * s;
        float k = 4 * s;
        System.out.println("Luas Persegi adalah " + l);
        System.out.println("Keliling Persegi adalah " + k);
    }

    public static void CekModulo0(int n, int pembagi) {
        int res = n % pembagi;

        if (res == 0) System.out.println("Angka " + n + " % " + pembagi + " adalah 0");
        else System.out.println("Angka " + n + " % " + pembagi + " bukan 0 melainkan " + res);
    }

    public static void HitungRupiahRokok(int puntungRokok) {
        int batangRokok = puntungRokok / 8;
        int sisaPuntung = puntungRokok % 8;
        int rupiah = batangRokok * 500;

        System.out.println("Puntung yang terkumpul: " + puntungRokok);
        System.out.println("Batang rokok yang dihasilkan: " + batangRokok);
        System.out.println("Sisa Puntung Rokok: " + sisaPuntung);
        System.out.println("Rupiah yang dihasilkan: Rp. " + rupiah);
    }

    public static void main(String[] args) {
        // No 1.
        float jariJariLingkaran = 28;
        HitungKelilingDanLuasLingkaran(jariJariLingkaran);
        System.out.println();

        // No 2.
        float sisiPersegi = 20;
        HitungLuasDanKelilingPersegi(sisiPersegi);
        System.out.println();

        // No 3.
        int angka = 10;
        int pembagi = 3;
        CekModulo0(angka, pembagi);
        System.out.println();

        //No 4.
        int puntungRokokYangDidapat = 100;
        HitungRupiahRokok(puntungRokokYangDidapat);
        System.out.println();
    }
}
