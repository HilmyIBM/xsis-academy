import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) {
        fungsiSopi(65000, 10000, 2);
        
    }

    
    // ========================================================================================================
    public static void fungsiCekGrade(int nilai){ /*No. 1 */
        if(nilai >= 90){
            System.out.println("Grade A");
        }else if(nilai >= 70 && nilai <= 89){
            System.out.println("Grade B");
        }else if(nilai >= 50 && nilai <= 69){
            System.out.println("Grade C");
        }else{
            System.out.println("Grade E");
        }
    }

    
    // ========================================================================================================
    public static void fungsiHitungPulsa(int pulsa){ /*No. 2 */
        int poin = pulsa/10000;
        System.out.println("Pulsa : " + pulsa);
        System.out.println("Point : " + poin);
    }

    
    // ========================================================================================================
    public static void fungsiTotalHarga(int jarak, int belanja, String Promo){ /*No. 3 */
        int totalHarga = 0;
        int ongkir = 0;
        if(jarak <= 5){
            ongkir = 5000;
            totalHarga = 5000;
        }else{
            ongkir = jarak * 1000;
            totalHarga = jarak * 1000;
        }
        if(Promo == "JKTOVO"){
            if(belanja >= 30000){
                totalHarga = (int)(belanja * 4 / 10);
            }else{
                totalHarga += belanja;
            }
        }else{
            totalHarga += belanja;
            System.out.println("Kode Promo Salah");
        }
        System.out.println("Belanja : " + belanja);
        System.out.println("Diskon : " + Promo + "%");
        System.out.println("Ongkir : " + ongkir);
        System.out.println("Total Belanja : " + totalHarga);
    }

    // ========================================================================================================
    public static void fungsiSopi(int belanja, int ongkir, int pilihvoucher){ /*No. 4 */
        int[] pilihan = getVoucher(pilihvoucher);
        int belanjakhir = belanja - pilihan[0];
        int ongkirakhir = fungsiOngkir(ongkir, pilihan[1]);
        int totalHarga = fungsiTotal(belanjakhir, ongkirakhir);
        System.out.println("Belanja: " + belanja);
        System.out.println("Ongkos Kirim: " + ongkir);
        System.out.println("Diskon Ongkir: " + pilihan[0]);
        System.out.println("Diskon Belanja: " + pilihan[1]);
        System.out.println("Total Belanja: " + totalHarga);
    }

    public static int fungsiOngkir(int ongkir, int diskonOngkir){
        return ongkir < diskonOngkir ? 0 : ongkir -  diskonOngkir;
    }
    public static int fungsiTotal(int belanjakhir, int ongkirakhir){
        return belanjakhir + ongkirakhir;
    }
    public static int[] getVoucher(int pilihvoucher){
        HashMap<Integer, int[]> map = new HashMap<>();
        map.put(1, new int[]{5000, 5000});
        map.put(2, new int[]{10000, 10000});
        map.put(3, new int[]{20000, 20000});
        return map.get(pilihvoucher);
    }

    // ========================================================================================================

    public static void fungsiCekGenerasi(String nama, int tahunLahir){
        String generasi = getGenerasi(tahunLahir);
        System.out.println(nama + ", berdasarkan tahun lahir anda tergolong " + generasi);
    }

    public static String getGenerasi(int tahunLahir){
        if(tahunLahir >= 1944 && tahunLahir <= 1964){
            return "Baby Boomer";
        }else if(tahunLahir >= 1965 && tahunLahir <= 1979){
            return "Generasi X";
        }else if(tahunLahir >= 1980 && tahunLahir <= 1994){
            return "Generasi Y";
        }else if(tahunLahir >= 1995 && tahunLahir <= 2015){
            return"Generasi Z";
        }else{
            return "ga ada generasi :D";
        }
    }
}
