import java.util.ArrayList;

public class Day01 {
    public static void main(String[] args) {
        Lingkaran(28);
        persegi(5);
        modulus(10, 2);
        rokok(50);
    }

    public static void Lingkaran(float r){
        float keliling=2*(22/7.0f) *r;
        float luas=(22/7.0f)*r*r;
        System.out.println("Keliling lingkarang dengan jari "+r +" : " + keliling);
        System.out.println("Luas lingkarang dengan jari "+r +" : " + luas);
    }

    public static void persegi(int s){
        int luas=s*s;
        int keliling=4*s;
        System.out.println("Keliling Persegi dengan sisi "+s +" : " + keliling);
        System.out.println("Luas Persegi dengan sisi "+s +" : " + luas);
    }

    public static void modulus(int i,int j){
        if(i%j == 0){
            System.out.println("angka "+ i +" % "+ j + " Adalah 0");
        }else{
            int hasil=i%j;
            System.out.println("angka "+ i +" % "+ j + " bukan 0 melainkan " + hasil);
        }
    }

    public static void rokok(int n){
        int rokok_100=100/8;
        int sisa=n%8;
        System.out.println("batang rokok jika 100 puntung rokok dikumpulkan hasilnya : "+rokok_100);
        System.out.println("Bagian N puntung rokok");
        int n_rokok=n/8;
        System.out.println("batang rokok yang dihasilkan sebanyak n puntung rokok hasilnya : "+n_rokok);
        System.out.println("Penghasilan jualan batang rokok : "+n_rokok*500);
        System.out.println("Sisa Puntung Rokok "+ sisa);
    }

    public static void x(ArrayList word){
        ArrayList<String> result= new ArrayList<>();
        Boolean contain_x=false;
        

    }

}