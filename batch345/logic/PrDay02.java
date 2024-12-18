import java.util.Scanner;

public class PrDay02 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Nama : ");
        String nama = input.nextLine();
        System.out.print("Masukkan Tunjangan : ");
        int tj=input.nextInt();
        System.out.print("Masukkan Gaji Pokok : ");
        int gapok=input.nextInt();
        System.out.print("Banyak Bulan : ");
        int bulan=input.nextInt();
        gaji(nama, tj, gapok, bulan); 

        System.out.println("===================");
        System.out.print("Masukkan Berat(kg): ");
        double berat=input.nextInt();
        System.out.print("Masukkan Tinggi(cm) : ");
        double tinggi=input.nextInt();
        BMI(berat, tinggi); 

        System.out.println("====================");
        System.out.print("Masukkan Nilai MTK : ");
        int mtk=input.nextInt();
        System.out.print("Masukkan Nilai Fisika : ");
        int fisika=input.nextInt();
        System.out.print("Masukkan Nilai Kimia : ");
        int kimia=input.nextInt();
        nilai(mtk, fisika, kimia);
    }

    public static void gaji(String nama,int tunjangan,int gaji,int bulan ){
        if(gaji + tunjangan <= 5000000){
            double pajak= (gaji+tunjangan)*0.05;
            double bpjs=0.03 * (gaji+tunjangan);
            double bulan_gaji=(gaji+tunjangan)-(pajak+bpjs);
            double total_gaji=((gaji+tunjangan)-(pajak+bpjs))*bulan;
            System.out.println("Karyawan atas nama " + nama + " slip gaji sebagai berikut : ");
            System.out.println(String.format("Pajak:  %1$,.2f",pajak));
            System.out.println(String.format("BPJS:  %1$,.2f",bpjs));
            System.out.println(String.format("Gaji/bln:  %1$,.2f",bulan_gaji));
            System.out.println(String.format("Total Gaji:  %1$,.2f",total_gaji));
        }else if(gaji + tunjangan > 5000000 && gaji + tunjangan <= 10000000){
            double pajak= (gaji+tunjangan)*0.1;
            double bpjs=0.03 * (gaji+tunjangan);
            double bulan_gaji=(gaji+tunjangan)-(pajak+bpjs);
            double total_gaji=((gaji+tunjangan)-(pajak+bpjs))*bulan;
            System.out.println("Karyawan atas nama " + nama + " slip gaji sebagai berikut : ");
            System.out.println(String.format("Pajak:  %1$,.2f",pajak));
            System.out.println(String.format("BPJS:  %1$,.2f",bpjs));
            System.out.println(String.format("Gaji/bln:  %1$,.2f",bulan_gaji));
            System.out.println(String.format("Total Gaji:  %1$,.2f",total_gaji));
        }else{
            double pajak= (gaji+tunjangan)*0.15;
            double bpjs=0.03 * (gaji+tunjangan);
            double bulan_gaji=(gaji+tunjangan)-(pajak+bpjs);
            double total_gaji=((gaji+tunjangan)-(pajak+bpjs))*bulan;
            System.out.println("Karyawan atas nama " + nama + " slip gaji sebagai berikut : ");
            System.out.println(String.format("Pajak:  %1$,.2f",pajak));
            System.out.println(String.format("BPJS:  %1$,.2f",bpjs));
            System.out.println(String.format("Gaji/bln:  %1$,.2f",bulan_gaji));
            System.out.println(String.format("Total Gaji:  %1$,.2f",total_gaji));  
        }
    }

    public static void BMI(double berat, double tinggi ){
        double tinggi_meter=tinggi/100;
        double tinggi_kuadrat=tinggi_meter*tinggi_meter;
        double hasil=berat/tinggi_kuadrat;
        if(hasil < 18.5){
            System.out.println("Nilai BMI anda adalah : "+ hasil);
            System.out.println("Anda Termasuk Kurus");
        }else if(hasil < 25){
            System.out.println("Nilai BMI anda adalah : "+ hasil);
            System.out.println("Anda termasuk langsing");
        }else{
            System.out.println("Nilai BMI anda adalah : "+ hasil);
            System.out.println("Anda Termasuk Gemuk");
        }
    }

    public static void nilai(int mtk,int fisika, int kimia){
        int hasil=(mtk+fisika+kimia)/3;
        System.out.println("Nilai Rata - Rata : " + hasil);
        if(hasil >= 50){
            System.out.println("Selamat Kamu Berhasil Hebat");
        }else{
            System.out.println("Maaf Kamu Gagal");
        }
    }
}
