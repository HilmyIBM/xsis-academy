import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day02 {
   private static Scanner sc = new Scanner(System.in);
   public static int scanInt(){
    int temp;
    while (true) {
        try {
            temp = sc.nextInt();
            sc.nextLine();
            return temp;
        } catch (Exception e) {
            sc.nextLine();
            // TODO: handle exception
        }
    }
    
    // return -1;
   }
   public static float scanFloat(){
    float temp;
    while (true) {
        try {
            temp = sc.nextFloat();
            sc.nextLine();
            return temp;
        } catch (Exception e) {
            // TODO: handle exception
        }
        sc.nextLine();
    }
   }
   public static String scanStr(){
    String temp;
    while (true) {
        try {
            temp = sc.nextLine();
            return temp;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
   }
   public static void pause(){
    System.out.print("Tekan Enter untuk melanjutkan...");
    sc.nextLine();
    System.out.printf("\n\n\n\n\n\n");
   }
    public static void main(String[] args) {
        int menu = 0;
        while (true) {
            System.out.println("1. Fungsi Grade Nilai");
            System.out.println("2. Fungsi Poin Transaksi Pulsa");
            System.out.println("3. Fungsi Hitung Total Harga");
            System.out.println("4. Fungsi Harga Akhir Sopi");
            System.out.println("5. Fungsi Cek Generasi");
            System.out.println("6. Fungsi Net Gaji");
            System.out.println("7. Fungsi BMI Calculator");
            System.out.println("8. Fungsi Median");
            System.out.println("9. Exit");
            System.out.print("Input: ");
            menu = scanInt();
            switch (menu) {
                case 1:
                    fungsiCekGrade();
                    pause();
                    break;
                case 2:
                    fungsiHitungPulsa();
                    pause();
                    break;
                case 3:
                    fungsiTotalHarga();
                    pause();
                    break;
                case 4:
                     fungsiSopi();
                     pause();
                    break;
                case 5:
                    fungsiCekGenerasi();
                    pause();
                    break;
                case 6:
                    gapokMenu();
                    pause();
                    break;  
                case 7:
                    bMIMenu();
                    pause();
                    break;
                case 8:
                    cekLulus();
                    pause();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Inputan Salah");
                    break;
            }
        }
    }

    
    // ========================================================================================================
    public static void fungsiCekGrade(){ /*No. 1 */
        System.out.print("Masukan Nilainya: ");
        int nilai = scanInt();
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
    public static void fungsiHitungPulsa(){ /*No. 2 */
        int pulsa = scanInt();
        int poin = pulsa/10000;
        System.out.println("Pulsa : " + pulsa);
        System.out.println("Point : " + poin);
    }

    
    // ========================================================================================================
    public static void fungsiTotalHarga(){ /*No. 3 */
        System.out.print("Masukann jarak toko : ");
        int jarak = scanInt();
        System.out.print("Masukan total belanja anda : ");
        int belanja = scanInt();
        System.out.print("Masukan nama TOotal harga order : ");
        String Promo = scanStr();
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
    public static void fungsiSopi(){ /*No. 4 */
        System.out.print("Masukan total harga belanja : ");
        int belanja = scanInt();
        System.out.print("Masukan total Ongkos kirim: ");
        int ongkir = scanInt();
        int pilihvoucher = 0;
        while(true){
            System.out.print("Masukan Voucher: ");
            pilihvoucher = scanInt();
            if(cekminimumorder(pilihvoucher, belanja) == true){
                break;
            }
        };
        int[] pilihan = sopiVoucher(pilihvoucher);
        int belanjakhir = belanja - pilihan[0];
        int ongkirakhir = fungsiOngkir(ongkir, pilihan[1]);
        int totalHarga = fungsiTotal(belanjakhir, ongkirakhir);
        System.out.println("Belanja: " + belanja);
        System.out.println("Ongkos Kirim: " + ongkir);
        System.out.println("Diskon Ongkir: " + pilihan[0]);
        System.out.println("Diskon Belanja: " + pilihan[1]);
        System.out.println("Total Belanja: " + totalHarga);
    }
    public static boolean cekminimumorder(int pilihan, int belanja){
        if(pilihan == 1 && belanja >=30000){
            return true;
        }else if(pilihan == 2 && belanja >= 50000){
            return true;
        }else if(pilihan == 3 && belanja >= 100000){
            return true;
        }
        System.out.println("Transaksi belum memenuhi minimum order");
        return false;
    }
    public static int fungsiOngkir(int ongkir, int diskonOngkir){
        return ongkir < diskonOngkir ? 0 : ongkir -  diskonOngkir;
    }
    public static int fungsiTotal(int belanjakhir, int ongkirakhir){
        return belanjakhir + ongkirakhir;
    }
    public static int[] sopiVoucher(int pilihvoucher){
        HashMap<Integer, int[]> map = new HashMap<>();
        map.put(1, new int[]{5000, 5000});
        map.put(2, new int[]{10000, 10000});
        map.put(3, new int[]{20000, 20000});
        return map.get(pilihvoucher);
    }

    // ========================================================================================================

    public static void fungsiCekGenerasi(){ /*No. 5 */
        System.out.print("Masukan nama anda: ");
        String nama = scanStr();
        System.out.print("Masukan usia anda: ");
        int tahunLahir = scanInt() ;
        
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




    // ========================================================================================================
    public static void gapokMenu(){ /*No. 6 */
        System.out.print("Nama: ");
        String nama = scanStr();
        System.out.print("Tunjangan: ");
        int tunjangan = scanInt() ;
        System.out.print("Gapok: ");
        int gapok = scanInt() ;
        System.out.print("Banyak Bulan: ");
        int bnykBln = scanInt() ;
        int brutoGaji = brutoGajiCount(gapok, tunjangan);
        int pajak = countPajak(brutoGaji);
        int bpjs = countBpjs(brutoGaji);
        int perbulan = countTotalGaji(brutoGaji, pajak, bpjs, 1);
        int total = countTotalGaji(brutoGaji, pajak, bpjs, bnykBln);

        System.out.println("Karyawan atas nama " + nama + " slip gaji sebagai berikut:");
        System.out.println("Pajak " + pajak);
        System.out.println("bpjs " + bpjs);
        System.out.println("gaji/bulan " + perbulan);
        System.out.println("Total gaji/banyak bulan " + total);
    }
    public static int brutoGajiCount(int gapok, int tunjangan){
        return gapok + tunjangan;
    }
    public static int countPajak(int bruto){
        int total = 0;
        if(bruto <= 5000000){
            total = bruto * 5/100;
        }else if(bruto <= 10000000){
            total = bruto * 10/100;
        }else{
            total = bruto * 15/100;
        }
        return total;
    }
    public static int countBpjs(int bruto){
        return bruto * 3 / 100;
    }
    public static int countTotalGaji(int bruto, int pajak, int bpjs, int bnykBln){
        return (bruto - (pajak + bpjs))*bnykBln;
    }

    // ========================================================================================================
    public static void bMIMenu(){
        System.out.print("Masukan berat badan anda (kg): ");
        float berat = scanFloat() ;
        System.out.print("Masukan tinggi badan anda (cm): ");
        float tinggi = scanFloat() ;
        float BMI = bMICalculator(berat, tinggi/100);
        System.out.println(bMIOutput(BMI));
    }
    public static float bMICalculator(float berat, float tinggi){
        System.out.println(berat + " " + tinggi);
        return berat/(float)(Math.pow(tinggi, 2));
    }
    public static String bMIOutput(float bMI){
        System.out.printf("Nilai BMI anda adalah %.4f\n", bMI);
        String[] output = {"Anda terlalu kurus", "Anda termasuk badan langsing", "anda termasuk badan gemuk"};
        if(bMI < 18.5){
            return output[0];
        }else if(bMI <25){
            return output[1];
        }else{
            return output[2];
        }
    }
    public static void cekLulus(){
        String[] mapel = {"MTK", "Fisika", "Kimia"};
        int totalNilai = 0;
        for(int i = 0; i<3;i++){
            System.out.print("Masukan Nilai " + mapel[i] + " : ");
            int nilai = scanInt() ;
            totalNilai += nilai;
        }
        int median = medianNilai(totalNilai);
        System.out.println("Nilai Rata-Rata : " + median);
        if(median < 50){
            System.out.printf("Maaf\nKamu Gagal\n");
        }else{
            System.out.printf("Selamat\nKamu Berhasil\nKamu Hebat\n");
        }
    }
    public static int medianNilai(int totalNilai){
        return totalNilai/3;
    }
}
