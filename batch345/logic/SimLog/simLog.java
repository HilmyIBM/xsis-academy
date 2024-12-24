package SimLog;
import java.sql.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class simLog {

    public static void main(String[] args) {
        // soal1();
        // soal2();
        // soal3();
        soal4();
        // soal5();
        // soal6();

    }

    public static void soal1(){
        Scanner s = new Scanner(System.in);

        System.out.print("Masukan kalimat ex:(AkuDanKamu): ");
        String sentences = s.nextLine();

        String[] words = sentences.split("(?=[A-Z])");
        
        // for (String word : words){
        //     System.out.println(word);
        // }
        System.out.println(words.length);
        s.close();
    }
    
    public static void soal2(){
        Scanner s = new Scanner(System.in);

        System.out.print("Start = ");
        int start = s.nextInt();
        System.out.print("End = ");
        int end = s.nextInt();

        DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate date = LocalDate.now();

        // int repeat = 0;

        for(int i = start; i <= end; i++){

            // if (i == 0) repeat = 5;
            // if (i < 10) repeat = 4;
            // if (i >= 10) repeat = 3;
            // if (i >= 100) repeat = 2;
            // if (i >= 1000) repeat = 1;
            // if (i >= 10000) repeat = 0;

            int repeat = Math.max(0, 5 - String.valueOf(i).length());

            System.out.println("XA-" + date.format(formatdate) + "-" + "0".repeat(repeat) + i);
        }

        s.close();
    }

    public static void soal3(){
        Scanner s = new Scanner(System.in);

        System.out.print("Masukan jumlah keranjang: ");
        int jumlahKeranjang = s.nextInt();
        s.nextLine();

        ArrayList<Integer> fruit = new ArrayList<>();
        for(int i = 0; i < jumlahKeranjang; i++){
            if(i == 0){
                System.out.println("== Isi Keranjang ==");
            }
            System.out.print("Keranjang " + (i + 1) + " = ");
            String manyFruitString = s.nextLine();
            try {
                int manyFruit = Integer.parseInt(manyFruitString);
                fruit.add(manyFruit);
            } catch (Exception e) {
                fruit.add(0);
            }
        }

        System.out.print("Keranjang dibawa ke pasar = ");
        int getOut = s.nextInt();

        fruit.remove(getOut-1);

        int sisaBuah = 0;
        for (int i : fruit){
            sisaBuah += i;
        }

        System.out.println("Sisa Buah = " + sisaBuah);

        s.close();
    }

    public static void soal4(){
        Scanner s = new Scanner(System.in);
        char repeat;

        System.out.println("Menu: ");
        System.out.println("1. Laki Dewasa ");
        System.out.println("2. Wanita Dewasa ");
        System.out.println("3. Anak - anak ");
        System.out.println("4. Bayi ");

        String[] typeOfPeople = {"Laki Dewasa", "Wanita Dewasa", "Anak - anak", "Bayi"};

        int adultMan = 0;
        int adultWoman = 0;
        int child = 0;
        int baby = 0;
        int totalBaju = 0;

        do {
            System.out.print("Input baju untuk: ");
            int menu = s.nextInt();
            System.out.print("Jumlah baju untuk " + typeOfPeople[menu-1] + ": ");
            int banyakBaju = s.nextInt();
            s.nextLine();

            switch (menu) {
                case 1:
                    adultMan += banyakBaju;
                    break;
                case 2:
                    adultWoman += (banyakBaju*2);
                    break;
                case 3:
                    child += (banyakBaju*3);
                    break;
                case 4:
                    baby += (banyakBaju*5);
                    break;
                default:
                    break;
            }

            totalBaju = adultMan + adultWoman + child + baby;

            if (totalBaju % 2 !=0 && totalBaju > 10){
                totalBaju += (adultWoman/2);
            }

            System.out.print("Ingin input lagi (y/n)? ");
            repeat = s.nextLine().toLowerCase().charAt(0);

        } while (repeat == 'y');

        System.out.println("Total baju = " + totalBaju);


        s.close();
    }

    public static void soal5(){
        Scanner s = new Scanner(System.in);

        System.out.print("Masukan nilai (pisahkan dengan koma): ");
        String gradeInput = s.nextLine();

        String[] gradeInputSplit = gradeInput.split(",");

        ArrayList<Integer> grade = new ArrayList<>();
        for (int i = 0; i < gradeInputSplit.length; i++) {
            grade.add(Integer.parseInt(gradeInputSplit[i]));
        }

        for (int i = 0; i < grade.size(); i++){
            if (grade.get(i) > 35){
                int rounded = grade.get(i) + (5 - (grade.get(i) % 5));
                if (rounded - grade.get(i) < 3) {
                    grade.set(i, rounded);
                }
            }

            System.out.println(grade.get(i));
        }

        s.close();
    }

    public static void soal6(){
        Scanner s = new Scanner(System.in);

        System.out.print("Input kalimat: ");
        String str = s.nextLine();

        boolean[] alphaList = new boolean[26];

        int index = 0;
        int flag = 1;
        for (int i = 0; i < str.length(); i++) 
        {
            if ( str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') 
            {
                index = str.charAt(i) - 'A';
            } 
            else if( str.charAt(i) >= 'a' &&  str.charAt(i) <= 'z') 
            {
                index = str.charAt(i) - 'a';
            }
            alphaList[index] = true;
        }

        for (int i = 0; i <= 25; i++) 
        {
            if (alphaList[i] == false)
                flag = 0;
        }
        // System.out.print("String: " + str);
        if (flag == 1)
            System.out.print("Kalimat tersebut adalah pangram");
        else
            System.out.print("Kalimat tersebut bukan pangram");

        s.close();
    }

    public static void soal7(){
        Scanner s = new Scanner(System.in);

        s.close();
    }
}
