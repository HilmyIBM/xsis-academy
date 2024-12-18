import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) {
        /* float jari = 28.0F;
        
        System.out.println("Keliling lingkaran dengan jari-jari " + jari + ": " + kelilingLingkaran(jari));

        forLoop();

        whileLoop();*/
        arrayAndDataStructure();
        // stringOperation();
    }

    public static float kelilingLingkaran (float jari) {
        // System.out.println(22/7.0);
        return (2 * (22/7.0F) * jari);
    }

    public static void forLoop() {
        /* for (int i=10, j=20; ;);
         for (System.out.println("Hello");;);
         for (int num=1;;num++) {
           if (num == 10) break;
           if (num%2 == 0) continue;
           System.out.println(num);
         } */
         
         int[] numList = {10, 20, 30, 40, 50};
         for (int i=0; i < numList.length; i++) {
            System.out.println(numList[i]);
         }

         int[] numList2 = {100, 200, 300, 400, 500};
         for (int i : numList2) {
            System.out.println(i);
         }
    }

    public static void whileLoop() {
        int i;

        Scanner input = new Scanner(System.in);

        System.out.print("Input i: ");
        i = input.nextInt();

        while (i <= 10){
            System.out.println(++i);
            if (i == 5) i = 11;
        }

        System.err.println();

        System.out.print("Next i: ");
        i = input.nextInt();

        while (i <= 10){
            System.out.println(i++);
            if (i == 5) break;
        }

        input.close();
    }

    public static void arrayAndDataStructure() {
        // int[] empId = new int[5];

        // Object obj = empId; 

        Integer[] empId = {2, 4, 3, 5, 1, };
        
        //Ascending Sort Array
        Arrays.sort(empId);

        //Reversed Sort
        Collections.reverse(Arrays.asList(empId));

        System.out.println(Arrays.asList(empId));

        System.out.println("Jumlah empId: " + empId.length);

        /* ArrayList<String> cars = new ArrayList<String>();
        System.out.println(cars.size());

        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");

        System.out.println(cars.size());

        for (int i = 0; i <cars.size(); i++) {
            System.out.println("Cars " + (i+1) + ": " + cars.get(i));
        }

        List<String> cars = new ArrayList<>();
        System.out.println("Jumlah cars: " + cars.size());

        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");

        System.out.println("Jumlah cars: " + cars.size());

        for (int i = 0; i <cars.size(); i++) {
            System.out.println("Cars " + (i+1) + ": " + cars.get(i));
        }

        Collections.sort(cars);
        for (int i = 0; i <cars.size(); i++) {
            System.out.println("Cars " + (i+1) + ": " + cars.get(i));
        }

        System.out.println(cars);

        cars.add(1, cars.get(cars.size()-1));
        cars.remove(cars.size()-1);
        
        System.out.println(cars); */

    }

    public static void stringOperation() {
        /* String kosong = new String();
        String baru = "Ini string baru";

        System.out.println("kosong: " + kosong);
        System.out.println("baru: " + baru);

        System.out.println("Panjang string baru: " + baru.length()); */

        String str;

        str = new String("Just a string");
        
        str = new String("Another string");
        char c1 = 'a', c2 = 'x';

        System.out.println((int)c1);
        System.out.println((int)c2 - (int)c1);
    }
}
