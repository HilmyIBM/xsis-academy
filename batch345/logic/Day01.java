public class Day01 {
    public static float luasLingkaran(float r) {
        return ((22 / 7.0F) * r * r);
    }

    public static float kelilingLingkaran(float r) {
        return (2 * (22 / 7.0F) * r);
    }

    public static int luasPersegi(int s) {
        return s * s;
    }

    public static int kelilingPersegi(int s) {
        return 4 * s;
    }

    public static void findMod(int num1, int num2) {
        if (num1 % num2 == 0) {
            System.out.println("Angka " + num1 + " % " + num2 + " adalah 0" + "\n");
        } else {
            System.out.println("Angka " + num1 + " % " + num2 + " bukan 0, melainkan " + (num1 % num2) + "\n");
        }
    }

    public static void merakitRokok(int putung) {
        int rokok = putung / 8;
        int sisa = putung % 8;
        int harga = rokok * 500;
        System.out.println("Rokok yang berhasil dirakit sebanyak " + rokok + " batang");
        System.out.println("Sisa putung rokoknya sebanyak " + sisa + " Putung");
        System.out.println(
                "Penghasilan yang didapatkan pemulung dari menjual rokok rakitannya adalah Rp. " + harga + "\n");
    }

    public static void main(String[] args) {
        // No. 1
        float r = 28.0F;
        System.out.println("Luas lingkaran dengan jari-jari " + r + " adalah " + luasLingkaran(r));
        System.out.println("Keliling lingkaran dengan jari-jari " + r + " adalah " + kelilingLingkaran(r) + "\n");

        // No. 2
        int s = 10;
        System.out.println("Luas persegi dengan sisi " + s + " adalah " + luasPersegi(s));
        System.out.println("Keliling persegi dengan sisi " + s + " adalah " + kelilingPersegi(s) + "\n");

        // No. 3
        findMod(7, 2);

        // No. 4
        merakitRokok(100);
    }
}
