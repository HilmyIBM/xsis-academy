package FT1;

import java.util.Scanner;

public record Soal10() {
    public static void main(String[] args) {
        no10();
    }

    public static void no10(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Input kalimat: ");
        String input = scan.nextLine().toLowerCase();
        String inputArr[] = input.split("");
        int count = 0;

        //ceknya pair 2
        // for (int i = 0; i < inputArr.length-1; i++){
        //         if (inputArr[i].matches("[^aiueo]") && inputArr[i+1].matches("[aiueo]") && !inputArr[i].equals(" ")){
        //             System.out.println(inputArr[i]  + inputArr[i+1]);
        //             count++;
        //         }

                
        // }

        //ceknya pair 3
        for (int i = 0; i < inputArr.length-2; i++){
            if (inputArr[i].matches("[^aiueo]") && inputArr[i+1].matches("[aiueo]") && !inputArr[i].equals(" ") && !inputArr[i+2].equals(" ") && inputArr[i+2].matches("[^aiueo]")){
                //System.out.println(inputArr[i]  + inputArr[i+1]);
                count++;
            }
    }

        System.out.println(count);

    }
}
