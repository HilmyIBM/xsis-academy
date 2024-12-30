public class Day01 {
    public static void main(String[] args){
        // Nomor 1
        double r = 28.0;
        System.out.println("Keliling Lingkaran dengan jari-jari " + r + " adalah : " + kelilingLingkaran(r));
        System.out.println("Luas Lingkaran dengan jari-jari " + r + " adalah : " + luasLingkaran(r) + "\n");
        
        //Nomor 2
        double sisi = 12.0;
        System.out.println("Keliling Persegi dengan panjang sisi " + sisi + " adalah : " + kelilingPersegi(sisi));
        System.out.println("Luas Persegi dengan jari-jari " + r + " adalah : " + luasPersegi(sisi));

        //Nomor 3
        int angka1 = 12;
        int angka2 = 2;
        System.out.println(hasilMod(angka1,  angka2));

        //Nomor 4
        int jumlahPuntung = 150;    
        System.out.println(hitungRokok(jumlahPuntung));

    }

    public static double kelilingLingkaran(double r){
        double phi = 22/7.0;
        return 2 * phi * r;
    }

    public static double luasLingkaran(double r){
        double phi = 22/7.0;
        return phi * r * r;
    }

    public static double luasPersegi(double s){
        return s * s;
    }
    
    public static double kelilingPersegi(double s){
        return s * 4;
    }

    public static String hasilMod(int i, int j){
        if (i % j == 0) {
            return "\nangka " + i + " % dengan angka " + j + " adalah 0\n";
        }else{
            return "\nangka " + i + " % dengan angka " + j + "bukan  0 melainkan " + i%j + "\n";
        }
    }
    
    public static String hitungRokok(int puntung){
        int banyakRokok = puntung/8;
        int sisaRokok = puntung % 8;
        int penghasilan = banyakRokok*500;
        return puntung + " Terkumpul\n" + banyakRokok + " Batang Rokok\n" + sisaRokok + " Sisa Puntung\n" + penghasilan + " Rupiah Dihasilkan"; 
    }    
}
