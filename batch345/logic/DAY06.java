import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DAY06 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
       /*  System.out.println("Masukkan langkah : ");
        int jumlah_langkah = input.nextInt();
        input.nextLine();
        System.out.println("Masukkan pola :");
        String langkah = input.nextLine();
        lembah(jumlah_langkah, langkah); */

        /* System.out.println("=================");
        System.out.print("Masukkan Pesan : ");
        String pesan =input.nextLine();
        System.out.println("Masukkan Rotasi");
        int rotasi=input.nextInt();
        julius(pesan, rotasi); */

        /* System.out.println("===================");
        System.out.println("Masukkan panjang : ");
        String input_panjang=input.nextLine();
        System.out.println("Masukkan text : ");
        String input_text=input.nextLine();
        pdf(input_panjang, input_text); */

       /*  System.out.println("==================");
        System.out.print("Masukkan Text : ");
        String test_vokal=input.nextLine();
        vokal(test_vokal); */

        System.out.println("===========================");
        System.out.print("Masukkan Password : ");
        String pass=input.nextLine();
        boolean hasil=password(pass);
        if (hasil == true) {
            System.out.println("Password Strong");
        }
       

    }

    public static void lembah(int l, String n) {
        int Jumlah = n.length();
        int gunung = 0;
        int lembah = 0;

        for (int i = 0; i < l; i++) {
            if (n.charAt(i) == 'U') {
                gunung++;
                if (gunung == 0) {
                    lembah++;
                }
            } else {
                gunung--;
            }
        }

        System.out.println("Jumlah karakter : " + Jumlah);
        System.out.println("Jumlah lembah : " + lembah);
    }

    public static void julius(String s, int rotasi){
        String hasil="";

        for(int i=0;i<s.length();i++){
            char isi=s.charAt(i);
            if(Character.isLetter(isi)){
                char alphabet=(Character.isUpperCase(isi)? 'A':'a');
                isi=(char) (alphabet + (isi-alphabet+rotasi)%26);
            }
            hasil+=isi;
        }
        System.out.println(hasil);
    }

    public static void pdf(String input,String pesan){
        int[] panjang = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        int tertinggi=0;
        pesan.toLowerCase();
        for(int j=0;j<pesan.length();j++){
            int temp=panjang[pesan.charAt(j) -'a'];
            if(temp> tertinggi){
                tertinggi=temp; 
            }
        }
        System.out.println("Tertinggi : "+tertinggi);
        System.out.println("Panjang text : "+ pesan.length());
        System.out.println("Hasil : "+(tertinggi*pesan.length()));
    }

    public static void vokal(String text){
        String[] pesan=text.split(" ");
        pesan.toString().toLowerCase();
        String vokal="";
        String konsonan="";
        for(String isi_pesan:pesan){
            for(int i=0;i<isi_pesan.length();i++){
                if(isi_pesan.charAt(i)== 'a'|| isi_pesan.charAt(i)== 'i'|| 
                   isi_pesan.charAt(i)== 'u'|| isi_pesan.charAt(i)== 'e'|| 
                   isi_pesan.charAt(i)== 'o'){
                    vokal+=isi_pesan.charAt(i);
                }else{
                    konsonan+=isi_pesan.charAt(i);
                }
            }
        }
        char[] sort_vokal=vokal.toCharArray();
        char[] sort_konsonan=konsonan.toCharArray();
        Arrays.sort(sort_vokal);
        Arrays.sort(sort_konsonan);
        vokal=String.valueOf(sort_vokal);
        konsonan=String.valueOf(sort_konsonan);
        System.out.println("Vokal : "+vokal);
        System.out.println("Konsonan : "+konsonan);
    }

    public static Boolean password(String pass){
        boolean test=true;
        Pattern angka=Pattern.compile("[0-9]");
        Pattern symbol = Pattern.compile("[!@#$%^&*()-+]");
        Pattern huruf_kecil=Pattern.compile("[a-z]");
        Pattern huruf_besar=Pattern.compile("[A-Z]");
        Matcher isangka=angka.matcher(pass);
        Matcher issymbol=symbol.matcher(pass);
        Matcher ishuruf_kecil=huruf_kecil.matcher(pass);
        Matcher ishuruf_besar=huruf_besar.matcher(pass);
        if(pass.length() < 6){
            System.out.println("Password Weak & Kurang dari 6 digit");
            test=false;
        }
        if(isangka.find()!=true){
            System.out.println("Password harus berisi angka");
            test=false;
        }
        if(issymbol.find()!=true){
            System.out.println("Password harus berisi symbol");
            test=false;
        }if(ishuruf_besar.find()!=true){
            System.out.println("Password harus berisi huruf besar");
            test=false;
        }if(ishuruf_kecil.find()!=true){
            System.out.println("Password harus huruf kecil");
            test=false;
        }
        return test;
    }
}
