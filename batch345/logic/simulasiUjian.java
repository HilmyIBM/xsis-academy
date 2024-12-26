import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class simulasiUjian {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        /*
         * System.out.print("Masukkan kata : ");
         * String kata = input.nextLine();
         * count_upper(kata);
         */
        /*
         * System.out.println("=================");
         * System.out.print("Masukkan Start : ");
         * int i=input.nextInt();
         * System.out.print("Masukkan End : ");
         * int j=input.nextInt();
         * input.nextLine();
         * System.out.print("Masukkan TGL Invoice : ");
         * String tgl_invoice=input.nextLine();
         * System.out.println("======================");
         * LocalDate tgl =LocalDate.parse(tgl_invoice,formatter);
         * invoice(i, j,tgl);
         * System.out.println("======================");
         * keranjang();
         * System.out.println("======================");
         * baju();
         */
        /*
         * System.out.println("====================");
         * System.out.print("Masukkan Nilai : ");
         * String input_nilai=input.nextLine();
         * dosen(input_nilai);
         */
        /*
         * System.out.println("=====================");
         * System.out.print("Masukkan Kalimat : ");
         * String kalimat_panam=input.nextLine();
         * panagram(kalimat_panam);
         */
        
       /*   System.out.println("=======================");
         System.out.print("Masukkan Maksimal Himpunan : ");
         int himpunan=input.nextInt();
          fibo(himpunan);
        
        System.out.println("=======================");
        System.out.print("Masukkan Digit : ");
        int digit=input.nextInt();
        System.out.print("Masukkan n : ");
        int n=input.nextInt();
        rekursif_digit(digit, n); */
/* 
        System.out.println("========================");
        System.out.print("Masukkan Pulsa : ");
        int isi=input.nextInt();
        pulsa(isi); */
     /*    System.out.println("=======================");
        System.out.print("Masukkan Selisih : ");
        int selisih = input.nextInt();
        selisih(selisih); */
        System.out.print("Masukkan Input : ");
        int n=input.nextInt();
        number_one(n);
    }

    public static void count_upper(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void invoice(int i, int j, LocalDate tgl) {
        for (int x = i; x <= j; x++) {
            System.out.printf("XA-" + tgl.format(formatter) + "-" + "%05d", x);
            System.out.println();
        }
    }

    public static void keranjang() {
        ArrayList<Integer> keranjang = new ArrayList<>();
        int hasil = 0;
        for (int i = 0; i < 3; i++) {
            System.out.print("Masukkan Keranjang " + (i + 1) + " : ");
            keranjang.add(input.nextInt());
        }
        System.out.print("Masukkan Keranjang Yang dibawa ke pasar : ");
        int pasar = input.nextInt();
        keranjang.remove(pasar - 1);

        for (int j = 0; j < keranjang.size(); j++) {
            hasil += keranjang.get(j);
        }
        System.out.println(hasil);
    }

    public static void baju() {
        int total = 0;
        int jumlah_wanita = 0;
        System.out.println("Menu : ");
        System.out.println("1. Laki Dewasa");
        System.out.println("2. Wanita Dewasa");
        System.out.println("3. Anak - Anak");
        System.out.println("4. Bayi");
        System.out.print("Input Baju Untuk : ");
        int menu = input.nextInt();
        while (menu != 0) {
            switch (menu) {
                case 1:
                    System.out.print("Jumlah baju untuk laki dewasa : ");
                    int baju_laki = input.nextInt();
                    total += baju_laki;
                    break;
                case 2:
                    System.out.print("Jumlah baju untuk wanita dewasa : ");
                    int baju_wanita = input.nextInt();
                    jumlah_wanita += baju_wanita;
                    total = total + (baju_wanita * 2);
                    break;
                case 3:
                    System.out.print("Jumlah baju untuk anak-anak : ");
                    int baju_anak = input.nextInt();
                    total = total + (baju_anak * 3);
                    break;
                case 4:
                    System.out.print("Jumlah baju untuk Bayi : ");
                    int baju_bayi = input.nextInt();
                    total = total + (baju_bayi * 5);
                    break;
                case 0:
                    System.out.println("Keluar");
                    break;
                default:
                    System.out.println("Menu Tidak Ada");
                    break;
            }
            input.nextLine();
            System.out.print("Ingin Input lagi y/n : ");
            String lagi = input.nextLine();
            if (lagi.equalsIgnoreCase("y")) {
                System.out.println();
                System.out.println("Menu : ");
                System.out.println("1. Laki Dewasa");
                System.out.println("2. Wanita Dewasa");
                System.out.println("3. Anak - Anak");
                System.out.println("4. Bayi");
                System.out.print("Input Baju Untuk : ");
                menu = input.nextInt();
            } else {
                if (total > 10 && total % 2 != 0) {
                    System.out.println("Total Baju : " + (total + jumlah_wanita));
                    break;
                } else {
                    System.out.println("Total Baju : " + total);
                    break;
                }
            }
        }
    }

    public static void dosen(String nilai) {
        int[] isi_nilai = Arrays.stream(nilai.split(",")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < isi_nilai.length; i++) {
            int nilai_kelipatan = (isi_nilai[i] + 4) / 5 * 5;
            if (isi_nilai[i] > 35) {
                if (nilai_kelipatan - isi_nilai[i] < 3) {
                    isi_nilai[i] = nilai_kelipatan;
                }
            }
            System.out.println("Nilai Akhir : " + isi_nilai[i] + " ");
        }
    }

    public static void panagram(String kalimat) {
        HashSet<Character> jumlah_alphabet = new HashSet<Character>();
        String filter = kalimat.toLowerCase();
        for (char kata : filter.toCharArray()) {
            if (Character.isLetter(kata)) {
                jumlah_alphabet.add(kata);
            }
        }
        if (jumlah_alphabet.size() == 26) {
            System.out.println("Kalimat Panagram");
        } else {
            System.out.println("Kalimat Bukan Panagram");
        }
    }

    public static void fibo(int n) {
        ArrayList<Integer> deret_fibo = new ArrayList<>();
        ArrayList<Integer> fibo_genap = new ArrayList<>();
        ArrayList<Integer> fibo_ganjil = new ArrayList<>();
        int sum_fibo = 0;
        int sum_genap = 0;
        int sum_ganjil = 0;
        int a = 1, b = 1, c, j = n * 2;
        for (int i = 0; i < n; i++) {
            deret_fibo.add(a);
            c = a + b;
            a = b;
            b = c;
        }
        while (j != 0) {
            if (j % 2 == 0) {
                fibo_genap.add(j);
            } else {
                fibo_ganjil.add(j);
            }
            j--;
        }
        Collections.sort(fibo_genap);
        Collections.sort(fibo_ganjil);
        System.out.print("Fibonacci : ");
        for (int k = 0; k < deret_fibo.size(); k++) {
            System.out.print(deret_fibo.get(k) + " ");
            sum_fibo += deret_fibo.get(k);
        }
        System.out.println();
        System.out.print("Genap : ");
        for (int k = 0; k < deret_fibo.size(); k++) {
            System.out.print(fibo_genap.get(k) + " ");
            sum_genap += fibo_genap.get(k);
        }
        System.out.println();
        System.out.print("Ganjil : ");
        for (int k = 0; k < deret_fibo.size(); k++) {
            System.out.print(fibo_ganjil.get(k) + " ");
            sum_ganjil += fibo_ganjil.get(k);
        }
        System.out.println();
        System.out.println("sum fibo : " + sum_fibo);
        System.out.println("sum genap : " + sum_genap);
        System.out.println("sum ganjil : " + sum_ganjil);
        System.out.println("avg fibo : " + (double) sum_fibo / deret_fibo.size());
        System.out.println("avg genap : " + (double) sum_genap / fibo_genap.size());
        System.out.println("avg ganjil : " + (double) sum_ganjil / fibo_ganjil.size());

    }

    public static void rekursif_digit(int digit,int n){
        ArrayList<Integer> isi_digit=new ArrayList<>();
        int total=0;
        int j=0;
        while (digit!=0) {
            isi_digit.add(digit%10);
            digit/=10;
        }
        Collections.reverse(isi_digit);
        for(int i=0;i<n;i++){
            j=0;
            while (j<isi_digit.size()) {
                total+=isi_digit.get(j);
                j++;
            }
        }
        if(total < 9){
            System.out.println(total);
        }else{
            int temp=total%10;
            total/=10;
            temp=temp+total;
            if(temp>9){
                total=temp%10;
                temp/=10;
                total+=temp;
                System.out.println(total);
            }else{
                System.out.println(temp);
            }
        }
    }

    public static void pulsa(int n){
        int hasil=0;
        if(n<10000){
            System.out.println("0 point");
        }else if(n<30000){
            int temp=20000-10000;
            n-=temp;
            hasil=n/1000;
            System.out.println("Point : "+ hasil);
        }else{;
            n-=10000;
            int temp=30000-10000;
            hasil+=temp/1000;
            n-=temp;
            hasil+=(n/1000)*2;
            System.out.println("Point : "+ hasil);
        }
    }

    public static void selisih(int n) {
        int[] arr = new int[5];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Masukkan isi array : ");
            arr[i] = input.nextInt();
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (Math.abs(arr[i] - arr[j]) == n) {
                    count++;
                }
            }
        }
        System.out.println("Jumlah Himpunan dengan selisih " + n + " sebanyak : " + count);
    }

    public static void number_one(int n){
        int count=1;
        int i=100;
        while(i<=1000 && count <= n){
            if(checknumber(i)){
                System.out.println(i +" Is the one number");
                count++;
            }
            i++;
        }
    }

    public static boolean checknumber(int num){
        while (num > 9) {
            num=count_pow(num);
        }
        return num==1;
    }

    public static int count_pow(int n){
        int total=0;
        while (n!=0) {
            int temp=n%10;
            total+=temp*temp;
            n/=10;
        }
        return total;
    }
}
