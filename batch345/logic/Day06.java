// import java.util.ArrayList;
// import java.util.Scanner;

// public class Day06 {
//     public static void main(String[] args) {
//         pilihSoal();    
//     }

//     public static void pilihSoal(){
//         Scanner input = new Scanner(System.in);     
//         int noSoal;

//         while (true) {
//             System.out.print("Soal No. Berapa yang akan dikerjakan\n(0 untuk keluar dari program) : ");
//             noSoal = input.nextInt();
//             input.nextLine();
            
//             if(noSoal == 0) {
//                 System.out.println("Terima Kasih");
//                 break;
//             }

//             switch (noSoal) {
//                 case 1:
//                     System.out.print("Masukkan sandi : ");
//                     String sandi = input.nextLine();
//                     System.out.print("rotasi : ");
//                     int rotasi = input.nextInt();

//                     enkripsiCaesar(sandi, rotasi);
//                     break;
//                 default:
//                     System.out.println("Nomor tidak ada!");
//                     break;

//             }
//         }
//         input.close();
//     }
//     // no 1 --------------------------------------------------------------------
//     public static void enkripsiCaesar(String sandi, int rotasi){
//         sandi.toLowerCase();
//         String sandiBaru = "";
//         for (int i = 0; i < sandi.length(); i++) {
//             if ((char)(sandi.charAt(i) + rotasi) < (char) 'z') {
//                 sandiBaru += (char)(sandi.charAt(i) + rotasi);
//             }else{
//                 sandiBaru +=  (char)((sandi.charAt(i) + rotasi)%26);
//             }
//         }
//     }
    
//     // no 1 --------------------------------------------------------------------
//     public static void pilahKalimat(String kalimat){

//         String vokal = "";
//         String konsonan = "";
        
//         for (int i = 0; i < kalimat.length(); i++) {
//             if(('a','i','u','e','o').equals(kalimat.charAt(i))){

//             }            
//         }
//     }

// }
