    import java.util.ArrayList;
    import java.util.Scanner;
    public class Day07 {

        public static void main(String[] args) {
            pilihSoal();    
        }

        public static void pilihSoal(){
            Scanner input = new Scanner(System.in);     
            int noSoal;

            while (true) {
                System.out.print("\nSoal No. Berapa yang akan dikerjakan\n(0 untuk keluar dari program) : ");
                noSoal = input.nextInt();
                input.nextLine();
                
                if(noSoal == 0) {
                    System.out.println("Terima Kasih");
                    break;
                }

                switch (noSoal) {
                    case 1:
                        System.out.print("panjang array : ");
                        // ArrayList<Integer> pembagi = new ArrayList<>();
                        int n = input.nextInt();
                        ArrayList<Integer> hasilPotong = new ArrayList<>();
                        for (int i = 0; i < n; i++) {
                            hasilPotong.add(input.nextInt());
                        }
                        potongKayu(hasilPotong);
                        break;
                    case 2:
                        System.out.print("Masukkan jumlah poin : ");
                        int poin = input.nextInt();
                        tebakAngka(poin);
                        break;
                    default:
                        System.out.println("Nomor tidak ada!");
                        break;

                }
            }
            input.close();
        }
        // no 1 --------------------------------------------------------------------
        public static void potongKayu(ArrayList<Integer> hasilPotongan){
            System.out.print(hasilPotongan.size() + " ");
            int pengurang = hasilPotongan.get(0);
            for (int i = 0; i < hasilPotongan.size(); i++) {
                if (pengurang > hasilPotongan.get(i)) {
                    pengurang = hasilPotongan.get(i);
                }
            }
            for (int i = hasilPotongan.size()-1;i >= 0; i--) {
                if (hasilPotongan.get(i)-pengurang > 0) {
                    hasilPotongan.set(i, hasilPotongan.get(i)-pengurang);
                }else{
                    hasilPotongan.remove(i);
                }
            }
            if(hasilPotongan.size() > 0) potongKayu(hasilPotongan);
        }
        // no 2 --------------------------------------------------------------------
        public static void tebakAngka(int poin){
            Scanner scNew = new Scanner(System.in);     
            System.out.print("Taruhan = ");
            int taruhan = scNew.nextInt();
            scNew.nextLine();
            System.out.println("Tebak(U/D) : ");
            String tebakan = scNew.nextLine();
            tebakan = tebakan.toUpperCase();
            int angkaRandom;
            do {
                angkaRandom = (int) (Math.random()*10);
            } while (angkaRandom == 5);
            System.out.println(angkaRandom);
            boolean hasilTebakan = true;
            
            if (tebakan == "U" && angkaRandom > 5) hasilTebakan = true;
            if (tebakan == "U" && angkaRandom < 5) hasilTebakan = false;
            if (tebakan == "D" && angkaRandom > 5) hasilTebakan = false;
            if (tebakan == "D" && angkaRandom < 5) hasilTebakan = true;
            
            System.out.println(angkaRandom);
            if (hasilTebakan) {
                System.out.println("\nYou Win");
                poin += taruhan;
                System.out.println("poin saat ini : " + poin);
            }else{
                System.out.println("\nYou lose\n");
                poin -= taruhan;
                System.out.println("poin saat ini : " + poin);
            }
            if(poin == 0) System.out.println("Rungkad");
            else tebakAngka(poin);
            scNew.close();
        }
    }
