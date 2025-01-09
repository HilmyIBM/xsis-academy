package Test;

import java.util.Scanner;

public class Soal8 {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] array = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 System.out.print("masukkan Angka : ");
                array[i][j] = input.nextInt();
            }
        }
        input.nextLine();
        String pola = input.nextLine();
        String action = input.nextLine();
        soal8(array, pola, action);
        input.close();
    }

    public static void soal8(int[][] array, String pola, String action){
        int result = 0;
        // System.out.println(array[1][1]);
        switch (pola) {
            case "x":
                int j = 2;
                if (action.equals("+")) {
                    for (int i = 0; i < 3; i++) {
                        result += array[i][i];
                        result += array[i][j];
                        j--;
                    }
                    System.out.println(result);
                }else if(action.equals("-")){
                    result = array[0][0];
                    for (int i = 0; i < 3; i++) {
                        if (i>0) {
                            result -= array[i][i];
                            result -= array[i][j];
                        }
                        j--;
                    }
                    System.out.println(result);

                }else if(action.equals("x")){
                    result = array[0][0];
                    for (int i = 0; i < 3; i++) {
                        result *= array[i][i];
                        result *= array[i][j];
                        j--;
                    }
                    System.out.println(result);

                }
                break;
            case "+":
                int x= 1;
                if (action.equals("+")) {
                    for (int i = 0; i < 3; i++) {
                        result += array[i][x];
                        result += array[x][i];
                        
                    }
                    System.out.println(result);
                }else if(action.equals("-")){
                    result = array[0][0];
                    for (int i = 0; i < 3; i++) {
                        result -= array[i][x];
                        result -= array[x][i];
                
                    }
                    System.out.println(result);

                }else if(action.equals("x")){
                    result = array[0][0];
                    for (int i = 0; i < 3; i++) {
                        result *= array[i][x];
                        result *= array[x][i];
                     
                    }
                    System.out.println(result);

                }
                
                break;
                case "-":
                    int a = 1;
                    if (action.equals("+")) {
                        for (int i = 0; i < 3; i++) {
                            
                            result += array[a][i];
                            
                        }
                        System.out.println(result);
                    }else if(action.equals("-")){
                        result = array[a][0];
                        for (int i = 0; i < 3; i++) {
                            // System.out.println(result);
                            if (i>0) {
                                result = result - array[a][i];
                            }
                    
                        }
                    System.out.println(result);

                    }else if(action.equals("x")){
                        result = array[0][0];
                        for (int i = 0; i < 3; i++) {
                            result *= array[a][i];
                        
                        }
                    System.out.println(result);
                    }
                break;
        
            default:
                break;
        }
        // System.out.println(result);
        
    }
    
}
